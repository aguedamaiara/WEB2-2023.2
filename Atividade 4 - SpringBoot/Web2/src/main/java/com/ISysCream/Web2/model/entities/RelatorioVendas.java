package com.ISysCream.Web2.model.entities;
public class RelatorioVendas {

    private String sabor;
    private int quantidadeVendida;

    public RelatorioVendas(String sabor, int quantidadeVendida) {
        this.sabor = sabor;
        this.quantidadeVendida = quantidadeVendida;
    }


    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
