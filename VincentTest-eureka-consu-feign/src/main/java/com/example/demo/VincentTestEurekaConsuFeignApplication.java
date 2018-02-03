package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableSwagger2
public class VincentTestEurekaConsuFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(VincentTestEurekaConsuFeignApplication.class, args);
	}
	
	 ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("素材API")
	            .description("定义素材暴露的API ")
	            .license("")
	            .licenseUrl("http://unlicense.org")
	            .termsOfServiceUrl("")
	            .version("0.1")
	            .contact(new Contact("","", ""))
	            .build();
	    }

	    @Bean
	    public Docket customImplementation(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                    .apis(RequestHandlerSelectors.basePackage("com.example.demo.interfaces"))
	                    .build()
	                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
	                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
	                .apiInfo(apiInfo());
	    }
}
