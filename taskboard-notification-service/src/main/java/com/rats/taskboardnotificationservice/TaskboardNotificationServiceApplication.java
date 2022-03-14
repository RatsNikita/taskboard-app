package com.rats.taskboardnotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@SpringBootApplication
public class TaskboardNotificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskboardNotificationServiceApplication.class, args);
  }

}
