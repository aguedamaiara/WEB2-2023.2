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
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/UsuariosServlet"})
public class UsuariosServlet extends HttpServlet {

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
            out.println("<title>Servlet UsuariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuariosServlet at " + request.getContextPath() + "</h1>");
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

            Usuario u = RepositorioUsuario.read(Integer.parseInt(codigo));

            String op = request.getParameter("operacao");

            if (op != null && op.equals("edit")) {

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet UsuariosServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Usuário: " + u.getLogin() + "</h1>");

                    out.println("<form method='post' action='UsuariosServlet'>");

                    out.println("<input type='hidden' name='codigo' value='" + u.getCodigo() + "'/></br>");
                    out.println("e-mail:<input type='text' name='email' value='" + u.getEmail() + "'/></br>");
                    out.println("Cidade:<input type='text' name='cidade' value='" + u.getCidade() + "'/></br>");
                    out.println("Bairro:<input type='text' name='bairro' value='" + u.getBairro() + "'/></br>");
                    out.println("Contato:<input type='text' name='contato' value='" + u.getContato() + "'/></br>");
                    out.println("<input type='submit' value='editar'/></br>");
                    out.println("</form>");

                    out.println("<a href='UsuariosServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                    return;

                }
            }else if(op != null && op.equals("delete")){
                
                RepositorioUsuario.delete(u);
                
                response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuário Deletado com Sucesso!</h1>");
            out.println("<a href='UsuariosServlet'>voltar</a>");
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
                out.println("<title>Servlet UsuariosServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Detalhes do usuário: " + u.getLogin() + "</h1>");
                out.println("<h5>e-mail:" + u.getEmail() + "</h5>");
                out.println("<h5>Cidade:" + u.getCidade() + "</h5>");
                out.println("<h5>Bairro:" + u.getBairro() + "</h5>");
                out.println("<h5>Telefone de Contato:" + u.getContato() + "</h5>");
                out.println("<a href='UsuariosServlet'>voltar</a>");
                out.println("</body>");
                out.println("</html>");

                return;
            }

        }

        List<Usuario> usuarios = RepositorioUsuario.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuários Cadatrados</h1>");
            out.println("<a href='index.html'>home</a>");
            out.println("<table border='1'>");
            out.println("<tr><th>Código</th>"
                    + "<th>Login</th>"
                    + "<th>e-mail</th>"
                    + "<th>Telefone Contato</th>"
                    + "<th>ações</th></tr>");

            for (Usuario uAux : usuarios) {
                out.println("<tr>");
                out.println("<td>" + uAux.getCodigo() + "</td>");
                out.println("<td>" + uAux.getLogin() + "</td>");
                out.println("<td>" + uAux.getEmail() + "</td>");
                out.println("<td>" + uAux.getContato() + "</td>");
                out.println("<td><a href='UsuariosServlet?codigo="
                        + uAux.getCodigo() + "'>detalhar</a> "
                        + " <a href='UsuariosServlet?codigo="
                        + uAux.getCodigo() + "&operacao=edit'>editar</a>"
                                + " <a href='UsuariosServlet?codigo="
                        +uAux.getCodigo()+"&operacao=delete'>deletar</a></td>");
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

        Usuario u = RepositorioUsuario.read(codigo);

        u.setCidade(request.getParameter("cidade"));
        u.setEmail(request.getParameter("email"));
        u.setBairro(request.getParameter("bairro"));
        u.setContato(request.getParameter("contato"));

        RepositorioUsuario.update(u);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Usuário Atualizado com Sucesso!</h1>");
            out.println("<a href='UsuariosServlet'>voltar</a>");
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
