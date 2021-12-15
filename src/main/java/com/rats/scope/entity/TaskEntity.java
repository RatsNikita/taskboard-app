package com.rats.scope.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;
  private TaskStatus status;
}
