package com.rats.taskboardnotificationservice.service;

import com.rats.taskboardnotificationservice.controller.NotificationController;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;

  private final NotificationController notificationController;

  public void sendSimpleMessage() {
    Map<String,String> list = getList();
    SimpleMailMessage message = new SimpleMailMessage();
    list.forEach( (k,v) -> {
    message.setFrom("TaskBoard");
    message.setTo(v);
    message.setSubject("Скоро дедлайн");
    message.setText("У вас остался один день для решения задачи [" + k + "]. Поторопитесь!");
    mailSender.send(message);
    });
  }

  public Map<String,String> getList() {
    return notificationController.getDeadline();
  }
}
