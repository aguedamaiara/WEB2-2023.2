package com.ISysCream.Web2.model.entities;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Sorvete {
    private int codigo;
    private Date dataCompra;
    private List<Sabor> sabores = new ArrayList<>();
    private TipoSorvete tipoSorvete;

    public Sorvete(int codigo, Date dataCompra, TipoSorvete tipoSorvete) {
        this.codigo = codigo;
        this.dataCompra = dataCompra;
        this.tipoSorvete = tipoSorvete;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void adicionarSabor(Sabor sabor) {
        sabores.add(sabor);
        System.out.println("Sabor adicionado: " + sabor.getNome());
    }

    public void exibirSabores() {
        System.out.println("Sabores do sorvete:");
        for (Sabor sabor : sabores) {
            System.out.println("- " + sabor.getNome());
        }
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public TipoSorvete getTipoSorvete() {
        return tipoSorvete;
    }
}