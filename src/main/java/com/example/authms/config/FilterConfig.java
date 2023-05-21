package com.example.authms.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new JwtFilter());

        // Exclude the register endpoint from authentication
        filter.addUrlPatterns("/api/v1/endpoint/restricted");
        return filter;
    }
}
