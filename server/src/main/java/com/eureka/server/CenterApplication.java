package com.eureka.server;
import demo.Demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CenterApplication {

    public static void main(String[] args) {
        Demo d= new Demo();
        SpringApplication.run(CenterApplication.class,args);
    }
}