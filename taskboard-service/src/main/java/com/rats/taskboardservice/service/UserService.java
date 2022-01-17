package com.rats.taskboardservice.service;

import com.rats.taskboardservice.entity.UserEntity;
import com.rats.taskboardservice.entity.dto.AuthRequest;
import com.rats.taskboardservice.exception.RequestException;
import com.rats.taskboardservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private  final UsersRepository usersRepository;

  public UserEntity save(UserEntity user) {
    return usersRepository.save(user);
  }

  public UserEntity findByNickname(String nickname) {
    return usersRepository.findByNickname(nickname);
  }

  public UserEntity authorizeUser(AuthRequest authData) {
    UserEntity user = findByNickname(authData.getLogin());
    if (user == null || !user.getPassword().equals(authData.getPassword())) {
      throw new RequestException("Wrong name or password");
    }
    return user;
  }

  public List<UserEntity> findAll() {
    return usersRepository.findAll();
  }

  public UserEntity updateSettingsOfUser(UserEntity user) {
    return usersRepository.saveAndFlush(user);
  }

}
