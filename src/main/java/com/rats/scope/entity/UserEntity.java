package com.rats.scope.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.OffsetDateTime;

@Data
@Entity
public class UserEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;
  private String nickname;
  private String password;
  private String email;
  private OffsetDateTime creationDate;
  private OffsetDateTime updateDate;

}
