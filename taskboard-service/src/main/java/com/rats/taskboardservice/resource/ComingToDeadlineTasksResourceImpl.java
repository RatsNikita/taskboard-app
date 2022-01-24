package com.rats.taskboardservice.resource;


import com.rats.taskboardservice.api.resource.ComingToDeadlineTasksResource;
import com.rats.taskboardservice.entity.enums.TaskStatus;
import com.rats.taskboardservice.repository.TaskRepository;
import com.rats.taskboardservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ComingToDeadlineTasksResourceImpl implements ComingToDeadlineTasksResource {

  private final TaskRepository taskRepository;

  private final UsersRepository usersRepository;

  @Override
  public Map<String,String> getDeadline() {
    Map<String,String> list = new HashMap<>();
    taskRepository.findAll().stream()
            .filter(taskEntity -> taskEntity.getStatus().equals(TaskStatus.IN_PROGRESS))
            .filter(taskEntity -> usersRepository.findByNickname(taskEntity.getExecutor())
                    .getSettings().isMailing())
            .filter(taskEntity -> LocalDate.now().equals(taskEntity.getEndDate()))
            .forEach(taskEntity -> list.put(taskEntity.getTitle(),usersRepository
                    .findByNickname(taskEntity.getExecutor()).getEmail()));
    return list;
  }
}
