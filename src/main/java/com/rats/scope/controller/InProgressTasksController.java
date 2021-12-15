package com.rats.scope.controller;

import com.rats.scope.entity.TaskEntity;
import com.rats.scope.entity.TaskStatus;
import com.rats.scope.entity.UserEntity;
import com.rats.scope.entity.dto.TaskDto;
import com.rats.scope.service.TaskService;
import com.rats.scope.service.UserService;
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
@RequestMapping("/in-progress-tasks")
public class InProgressTasksController {

  private final UserService userService;

  private final TaskService taskService;

  private final MapperFacade mapperFacade;

  @RequestMapping("/resolve")
  public String resolve(Model model, @PathParam("id") Long id, @CookieValue(name = "authUser") String authUser) {
    taskService.changeStatus(id, TaskStatus.RESOLVED);
    model.addAttribute("currentUser", authUser);
    UserEntity user = userService.findByNickname(authUser);
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks", mapperFacade.mapAsList(tasksOfUser, TaskDto.class));
    return "my-tasks";
  }
}
