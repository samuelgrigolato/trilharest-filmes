package com.opensanca.trilharest.filmes.comum;

import io.swagger.annotations.ApiParam;

public class ParametrosDePaginacao {

  @ApiParam(value="Número da página desejada", defaultValue="1")
  private Integer pagina = 1;

  @ApiParam(value="Tamanho máximo da página",  defaultValue="3")
  private Integer tamanhoDaPagina = 3;

  public Integer getPagina() {
    return pagina;
  }

  public void setPagina(Integer pagina) {
    this.pagina = pagina;
  }

  public Integer getTamanhoDaPagina() {
    return tamanhoDaPagina;
  }

  public void setTamanhoDaPagina(Integer tamanhoDaPagina) {
    this.tamanhoDaPagina = tamanhoDaPagina;
  }
}
