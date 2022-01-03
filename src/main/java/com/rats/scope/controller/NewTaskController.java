package com.rats.scope.controller;


import com.rats.scope.entity.TaskEntity;
import com.rats.scope.entity.UserEntity;
import com.rats.scope.entity.dto.TaskDto;
import com.rats.scope.service.EmailService;
import com.rats.scope.service.TaskService;
import com.rats.scope.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/new-task")
public class NewTaskController {

  private final MapperFacade mapperFacade;

  private final TaskService taskService;

  private final UserService userService;

  private final EmailService emailService;

  @PostMapping("/save")
  public String saveTask(Model model, TaskDto task,
                         @CookieValue(name = "authUser") Cookie authUser) {
    UserEntity user = userService.findByNickname(authUser.getValue());
    TaskEntity taskEntity =  mapperFacade.map(task, TaskEntity.class);
    taskEntity.getUserEntityList().add(user);
    taskService.save(taskEntity,user);
    model.addAttribute("currentUser", user.getNickname());
    List<TaskEntity> tasksOfUser = taskService.getMyTasksOfUser(user);
    model.addAttribute("tasks", mapperFacade.mapAsList(tasksOfUser,TaskDto.class));
    emailService.sendSimpleMessage(user);
    return "my-tasks";
  }
}
