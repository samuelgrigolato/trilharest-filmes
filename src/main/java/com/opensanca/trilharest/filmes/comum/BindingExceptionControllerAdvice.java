package com.opensanca.trilharest.filmes.comum;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class BindingExceptionControllerAdvice {

  @ExceptionHandler(BindingException.class)
  public BindingExceptionDetalhadoDTO tratarBindingException(BindingException ex) {
    return new BindingExceptionDetalhadoDTO(ex.getResults());
  }

}
