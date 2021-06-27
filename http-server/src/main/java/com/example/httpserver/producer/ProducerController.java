package com.example.httpserver.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/prod")
public class ProducerController {

    @GetMapping("/process")
    public ResponseEntity<String> process(){
    	return ResponseEntity.status(200).body("Successful process response");
    }
}
