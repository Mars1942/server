package com.ut.business.user.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
        registry.addMapping("/user/**")
                .allowedOrigins("http://192.168.31.126")
                .allowedMethods("GET", "POST")
                .allowCredentials(false).maxAge(3600);
    }
}
