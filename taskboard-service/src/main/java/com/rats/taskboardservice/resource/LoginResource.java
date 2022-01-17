package com.rats.taskboardservice.resource;

import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.AuthRequest;
import com.rats.taskboardservice.entity.dto.RequestResponse;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class LoginResource {

  private final UserService userService;

  @PostMapping("/login")
  public RequestResponse login(@Validated @RequestBody AuthRequest authRequest, HttpServletResponse response) {
    UserEntity userEntity = userService.authorizeUser(authRequest);
    Cookie authCookie = new Cookie("authUser", userEntity.getNickname());
    authCookie.setMaxAge(3600);
    authCookie.setPath("/");
    response.addCookie(authCookie);
    return new RequestResponse("Успешно", "Авторизация успешна","Куки сохранены");
  }

  @PostMapping("/logout")
  public RequestResponse logout(HttpServletResponse response,
                                @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new RequestResponse("Успешно","Разавторизация");
  }
}
