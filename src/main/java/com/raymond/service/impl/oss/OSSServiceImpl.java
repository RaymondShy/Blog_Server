package com.raymond.service.impl.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.raymond.properties.OSSProperties;
import com.raymond.service.oss.OSSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class OSSServiceImpl implements OSSService {

    private final OSS ossClient;
    private final OSSProperties ossProperties;

    /**
     * 初始化bucket
     */
    @PostConstruct
    public void init() {
        String bucketName = ossProperties.getBucketName();
        if (!ossClient.doesBucketExist(bucketName)){
            CreateBucketRequest request = new CreateBucketRequest(bucketName);
            request.setCannedACL(CannedAccessControlList.Private);
            ossClient.createBucket(request);
            log.info("创建bucket成功{}",bucketName);
        }else{
            log.info("bucket以存在{}",bucketName);
        }
    }


    @Override
    public void upload(String objectName, InputStream inputStream, long size) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata(); // 元数据
            objectMetadata.setContentLength(size);
            objectMetadata.setContentType(getContentType(objectName));
            ossClient.putObject(ossProperties.getBucketName(), objectName, inputStream, objectMetadata);
            log.info("文件上传成功 {} ", objectName);
        }catch (Exception e){
            log.error("上传失败",e);
            throw new RuntimeException("上传失败",e);
        }
    }

    @Override
    public InputStream download(String objectName) {
        OSSObject ossObject = ossClient.getObject(ossProperties.getBucketName(), objectName);
        return ossObject.getObjectContent();
    }

    @Override
    public void delete(String objectName) {
        ossClient.deleteObject(ossProperties.getBucketName(), objectName);
    }

    @Override
    public List<String> list(String prefix) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossProperties.getBucketName()).withPrefix(prefix);
        ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
        return objectListing.getObjectSummaries().stream()
                .map(OSSObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public String generateUrl(String objectName, int minutes) {
        Date expiration = new Date(System.currentTimeMillis() + minutes * 60 * 1000);
        URL url = ossClient.generatePresignedUrl(ossProperties.getBucketName(), objectName, expiration);
        return url.toString();
    }

    /**
     * 自动识别文件ContentType，避免下载文件打不开
     */
    private String getContentType(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (ext) {
            case "bmp": return "image/bmp";
            case "gif": return "image/gif";
            case "jpeg": case "jpg": case "png": return "image/jpeg";
            case "html": return "text/html";
            case "txt": return "text/plain";
            case "vsd": return "application/vnd.visio";
            case "ppt": case "pptx": return "application/vnd.ms-powerpoint";
            case "doc": case "docx": return "application/msword";
            case "xml": return "text/xml";
            case "pdf": return "application/pdf";
            default: return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}
