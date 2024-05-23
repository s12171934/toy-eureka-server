package com.solo.toyeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ToyEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyEurekaServerApplication.class, args);
    }

}
