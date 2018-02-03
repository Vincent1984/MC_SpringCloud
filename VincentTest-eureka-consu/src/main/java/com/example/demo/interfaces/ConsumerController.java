package com.example.demo.interfaces;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "content", description = "the content API")
public class ConsumerController {
	
	@Autowired
	private HystrixService ribbonHystrixService;
	
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
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@QueryParam("a") int a, @QueryParam("b") int b) {
		return this.ribbonHystrixService.add(a, b);
	}
}
