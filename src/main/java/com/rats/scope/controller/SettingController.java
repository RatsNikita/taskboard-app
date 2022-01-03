package com.rats.scope.controller;


import com.rats.scope.entity.SettingEntity;
import com.rats.scope.entity.UserEntity;
import com.rats.scope.entity.dto.SettingDto;
import com.rats.scope.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController {


  private final UserService userService;

  private final MapperFacade mapperFacade;

  @PostMapping("/accept")
  public String acceptSettings(Model model, SettingDto userSettings,
                               @CookieValue(name="authUser")Cookie authUser) {
    UserEntity user =  userService.findByNickname(authUser.getValue());
    SettingEntity settings = mapperFacade.map(userSettings,SettingEntity.class);
    user.setSettings(settings);
    userService.updateSettingsOfUser(user);
    model.addAttribute("currentUser", user.getNickname());
    return "main";
  }
}
