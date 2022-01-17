package com.rats.taskboardnotificationservice.service;



import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTask {

  private final EmailService emailService;

  @Scheduled(cron = "0 28 12 * * ?")
  public void taskDeadlineNotice() {
    emailService.sendSimpleMessage();
  }
}
