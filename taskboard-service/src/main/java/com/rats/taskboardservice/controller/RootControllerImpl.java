package com.rats.taskboardservice.controller;

import com.rats.taskboardservice.api.controller.RootController;
import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.TaskDto;
import com.rats.taskboardservice.service.TaskService;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RootControllerImpl implements RootController {

  private final TaskService taskService;

  private final UserService userService;

  private final MapperFacade mapperFacade;

  @Override
  public String index() {
    return "login";
  }

  @Override
  public String login(Model model, String authUser) {
    model.addAttribute("currentUser",authUser);
    return "login";
  }

  @Override
  public String logout(Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @Override
  public String mainPage(Model model, String authUser) {
    model.addAttribute("currentUser",authUser);
    return "main";
  }

  @Override
  public String newTask(Model model, String authUser) {
    model.addAttribute("currentUser",authUser);
    return "new-task";
  }

  @Override
  public String myTasks(Model model, String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks",mapperFacade.mapAsList(tasksOfUser, TaskDto.class));
    return "my-tasks";
  }

  @Override
  public String tasksBoard(Model model, String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasks = taskService.getAllTasks();
    model.addAttribute("tasks",mapperFacade.mapAsList(tasks,TaskDto.class));
    return "task-board";
  }

  @Override
  public String inProgressTasks(Model model, String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasks = taskService.getInProgressTasksOfUser(user);
    model.addAttribute("tasks",mapperFacade.mapAsList(tasks,TaskDto.class));
    return "in-progress-tasks";
  }

  @Override
  public String setting(Model model, String authUser) {
    model.addAttribute("currentUser",authUser);
    return "setting";
  }



}
