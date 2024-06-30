package com.nbb.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 认证授权中心
 * 
 */
@Slf4j
@SpringBootApplication
public class NbbAuthApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NbbAuthApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "/");
        String accessUrl = "http://localhost:" + port + path;
        log.info("\n--------------------------------------------------------------------\n" +
                "\t认证授权中心启动成功，访问链接为：" + accessUrl  + "\n" +
                "--------------------------------------------------------------------");
    }
}
