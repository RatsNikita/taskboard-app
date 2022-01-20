package com.rats.taskboardservice.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
  @NotEmpty
  private String nickname;
  @NotEmpty
  private String password;
}
