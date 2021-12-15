package com.rats.scope.entity.dto;

import com.rats.scope.entity.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private OffsetDateTime creationDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private OffsetDateTime updateDate;
}
