package com.example.digitalimage;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableScheduling
@SpringBootApplication
public class DigitalImageApplication{

    public static void main(String[] args) {
        SpringApplication.run(DigitalImageApplication.class, args);
    }


}
