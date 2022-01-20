package com.rats.taskboardservice.resource;

import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.AuthRequest;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginResource {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Validated @RequestBody AuthRequest authRequest, HttpServletResponse response) {
    UserEntity userEntity = userService.authorizeUser(authRequest);
    Cookie authCookie = new Cookie("authUser", userEntity.getNickname());
    authCookie.setMaxAge(3600);
    authCookie.setPath("/");
    response.addCookie(authCookie);
    return new ResponseEntity<>("Authorization was successful",HttpStatus.OK);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletResponse response,
                                @CookieValue(value = "authUser", required = false) Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new ResponseEntity<>("Sign out was successful",HttpStatus.OK);
  }
}
