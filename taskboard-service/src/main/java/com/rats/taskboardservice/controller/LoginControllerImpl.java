package com.rats.taskboardservice.controller;

import com.rats.taskboardservice.api.controller.LoginController;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.api.dto.UserDto;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetDateTime;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

  private final MapperFacade mapperFacade;

  private final UserService userService;


  @Override
  public String save(Model model, UserDto user) {
    UserEntity userEntity = mapperFacade.map(user, UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    model.addAttribute("result", "Account created");
    return "login";
  }

  @Override
  public String authorization(Model model, AuthRequest authRequest,
                              HttpServletResponse response) {
    UserEntity user = userService.authorizeUser(authRequest);
    model.addAttribute("currentUser", user.getNickname());
    Cookie authUser = new Cookie("authUser", user.getNickname());
    authUser.setMaxAge(3600);
    authUser.setPath("/");
    response.addCookie(authUser);
    return "main";
  }
}
