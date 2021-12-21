package com.rats.scope.entity;

import com.rats.scope.entity.enums.TaskDifficulty;
import com.rats.scope.entity.enums.TaskPriority;
import com.rats.scope.entity.enums.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(targetEntity = UserEntity.class)
  @JoinTable(name = "users_task",
  joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  private List<UserEntity> userEntityList = new ArrayList<>();

  private String title;
  private String body;
  private LocalDate endDate;
  private String customer;
  private String executor;
  private LocalDateTime creationDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime updateDate;
  private TaskStatus status;
  private TaskDifficulty difficulty;
  private TaskPriority priority;
}
