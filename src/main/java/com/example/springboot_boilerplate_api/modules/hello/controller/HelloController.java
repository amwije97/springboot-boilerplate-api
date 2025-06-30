package com.example.springboot_boilerplate_api.modules.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_boilerplate_api.common.constants.AppConstants;
import com.example.springboot_boilerplate_api.common.dto.ApiResponse;
import com.example.springboot_boilerplate_api.modules.hello.service.HelloService;

/**
 * Hello World controller demonstrating the boilerplate structure. Replace this with your actual
 * business controllers.
 */
@RestController
@RequestMapping(AppConstants.API_V1 + "/hello")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /** Personalized greeting with path variable. Example: GET /api/v1/hello/John */
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<String>> sayHelloToName(@PathVariable String name) {
        logger.info("Personalized hello endpoint called for: {}", name);

        final String message = helloService.getPersonalizedGreeting(name);
        final ApiResponse<String> response = ApiResponse.success(message);

        return ResponseEntity.ok(response);
    }
}
