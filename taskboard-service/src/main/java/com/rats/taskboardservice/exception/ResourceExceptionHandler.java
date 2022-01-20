package com.rats.taskboardservice.exception;


import com.rats.taskboardservice.entity.dto.RequestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;


@Slf4j
@ControllerAdvice(basePackages = "com.rats.taskboardservice.resource")
public class ResourceExceptionHandler {

  @ExceptionHandler(RequestException.class)
  public ResponseEntity<String[]> handleRestRequestException(RequestException e) {
    log.error("RequestException: {}", Arrays.toString(e.getErrors()));
    return new ResponseEntity<>(e.getErrors(), e.getStatus());
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<RequestResponse> handleValidationException(BindException e) {
    log.warn("Validation exception: ", e);
    String[] violations = e.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toArray(String[]::new);
    RequestResponse errorResponse = new RequestResponse();
    errorResponse.setResult(violations);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
