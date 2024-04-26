package com.samah.userservice.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${myapp.openapi.dev-url}")
    private String devUrl;

    @Value("${myapp.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URL Production environment");

        Contact contact = new Contact();
        contact.setEmail("samah.mahdi.hassan@gmail.com");
        contact.setName("Samah");
        contact.setUrl("https://www.linkedin.com/in/samah-alwahbani/");

        License mitLicense = new License().name("Open-source License") ;//.url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Bookstore - User Service APIs")
                .version("1.0")
                .contact(contact)
                .description("These APIs exposes endpoints to manage user-service as part of Book-store microservice project.") //.termsOfService("https://www.bezkoder.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
