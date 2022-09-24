package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private springfox.documentation.service.ApiKey apiKey() {
		return new springfox.documentation.service.ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContext() {
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReference()).build());
	}

	private List<SecurityReference> securityReference() {
		AuthorizationScope scope = new AuthorizationScope("global", "Access everything");
		return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[] { scope }));
	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(PathSelectors.any()).build();

	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Social Media API").description("\"Swagger configuration for application \"")
				.version("V1.0").license("V.JET").build();
	}

}