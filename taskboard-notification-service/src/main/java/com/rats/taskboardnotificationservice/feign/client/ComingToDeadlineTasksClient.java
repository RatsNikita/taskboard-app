package com.rats.taskboardnotificationservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "taskboard-service")
public interface ComingToDeadlineTasksClient {

  @GetMapping("/comingtodeadlinetasks")
   Map<String,String> getDeadline();

}
