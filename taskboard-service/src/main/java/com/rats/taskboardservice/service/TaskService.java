package com.rats.taskboardservice.service;

import com.rats.taskboardservice.entity.TaskEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public ResponseEntity<?> save(TaskEntity task, UserEntity user) {
    task.setStatus(TaskStatus.ACTIVE);
    task.setCreationDate(LocalDateTime.now());
    task.setCustomer(user.getNickname());
    taskRepository.save(task);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  public ResponseEntity<?> startTask(UserEntity user, Long id) {
    TaskEntity taskEntity = taskRepository.findById(id)
            .orElseThrow(() -> new RequestException("Не существует записи с ID = " + id));
    if(taskEntity.getStatus().equals(TaskStatus.ACTIVE)) {
      taskEntity.setExecutor(user.getNickname());
      taskEntity.setStatus(TaskStatus.IN_PROGRESS);
      taskRepository.saveAndFlush(taskEntity);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  public List<TaskEntity> getMyTasksOfUser(UserEntity user) {
    return taskRepository.findByUserId(user.getId()).stream()
            .filter(TaskEntity -> TaskEntity.getStatus().equals(TaskStatus.ACTIVE) ||
                    TaskEntity.getStatus().equals(TaskStatus.IN_PROGRESS) )
            .collect(Collectors.toList());
  }

  public List<TaskEntity> getInProgressTasksOfUser(UserEntity user) {
    return taskRepository.findByExecutor(user.getNickname()).stream()
            .filter( TaskEntity -> TaskEntity.getStatus().equals(TaskStatus.IN_PROGRESS))
            .collect(Collectors.toList());
  }

  public List<TaskEntity> getAllTasks() {
    return new ArrayList<>(taskRepository.findAllByOrderByUpdateDateDesc());
  }

  public void changeStatus(Long id,TaskStatus status) {
    TaskEntity taskEntity = taskRepository.findById(id)
            .orElseThrow(() -> new RequestException("Task with id=" + id + " does not exist"));
    taskEntity.setStatus(status);
    taskEntity.setUpdateDate(LocalDateTime.now());
    taskRepository.saveAndFlush(taskEntity);
  }

  public ResponseEntity<?> deactivateTask(Long id,String authUser) {
    if(!taskRepository.existsById(id)) {
      return new ResponseEntity<>("Task with this ID was not found",HttpStatus.NOT_FOUND);
    }
    TaskEntity taskEntity = taskRepository.getById(id);
    if(!taskEntity.getCustomer().equals(authUser)) {
      return new ResponseEntity<>("You are not the customer of this task",HttpStatus.BAD_REQUEST);
    }
    taskEntity.setStatus(TaskStatus.DEACTIVATED);
    taskEntity.setUpdateDate(LocalDateTime.now());
    taskRepository.saveAndFlush(taskEntity);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<?> resolveTask(Long id,String authUser) {
    if(!taskRepository.existsById(id)) {
      return new ResponseEntity<>("Task with this ID was not found",HttpStatus.NOT_FOUND);
    }
    TaskEntity taskEntity = taskRepository.getById(id);
    if(!taskEntity.getExecutor().equals(authUser)) {
      return new ResponseEntity<>("You are not working on this task",HttpStatus.BAD_REQUEST);
    }
    taskEntity.setStatus(TaskStatus.RESOLVED);
    taskEntity.setUpdateDate(LocalDateTime.now());
    taskRepository.saveAndFlush(taskEntity);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
