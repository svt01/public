package com.example.httpclient.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cons")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${producer.path}")
    private String producer_path;

    @GetMapping("/init")
    public ResponseEntity<String> initiateCall(){

    	//Call producer from here http://localhost:9091/prod/process
        ResponseEntity<String> response = restTemplate.getForEntity(producer_path, String.class);


        return ResponseEntity.status(200).body("Successful process response");
    }
}
