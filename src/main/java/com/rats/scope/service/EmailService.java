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

  public void sendSimpleMessage(UserEntity user) {
    if(user.getSettings().isMailing()) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("TaskBoard");
      message.setTo(user.getEmail());
      message.setSubject("У вас " + user.getTaskEntityList().size() + " нерешенных задач. Поторопитесь!");
      message.setText("You had create task");
      mailSender.send(message);
    }
  }
}
