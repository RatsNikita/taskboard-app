package com.rats.taskboardservice.util;

import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.entity.UserEntity;

import java.time.OffsetDateTime;

public class TestUtils {

  public static UserEntity getDefaultUser() {
    UserEntity user = new UserEntity();
    user.setId(1L);
    user.setCreationDate(OffsetDateTime.now());
    user.setEmail("ke@mail.ru");
    user.setNickname("Kekeke");
    user.setPassword("Keke111");
    return user;
  }
  public static UserEntity getNewUser() {
    UserEntity user = new UserEntity();
    user.setId(2L);
    user.setCreationDate(OffsetDateTime.now());
    user.setEmail("aa@mail.ru");
    user.setNickname("Aaaaaa");
    user.setPassword("Aaaaaaa222");
    return user;
  }

  public static UserEntity getUserWithTakenNickname() {
    UserEntity user = new UserEntity();
    user.setId(3L);
    user.setCreationDate(OffsetDateTime.now());
    user.setEmail("bbb@mail.ru");
    user.setNickname("Kekeke");
    user.setPassword("Bbbb333");
    return user;
  }

  public static UserEntity getUserWithTakenEmail() {
    UserEntity user = new UserEntity();
    user.setId(4L);
    user.setCreationDate(OffsetDateTime.now());
    user.setEmail("ke@mail.ru");
    user.setNickname("Cccccc");
    user.setPassword("Cccc444");
    return user;
  }

  public static UserEntity getUpdatedUser() {
    UserEntity user = new UserEntity();
    user.setEmail("na@mail.ru");
    user.setNickname("Nanana");
    user.setPassword("Nanana11");
    return user;
  }

  public static AuthRequest getDefaultAuthRequest() {
    AuthRequest authRequest = new AuthRequest();
    authRequest.setNickname("Kekeke");
    authRequest.setPassword("Keke111");
    return authRequest;
  }
}
