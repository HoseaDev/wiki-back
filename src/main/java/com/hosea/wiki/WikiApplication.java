package com.hosea.wiki;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
//加下这个可以省去每个Mapper类上面加上@Mapper 注解
@MapperScan("com.hosea.wiki.dao.mapper")
public class WikiApplication {
    private static final Logger logger = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(WikiApplication.class, args);
        Environment ev = app.getEnvironment();
        logger.info("启动成功");
        logger.info("地址:\thttp://127.0.0.1:{}", ev.getProperty("server.port"));
    }

}
