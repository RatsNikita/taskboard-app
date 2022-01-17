package com.rats.taskboardservice.entity.dto;

import com.rats.taskboardservice.entity.enums.TaskDifficulty;
import com.rats.taskboardservice.entity.enums.TaskPriority;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDto {

  private Long id;
  private List<UserDto> userList = new ArrayList<>();

  private String title;
  private String body;
  private String customer;
  private TaskStatus status;
  private String executor;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime creationDate;
  @DateTimeFormat()
  private LocalDateTime updateDate;
  private TaskDifficulty difficulty;
  private TaskPriority priority;
}
