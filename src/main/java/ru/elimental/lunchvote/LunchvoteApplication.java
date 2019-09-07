package ru.elimental.lunchvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LunchvoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunchvoteApplication.class, args);
    }

}
