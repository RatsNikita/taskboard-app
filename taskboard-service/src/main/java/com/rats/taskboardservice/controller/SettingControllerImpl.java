package com.rats.taskboardservice.controller;


import com.rats.taskboardservice.api.controller.SettingController;
import com.rats.taskboardservice.entity.SettingEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.SettingDto;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingControllerImpl implements SettingController {


  private final UserService userService;

  private final MapperFacade mapperFacade;

  @Override
  public String acceptSettings(Model model, SettingDto userSettings, Cookie authUser) {
    UserEntity user =  userService.findByNickname(authUser.getValue());
    SettingEntity settings = mapperFacade.map(userSettings,SettingEntity.class);
    user.setSettings(settings);
    userService.updateSettingsOfUser(authUser.getValue(),settings);
    model.addAttribute("currentUser", user.getNickname());
    return "main";
  }
}
