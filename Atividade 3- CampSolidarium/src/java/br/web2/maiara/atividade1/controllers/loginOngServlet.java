/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Campanha;
import br.web2.maiara.atividade1.negocio.Ong;
import br.web2.maiara.atividade1.repositorios.RepositorioCampanha;
import br.web2.maiara.atividade1.repositorios.RepositorioOng;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "loginOngServlet", urlPatterns = {"/loginOngServlet"})
public class loginOngServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("op");

        if (op != null && op.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("loginOng.jsp");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long cnpj = Long.parseLong(request.getParameter("cnpj"));
        String senha = request.getParameter("senha");

        Ong ong = RepositorioOng.realizarLogin(cnpj, senha);

        if (ong == null) {

            request.getSession().setAttribute("msg", "O login falhou!");

            response.sendRedirect("loginOng.jsp");
            return;

        }

        request.getSession().setAttribute("ongLogada", ong);

        List<Campanha> campanhas = RepositorioCampanha.readCampanha(ong);

        request.getSession().setAttribute("campanhas", campanhas);

        response.sendRedirect("indexOng.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
