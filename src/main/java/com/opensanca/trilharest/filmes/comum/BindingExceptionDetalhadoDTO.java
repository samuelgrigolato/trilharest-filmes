package com.opensanca.trilharest.filmes.comum;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BindingExceptionDetalhadoDTO {

  private List<ObjectError> erros;

  public BindingExceptionDetalhadoDTO(BindingResult results) {
    this.erros = results.getAllErrors();
  }

  public List<ObjectError> getErros() {
    return erros;
  }
}
