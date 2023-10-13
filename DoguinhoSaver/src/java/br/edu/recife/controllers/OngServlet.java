/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.recife.controllers;

import br.edu.ifpe.recife.model.negocio.Ong;
import br.edu.ifpe.recife.model.repositorios.RepositorioOng;
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
 * @author ALUNO
 */
@WebServlet(name = "OngServlet", urlPatterns = {"/OngServlet"})
public class OngServlet extends HttpServlet {

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
            out.println("<title>Servlet OngServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OngServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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

        String codigo = request.getParameter("codigo");

        if (codigo != null) {

            Ong o = RepositorioOng.read(Integer.parseInt(codigo));

            String op = request.getParameter("operacao");

            if (op != null && op.equals("edit")) {

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet OngServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Ong: " + o.getNome() + "</h1>");

                    out.println("<form method='post' action='OngServlet'>");

                    out.println("<input type='hidden' name='codigo' value='" + o.getCodigo() + "'/></br>");
                    out.println("e-mail:<input type='text' name='email' value='" + o.getEmail() + "'/></br>");
                    out.println("Endereço:<input type='text' name='endereco' value='" + o.getEndereco() + "'/></br>");
                    out.println("Contato:<input type='text' name='contato' value='" + o.getTelefone() + "'/></br>");
                    out.println("Especialidade:<input type='text' name='especialidade' value='" + o.getEspecialidade() + "'/></br>");
                    out.println("<input type='submit' value='editar'/></br>");
                    out.println("</form>");

                    out.println("<a href='OngServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                    return;

                }
            } else if (op != null && op.equals("delete")) {

                RepositorioOng.delete(o);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet OngServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Ong Deletado com Sucesso!</h1>");
                    out.println("<a href='OngServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OngServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Ongs Cadastradas</h1>");
                out.println("<h2> Detalhes da ONG: " + o.getNome() + "</h2>");
                out.println("<h3>Endereco: " + o.getEndereco() + "</h3>");
                out.println("<h4>Telefone: " + o.getTelefone() + "</h4>");
                out.println("<h5>Especialidade: " + o.getEspecialidade() + "</h5>");
                out.println("<a href='OngServlet'>Voltar</a>");
                out.println("</body>");
                out.println("</html>");

                return;
            }
        }

        List<Ong> ongs = RepositorioOng.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OngServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ServletOngs Cadastradas</h1>");
            out.println("<a href='index.html'>home</a>");
            out.println("<table border='1'>");
            out.println("<tr><th>Código</th>"
                    + "<th>nome</th>"
                    + "+<th>CNPJ</th>"
                    + "<th>e-mail</th>"
                    + "<th>Telefone Contato</th>"
                    + "<th> Ações </th></tr>");

            for (Ong oAux : ongs) {
                out.println("<tr>");
                out.println("<td>" + oAux.getCodigo() + "</td>");
                out.println("<td>" + oAux.getNome() + "</td>");
                out.println("<td>" + oAux.getCnpj() + "</td>");
                out.println("<td>" + oAux.getNome() + "</td>");
                out.println("<td>" + oAux.getTelefone() + "</td>");
                
                out.println("<td><a href='OngServlet?codigo="  + oAux.getCodigo() + "'>detalhar</a>"
                        + " <a href='OngServlet?codigo=" + oAux.getCodigo() + "&operacao=edit'>editar</a>"
                        + " <a href='OngServlet?codigo=" + oAux.getCodigo() + "&operacao=delete'>deletar</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }

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

        Ong o = RepositorioOng.read(codigo);

        o.setEmail(request.getParameter("email"));
        o.setEndereco(request.getParameter("endereco"));
        o.setEspecialidade(request.getParameter("especialidade"));
        o.setTelefone(request.getParameter("telefone"));

        RepositorioOng.update(o);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OngServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ong Atualizada com Sucesso!</h1>");
            out.println("<a href='OngServlet'>voltar</a>");
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
