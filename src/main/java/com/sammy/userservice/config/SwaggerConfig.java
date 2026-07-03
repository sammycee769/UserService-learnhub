package com.sammy.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LearnHub User Service API")
                        .description("Handles user profile management")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sammy")
                                .email("sammy@learnhub.com")));
    }
}