package com.eureka.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class HomeController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String home(){
        return "FCat User";
    }
}