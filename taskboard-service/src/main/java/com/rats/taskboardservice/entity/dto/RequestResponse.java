package com.rats.taskboardservice.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestResponse {
  private String status;
  private String[] result;

  public RequestResponse(String status,String... result) {
    this.status=status;
    this.result=result;
  }
}
