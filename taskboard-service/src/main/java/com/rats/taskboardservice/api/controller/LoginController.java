package com.rats.taskboardservice.api.controller;

import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.api.dto.UserDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface LoginController {

  @PostMapping("/save")
  String save(Model model, @Valid @ModelAttribute("user") UserDto user);

  @PostMapping("/logon")
  String authorization(Model model, @Valid @ModelAttribute("authData") AuthRequest authRequest,
                       HttpServletResponse response);
}
