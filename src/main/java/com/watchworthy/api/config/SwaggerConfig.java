package com.watchworthy.api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("watchworthy@proton.me");
        contact.setName("WatchWorthy");
        contact.setUrl("https://www.github.com/watchworthy");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("WatchWorthy API")
                .version("1.0")
                .contact(contact)
                .description("Watch Worthy API endpoints.").termsOfService("https://www.github.com/watchworthy")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
