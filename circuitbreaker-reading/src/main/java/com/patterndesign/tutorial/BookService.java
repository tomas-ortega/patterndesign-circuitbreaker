package com.patterndesign.tutorial;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BookService {
  private final RestTemplate restTemplate;

  public BookService(RestTemplate rest) {
    this.restTemplate = rest;
  }

  /*El método lleva una anotación para indicar el método alternativo
  a invocar en caso de detección de fallo*/
  @HystrixCommand(fallbackMethod = "reliable")
  public String readingList() {
    URI uri = URI.create("http://localhost:8090/recommended");

    return this.restTemplate.getForObject(uri, String.class);
  }

  /*Método alternativo*/
  public String reliable() {
    return "Main Endpoint Down... Alternative Message!";
  }
}
