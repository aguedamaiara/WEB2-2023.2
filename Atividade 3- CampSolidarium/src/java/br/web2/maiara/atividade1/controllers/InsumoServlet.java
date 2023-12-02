package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Insumo;
import br.web2.maiara.atividade1.repositorios.RepositorioInsumo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "InsumoServlet", urlPatterns = {"/InsumoServlet"})
public class InsumoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("c");
        String operacao = request.getParameter("op");

        if (codigo != null) {

            Insumo insumo = RepositorioInsumo.read(Integer.parseInt(codigo));

            if (operacao != null && operacao.equals("deletar")) {
                RepositorioInsumo.delete(insumo);

                request.setAttribute("msg", "Insumo deletado com sucesso!");

                List<Insumo> insumos = RepositorioInsumo.readAll();

                HttpSession session = request.getSession();

                session.setAttribute("insumos", insumos);

                request.getRequestDispatcher("/insumo.jsp")
                        .forward(request, response);

            } else {
                // Verifica se a operação é de editar, se sim, encaminha para /editarInsumo.jsp
                // caso contrário, encaminha para /insumo.jsp
                if (operacao == null || !operacao.equals("editar")) {
                    request.getRequestDispatcher("/insumo.jsp").forward(request, response);
                } else {
                    request.setAttribute("insumo", insumo); // Adiciona o Insumo como atributo de solicitação
                    request.getRequestDispatcher("/editarInsumo.jsp").forward(request, response);
                }
            }
        }

        List<Insumo> insumos = RepositorioInsumo.readAll();

        HttpSession session = request.getSession();

        session.setAttribute("insumos", insumos);

        response.sendRedirect("insumo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacao = request.getParameter("op");

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        Insumo.CategoriaInsumo categoria = Insumo.CategoriaInsumo.valueOf(request.getParameter("categoria"));

        // Criando
        Insumo insumo = new Insumo();

        insumo.setCodigo(codigo);
        insumo.setNome(nome);
        insumo.setMarca(marca);
        insumo.setCategoria(categoria);

        if (operacao.equals("cadastro")) {
            RepositorioInsumo.create(insumo);
        } else {
            RepositorioInsumo.update(insumo);
        }

        HttpSession session = request.getSession();
        session.setAttribute("msg", "Insumo "
                + nome + ((operacao.equals("cadastro") ? "cadastrado " : "editado "))
                + " com sucesso!");

        response.sendRedirect("InsumoServlet");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}