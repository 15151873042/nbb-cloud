package com.nbb.common.feign.interceptor;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * feign 请求拦截器
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {


    private String tokenName;

    @Autowired
    public void setSaTokenConfig(SaTokenConfig saTokenConfig) {
        this.tokenName = saTokenConfig.getTokenName();
    }
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String tokenValue = StpUtil.getTokenValue();
        if (StrUtil.isNotBlank(tokenValue)) {
            // 传递用户token
            requestTemplate.header(tokenName, tokenValue);
        }
        // 传递客户端IP
        requestTemplate.header("X-Forwarded-For", NetUtil.getLocalhostStr());
    }
}