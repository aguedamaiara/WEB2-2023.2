/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Campanha;
import br.web2.maiara.atividade1.negocio.Ong;
import br.web2.maiara.atividade1.repositorios.RepositorioCampanha;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        try {
            // Obtenha os parâmetros do formulário
            String dataInicioStr = request.getParameter("dataInicio");
            String dataFimStr = request.getParameter("dataFim");
            String objetivo = request.getParameter("objetivo");
            boolean ativa = request.getParameter("ativa") != null; // Se checkbox está marcada, será "on"
            String localizacao = request.getParameter("localizacao");
            String descricao = request.getParameter("descricao");

            // Converta as datas de String para Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataInicio = dateFormat.parse(dataInicioStr);
            java.util.Date dataFim = dateFormat.parse(dataFimStr);

            // Crie uma instância de Campanha
            Campanha campanha = new Campanha();
            campanha.setDataInicio(dataInicio);
            campanha.setDataFim(dataFim);
            campanha.setObjetivo(objetivo);
            campanha.setAtiva(ativa);
            campanha.setLocalizacao(localizacao);
            campanha.setDescricao(descricao);

            Ong autor = (Ong) request.getSession().getAttribute("ongLogada");
            campanha.setAutor(autor);

            // Adiciona a campanha ao repositório de campanhas
            RepositorioCampanha.addCampanha(campanha);

            // Envie a lista de campanhas para a página indexOng.jsp
            List<Campanha> campanhas = RepositorioCampanha.readCampanha(autor);
            request.getSession().setAttribute("campanhas", campanhas);

            request.getSession().setAttribute("msg", "campanha cadastrada com sucesso!");

            // Redireciona para a página desejada (ajuste conforme sua estrutura de navegação)
            response.sendRedirect("indexOng.jsp");

        } catch (ParseException ex) {
            Logger.getLogger(CampanhaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}