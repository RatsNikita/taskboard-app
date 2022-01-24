package com.rats.taskboardservice.api.controller;

import com.rats.taskboardservice.api.dto.UserDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserController {
  @PostMapping("/save")
  String save(Model model, @ModelAttribute("user") UserDto user);
}
