package com.rats.taskboardservice.api.dto;

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
