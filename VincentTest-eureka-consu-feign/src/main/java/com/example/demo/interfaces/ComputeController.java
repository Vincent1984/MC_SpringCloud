package com.example.demo.interfaces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value = "content", description = "the content API")
public class ComputeController {
	@Autowired
	  private testFeignClient testFeignClient;
	@ApiOperation(httpMethod = "GET", value = "Create add function")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful operation"),
            @ApiResponse(code = 400, message = "Request body failed validation"),
            @ApiResponse(code = 404, message = "Resource type not supported"),
            @ApiResponse(code = 422, message = "The proposed resource violated applicable FHIR profiles or server business rules.")
    })
		@ApiImplicitParams({
		    @ApiImplicitParam(paramType="query", name = "a", value = "a", required = true, dataType = "Integer"),
		    @ApiImplicitParam(paramType="query", name = "b", value = "b", required = true, dataType = "Integer")
		})
	  @RequestMapping(value = "/multiple" , method = RequestMethod.GET)
	  public String add(@RequestParam("a") Integer a,@RequestParam("b") Integer b) {
	    String string = this.testFeignClient.multiple(a,b);
	    return string;
	  }
}
