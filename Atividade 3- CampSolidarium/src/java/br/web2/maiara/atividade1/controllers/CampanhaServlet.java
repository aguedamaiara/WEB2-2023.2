/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Campanha;
import br.web2.maiara.atividade1.negocio.Emergencia;
import br.web2.maiara.atividade1.negocio.Insumo;
import br.web2.maiara.atividade1.negocio.Insumo.CategoriaInsumo;
import br.web2.maiara.atividade1.negocio.Ong;
import br.web2.maiara.atividade1.repositorios.RepositorioCampanha;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agued
 */
@WebServlet(name = "CampanhaServlet", urlPatterns = {"/CampanhaServlet"})
public class CampanhaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dataInicioStr = request.getParameter("dataInicio"); 
        String dataFimStr = request.getParameter("dataFim"); 
        String objetivo = request.getParameter("objetivo");
        boolean ativa = request.getParameter("ativa") != null; 
        String localizacao = request.getParameter("localizacao");
        String descricao = request.getParameter("descricao");
        String tipoEmergenciaStr = request.getParameter("tipoEmergencia");
        Emergencia.TipoEmergencia tipoEmergencia = Emergencia.TipoEmergencia.valueOf(tipoEmergenciaStr);

        // Convertendo as strings para objetos Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio;
        Date dataFim;

        try {
            dataInicio = formato.parse(dataInicioStr);
            dataFim = formato.parse(dataFimStr);
        } catch (ParseException e) {
            e.printStackTrace(); 
            return;
        }

        SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd/MM/yyyy");
        String dataInicioFormatada = formatoExibicao.format(dataInicio);
        String dataFimFormatada = formatoExibicao.format(dataFim);
        request.setAttribute("dataInicioFormatada", dataInicioFormatada);
        request.setAttribute("dataFimFormatada", dataFimFormatada);

        // Crie uma instância de Campanha
        Campanha campanha = new Campanha();
        campanha.setDataInicio(dataInicio);
        campanha.setDataFim(dataFim);
        campanha.setObjetivo(objetivo);
        campanha.setAtiva(ativa);
        campanha.setLocalizacao(localizacao);
        campanha.setDescricao(descricao);
        campanha.setTipoEmergencia(tipoEmergencia);

        Emergencia emergencia = new Emergencia();
        emergencia.setTipo(tipoEmergencia);
        
        campanha.setEmergencia(emergencia);

        Ong autor = (Ong) request.getSession().getAttribute("ongLogada");
        campanha.setAutor(autor);

        String[] insumosSelecionados = request.getParameterValues("insumos");

        // Verifica se insumos foram selecionados
        if (insumosSelecionados != null) {
            List<Insumo> insumos = new ArrayList<>();

            for (String categoria : insumosSelecionados) {
                CategoriaInsumo categoriaInsumo = CategoriaInsumo.valueOf(categoria);
                Insumo insumo = new Insumo(); 
                insumo.setCategoria(categoriaInsumo);
                insumos.add(insumo);
            }

            campanha.setInsumos(insumos);
        }

        RepositorioCampanha.addCampanha(campanha);

        List<Campanha> campanhas = RepositorioCampanha.readCampanha(autor);
        request.getSession().setAttribute("campanhas", campanhas);
        request.getSession().setAttribute("msg", "campanha cadastrada com sucesso!");
        response.sendRedirect("indexOng.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
