package com.apigateway.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/service-one")
public class ServiceOneController {

    private Logger logger = LoggerFactory.getLogger(ServiceOneController.class);

    @GetMapping("/list")
    public List<ServieOneObject> getServiceOneObjectList(@RequestHeader HttpHeaders headers) {
        logger.info("headers ::: {}", headers);
        return Arrays.asList(
                new ServieOneObject(1, "one"),
                new ServieOneObject(2, "two"),
                new ServieOneObject(3, "three")

        );

    }
}
