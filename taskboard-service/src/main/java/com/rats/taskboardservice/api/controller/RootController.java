package com.rats.taskboardservice.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface RootController {
  @GetMapping({"", "/"})
  String index();

  @GetMapping("/login")
  String login(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/logout")
  String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response);

  @GetMapping("/main")
  String mainPage(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/new-task")
  String newTask(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/my-tasks")
  String myTasks(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/task-board")
  String tasksBoard(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/in-progress-tasks")
  String inProgressTasks(Model model, @CookieValue("authUser") String authUser);

  @GetMapping("/setting")
  String setting(Model model, @CookieValue("authUser") String authUser);
}
