package com.westlakefinancial.technology.config;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.lang.reflect.Field;
import java.util.*;
/**
 * @ClassName SwaggerConfiguration
 * @Description TODO
 * @Author james
 * @Date 2021/11/25 14:38
 * @Version 1.0
 **/

@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
    private final SwaggerProperties swaggerProperties;

    public SwaggerConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")

                // Define whether swagger is turned on, false is turned off, and can be controlled by variables
                .enable(swaggerProperties.getEnable())

                // Set the meta information of the api to be included in the json ResourceListing response.
                .apiInfo(apiInfo())

                // Interface debugging address
                .host(swaggerProperties.getTryHost())

                // Which interfaces to choose to publish as swagger doc
                .select()
                // Monitor all APIs
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                //Do not display the wrong interface address
                //The wrong path is not monitored
                .paths(PathSelectors.regex("/myAccountSalesService/error").negate())
                // Monitor all paths under the root
                .paths(PathSelectors.regex("/.*"))
                .build()

                // Supported communication protocol set
                .protocols(newHashSet("https", "http"))

                // Authorization information settings, necessary header token and other authentication information
                .securitySchemes(securitySchemes())

                // Global application of authorization information
                .securityContexts(securityContexts());
    }

    /**
     * Information displayed in the upper part of the API page
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerProperties.getApplicationName() + " Api Doc")
                .description(swaggerProperties.getApplicationDescription())
                .contact(new Contact("jiapeng.wu", null, "jiapeng.wu@globulets.com"))
                .version("Application Version: " + swaggerProperties.getApplicationVersion() + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }

    /**
     * Set authorization information
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * Global application of authorization information
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

    /**
     * Universal interceptor excludes swagger settings, all interceptors will automatically add swagger-related resource exclusion information
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration
                            .excludePathPatterns("/swagger**/**")
                            .excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**")
                            .excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}