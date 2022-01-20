package com.rats.taskboardservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class RequestException extends RuntimeException {

  @Getter
  private final String[] errors;

  @Getter
  @Setter
  private HttpStatus status = HttpStatus.BAD_REQUEST;

  public RequestException(String...errors) {
    super();
    this.errors = errors;
  }

  public RequestException(HttpStatus status,String... errors) {
    super();
    this.errors = errors;
    this.status=status;

  }

}