/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALUNO
 */
public class Alerta {
    
    private int codigo;
    private long dataCriacao;
    private String relatoCriacao;
    private Usuario autor;
    
    private Doguinho doguinho;
    
    private long dataAcolhimento;
    private String relatoAcolhimento;
    private String intervencoesFeitas;
    private Ong acolhedor;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public long getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(long dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public Date getDataAcolhimentoFormatada(){
        return new Date(this.dataAcolhimento);
    }
    
    public String getDataAcolhimentoString(){
        return new SimpleDateFormat("dd/MM/yyyy")
                .format(new Date(this.dataAcolhimento));
    }

    public String getRelatoCriacao() {
        return relatoCriacao;
    }

    public void setRelatoCriacao(String relatoCriacao) {
        this.relatoCriacao = relatoCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Doguinho getDoguinho() {
        return doguinho;
    }

    public void setDoguinho(Doguinho doguinho) {
        this.doguinho = doguinho;
    }

    public long getDataAcolhimento() {
        return dataAcolhimento;
    }

    public void setDataAcolhimento(long dataAcolhimento) {
        this.dataAcolhimento = dataAcolhimento;
    }

    public String getRelatoAcolhimento() {
        return relatoAcolhimento;
    }

    public void setRelatoAcolhimento(String relatoAcolhimento) {
        this.relatoAcolhimento = relatoAcolhimento;
    }

    public String getIntervencoesFeitas() {
        return intervencoesFeitas;
    }

    public void setIntervencoesFeitas(String intervencoesFeitas) {
        this.intervencoesFeitas = intervencoesFeitas;
    }

    public Ong getAcolhedor() {
        return acolhedor;
    }

    public void setAcolhedor(Ong acolhedor) {
        this.acolhedor = acolhedor;
    }
    
    
    
}
