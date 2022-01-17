package com.rats.taskboardservice.entity;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class SettingEntity {

  private boolean mailing;

  private String telegramId;
}
