package com.rats.scope.service;

import com.rats.scope.entity.TaskEntity;
import com.rats.scope.entity.UserEntity;
import com.rats.scope.exception.RequestException;
import com.rats.scope.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskEntity save(TaskEntity task) {
    task.setIsActive(true);
    task.setCreationDate(OffsetDateTime.now());
    return taskRepository.save(task);
  }

  public List<TaskEntity> getActiveTasksOfUser(UserEntity user) {
    return taskRepository.findByUserId(user.getId()).stream()
            .filter(TaskEntity::getIsActive)
            .collect(Collectors.toList());
  }

  public void deactivate(Long id) {
    TaskEntity taskEntity = taskRepository.findById(id)
            .orElseThrow(() -> new RequestException("Не существует записи с ID = " + id));
    taskEntity.setIsActive(false);
    taskRepository.saveAndFlush(taskEntity);
  }
}
