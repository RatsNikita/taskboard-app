package com.rats.scope.controller;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class RootController {

  private final MapperFacade mapperFacade;

  @GetMapping({"", "/"})
  public String index() {
    return "login";
  }

  @GetMapping("/login")
  public String login(Model model, @CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser",authUser);
    return "login";
  }

  @GetMapping("/logout")
  public String logout(@CookieValue("authUser") Cookie authUser, HttpServletResponse response) {
    authUser.setMaxAge(0);
    response.addCookie(authUser);
    return "login";
  }

  @GetMapping("/main")
  public String mainPage(Model model,@CookieValue("authUser") String authUser) {
    model.addAttribute("currentUser",authUser);
    return "main";
  }

}
