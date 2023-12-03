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
public class Insumo {

    private int codigo;
    private String nome;
    private String marca;
    private CategoriaInsumo categoria;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public CategoriaInsumo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaInsumo categoria) {
        this.categoria = categoria;
    }

    public enum CategoriaInsumo {
        ALIMENTO,
        VESTUARIO,
        BRINQUEDO,
        MEDICAMENTOS,
        HIGIENE,
        MATERIAL_DE_CONSTRUCAO,
    }
}
