package com.rats.taskboardservice.api.dto;

import com.rats.taskboardservice.entity.enums.TaskDifficulty;
import com.rats.taskboardservice.entity.enums.TaskPriority;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ApiModel(description ="Details about the task" )
public class TaskDto {

  @ApiModelProperty(notes = "The unique id of the task")
  private Long id;
  @ApiModelProperty(notes = "Task title")
  private String title;
  @ApiModelProperty(notes = "Task description")
  private String body;
  @ApiModelProperty(notes = "Task customer")
  private String customer;
  @ApiModelProperty(notes = "Task status")
  private TaskStatus status;
  @ApiModelProperty(notes = "Task executor")
  private String executor;
  @ApiModelProperty(notes = "Task deadline")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime creationDate;
  @DateTimeFormat()
  private LocalDateTime updateDate;
  @ApiModelProperty(notes = "Task difficulty")
  private TaskDifficulty difficulty;
  @ApiModelProperty(notes = "Task priority")
  private TaskPriority priority;
}
