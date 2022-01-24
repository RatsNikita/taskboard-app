package com.rats.taskboardservice.api.controller;

import com.rats.taskboardservice.api.dto.SettingDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;

public interface SettingController {
  @PostMapping("/accept")
  String acceptSettings(Model model, SettingDto userSettings,
                        @CookieValue(name = "authUser") Cookie authUser);
}
