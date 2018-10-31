package com.eureka.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/hello1")
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HomeController {

    @PostConstruct
    public void init(){
        System.out.println(1);
    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String home(){
        return "FCat User";
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home1(){
        return "Fasdfr";
    }
}