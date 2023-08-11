package com.fithub.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.fithub.app.repositoris",
        "com.fithub.app.services",
        "com.fithub.app.controllers" // Agrega otros paquetes si es necesario
})
public class FitHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitHubApplication.class, args);
    }

}
