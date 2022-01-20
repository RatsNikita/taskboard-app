package com.rats.taskboardservice.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestResponse {
  private String[] result;

  public RequestResponse( String... result) {
    this.result=result;
  }
}
