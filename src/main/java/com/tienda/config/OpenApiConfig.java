package com.tienda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tiendaOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("API Tienda")
                                .description(
                                        "Microservicio para gestión de productos y ventas")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Wilver")
                                                .email("wilver@localhost")));
    }
}