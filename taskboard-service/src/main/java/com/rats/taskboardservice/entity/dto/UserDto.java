package com.rats.taskboardservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

  private Long id  = 0L;
  @JsonAlias("user")
  @NotEmpty
  private String nickname;
  private String password;
  @NotEmpty
  @Email
  private String email;
}
