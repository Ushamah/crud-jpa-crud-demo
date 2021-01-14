package com.ushwamala.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class CrudSpringRestJPA_App {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringRestJPA_App.class, args);
    }

    @Bean
    public Docket swaggercongiguration() {

    	//https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.ushwamala.springboot.controller"))
                .build().apiInfo(apiDetailsApiInfo());

        return docket;
    }

    private ApiInfo apiDetailsApiInfo() {

        ApiInfo apiInfo = new ApiInfo("Employee API",
                "Sample API using Swagger UI",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ushamah Wamala", "https://github.com/Ushamah", "ushwamala@gmail.com"),
                "API License",
                "https://github.com/Ushamah",
                Collections.emptyList());

        return apiInfo;
    }

}
