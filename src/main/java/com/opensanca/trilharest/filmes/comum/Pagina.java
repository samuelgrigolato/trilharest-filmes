package com.opensanca.trilharest.filmes.comum;

import java.util.List;

public class Pagina<T> {

    private List<T> registros;
    private Integer totalDeRegistros;

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public Integer getTotalDeRegistros() {
        return totalDeRegistros;
    }

    public void setTotalDeRegistros(Integer totalDeRegistros) {
        this.totalDeRegistros = totalDeRegistros;
    }
}
