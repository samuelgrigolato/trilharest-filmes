package com.opensanca.trilharest.filmes.filmes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FimExibicaoAposInicioValidator implements ConstraintValidator<FimExibicaoAposInicio, FilmeFormDTO> {

  @Override
  public void initialize(FimExibicaoAposInicio constraintAnnotation) {
  }

  @Override
  public boolean isValid(FilmeFormDTO value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    if (value.getInicioExibicao() == null || value.getFimExibicao() == null) {
      return true;
    }
    return !value.getFimExibicao().isBefore(value.getInicioExibicao());
  }
}
