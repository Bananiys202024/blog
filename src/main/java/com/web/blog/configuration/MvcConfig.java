package com.web.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/editOwnCV").setViewName("CV");
        registry.addViewController("/").setViewName("Jokes");
        registry.addViewController("/diary").setViewName("Diary");
        registry.addViewController("/login").setViewName("login");
    }

}
