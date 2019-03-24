package com.apibatdongsan.batdongsandanang;

import com.apibatdongsan.batdongsandanang.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.apibatdongsan.batdongsandanang")
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class BatdongsandanangApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatdongsandanangApplication.class, args);
    }

}
