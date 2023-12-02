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

        String dataInicioStr = request.getParameter("dataInicio"); // Substitua pelo nome real do campo no formulário
        String dataFimStr = request.getParameter("dataFim"); // Substitua pelo nome real do campo no formulário

        String objetivo = request.getParameter("objetivo");
        boolean ativa = request.getParameter("ativa") != null; // Se checkbox está marcada, será "on"
        String localizacao = request.getParameter("localizacao");
        String descricao = request.getParameter("descricao");
        String tipoEmergenciaStr = request.getParameter("tipoEmergencia");
        Emergencia.TipoEmergencia tipoEmergencia = Emergencia.TipoEmergencia.valueOf(tipoEmergenciaStr);

//        // Formata as datas FUNCIONAAAAAAAAAAAAAA
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        Date dataInicio;
//        Date dataFim;


// Convertendo as strings para objetos Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicio;
        Date dataFim;

        try {
            dataInicio = formato.parse(dataInicioStr);
            dataFim = formato.parse(dataFimStr);
        } catch (ParseException e) {
            e.printStackTrace(); // Ou trate a exceção de outra forma, de acordo com sua lógica de negócios
            return;
        }

        // Exemplo de como enviar a data formatada para a página JSP
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

        // criar uma instância de Emergencia e associar à campanha
        Emergencia emergencia = new Emergencia();
        emergencia.setTipo(tipoEmergencia);

        // configurar campos da emergencia
        campanha.setEmergencia(emergencia);

        //Relação com ONG
        Ong autor = (Ong) request.getSession().getAttribute("ongLogada");
        campanha.setAutor(autor);

        // Processa os insumos selecionados 
        String[] insumosSelecionados = request.getParameterValues("insumos");

        // Verifica se insumos foram selecionados
        if (insumosSelecionados != null) {
            List<Insumo> insumos = new ArrayList<>();

            for (String categoria : insumosSelecionados) {
                // Aqui, você precisa criar uma instância de Insumo para cada categoria selecionada
                CategoriaInsumo categoriaInsumo = CategoriaInsumo.valueOf(categoria);
                Insumo insumo = new Insumo(); // Substitua com a construção correta do Insumo
                insumo.setCategoria(categoriaInsumo);
                insumos.add(insumo);
            }

            campanha.setInsumos(insumos);
        }

        // Adiciona a campanha ao repositório de campanhas
        RepositorioCampanha.addCampanha(campanha);

        // Envie a lista de campanhas para a página indexOng.jsp
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
