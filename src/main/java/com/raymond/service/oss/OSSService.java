package com.raymond.service.oss;

import java.io.InputStream;
import java.util.List;

public interface OSSService {
    public void upload(String objectName, InputStream inputStream,long size);
    public InputStream download(String objectName);
    public void delete(String objectName);
    public List<String> list(String prefix);
    public String generateUrl(String objectName, int minutes);
}
