package com.example.demo.interfaces;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixService {
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(HystrixService.class);

	/**
	 * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
	 */
	@HystrixCommand(fallbackMethod = "fallback")
	public String add(@QueryParam("a") int a, @QueryParam("b") int b) {
//		 this.loadBalancerClient.choose("compute-service");//随机访问策略
		return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=" + a + "&b=" + b, String.class).getBody();
	}

	/**
	 * hystrix fallback方法
	 */
	public String fallback(@QueryParam("a") int a, @QueryParam("b") int b) {
		HystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", "qqq");
		return "出错了 a=" + a + " b=" + b;
	}
}
