package com.raymond.controller.oss;

import com.raymond.service.oss.OSSService;
import com.raymond.utils.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping("/api/oss")
@RequiredArgsConstructor
public class OSSController {

    private final OSSService ossService;

    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file) {
        try(InputStream in = file.getInputStream()){
            String objectName= "upload/"+file.getOriginalFilename();
            this.ossService.upload(objectName,
                    in,
                    file.getSize());
            return AjaxResult.success("文件上传成功",objectName);
        } catch (IOException e) {
            return AjaxResult.error("文件上传失败",e.getMessage());
        }
    }
}
