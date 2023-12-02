/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Ong;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioOng {

    public static List<Ong> ongs = null;

    static {
        ongs = new ArrayList<>();
    }

    public static void create(Ong o) {
        ongs.add(o);
    }

    public static void update(Ong o) {

        for (Ong oAux : ongs) {
            if (oAux.getCodigo() == o.getCodigo()) {
                oAux.setNome(o.getNome());
                oAux.setLogin(o.getLogin());
                oAux.setSenha(o.getSenha());
                oAux.setEmail(o.getEmail());
                oAux.setEndereco(o.getEndereco());

                return;
            }
        }
    }

    public static Ong read(int codigo) {
        for (Ong oAux : ongs) {
            if (oAux.getCodigo() == codigo) {
                return oAux;
            }
        }
        return null;
    }

    public static void delete(Ong o) {
        ongs.remove(o);
    }

    public static List<Ong> readAll() {
        return ongs;
    }
}
