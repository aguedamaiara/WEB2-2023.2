/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.repositorios;

import br.edu.ifpe.recife.model.negocio.Alerta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioAlerta {
    
   public static List<Alerta> alertas = null;
   
   static{
       alertas = new ArrayList();
   }
    
    //create
    public static void create(Alerta a){
        alertas.add(a);
    }
    
    //read
    public static Alerta read(int codigo){
        for(Alerta aAux: alertas){
            if(aAux.getCodigo()==codigo){
                return aAux;
            }
        }
        return null;
    }
    
    //update
    public static void update(Alerta a){
        
        for(Alerta aAux: alertas){
            if(aAux.getCodigo()==a.getCodigo()){
            aAux.setDataCriacao(a.getDataCriacao());
            aAux.setRelatoCriacao(a.getRelatoCriacao());
            aAux.setAutor(a.getAutor());
            aAux.setDoguinho(a.getDoguinho());
            aAux.setDataAcolhimento(a.getDataAcolhimento());
            aAux.setRelatoAcolhimento(a.getRelatoAcolhimento());
            aAux.setIntervencoesFeitas(a.getIntervencoesFeitas());
            aAux.setAcolhedor(a.getAcolhedor());
            return;
            }
        }
    }
    
    //delete
    public static void delete(Alerta a){
        alertas.remove(a);
    }
}
