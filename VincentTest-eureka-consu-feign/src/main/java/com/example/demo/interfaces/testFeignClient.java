package com.example.demo.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interfaces.testFeignClient.HystrixClientFallback;

@FeignClient(value = "compute-service",fallback = HystrixClientFallback.class)
public interface testFeignClient {
	 @RequestMapping(value = "/multiple",method = RequestMethod.GET)
	 public String multiple(@RequestParam("a") Integer a,@RequestParam("b") Integer b);
	 
	 @Component
	  static class HystrixClientFallback implements testFeignClient {
	    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

		@Override
		public String multiple(Integer a, Integer b) {
			HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数： {},{}",a,b);
			return "feign-hystrix";
		}
	  }
}
