package com.tmasuda.fc;

import com.tmasuda.fc.filter.FcCorsFilter;
import com.tmasuda.fc.filter.FcJwtFilter;
import com.tmasuda.fc.prop.JsonWebTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class FinanceCenterApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Autowired
    private JsonWebTokenProperties jsonWebTokenProperties;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FinanceCenterApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FinanceCenterApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new FcCorsFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new FcJwtFilter(jsonWebTokenProperties));
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

}
