package com.bosonit.block7crudvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Block13TestingCrud {

    public static void main(String[] args) {
        SpringApplication.run(Block13TestingCrud.class, args);
    }

}
