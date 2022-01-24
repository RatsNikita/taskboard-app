package com.rats.taskboardservice.resource;


import com.rats.taskboardservice.api.resource.TaskResource;
import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.TaskDto;
import com.rats.taskboardservice.service.TaskService;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskResourceImpl implements TaskResource {

  private final TaskService taskService;

  private final UserService userService;

  private final MapperFacade mapperFacade;

  @Override
  public ResponseEntity<?> getTaskBoard() {
    List<TaskDto> tasks = mapperFacade.mapAsList(taskService.getAllTasks(),TaskDto.class);
    return !tasks.isEmpty()
            ? new ResponseEntity<>(tasks, HttpStatus.OK)
            : new ResponseEntity<>("Task board is empty",HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<?> getMyTasks(String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    List<TaskDto> tasksOfUser = mapperFacade.mapAsList(taskService.getMyTasksOfUser(user),TaskDto.class);
    return !tasksOfUser.isEmpty()
            ? new ResponseEntity<>(tasksOfUser,HttpStatus.OK)
            : new ResponseEntity<>("My tasks is empty",HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<?> getInProgressTasks(String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    List<TaskDto> inProgressTasksOfUser = mapperFacade.mapAsList(taskService.getInProgressTasksOfUser(user),TaskDto.class);
    return !inProgressTasksOfUser.isEmpty()
            ? new ResponseEntity<>(inProgressTasksOfUser,HttpStatus.OK)
            : new ResponseEntity<>("In progress tasks is empty", HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<?> createNewTask( String authUser, TaskDto taskDto) {
    UserEntity user = userService.findByNickname(authUser);
    TaskEntity task = mapperFacade.map(taskDto,TaskEntity.class);
    return taskService.save(task,user);
  }

  @Override
  public ResponseEntity<?> resolveTask(Long id, String authUser) {
    return taskService.resolveTask(id,authUser);
  }

  @Override
  public ResponseEntity<?> deleteTask(Long id, String authUser) {
    return taskService.deactivateTask(id,authUser);
  }

  @Override
  public ResponseEntity<?> startTask(Long id, String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    return taskService.startTask(user,id);
  }


}
