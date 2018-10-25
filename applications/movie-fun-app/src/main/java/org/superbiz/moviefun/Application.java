package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.superbiz.moviefun.moviesapi.MoviesServlet;
import org.superbiz.moviefun.cfsupport.ServiceCredentials;

@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean actionServletRegistration(MoviesServlet actionServlet) {
        return new ServletRegistrationBean(actionServlet, "/moviefun/*");
    }

}
