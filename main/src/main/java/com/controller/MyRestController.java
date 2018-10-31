package com.controller;

import demo.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RestTemplate restTemplate;

    public String findById() {
        return restTemplate.getForObject("http://localhost:1003/hello1/hello",String.class);
    }

    @PostConstruct
    public void init(){
        System.out.println(findById());
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
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
