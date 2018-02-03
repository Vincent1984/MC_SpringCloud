package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.RibbonTestConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableHystrix
@EnableHystrixDashboard
@ComponentScan(basePackages = "com.example.demo.interfaces")
//@RibbonClient(name="microservice-provider-user",configuration=RibbonTestConfiguration.class)
public class VincentTestEurekaConsuApplication {
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
//	@Bean
//    public IRule ribbonRule() {
//        return new RandomRule();//这里配置策略，和配置文件对应
//    }

	public static void main(String[] args) {
		SpringApplication.run(VincentTestEurekaConsuApplication.class, args);
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
