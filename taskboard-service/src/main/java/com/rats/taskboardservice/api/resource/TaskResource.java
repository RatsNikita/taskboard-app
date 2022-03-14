package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.api.dto.TaskDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface TaskResource {

  @GetMapping("/task-board")
  @ApiOperation(value = "View tasks board",
          notes = "View all tasks",
          response = ResponseEntity.class)
  ResponseEntity<?> getTaskBoard();

  @GetMapping("/my-task")
  @ApiOperation(value = "View my tasks",
          notes = "View all task created by the user",
          response = ResponseEntity.class)
  ResponseEntity<?> getMyTasks(@CookieValue("authUser") String authUser);

  @GetMapping("/in-progress-task")
  @ApiOperation(value = "View in progress tasks",
          notes = "View all task executing by the user",
          response = ResponseEntity.class)
  ResponseEntity<?> getInProgressTasks(@CookieValue("authUser") String authUser);

  @PostMapping("/new-task/save")
  @ApiOperation(value = "Create new task",
          notes = "Provide task data for creating a new task",
          response = ResponseEntity.class)
  ResponseEntity<?> createNewTask(@CookieValue("authUser") String authUser,@Valid @RequestBody TaskDto taskDto);

  @PutMapping("/in-progress-task/resolve/{id}")
  @ApiOperation(value = "Resolve task",
          notes = "Specify task id to resolve it",
          response = ResponseEntity.class)
  ResponseEntity<?> resolveTask(@PathVariable Long id, @CookieValue("authUser") String authUser);

  @PutMapping("/my-task/delete/{id}")
  @ApiOperation(value = "Delete my task",
          notes = "Specify task id to delete it",
          response = ResponseEntity.class)
  ResponseEntity<?> deleteTask(@PathVariable Long id, @CookieValue("authUser") String authUser);

  @PutMapping("/task-board/start/{id}")
  @ApiOperation(value = "Start task",
          notes = "Specify id of the task you will be executing",
          response = ResponseEntity.class)
  ResponseEntity<?> startTask(@PathVariable Long id, @CookieValue("authUser") String authUser);
}
