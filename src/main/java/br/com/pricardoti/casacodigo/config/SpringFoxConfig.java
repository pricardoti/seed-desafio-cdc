package br.com.pricardoti.casacodigo.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Collections.emptyList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.pricardoti.casacodigo"))
                .paths(Predicates.not(PathSelectors.regex("/swagger-resources.*")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private final ApiInfo apiInfo() {
        return new ApiInfo(
                "Projeto REST API - Casa do Código",
                "Este projeto simula as APIs para uma loja de livros.",
                "1.0.0",
                "Terms of service",
                new Contact("Paulo Ricardo", null, "pricardo.ti@gmail.com"),
                "License of API", "API license URL", emptyList());
    }
}