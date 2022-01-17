package com.rats.taskboardservice.repository;

import com.rats.taskboardservice.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

  @Query("SELECT te FROM UserEntity ue JOIN ue.taskEntityList te ON ue.id = :id")
  List<TaskEntity> findByUserId(Long id);

  List<TaskEntity> findByExecutor(String executor);
  List<TaskEntity> findAllByOrderByUpdateDateDesc();
}
