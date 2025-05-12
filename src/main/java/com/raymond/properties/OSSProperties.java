package com.raymond.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OSS 对象存储配置类 自动注入
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
