package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.annotation.AccessCheck;
import com.rats.taskboardservice.api.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.List;

public interface UserResource {
  @AccessCheck
  @GetMapping("/{id}")
  ResponseEntity<UserDto> getById(@PathVariable Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @GetMapping
  ResponseEntity<List<UserDto>> getAll(@CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @PostMapping
  ResponseEntity<?> addUser(@RequestBody UserDto user,
                            @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @PutMapping("/{id}")
  ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto user,
                               @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteUser(@PathVariable Long id,
                               @CookieValue(name = "authUser", required = false) Cookie authCookie);
}
