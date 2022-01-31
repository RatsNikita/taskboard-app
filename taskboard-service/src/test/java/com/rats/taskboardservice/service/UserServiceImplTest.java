package com.rats.taskboardservice.service;

import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.entity.SettingEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.rats.taskboardservice.util.TestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;


  @Test
  void shouldSaveUser() {
    UserEntity user = getDefaultUser();
    userService.save(user);
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void shouldThrowExceptionIfNicknameIsUsed() {
    UserEntity user = getDefaultUser();
    when(userRepository.existsByNickname(user.getNickname())).thenReturn(true);
    assertThrows(RequestException.class, () -> userService.save(user));
    verify(userRepository,times(1)).existsByNickname(user.getNickname());
    verify(userRepository,never()).save(any());
  }

  @Test
  void shouldThrowExceptionIfEmailIsUsed() {
    UserEntity user = getDefaultUser();
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
    assertThrows(RequestException.class, () -> userService.save(user));
    verify(userRepository,times(1)).existsByEmail(user.getEmail());
    verify(userRepository,never()).save(any());
  }

  @Test
  void shouldUpdateUser() {
    UserEntity user = getDefaultUser();
    UserEntity updatedUser = getUpdatedUser();
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    when(userRepository.getById(1L)).thenReturn(user);
    user.setNickname("Iiiiii");
    user.setPassword("iiiii111");
    user.setEmail("ii@mail.ru");
    userService.update(1L,updatedUser);
    verify(userRepository,times(1)).saveAndFlush(any());
    verify(userRepository,times(1)).getById(any());
  }

  @Test
  void shouldThrowExceptionIfUserNotFound() {
    UserEntity user = getDefaultUser();
    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
    assertThrows(RequestException.class, () -> userService.update(user.getId(),user));
    verify(userRepository,times(1)).findById(user.getId());
    verify(userRepository,never()).saveAndFlush(any());
  }

  @Test
  void findByNickname() {
    UserEntity user = getDefaultUser();
    when(userRepository.findByNickname(user.getNickname())).thenReturn(user);
    userService.findByNickname(user.getNickname());
    verify(userRepository,times(1)).findByNickname(any());
  }

  @Test
  void authorizeUser() {
    UserEntity user = getDefaultUser();
    AuthRequest authRequest = getDefaultAuthRequest();
    when(userRepository.findByNickname(authRequest.getNickname())).thenReturn(user);
    UserEntity result = userService.authorizeUser(authRequest);
    verify(userRepository,times(1)).findByNickname(any());
    assertThat(result.getNickname()).isEqualTo(authRequest.getNickname());
    assertThat(result.getPassword()).isEqualTo(authRequest.getPassword());
  }

  @Test
  void shouldThrowExceptionIfAuthorizationDataIsWrong() {
    AuthRequest authData = getDefaultAuthRequest();
    when(userRepository.findByNickname(authData.getNickname())).thenReturn(null);
    assertThrows(RequestException.class, () -> userService.authorizeUser(authData));
    verify(userRepository,times(1)).findByNickname(any());

  }


  @Test
  void canFindAllUsers() {
    List<UserEntity> userEntityList = new ArrayList<>();
    UserEntity user = getDefaultUser();
    userEntityList.add(user);
    when(userRepository.findAll()).thenReturn(userEntityList);
    userService.findAll();
    verify(userRepository,times(2)).findAll();
  }

  @Test
  void shouldThrowExceptionIfUsersWereNotFound() {
    when(userRepository.findAll()).thenReturn(Collections.emptyList());
    assertThrows(RequestException.class, () -> userService.findAll());
    verify(userRepository,times(1)).findAll();
  }

  @Test
  void deleteById() {
    when(userRepository.existsById(1L)).thenReturn(true);
    userService.deleteById(1L);
    verify(userRepository,times(1)).existsById(any());
    verify(userRepository,times(1)).deleteById(any());
  }

  @Test
  void shouldThrowExceptionIfUserWithThisIdDoesNotExist() {
    UserEntity user = getDefaultUser();
    when(userRepository.existsById(user.getId())).thenReturn(false);
    assertThrows(RequestException.class,
            () -> userService.deleteById(user.getId()));
    assertThrows(RequestException.class,
            () -> userService.findById(user.getId()));
    verify(userRepository,never()).deleteById(any());
    verify(userRepository,never()).getById(any());
  }

  @Test
  void findById() {
    UserEntity user = getDefaultUser();
    when(userRepository.existsById(user.getId())).thenReturn(true);
    userService.findById(1L);
    verify(userRepository, times(1)).getById(any());

  }

  @Test
  void updateSettingsOfUser() {
    UserEntity user = getDefaultUser();
    SettingEntity settings = new SettingEntity();
    settings.setMailing(true);
    settings.setTelegramId("aaaaa");
    when(userRepository.findByNickname(user.getNickname())).thenReturn(user);
    userService.updateSettingsOfUser(user.getNickname(),settings);
    verify(userRepository,times(1)).findByNickname(any());
    verify(userRepository,times(1)).saveAndFlush(any());


  }
}