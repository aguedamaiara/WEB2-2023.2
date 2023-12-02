/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Emergencia;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author agued
 */
public class RepositorioEmergencia {
        public static List<Emergencia> emergencias = null;

         static {
        emergencias = new ArrayList<>();
    }
         
        public static void create(Emergencia e){
            emergencias.add(e);
        }
        
        public static void update(Emergencia e){
            
            for(Emergencia eAux : emergencias){
                if(eAux.getCodigo()== e.getCodigo()){
                    eAux.setLocal(e.getLocal());
                    eAux.setTipo(e.getTipo());
                    eAux.setDescricao(e.getDescricao());
                    return;
                }
            }
        }
        
        public static Emergencia read (int codigo){
            for(Emergencia eAux: emergencias){
                if(eAux.getCodigo()== codigo){
                    return eAux;
                }
            }
            return null;
        }
        
        public static void delete(Emergencia e){
            emergencias.remove(e);
        }
        
        public static List<Emergencia> readAll(){
            return emergencias;
        }   
}
