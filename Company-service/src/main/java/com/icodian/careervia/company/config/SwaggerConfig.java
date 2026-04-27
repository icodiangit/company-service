package com.icodian.careervia.company.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI companyServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CareerVia Company Service API")
                        .version("1.0")
                        .description("API documentation for Company Service"));
    }
}

