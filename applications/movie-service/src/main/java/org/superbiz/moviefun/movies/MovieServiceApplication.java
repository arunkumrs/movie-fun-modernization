package org.superbiz.moviefun.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.superbiz.moviefun.utils.ServiceCredentials;

@SpringBootApplication
public class MovieServiceApplication {

    public static void main(String... args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }


    @Bean
    ServiceCredentials serviceCredentials(@Value("${vcap.services}") String vcapServices) {
        return new ServiceCredentials(vcapServices);
    }
}
