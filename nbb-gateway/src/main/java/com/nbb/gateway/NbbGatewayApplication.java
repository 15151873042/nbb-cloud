package com.nbb.gateway;

import com.nbb.gateway.config.NbbGatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 网关启动程序
 */
@Slf4j
@SpringBootApplication
@LoadBalancerClients(defaultConfiguration = NbbGatewayConfig.class)
public class NbbGatewayApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NbbGatewayApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "/");
        String accessUrl = "http://localhost:" + port + path;
        log.info("\n--------------------------------------------------------------------\n" +
                "\t认证授权中心启动成功，访问链接为：" + accessUrl  + "\n" +
                "--------------------------------------------------------------------");
    }
}
