package com.rats.taskboardservice.resource;


import com.rats.taskboardservice.api.resource.UserResource;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.UserDto;
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
public class UserResourceImpl implements UserResource {

  private final UserService userService;

  private final MapperFacade mapperFacade;


  @Override
  public ResponseEntity<UserDto> getById(Long id, Cookie authCookie) {
    UserDto user = mapperFacade.map(userService.findById(id),UserDto.class);
    return new ResponseEntity<>(user,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<UserDto>> getAll(Cookie authCookie){
    List<UserDto> users = mapperFacade.mapAsList(userService.findAll(),UserDto.class);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> addUser(UserDto user, Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
    userEntity.setCreationDate(OffsetDateTime.now());
    userService.save(userEntity);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<?> updateUser(Long id, UserDto user, Cookie authCookie) {
    UserEntity userEntity = mapperFacade.map(user,UserEntity.class);
    userService.update(id,userEntity);
    return  new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> deleteUser(Long id, Cookie authCookie) {
     userService.deleteById(id);
     return new ResponseEntity<>(HttpStatus.OK);
  }
}
