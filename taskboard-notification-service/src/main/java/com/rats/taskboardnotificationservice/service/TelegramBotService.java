package com.rats.taskboardnotificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {


  @Value("${bot.name}")
  private String botUsername;

  @Value("${bot.token}")
  private String botToken;

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }


  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();
      SendMessage response = new SendMessage();
      Long chatId = message.getChatId();
      response.setChatId(String.valueOf(chatId));
      String text = message.getText();
      response.setText(text);
      try {
        execute(response);

      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }

  }

  public void notificationMessage(String chatId, String task) {
    SendMessage.SendMessageBuilder builder = SendMessage.builder();
    builder.chatId(chatId);
    builder.text("У вас остался один день для решения задачи [" + task + "]. Поторопитесь!");
    try {
      execute(builder.build());
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }
}
