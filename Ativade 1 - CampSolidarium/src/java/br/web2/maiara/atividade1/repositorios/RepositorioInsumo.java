/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Insumo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioInsumo {


    public static List<Insumo> insumos = null;

    static {
        insumos = new ArrayList<>();
    }

    public static void create(Insumo i) {
        insumos.add(i);
    }

    public static void update(Insumo i) {

        for (Insumo iAux : insumos) {
            if (iAux.getCodigo() == i.getCodigo()) {
                iAux.setNome(i.getNome());
                iAux.setMarca(i.getMarca());
                iAux.setCategoria(i.getCategoria());
                return;
            }
        }
    }

    public static Insumo read(int codigo){
        for(Insumo iAux: insumos){
            if(iAux.getCodigo()==codigo){
                return iAux;
            }
        }
        return null;
    }
    
    public static void delete(Insumo i){
        insumos.remove(i);
    }
    
    public static List<Insumo> readAll(){
        return insumos;
    }
}

    

