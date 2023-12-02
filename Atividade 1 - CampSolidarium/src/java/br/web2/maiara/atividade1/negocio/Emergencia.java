/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.negocio;

/**
 *
 * @author agued
 */
public class Emergencia {

    private int codigo;
    private String local;
    private TipoEmergencia tipo;
    private String descricao;

    public Emergencia(int codigo, String local, TipoEmergencia tipo, String descricao) {
        this.codigo = codigo;
        this.local = local;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public TipoEmergencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmergencia tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    enum TipoEmergencia {
        DESLIZAMENTO,
        ALAGAMENTO,
        INCENDIO,
        QUEIMADA,
        FURACAO,
    }
}
