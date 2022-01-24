package com.rats.taskboardservice.controller;

import com.rats.taskboardservice.api.controller.InProgressTasksController;
import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.TaskDto;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import com.rats.taskboardservice.service.TaskService;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/in-progress-tasks")
public class InProgressTasksControllerImpl implements InProgressTasksController {

  private final UserService userService;

  private final TaskService taskService;

  private final MapperFacade mapperFacade;

  @Override
  public String resolve(Model model,  Long id,  String authUser) {
    taskService.changeStatus(id, TaskStatus.RESOLVED);
    model.addAttribute("currentUser", authUser);
    UserEntity user = userService.findByNickname(authUser);
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks", mapperFacade.mapAsList(tasksOfUser, TaskDto.class));
    return "my-tasks";
  }
}
