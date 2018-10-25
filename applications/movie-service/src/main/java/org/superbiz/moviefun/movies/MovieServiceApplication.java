package org.superbiz.moviefun.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class MovieServiceApplication {

    public static void main(String... args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

}
