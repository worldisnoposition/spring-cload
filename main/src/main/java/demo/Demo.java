package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

//@EnableEurekaClient
@RibbonClient(name="eureka-server")
//@EnableEurekaServer
@Configuration
@ImportResource(locations={"classpath:applicationContext.xml"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
@EnableEurekaClient
public class Demo {
    @Autowired
    private RestTemplateBuilder builder;

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        System.out.println("aaaaaaa");
//        return new RestTemplate();
//    }
    public static void main(String[] args) {
        SpringApplication.run(Demo.class,args);
    }
}
