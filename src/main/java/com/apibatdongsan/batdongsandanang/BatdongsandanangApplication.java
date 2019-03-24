package com.apibatdongsan.batdongsandanang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.apibatdongsan.batdongsandanang")
public class BatdongsandanangApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatdongsandanangApplication.class, args);
    }

}
