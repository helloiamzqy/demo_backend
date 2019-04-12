package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo Restful Api Documentation")
                .description("SpringBoot Swagger Documentation Demo.")
                .version("0.0.0.1")
                .build();
    }

    @Bean
    public Docket restApiDocket() {
        List<ResponseMessage> messages = new ArrayList<>();
        messages.add(new ResponseMessageBuilder().code(400).message("Bad Request").responseModel(new ModelRef("ErrorResponses")).build());
        messages.add(new ResponseMessageBuilder().code(404).message("Not Found").responseModel(new ModelRef("ErrorResponse")).build());
        messages.add(new ResponseMessageBuilder().code(409).message("Conflict").responseModel(new ModelRef("ErrorResponse")).build());
        messages.add(new ResponseMessageBuilder().code(500).message("Internal Server Error").responseModel(new ModelRef("ErrorResponse")).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, messages)
                .globalResponseMessage(RequestMethod.POST, messages)
                .globalResponseMessage(RequestMethod.PUT, messages)
                .globalResponseMessage(RequestMethod.DELETE, messages)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
