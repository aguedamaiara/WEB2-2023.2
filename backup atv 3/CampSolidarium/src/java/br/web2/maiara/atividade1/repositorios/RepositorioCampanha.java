/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.repositorios;

import br.web2.maiara.atividade1.negocio.Campanha;
import br.web2.maiara.atividade1.negocio.Ong;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agued
 */
public class RepositorioCampanha {

    public static List<Campanha> campanhas = new ArrayList<>();
    private static int lastCampanha = 1;
    //private static int lastEmergencia = 1;

    public static List<Campanha> readCampanha(Ong ong) {
        List<Campanha> leitura = new ArrayList<>();

        for (Campanha c : campanhas) {
            if (c.getAutor() != null && c.getAutor().getCodigo() == ong.getCodigo()) {
                leitura.add(c);
            }
        }

        return leitura;
    }

    public static void addCampanha(Campanha campanha) {
        campanha.setCodigo(lastCampanha++);
        Ong autor = campanha.getAutor();
        
        if(autor!=null){
            campanha.setAutor(autor);
        }
        campanhas.add(campanha);
    }



}
