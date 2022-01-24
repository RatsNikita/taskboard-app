package com.rats.taskboardservice.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

public interface MyTasksController {
  @RequestMapping("/delete")
  String resolve(Model model, @PathParam("id") Long id, @CookieValue(name = "authUser") String authUser);
}
