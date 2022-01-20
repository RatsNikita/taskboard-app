package com.rats.taskboardservice.aspect;


import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessCheckAspect {

  @Pointcut("@annotation(com.rats.taskboardservice.annotation.AccessCheck) && args(..,authCookie)")
  public void beforeCallAccessCheck(Cookie authCookie){

  }

  @Before(value = "beforeCallAccessCheck(authCookie)", argNames = "authCookie")
  public void beforeCall(Cookie authCookie) {
    if(authCookie ==null || !authCookie.getValue().equals("admin")) {
      throw new RequestException(HttpStatus.FORBIDDEN,"No access");
    }
  }



}
