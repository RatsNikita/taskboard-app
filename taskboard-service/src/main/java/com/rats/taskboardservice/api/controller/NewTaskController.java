package com.rats.taskboardservice.api.controller;

import com.rats.taskboardservice.api.dto.TaskDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;

public interface NewTaskController {
  @PostMapping("/save")
  String saveTask(Model model, TaskDto task,
                  @CookieValue(name = "authUser") Cookie authUser);
}
