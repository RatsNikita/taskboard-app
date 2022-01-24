package com.rats.taskboardservice.service;

import com.rats.taskboardservice.entity.SettingEntity;
import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.api.dto.AuthRequest;
import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private  final UsersRepository usersRepository;

  public void save(UserEntity user) {
    if(usersRepository.existsByNickname(user.getNickname())){
      throw new RequestException(HttpStatus.BAD_REQUEST,"Name is used");
    } if(usersRepository.existsByEmail(user.getEmail())) {
      throw new RequestException(HttpStatus.BAD_REQUEST,"Email address is used");
    }
     usersRepository.save(user);
  }

  public void update(Long id, UserEntity user) {
    if (usersRepository.findById(id).isEmpty()) {
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    UserEntity userEntity = usersRepository.getById(id);
    userEntity.setPassword(user.getPassword());
    userEntity.setNickname(user.getNickname());
    userEntity.setEmail(user.getEmail());
    usersRepository.saveAndFlush(userEntity);
  }

  public UserEntity findByNickname(String nickname) {
    return usersRepository.findByNickname(nickname);
  }

  public UserEntity authorizeUser(AuthRequest authData) {
    UserEntity user = findByNickname(authData.getNickname());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException(HttpStatus.BAD_REQUEST,"Wrong name or password");
    }
    return user;
  }

  public List<UserEntity> findAll() {
    if(usersRepository.findAll().isEmpty()){
      throw new RequestException(HttpStatus.NOT_FOUND,"No users");
    }
    return usersRepository.findAll();
  }

  public void deleteById(Long id) {
    if(!usersRepository.existsById(id)) {
      usersRepository.deleteById(id);
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    usersRepository.deleteById(id);
  }

  public UserEntity findById(Long id) {
    if(usersRepository.findById(id).isEmpty()) {
      throw new RequestException(HttpStatus.NOT_FOUND,"User with this id does not exist");
    }
    return usersRepository.getById(id);
  }
  public void updateSettingsOfUser(String nickname, SettingEntity settings) {
    UserEntity userEntity = usersRepository.findByNickname(nickname);
    userEntity.setSettings(settings);
    usersRepository.saveAndFlush(userEntity);
  }

}
