package com.nbb.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 系统模块
 */
@Slf4j
@MapperScan("com.nbb.**.mapper")
@SpringBootApplication
public class NbbSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NbbSystemApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "/");
        String accessUrl = "http://localhost:" + port + path;
        log.info("\n--------------------------------------------------------------------\n" +
                "\t系统模块启动成功，访问链接为：" + accessUrl + "\n" +
                "--------------------------------------------------------------------");
    }
}
