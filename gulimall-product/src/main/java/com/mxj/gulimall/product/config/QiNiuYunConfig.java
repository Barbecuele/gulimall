package com.mxj.gulimall.product.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: QiNiuYunConfig
 * @Author mi xiu jin
 * @Package com.mxj.gulimall.product.config
 * @Date 2024/4/28 1:16
 * @description: 七牛云配置信息
 */
@Configuration
@Data
public class QiNiuYunConfig {
    /**
     * 七牛域名domain
     */
    @Value("${oss.url}")
    private String url;
    /**
     * 七牛ACCESS_KEY
     */
    @Value("${oss.accessKey}")
    private String accessKey;
    /**.accessKey
     * 七牛SECRET_KEY
     */
    @Value("${oss.secretKey}")
    private String secretKey;
    /**
     * 七牛空间名
     */
    @Value("${oss.bucketName}")
    private String bucketName;
}
