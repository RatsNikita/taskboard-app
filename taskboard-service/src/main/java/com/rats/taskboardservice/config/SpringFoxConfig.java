package com.rats.taskboardservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .apiInfo(apiDetails());
  }

  private ApiInfo apiDetails() {
    return new ApiInfo(
            "TaskBoard",
            "API for TaskBoard application",
            "1.0",
            "Free to use",
            new springfox.documentation.service.Contact("Ratz Nikita", "t.me/darksoul63",
                    "ratznikita163@gmail.com"),
            "API License",
            "t.me/darksoul63",
            Collections.emptyList()
    );
  }
}
