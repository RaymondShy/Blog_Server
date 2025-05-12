package com.raymond.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.raymond.properties.OSSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss_config: 线程安全、自动管理
 */
@Configuration
@RequiredArgsConstructor
public class OSSConfig {
    private final OSSProperties ossProperties;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder()
                .build(ossProperties.getEndpoint(),
                        ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret());
    }
}
