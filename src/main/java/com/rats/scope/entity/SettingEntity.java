package com.rats.scope.entity;


import lombok.Data;
import javax.persistence.*;

@Data
@Embeddable
public class SettingEntity {

  private boolean mailing;

  private String telegramId;
}
