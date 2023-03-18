package one.digitalinnovation.parking.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact contato() {
        return new Contact(
                "Seu nome",
                "http://www.seusite.com.br",
                "voce@seusite.com.br");
    }

    @Bean
    public Docket getDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.parking"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .securityContexts(Arrays.asList(getSecurityContext()))
                .securitySchemes(Arrays.asList(basicAuthScheme()));
    }

    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .build();
    }
    private SecurityScheme basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Parking REST API")
                .description("Spring Boot REST API for Parking")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }

}
