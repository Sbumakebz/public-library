package com.public_library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Public Library - Books API")
                        .description("API calls for public library backend")
                        .version("v1.0"));
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            Optional<PreAuthorize> preAuthorizeAnnotation = Optional.ofNullable(handlerMethod.getMethodAnnotation(PreAuthorize.class));
            StringBuilder sb = new StringBuilder();
            if (preAuthorizeAnnotation.isPresent()) {
                sb.append("This api requires **")
                        .append("** permission.");
            } else {
                sb.append("This api is **public**");
            }
            sb.append("<br /><br />");
            sb.append(operation.getDescription());
            operation.setDescription(sb.toString());
            return operation;
        };
    }
}
