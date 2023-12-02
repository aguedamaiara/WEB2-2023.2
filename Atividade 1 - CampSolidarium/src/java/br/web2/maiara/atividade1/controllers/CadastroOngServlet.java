

package br.web2.maiara.atividade1.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.web2.maiara.atividade1.negocio.Ong;
import br.web2.maiara.atividade1.repositorios.RepositorioOng;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author agued
 */
@WebServlet(name = "CadastroOngServlet", urlPatterns = {"/CadastroOngServlet"})
public class CadastroOngServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroOngServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastroOngServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Long cnpj = Long.parseLong(request.getParameter("cnpj"));
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String endereco = request.getParameter("endereco");

        //criando
        Ong ong = new Ong();

        ong.setCodigo(codigo);
        ong.setCnpj(cnpj);
        ong.setSenha(senha);
        ong.setNome(nome);
        ong.setEmail(email);
        ong.setLogin(login);
        ong.setEndereco(endereco);

        RepositorioOng.create(ong);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarOngServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ong " + nome + " Cadastrada com sucesso!</h1>");
            out.println("<a href='CadastroOng.html'>Cadastrar nova ONG</a>");
            out.println("<a href='index.html'>Home</a>");
            out.println("</body>");
            out.println("</html>");

        }
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
