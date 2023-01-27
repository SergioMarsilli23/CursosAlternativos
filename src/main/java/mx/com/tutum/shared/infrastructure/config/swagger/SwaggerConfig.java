package mx.com.tutum.shared.infrastructure.config.swagger;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;

import java.time.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_TITLE = "Cursos Alternativos";
    private static final String API_DESCRIPTION = "Evaluación de TUTUM";
    private static final String API_VERSION = "1.0.0";
    private static final String API_CONTACT_NAME = "Sergio Marsilli";
    private static final String API_CONTACT_URL = "https://www.linkedin.com/in/sergio-marsilli/";
    private static final String API_CONTACT_EMAIL = "smm.dsh@gmail.com";

    @Bean
    public Docket docketFactory(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(OffsetDateTime.class, String.class)
                .directModelSubstitute(OffsetTime.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .contact(new Contact(API_CONTACT_NAME,
                        API_CONTACT_URL,
                        API_CONTACT_EMAIL))
                .build();
    }

    @Bean
    public UiConfiguration swaggerUiConfig() {
        return UiConfigurationBuilder.builder().build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
