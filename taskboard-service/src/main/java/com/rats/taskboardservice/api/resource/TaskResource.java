package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.api.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TaskResource {
  @GetMapping("/task-board")
  ResponseEntity<?> getTaskBoard();

  @GetMapping("/my-tasks")
  ResponseEntity<?> getMyTasks(@CookieValue("authUser") String authUser);

  @GetMapping("/in-progress-tasks")
  ResponseEntity<?> getInProgressTasks(@CookieValue("authUser") String authUser);

  @PostMapping("/new-task/save")
  ResponseEntity<?> createNewTask(@CookieValue("authUser") String authUser, @RequestBody TaskDto taskDto);

  @PutMapping("/in-progress-tasks/resolve/{id}")
  ResponseEntity<?> resolveTask(@PathVariable Long id, @CookieValue("authUser") String authUser);

  @PutMapping("/my-tasks/delete/{id}")
  ResponseEntity<?> deleteTask(@PathVariable Long id, @CookieValue("authUser") String authUser);

  @PutMapping("/task-board/start/{id}")
  ResponseEntity<?> startTask(@PathVariable Long id, @CookieValue("authUser") String authUser);
}
