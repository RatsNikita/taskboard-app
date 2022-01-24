package com.rats.taskboardservice.resource;

import com.rats.taskboardservice.api.resource.LoginResource;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginResourceImpl implements LoginResource {

  private final UserService userService;

  @Override
  public ResponseEntity<?> login(AuthRequest authRequest, HttpServletResponse response) {
    UserEntity userEntity = userService.authorizeUser(authRequest);
    Cookie authCookie = new Cookie("authUser", userEntity.getNickname());
    authCookie.setMaxAge(3600);
    authCookie.setPath("/");
    response.addCookie(authCookie);
    return new ResponseEntity<>("Authorization was successful",HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> logout(HttpServletResponse response, Cookie authUser) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return new ResponseEntity<>("Sign out was successful",HttpStatus.OK);
  }
}
