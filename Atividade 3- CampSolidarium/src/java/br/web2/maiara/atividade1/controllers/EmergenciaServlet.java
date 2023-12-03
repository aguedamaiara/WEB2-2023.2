package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Emergencia;
import br.web2.maiara.atividade1.repositorios.RepositorioEmergencia;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmergenciaServlet", urlPatterns = {"/EmergenciaServlet"})
public class EmergenciaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("c");
        String operacao = request.getParameter("op");

        if (codigo != null) {

            Emergencia emergencia = RepositorioEmergencia.read(Integer.parseInt(codigo));

            if (operacao != null && operacao.equals("deletar")) {
                RepositorioEmergencia.delete(emergencia);

                request.setAttribute("msg", "Emergência deletada com sucesso!");

                List<Emergencia> emergencias = RepositorioEmergencia.readAll();

                HttpSession session = request.getSession();

                session.setAttribute("emergencias", emergencias);

                request.getRequestDispatcher("/emergencia.jsp").forward(request, response);

            } else {
               
                if (operacao == null || !operacao.equals("editar")) {
                    request.getRequestDispatcher("/emergencia.jsp").forward(request, response);
                } else {
                    request.setAttribute("emergencia", emergencia); 
                    request.getRequestDispatcher("/editarEmergencia.jsp").forward(request, response);
                }
            }
        }

        List<Emergencia> emergencias = RepositorioEmergencia.readAll();

        HttpSession session = request.getSession();

        session.setAttribute("emergencias", emergencias);

        response.sendRedirect("emergencia.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacao = request.getParameter("op");

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String local = request.getParameter("local");
        Emergencia.TipoEmergencia tipo = Emergencia.TipoEmergencia.valueOf(request.getParameter("tipo"));
        String descricao = request.getParameter("descricao");

        // Criando
        Emergencia emergencia = new Emergencia();

        emergencia.setCodigo(codigo);
        emergencia.setLocal(local);
        emergencia.setTipo(tipo);
        emergencia.setDescricao(descricao);

        if (operacao.equals("cadastro")) {
            RepositorioEmergencia.create(emergencia);
        } else {
            RepositorioEmergencia.update(emergencia);
        }

        HttpSession session = request.getSession();
        session.setAttribute("msg", "Emergência "
                + tipo + " cadastrada " + ((operacao.equals("cadastro") ? "cadastrada " : "editada "))
                + " com sucesso!");

        response.sendRedirect("EmergenciaServlet");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}