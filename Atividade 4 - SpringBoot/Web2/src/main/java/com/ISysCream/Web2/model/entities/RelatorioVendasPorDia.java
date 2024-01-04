package com.ISysCream.Web2.model.entities;

public class RelatorioVendasPorDia {

    private String dia;
    private int quantidadeVendida;

    public RelatorioVendasPorDia(String dia, int quantidadeVendida) {
        this.dia = dia;
        this.quantidadeVendida = quantidadeVendida;
    }


    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
