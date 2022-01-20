package com.rats.taskboardservice.resource;


import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.TaskDto;
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
public class TaskResource {

  private final TaskService taskService;

  private final UserService userService;

  private final MapperFacade mapperFacade;

  @GetMapping("/task-board")
  public ResponseEntity<?> getTaskBoard() {
    List<TaskDto> tasks = mapperFacade.mapAsList(taskService.getAllTasks(),TaskDto.class);
    return !tasks.isEmpty()
            ? new ResponseEntity<>(tasks, HttpStatus.OK)
            : new ResponseEntity<>("Task board is empty",HttpStatus.NOT_FOUND);
  }

  @GetMapping("/my-tasks")
  public ResponseEntity<?> getMyTasks(@CookieValue("authUser") String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    List<TaskDto> tasksOfUser = mapperFacade.mapAsList(taskService.getMyTasksOfUser(user),TaskDto.class);
    return !tasksOfUser.isEmpty()
            ? new ResponseEntity<>(tasksOfUser,HttpStatus.OK)
            : new ResponseEntity<>("My tasks is empty",HttpStatus.NOT_FOUND);
  }

  @GetMapping("/in-progress-tasks")
  public ResponseEntity<?> getInProgressTasks(@CookieValue("authUser") String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    List<TaskDto> inProgressTasksOfUser = mapperFacade.mapAsList(taskService.getInProgressTasksOfUser(user),TaskDto.class);
    return !inProgressTasksOfUser.isEmpty()
            ? new ResponseEntity<>(inProgressTasksOfUser,HttpStatus.OK)
            : new ResponseEntity<>("In progress tasks is empty", HttpStatus.NOT_FOUND);
  }

  @PostMapping("/new-task/save")
  public ResponseEntity<?> createNewTask(@CookieValue("authUser") String authUser,@RequestBody TaskDto taskDto) {
    UserEntity user = userService.findByNickname(authUser);
    TaskEntity task = mapperFacade.map(taskDto,TaskEntity.class);
    return taskService.save(task,user);
  }

  @PutMapping("/in-progress-tasks/resolve/{id}")
  public ResponseEntity<?> resolveTask(@PathVariable Long id,@CookieValue("authUser") String authUser) {
    return taskService.resolveTask(id,authUser);
  }

  @PutMapping("/my-tasks/delete/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id,@CookieValue("authUser") String authUser) {
    return taskService.deactivateTask(id,authUser);
  }

  @PutMapping("/task-board/start/{id}")
  public ResponseEntity<?> startTask(@PathVariable Long id, @CookieValue("authUser") String authUser) {
    UserEntity user = userService.findByNickname(authUser);
    return taskService.startTask(user,id);
  }


}
