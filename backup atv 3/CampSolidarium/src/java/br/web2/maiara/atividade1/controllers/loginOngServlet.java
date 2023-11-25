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
        
        request.getSession().removeAttribute("ongLogada");

        response.sendRedirect("loginOng.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            response.sendRedirect("indexOng.jsp"); // Alteração: Redirecionamento para a página principal de ONG

        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
