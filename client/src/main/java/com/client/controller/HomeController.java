package com.client.controller;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.net.URLDecoder;

@RestController
@RequestMapping(value = "/hello")
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HomeController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String home(@RequestParam("name") String name) {
        System.out.println(name);
        System.out.println(URLDecoder.decode(name));
        return "FCat User";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home1() {
        return "Fasdfr";
    }

    TransportClient client = null;

    @PostConstruct
    public void init() {

    }

    @RequestMapping(value = "/es", method = RequestMethod.GET)
    public String es() {
//        client.
        return null;
    }
}