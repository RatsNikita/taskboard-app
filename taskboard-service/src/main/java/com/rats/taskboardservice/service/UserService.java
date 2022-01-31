package com.rats.taskboardservice.service;

import com.rats.taskboardservice.entity.SettingEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private  final UserRepository userRepository;

  public UserEntity save(UserEntity user) {
    if(userRepository.existsByNickname(user.getNickname())){
      throw new RequestException(HttpStatus.BAD_REQUEST,"Name is used");
    } if(userRepository.existsByEmail(user.getEmail())) {
      throw new RequestException(HttpStatus.BAD_REQUEST,"Email address is used");
    }
    return userRepository.save(user);
  }

  public void update(Long id, UserEntity user) {
    if (userRepository.findById(id).isEmpty()) {
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    UserEntity userEntity = userRepository.getById(id);
    userEntity.setPassword(user.getPassword());
    userEntity.setNickname(user.getNickname());
    userEntity.setEmail(user.getEmail());
    userRepository.saveAndFlush(userEntity);
  }

  public UserEntity findByNickname(String nickname) {
    return userRepository.findByNickname(nickname);
  }

  public UserEntity authorizeUser(AuthRequest authData) {
    UserEntity user = findByNickname(authData.getNickname());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException(HttpStatus.BAD_REQUEST,"Wrong name or password");
    }
    return user;
  }

  public List<UserEntity> findAll() {
    if(userRepository.findAll().isEmpty()){
      throw new RequestException(HttpStatus.NOT_FOUND,"No users");
    }
    return userRepository.findAll();
  }

  public void deleteById(Long id) {
    if(!userRepository.existsById(id)) {
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    userRepository.deleteById(id);
  }

  public UserEntity findById(Long id) {
    if(!userRepository.existsById(id)) {
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    return userRepository.getById(id);
  }
  public void updateSettingsOfUser(String nickname, SettingEntity settings) {
    UserEntity userEntity = userRepository.findByNickname(nickname);
    userEntity.setSettings(settings);
    userRepository.saveAndFlush(userEntity);
  }

}
