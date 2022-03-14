package com.rats.taskboardservice.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(description ="User data" )
public class UserDto {

  @ApiModelProperty(notes = "The unique ID of the user")
  private Long id  = 0L;
  @JsonAlias("user")
  @NotEmpty(message = "Nickname must be filled")
  @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{5,16}$",
          message = "Nickname must consist of letters and numbers")
  @ApiModelProperty(notes = "The user nickname")
  private String nickname;
  @NotEmpty(message = "Password must be filled")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,50}$",
          message = "Password must be between 8 and 50 characters and contain at least one number")
  @ApiModelProperty(notes = "The user password")
  private String password;
  @NotEmpty(message = "Email must be filled")
  @Email(message = "Invalid email")
  @ApiModelProperty(notes = "The user email address")
  private String email;
}
