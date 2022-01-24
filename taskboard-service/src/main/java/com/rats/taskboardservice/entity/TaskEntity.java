package com.rats.taskboardservice.entity;

import com.rats.taskboardservice.entity.enums.TaskDifficulty;
import com.rats.taskboardservice.entity.enums.TaskPriority;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(targetEntity = UserEntity.class)
  @JoinTable(name = "users_task",
  joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
  inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  private UserEntity user = new UserEntity();

  private String title;
  private String body;
  private LocalDate endDate;
  private String customer;
  private String executor;
  private LocalDateTime creationDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime updateDate;
  @Enumerated(EnumType.STRING)
  private TaskStatus status;
  @Enumerated(EnumType.STRING)
  private TaskDifficulty difficulty;
  @Enumerated(EnumType.STRING)
  private TaskPriority priority;
}
