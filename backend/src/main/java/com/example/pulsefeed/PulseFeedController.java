package com.example.pulsefeed;

import java.io.IOException;
import java.util.Arrays;

import com.example.pulsefeed.Titan;

import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PulseFeedController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final BedrockRuntimeClient client;

    @Autowired
    public PulseFeedController(final BedrockRuntimeClient client){
        this.client = client;
    }

    @GetMapping("/hello")
    public String greet(){
        return "Hello there!";
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTestData(){
       String result = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?category=sports&apiKey=550e7560011e4c059930201409d8f05a", String.class);
       return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<String> getNewsByCategory(@RequestParam("category") String category){
       String result = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?category="+category+"&apiKey=550e7560011e4c059930201409d8f05a", String.class);
       return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/scraptest")
    public ResponseEntity<String> getTextFromWebScrapTest() throws Exception{
        Document document = Jsoup.connect("https://abcnews.go.com/Business/trumps-tariffs-achieved-experts-weigh/story?id=123859218").get();
        Elements paragraphs = document.select("p");
        StringBuilder sb = new StringBuilder();
        for (Element p : paragraphs) {
            sb.append(p.text()).append("\n\n"); 
        }
        String cleanText = sb.toString();


        String prompt = "Very shortly summarize the following article in strictly only 4 sentences. Focus on the main points only. Make sure the summary ends cleanly.\n\n" + cleanText;

        // maxTokenCount controls how long the summary can be, but in tokens, not words. Rule of thumb: 100 tokens ≈ 75 words

        // The temperature setting controls the randomness or creativity of the model’s output. 0.0 - always gives same output, 0.5 - balanced, 1.0 - may go off topic
        
       return new ResponseEntity<String>(Titan.invoke(client, prompt, 0.2, 400), HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getSummary(@RequestParam("url") String url) throws Exception{
        System.out.println("url = "+url);
        Document document = Jsoup.connect(url).get();
        Elements paragraphs = document.select("p");
        StringBuilder sb = new StringBuilder();
        for (Element p : paragraphs) {
            sb.append(p.text()).append("\n\n"); 
        }
        String cleanText = sb.toString();

        String prompt = """
Summarize the following article in exactly **4 full sentences**, no more and no less. The summary should cover the most important points only and end clearly.

Article:
""" + cleanText;
    String response = Titan.invoke(client, prompt, 0.2, 600);
    String[] sentences = response.split("(?<=[.!?])\\s+");

    String summary = String.join(" ", Arrays.copyOfRange(sentences, 0, Math.min(5, sentences.length)));
    return new ResponseEntity<>(summary.trim(), HttpStatus.OK);
    }
    
}