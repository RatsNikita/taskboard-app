package com.rats.scope.service;

import com.rats.scope.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;

  public void sendSimpleMessage(UserEntity user,String taskTitle) {
    if(user.getSettings().isMailing()) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("TaskBoard");
      message.setTo(user.getEmail());
      message.setSubject("Скоро дедлайн");
      message.setText("У вас остался один день для решения задачи [" + taskTitle + "]. Поторопитесь!");
      mailSender.send(message);
    }
  }
}
