/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.recife.controllers;

import br.edu.ifpe.recife.model.negocio.Usuario;
import br.edu.ifpe.recife.model.repositorios.RepositorioUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ALUNO
 */
@WebServlet(name = "CadastroUsuarioServlet", urlPatterns = {"/CadastroUsuarioServlet"})
public class CadastroUsuarioServlet extends HttpServlet {
    

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
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String cidade = request.getParameter("cidade");
        String bairro = request.getParameter("bairro");
        String contato = request.getParameter("contato");
        
        Usuario usuario = new Usuario();
        
        usuario.setCodigo(codigo);
        usuario.setCidade(cidade);
        usuario.setBairro(bairro);
        usuario.setContato(contato);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        
        RepositorioUsuario.create(usuario);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroUsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuario "+login+" Cadastrado com sucesso!</h1>");
            out.println("<a href='CadastroUsuario.html'>Cadastrar novo usuário</a>");
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
