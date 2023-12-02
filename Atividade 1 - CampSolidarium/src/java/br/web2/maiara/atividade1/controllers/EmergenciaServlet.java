/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Emergencia;
import br.web2.maiara.atividade1.repositorios.RepositorioEmergencia;
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
@WebServlet(name = "EmergenciaServlet", urlPatterns = {"/EmergenciaServlet"})
public class EmergenciaServlet extends HttpServlet {

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
            out.println("<title>Servlet EmergenciaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmergenciaServlet at " + request.getContextPath() + "</h1>");
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

            Emergencia emergencia = RepositorioEmergencia.read(Integer.parseInt(codigo));

            String op = request.getParameter("operacao");

            if (op != null && op.equals("edit")) {

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet EmergenciaServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Editar Emergência</h1>");

                    out.println("<form method='post' action='EmergenciaServlet'>");

                    out.println("<input type='hidden' name='codigo' value='" + emergencia.getCodigo() + "'/></br>");
                    out.println("Local:<input type='text' name='local' value='" + emergencia.getLocal() + "'/></br>");
                    out.println("Tipo: <select name='tipo'>");
                    for (Emergencia.TipoEmergencia tipo : Emergencia.TipoEmergencia.values()) {
                        out.print("<option value='" + tipo + "'");
                        if (tipo == emergencia.getTipo()) {
                            out.print(" selected");
                        }
                        out.println(">" + tipo + "</option>");
                    }
                    out.println("</select><br/>");
                    out.println("Descrição:<input type='text' name='descricao' value='" + emergencia.getDescricao() + "'/></br>");

                    out.println("<input type='submit' value='editar'/></br>");
                    out.println("</form>");

                    out.println("<a href='EmergenciaServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
            } else if (op != null && op.equals("delete")) {
                RepositorioEmergencia.delete(emergencia);

                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet EmergenciaServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Emergência Deletada com Sucesso!</h1>");
                    out.println("<a href='EmergenciaServlet'>voltar</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EmergenciaServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Detalhes da Emergência</h1>");
                out.println("<h2>Local: " + emergencia.getLocal() + "</h2>");
                out.println("<h3>Tipo: " + emergencia.getTipo() + "</h3>");
                out.println("<h4>Descrição: " + emergencia.getDescricao() + "</h4>");
                out.println("<a href='EmergenciaServlet'>Voltar</a>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
        }
        List<Emergencia> emergencias = RepositorioEmergencia.readAll();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmergenciaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Emergências Cadastradas</h1>");
            out.println("<a href='index.html'>Home</a>");
            out.println("<table border='1'>");
            out.println("<tr><th>Código</th>"
                    + "<th>Local</th>"
                    + "<th>Ações</th></tr>");

            for (Emergencia emergenciaAux : emergencias) {
                out.println("<tr>");
                out.println("<td>" + emergenciaAux.getCodigo() + "</td>");
                out.println("<td>" + emergenciaAux.getLocal() + "</td>");
                out.println("<td><a href='EmergenciaServlet?codigo=" + emergenciaAux.getCodigo() + "'>Detalhar</a>"
                        + " <a href='EmergenciaServlet?codigo=" + emergenciaAux.getCodigo() + "&operacao=edit'>Editar</a>"
                        + " <a href='EmergenciaServlet?codigo=" + emergenciaAux.getCodigo() + "&operacao=delete'>Deletar</a></td>");
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
        Emergencia emergencia = RepositorioEmergencia.read(codigo);

        emergencia.setLocal(request.getParameter("local"));
        String tipoString = request.getParameter("tipo");
        Emergencia.TipoEmergencia tipo = Emergencia.TipoEmergencia.valueOf(tipoString);
        emergencia.setTipo(tipo);
        emergencia.setDescricao(request.getParameter("descricao"));

        RepositorioEmergencia.update(emergencia);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmergenciaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Emergência Atualizada com Sucesso!</h1>");
            out.println("<a href='EmergenciaServlet'>Voltar</a>");
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
