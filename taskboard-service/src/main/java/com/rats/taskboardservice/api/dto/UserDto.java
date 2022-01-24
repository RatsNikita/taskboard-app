package com.rats.taskboardservice.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

  private Long id  = 0L;
  @JsonAlias("user")
  @NotEmpty(message = "Nickname must be filled")
  @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{5,16}$",
          message = "Nickname must consist of letters and numbers")
  private String nickname;
  @NotEmpty(message = "Password must be filled")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,50}$",
          message = "Password must be between 8 and 50 characters and contain at least one number")
  private String password;
  @NotEmpty(message = "Email must be filled")
  @Email(message = "Invalid email")
  private String email;
}
