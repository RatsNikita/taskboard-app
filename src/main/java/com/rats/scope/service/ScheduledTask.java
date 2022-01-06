package com.rats.scope.service;


import com.rats.scope.entity.enums.TaskStatus;
import com.rats.scope.repository.TaskRepository;
import com.rats.scope.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTask {

  private final TaskRepository taskRepository;

  private final UsersRepository usersRepository;

  private final EmailService emailService;

  @Scheduled(cron = "0 44 14 * * ?")
  public void taskDeadlineNotice() {
    taskRepository.findAll().stream()
            .filter(taskEntity -> taskEntity.getStatus().equals(TaskStatus.IN_PROGRESS))
            .filter(taskEntity -> LocalDate.now().equals(taskEntity.getEndDate()))
            .forEach(t -> emailService.sendSimpleMessage(usersRepository.findByNickname(t.getExecutor()),t.getTitle()));
  }
}
