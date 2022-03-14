package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.api.dto.AuthRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


public interface LoginResource {
  @PostMapping("/login")
  @ApiOperation(value = "Login",
          notes = "Specify login details",
          response = ResponseEntity.class)
  ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest, HttpServletResponse response);

  @PostMapping("/logout")
  @ApiOperation(value = "Logout",
          response = ResponseEntity.class)
  ResponseEntity<?> logout(HttpServletResponse response,
                           @CookieValue(value = "authUser", required = false) Cookie authUser);
}
