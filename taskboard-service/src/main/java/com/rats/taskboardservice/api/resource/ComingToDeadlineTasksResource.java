package com.rats.taskboardservice.api.resource;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public interface ComingToDeadlineTasksResource {
  @GetMapping("/comingtodeadlinetasks")
  Map<String, String> getDeadline();
}
