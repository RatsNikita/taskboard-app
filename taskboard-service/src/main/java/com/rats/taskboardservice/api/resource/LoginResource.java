package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.api.dto.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface LoginResource {
  @PostMapping("/login")
  ResponseEntity<?> login(@Validated @RequestBody AuthRequest authRequest, HttpServletResponse response);

  @PostMapping("/logout")
  ResponseEntity<?> logout(HttpServletResponse response,
                           @CookieValue(value = "authUser", required = false) Cookie authUser);
}
