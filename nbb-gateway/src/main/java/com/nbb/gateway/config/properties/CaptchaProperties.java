package com.nbb.gateway.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 验证码配置
 *
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.captcha")
public class CaptchaProperties {
    /**
     * 验证码开关
     */
    private Boolean enabled;

    /**
     * 需要校验验证码的url列表
     */
    private Set<String> validateUrls;

    /**
     * 验证码类型（math 数组计算 char 字符）
     */
    private String type;
}
