package com.eureka.client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserApplication.class,args);
        Thread.sleep(100000000L);
    }
}