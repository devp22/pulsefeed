package com.example.pulsefeed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PulseFeedController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hello")
    public String greet(){
        return "Hello there!";
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTestData(){
       String result = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?category=sports&apiKey=550e7560011e4c059930201409d8f05a", String.class);
       return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    
}