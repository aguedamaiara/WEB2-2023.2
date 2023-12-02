/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Adm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioAdm {
    
 public static List<Adm> admins = null;

    static {
        admins = new ArrayList<>();
    }

    public static void create(Adm adm) {
        admins.add(adm);
    }

    public static void update(Adm adm) {
        for (Adm admAux : admins) {
            if (admAux.getLogin().equals(adm.getLogin())) {
                admAux.setSenha(adm.getSenha());
                return;
            }
        }
    }

    public static Adm read(String login) {
        for (Adm admAux : admins) {
            if (admAux.getLogin().equals(login)) {
                return admAux;
            }
        }
        return null;
    }

    public static void delete(Adm adm) {
        admins.remove(adm);
    }

    public static List<Adm> readAll() {
        return admins;
    }
}
