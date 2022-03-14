package com.rats.taskboardservice.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "Login details")
public class AuthRequest {
  @ApiModelProperty(notes = "Nickname for login")
  @NotEmpty(message = "Nickname must be filled")
  private String nickname;
  @ApiModelProperty(notes = "Password for login")
  @NotEmpty(message = "Password must be filled")
  private String password;
}
