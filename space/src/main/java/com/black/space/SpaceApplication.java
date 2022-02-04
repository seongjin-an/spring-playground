package com.black.space;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceApplication.class, args);
    }

}
