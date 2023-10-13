/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.recife.model.repositorios;

import br.edu.ifpe.recife.model.negocio.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALUNO
 */
public class RepositorioUsuario {
    
    public static List<Usuario> usuarios = null;
    
    static {
        usuarios = new ArrayList<>();
        
        Usuario u = new Usuario();
        u.setCodigo(112);
        u.setLogin("Welrypertubador");
        u.setEmail("wpertubador123@gmail.com");
        u.setCidade("Recife");
        u.setBairro("Yellow House");
        u.setSenha("1234");
        u.setContato("1233123");
        
        usuarios.add(u);
        
        u = new Usuario();
        
        u.setCodigo(331);
        u.setLogin("Andreestudioso");
        u.setEmail("andredosestudos@gmail.com");
        u.setCidade("Recife");
        u.setBairro("Sands");
        u.setContato("12313132");
        u.setSenha("1234");
        
        usuarios.add(u);
    }
    
    public static void create(Usuario u){
        usuarios.add(u);
    }
    
    public static void update(Usuario u){
        
        for(Usuario uAux: usuarios){
            if(uAux.getCodigo() == u.getCodigo()){
                uAux.setBairro(u.getBairro());
                uAux.setCidade(u.getCidade());
                uAux.setContato(u.getContato());
                uAux.setEmail(u.getEmail());
                uAux.setSenha(u.getSenha());
                return;
            }
        }
        
    }
    
    public static Usuario read(int codigo){
        
        for(Usuario uAux: usuarios){
            if(uAux.getCodigo()==codigo){
                return uAux;
            }
        }
        return null;
    }
    
    public static void delete(Usuario u){
        usuarios.remove(u);
    }
    
    public static List<Usuario> readAll(){
        return usuarios;
    }
    
    
    
}
