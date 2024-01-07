package com.developermy.crosscutting.configs;

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
		Server devServer = new Server();
		devServer.setUrl("/developerme");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setEmail("dev@gmail.com");
		contact.setName("Admin Console");
		contact.setUrl("https://admin-console.com");

		License mitLicense = new License().name("Copyright All Rights Reserved")
			.url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Console Admin API")
			.version("1.0")
			.contact(contact)
			.description("This API exposes endpoints to manage the admin console.")
			.termsOfService("https://admin-console.com/terms")
			.license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}

}