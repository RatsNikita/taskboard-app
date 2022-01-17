package com.rats.taskboardservice.controller;


import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.TaskDto;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import com.rats.taskboardservice.service.TaskService;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-tasks")
public class MyTasksController {

  private final UserService userService;

  private final TaskService taskService;

  private final MapperFacade mapperFacade;

  @RequestMapping("/delete")
  public String resolve(Model model, @PathParam("id") Long id, @CookieValue(name = "authUser") String authUser) {
    taskService.changeStatus(id, TaskStatus.DEACTIVATED);
    model.addAttribute("currentUser", authUser);
    UserEntity user = userService.findByNickname(authUser);
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks", mapperFacade.mapAsList(tasksOfUser, TaskDto.class));
    return "my-tasks";
  }
}
