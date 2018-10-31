package com.controller;

import demo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
@RestController
public class MyRestController {
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        System.out.println("aaaaaaa");
        return builder.build();
    }

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public String findById() {
        return restTemplate.getForObject("http://eureka-server/hello1/hello",String.class);
//        return restTemplate.getForObject("http://hello-service/hello1",String.class);

    }

    @PostConstruct
    public void init(){
//        System.out.println(findById());
    }

    @RequestMapping("/hello")
    public String hello() {
        return findById();
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public Person findPerson(@PathVariable("personId") Integer personId) {
        Person p = new Person();
//        p.setId(personId);
//        p.setName("Crazyit");
//        p.setAge(30);
        return p;
    }
}
