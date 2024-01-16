package com.dongmanee.global.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes("bearer-jwt", securityScheme()))
			.addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
			.info(apiInfo());
	}

	private SecurityScheme securityScheme(){
		return new SecurityScheme().type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
			.in(SecurityScheme.In.HEADER)
			.name("Authorization");
	}

	private Info apiInfo() {
		return new Info()
			.title("동만이")
			.description("동만이 프로젝트의 API 문서입니다.")
			.version("1.0.0");
	}
}