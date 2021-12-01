package com.rats.scope.repository;

import com.rats.scope.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {

  UserEntity findByNickname(String name);
}
