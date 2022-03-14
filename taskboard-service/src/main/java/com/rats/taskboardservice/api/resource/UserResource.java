package com.rats.taskboardservice.api.resource;

import com.rats.taskboardservice.annotation.AccessCheck;
import com.rats.taskboardservice.api.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.List;


@RequestMapping("/api/user")
public interface UserResource {
  @AccessCheck
  @GetMapping("/{id}")
  @ApiOperation(value = "Find user by ID",
  notes = "Provide an id to look up specific contact from the users list",
  response = ResponseEntity.class)
  ResponseEntity<UserDto> getById(@ApiParam( value = "ID value for the user you need to get", required = true)
                                  @PathVariable Long id, @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @GetMapping
  @ApiOperation(value = "Get a list of all users",
          notes = "Provide a list of all existing users",
          response = ResponseEntity.class)
  ResponseEntity<List<UserDto>> getAll(@CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @PostMapping
  @ApiOperation(value = "Create new user",
          notes = "Specify data to create a new user",
          response = ResponseEntity.class)
  ResponseEntity<?> addUser(@Valid @RequestBody UserDto user,
                            @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @PutMapping("/{id}")
  @ApiOperation(value = "Changing information about an existing user",
          notes = "Specify the user id and new value for data you want to be change",
          response = ResponseEntity.class)
  ResponseEntity<?> updateUser(@ApiParam( value = "ID value for the user you need to change", required = true)
                               @PathVariable Long id, @Valid @RequestBody UserDto user,
                               @CookieValue(name = "authUser", required = false) Cookie authCookie);

  @AccessCheck
  @DeleteMapping("/{id}")
  @ApiOperation(value = "Deleting user without possibility of restore",
          notes = "Provide id of the user you want to delete",
          response = ResponseEntity.class)
  ResponseEntity<?> deleteUser(@ApiParam( value = "ID value for the user you need to remove", required = true)
                               @PathVariable Long id,
                               @CookieValue(name = "authUser", required = false) Cookie authCookie);
}
