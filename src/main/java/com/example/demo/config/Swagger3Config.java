package com.example.demo.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * 小月
 * 2023/9/4/14点10分
 *
 * swagger配置
 */
@Configuration
@EnableOpenApi
@Component
@Slf4j
public class Swagger3Config implements ApplicationListener<WebServerInitializedEvent> {

    //可以配置在application文件中
    /**
     * 与Spring 同一级的配置
     *swagger:
     *   enable: true
     *   application-name: ${spring.application.name}
     *   application-version: 1.0
     *   application-description: springfox swagger 3.0整合Demo
     *   try-host: http://localhost:${server.port}
     */
    Boolean swaggerEnabled=true;//ture 启用Swagger3.0 fasle 禁用（生产环境要禁用）
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径使用@Api的controller
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("适用于前后端分离统一的接口文档")
                //作者信息
                //.contact(new Contact("name","url", "email"))
                .contact(new Contact("小月", "www.biying.cn", "lengxiaoyue7@gamil.com"))
                .version("1.0")
                .build();
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            //获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            //获取端口号
            int port = event.getWebServer().getPort();
            //获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            log.info("项目启动启动成功！接口文档地址: http://"+hostAddress+":"+event.getWebServer().getPort()+applicationName+"/swagger-ui/");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}