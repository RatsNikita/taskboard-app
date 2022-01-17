package com.rats.taskboardservice.controller;


import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.UserDto;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.util.List;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

  private final MapperFacade mapperFacade;

  private final UserService userService;

  @PostMapping("/save")
  public String save(Model model, @ModelAttribute("user")UserDto user) {
    UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    List<UserEntity> userEntityList = userService.findAll();
    model.addAttribute("users", userEntityList);
    return "user";
  }
}
