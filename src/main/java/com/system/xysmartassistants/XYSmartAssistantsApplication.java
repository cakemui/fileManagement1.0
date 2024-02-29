package com.system.xysmartassistants;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@MapperScan("com.system.xysmartassistants.dao")
@ComponentScan(basePackages = {"com.system.xysmartassistants.*"})
public class XYSmartAssistantsApplication {

    private static final Logger logger = LoggerFactory.getLogger(XYSmartAssistantsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(XYSmartAssistantsApplication.class, args);
        logger.info("启动成功!");
    }

}
