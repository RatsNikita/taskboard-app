package com.rats.scope.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthRequest {
  @JsonAlias("user")
  @NotEmpty
  private String login;
  @NotEmpty
  private String password;
}
