package com.example.demo.interfaces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testConfigClient {
	
	@Value("${foo}")
    String foo;
    @RequestMapping(value = "/test")
    public String hi(){
        return foo;
    }
}
