/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.repositorios;

import br.edu.ifpe.recife.model.negocio.Ong;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALUNO
 */
public class RepositorioOng {
    
    public static List<Ong> ongs = null;
    
    static {
        ongs = new ArrayList<>();
    }
    
    public static void create(Ong o){
        ongs.add(o);
    }
    
    public static void update(Ong o){
        
        for(Ong oAux: ongs){
            if(oAux.getCodigo() == o.getCodigo()){
                oAux.setEmail(o.getEmail());
                oAux.setEndereco(o.getEndereco());
                oAux.setEspecialidade(o.getEspecialidade());
                oAux.setNome(o.getNome());
                oAux.setSenha(o.getSenha());
                oAux.setTelefone(o.getTelefone());
                return;
            }
        }
        
    }
    
    public static Ong read(int codigo){
        
        for(Ong oAux: ongs){
            if(oAux.getCodigo()==codigo){
                return oAux;
            }
        }
        return null;
    }
    
    public static void delete(Ong o){
        ongs.remove(o);
    }
    
    public static List<Ong> readAll(){
        return ongs;
    }
}
