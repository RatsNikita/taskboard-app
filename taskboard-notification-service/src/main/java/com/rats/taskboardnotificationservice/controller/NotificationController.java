package com.rats.taskboardnotificationservice.controller;

import com.rats.taskboardnotificationservice.feign.client.ComingToDeadlineTasksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NotificationController {

  private final ComingToDeadlineTasksClient comingToDeadlineTasks;

  @GetMapping("/comingtodeadlinetasks")
  public Map<String,String> getDeadline() {
    return comingToDeadlineTasks.getDeadline();
  }
}
