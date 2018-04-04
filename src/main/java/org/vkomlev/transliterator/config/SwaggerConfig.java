package org.vkomlev.transliterator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("Transliterator Application")
        .globalResponseMessage(RequestMethod.GET, getGlobalResponseMessage())
        .globalResponseMessage(RequestMethod.POST, getGlobalResponseMessage())
        .globalResponseMessage(RequestMethod.PUT, getGlobalResponseMessage())
        .globalResponseMessage(RequestMethod.DELETE, getGlobalResponseMessage()).apiInfo(apiInfo())
        .select().apis(RequestHandlerSelectors.basePackage("org.vkomlev.transliterator.controller"))
        .paths(PathSelectors.any()).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Transliteration API")
        .description("Transliteration from RU and UA to EN").contact(
            new Contact("Victor Komlev", "https://github.com/victor-komlev/",
                "victor.komlev@gmail.ocm")).version("1.0").build();

  }

  private List<ResponseMessage> getGlobalResponseMessage() {
    List<ResponseMessage> responseMessages = new ArrayList<>();
    responseMessages.add(new ResponseMessageBuilder().code(500).message("Failure")
        .responseModel(new ModelRef("Error")).build());
    responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized")
        .responseModel(new ModelRef("Error")).build());
    responseMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden")
        .responseModel(new ModelRef("Error")).build());
    responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found")
        .responseModel(new ModelRef("Error")).build());
    return responseMessages;
  }

}
