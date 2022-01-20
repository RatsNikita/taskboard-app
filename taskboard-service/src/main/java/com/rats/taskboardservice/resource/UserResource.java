package com.rats.taskboardservice.resource;


import com.rats.taskboardservice.annotation.AccessCheck;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.UserDto;
import com.rats.taskboardservice.service.UserService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserResource {

  private final UserService userService;

  private final MapperFacade mapperFacade;


  @AccessCheck
  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    UserDto user = mapperFacade.map(userService.findById(id),UserDto.class);
    return new ResponseEntity<>(user,HttpStatus.OK);
  }

  @AccessCheck
  @GetMapping
  public ResponseEntity<List<UserDto>> getAll(@CookieValue(name = "authUser", required = false) Cookie authCookie){
    List<UserDto> users = mapperFacade.mapAsList(userService.findAll(),UserDto.class);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @AccessCheck
  @PostMapping
  public ResponseEntity<?> addUser(@RequestBody UserDto user,
                                   @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @AccessCheck
  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto user,
                                      @CookieValue(name = "authUser", required = false) Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
    userService.update(id,userEntity);
    return  new ResponseEntity<>(HttpStatus.OK);
  }

  @AccessCheck
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id,
                                      @CookieValue(name = "authUser", required = false) Cookie authCookie) {
     userService.deleteById(id);
     return new ResponseEntity<>(HttpStatus.OK);
  }
}
