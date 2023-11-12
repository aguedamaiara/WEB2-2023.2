package br.web2.maiara.atividade1.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.web2.maiara.atividade1.negocio.Insumo;
import br.web2.maiara.atividade1.repositorios.RepositorioInsumo;
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
@WebServlet(name = "InsumoServlet", urlPatterns = {"/InsumoServlet"})
public class InsumoServlet extends HttpServlet {

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
            out.println("<title>Servlet InsumoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsumoServlet at " + request.getContextPath() + "</h1>");
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

        String codigo = request.getParameter("codigo");

        if (codigo != null) {

            Insumo i = RepositorioInsumo.read(Integer.parseInt(codigo));

            String op = request.getParameter("operacao");

            if (op != null && op.equals("edit")) {

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet InsumoServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Insumo: " + i.getNome() + "</h1>");

                    out.println("<form method='post' action='InsumoServlet'>");

                    out.println("<input type='hidden' name='codigo' value='" + i.getCodigo() + "'/></br>");
                    out.println("NOME:<input type='text' name='nome' value='" + i.getNome() + "'/></br>");
                    out.println("MARCA:<input type='text' name='marca' value='" + i.getMarca() + "'/></br>");
                    out.println("Categoria: <select name='categoria'>");
                    out.println("<option value='ALIMENTO'" + (i.getCategoria() == Insumo.CategoriaInsumo.ALIMENTO ? " selected" : "") + ">Alimento</option>");
                    out.println("<option value='VESTUARIO'" + (i.getCategoria() == Insumo.CategoriaInsumo.VESTUARIO ? " selected" : "") + ">Vestuário</option>");
                    out.println("<option value='BRINQUEDO'" + (i.getCategoria() == Insumo.CategoriaInsumo.BRINQUEDO ? " selected" : "") + ">Brinquedo</option>");
                    out.println("<option value='MEDICAMENTOS'" + (i.getCategoria() == Insumo.CategoriaInsumo.MEDICAMENTOS ? " selected" : "") + ">Medicamentos</option>");
                    out.println("<option value='HIGIENE'" + (i.getCategoria() == Insumo.CategoriaInsumo.HIGIENE ? " selected" : "") + ">Higiene</option>");
                    out.println("<option value='MATERIAL_DE_CONSTRUCAO'" + (i.getCategoria() == Insumo.CategoriaInsumo.MATERIAL_DE_CONSTRUCAO ? " selected" : "") + ">Material de Construção</option>");
                    out.println("</select><br/>");

                    out.println("<input type='submit' value='editar'/></br>");
                    out.println("</form>");

                    out.println("<a href='InsumoServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                    return;

                }

            } else if (op != null && op.equals("delete")) {

                RepositorioInsumo.delete(i);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet InsumoServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Insumo Deletado com Sucesso!</h1>");
                    out.println("<a href='InsumoServlet'>voltar</a>");
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
                out.println("<title>Servlet InsumoServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Insumos Cadastrada=os</h1>");
                out.println("<h2> Detalhes do Insumo: " + i.getNome() + "</h2>");
                out.println("<h3>Marca: " + i.getMarca() + "</h3>");
                out.println("<h4>Categoria: " + i.getCategoria() + "</h4>");
                out.println("<a href='InsumoServlet'>Voltar</a>");
                out.println("</body>");
                out.println("</html>");

                return;
            }
        }
        List<Insumo> insumos = RepositorioInsumo.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsumoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>InsumoServlet Cadastrados</h1>");
            out.println("<a href='index.html'>home</a>");
            out.println("<table border='1'>");
            
            out.println("<tr><th>Código</th>"
                    + "<th>nome</th>"
                    + "<th> Ações </th></tr>");

            for (Insumo iAux : insumos) {
                out.println("<tr>");
                out.println("<td>" + iAux.getCodigo() + "</td>");
                out.println("<td>" + iAux.getNome() + "</td>");

                out.println("<td><a href='InsumoServlet?codigo=" + iAux.getCodigo() + "'>detalhar</a>"
                        + " <a href='InsumoServlet?codigo=" + iAux.getCodigo() + "&operacao=edit'>editar</a>"
                        + " <a href='InsumoServlet?codigo=" + iAux.getCodigo() + "&operacao=delete'>deletar</a></td>");
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

        Insumo i = RepositorioInsumo.read(codigo);

        i.setNome(request.getParameter("nome"));
        i.setMarca(request.getParameter("marca"));
        
        // Mapeie a String da categoria para o valor de CategoriaInsumo
        String categoriaString = request.getParameter("categoria");
        Insumo.CategoriaInsumo categoria = Insumo.CategoriaInsumo.valueOf(categoriaString);

        i.setCategoria(categoria);
        
        RepositorioInsumo.update(i);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsumoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Insumo Atualizado com Sucesso!</h1>");
            out.println("<a href='InsumoServlet'>voltar</a>");
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
