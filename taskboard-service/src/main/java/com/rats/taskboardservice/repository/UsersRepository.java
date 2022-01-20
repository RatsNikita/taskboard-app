package com.rats.taskboardservice.repository;

import com.rats.taskboardservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {

  UserEntity findByNickname(String nickname);
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);
}
