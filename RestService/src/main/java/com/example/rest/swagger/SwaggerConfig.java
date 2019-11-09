package com.example.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Permite usar este archivo como configuración en lugar de un XML
@Configuration
// Activa Swagger2
@EnableSwagger2
public class SwaggerConfig {
	// Marca los métodos para que estén disponibles para Spring
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				// cambiamos .any() por .basePackage("paquete que queramos documentar")
				.apis(RequestHandlerSelectors.basePackage("com.example.rest.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
