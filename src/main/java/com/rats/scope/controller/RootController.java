package com.rats.scope.controller;

import com.rats.scope.entity.TaskEntity;
import com.rats.scope.entity.UserEntity;
import com.rats.scope.entity.dto.TaskDto;
import com.rats.scope.service.TaskService;
import com.rats.scope.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RootController {

  private final TaskService taskService;
  private final UserService userService;
  private final MapperFacade mapperFacade;

  @GetMapping({"", "/"})
  public String index() {
    return "login";
  }

  @GetMapping("/login")
  public String login(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser",authUser);
    return "login";
  }

  @GetMapping("/logout")
  public String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @GetMapping("/main")
  public String mainPage(Model model,@CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser",authUser);
    return "main";
  }
  @GetMapping("/new-task")
  public String newTask(Model model,@CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser",authUser);
    return "new-task";
  }

  @GetMapping("/my-tasks")
  public String myTasks(Model model,@CookieValue("authUser") String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks",mapperFacade.mapAsList(tasksOfUser, TaskDto.class));
    return "my-tasks";
  }

  @GetMapping("/task-board")
  public String tasksBoard(Model model,@CookieValue("authUser") String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasks = taskService.getAllTasks();
    model.addAttribute("tasks",mapperFacade.mapAsList(tasks,TaskDto.class));
    return "task-board";
  }

}
