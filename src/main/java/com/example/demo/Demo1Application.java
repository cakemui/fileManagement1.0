package com.example.demo;

import com.example.demo.controller.filesystem.FileManagementController;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@MapperScan("mapper")
public class Demo1Application {

    private static final Logger logger = LoggerFactory.getLogger(Demo1Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
        logger.info("启动成功!");
    }

}
