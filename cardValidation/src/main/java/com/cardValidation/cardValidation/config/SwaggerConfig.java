package com.cardValidation.cardValidation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Card-Validation  Endpoint")
                        .description("This API is used to validate the credit card and check that the card exists in the database, and not expired")
                        .version("1.0.0"));
    }
}