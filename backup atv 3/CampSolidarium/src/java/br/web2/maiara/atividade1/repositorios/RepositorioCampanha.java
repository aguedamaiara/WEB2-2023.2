/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Campanha;
import br.web2.maiara.atividade1.negocio.Ong;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioCampanha {

    public static List<Campanha> campanhas = null;

    static {
        campanhas = new ArrayList<>();
    }

    public static void creat(Campanha c) {
        campanhas.add(c);
    }

    public static void update(Campanha c) {
        for (Campanha cAux : campanhas) {
            if (cAux.getCodigo() == c.getCodigo()) {
                cAux.setDataInicio(c.getDataInicio());
                cAux.setDataFim(c.getDataFim());
                cAux.setObjetivo(c.getObjetivo());
                cAux.setAtiva(c.isAtiva());
                cAux.setLocalizacao(c.getLocalizacao());
                cAux.setDescricao(c.getDescricao());
                return;

            }
        }
    }

    public static Campanha read(int codigo) {
        for (Campanha cAux : campanhas) {
            if (cAux.getCodigo() == codigo) {
                return cAux;
            }
        }
        return null;
    }

    public static void delete(Campanha c) {
        campanhas.remove(c);
    }

    public static List<Campanha> readAll() {
        return campanhas;
    }

    public static List<Campanha> readCampanha(Ong ong) {

        List<Campanha> leitura = new ArrayList();

        for (Campanha c : campanhas) {
            if (c.getAutor().getCodigo() == ong.getCodigo()) {
                leitura.add(c);
            }
        }
        return leitura;
    }
}
