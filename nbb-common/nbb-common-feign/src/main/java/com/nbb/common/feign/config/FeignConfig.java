package com.nbb.common.feign.config;

import cn.dev33.satoken.stp.StpUtil;
import com.nbb.common.feign.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.nbb")
@ComponentScan(basePackages = {"com.nbb.**.api"}) // feign client fallback类扫描路径
public class FeignConfig {


    @Bean
    @ConditionalOnClass(StpUtil.class)
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
