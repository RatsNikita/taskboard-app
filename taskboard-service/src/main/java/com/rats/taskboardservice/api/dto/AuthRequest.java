package com.rats.taskboardservice.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
  @NotEmpty(message = "Nickname must be filled")
  private String nickname;
  @NotEmpty(message = "Password must be filled")
  private String password;
}
