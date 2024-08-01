package com.demo.project73;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@Modulith
@EnableAsync
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner sendData() {
        return args -> {
            log.info("On Start");
        };
    }
}
