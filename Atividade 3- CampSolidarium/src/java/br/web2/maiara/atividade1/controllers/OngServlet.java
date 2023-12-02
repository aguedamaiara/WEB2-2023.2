package br.web2.maiara.atividade1.controllers;

import br.web2.maiara.atividade1.negocio.Ong;
import br.web2.maiara.atividade1.repositorios.RepositorioOng;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agued
 */
@WebServlet(name = "OngServlet", urlPatterns = {"/OngServlet"})
public class OngServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("c");
        String operacao = request.getParameter("op");

        if (codigo != null) {

            Ong o = RepositorioOng.read(Integer.parseInt(codigo));

            if (operacao != null && operacao.equals("deletar")) {
                RepositorioOng.delete(o);

                request.setAttribute("msg", "ong deletado com sucesso!");

                List<Ong> ongs = RepositorioOng.readAll();

                HttpSession session = request.getSession();

                session.setAttribute("ongs", ongs);

                request.getRequestDispatcher("/ongs.jsp")
                        .forward(request, response);

            } else {
                // Verifica se a operação é de editar, se sim, encaminha para /editarOng.jsp
                // caso contrário, encaminha para /ongs.jsp
                if (operacao == null || !operacao.equals("editar")) {
                    request.getRequestDispatcher("/ongs.jsp").forward(request, response);
                } else {
                    request.setAttribute("ong", o); // Adiciona a ONG como atributo de solicitação
                    request.getRequestDispatcher("/editarOng.jsp").forward(request, response);
                }
            }
        }

        List<Ong> ongs = RepositorioOng.readAll();

        HttpSession session = request.getSession();

        session.setAttribute("ongs", ongs);

        response.sendRedirect("ongs.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operacao = request.getParameter("op");

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

        if (operacao.equals("cadastro")) {
            RepositorioOng.create(ong);
        } else {
            RepositorioOng.update(ong);
        }

        HttpSession session = request.getSession();
        session.setAttribute("msg", "Ong "
                + login + ((operacao.equals("cadastro") ? "cadastrado " : "editado "))
                + " com sucesso!");

       // response.sendRedirect("OngServlet");

        request.getSession().setAttribute("msg","Registro realizado com sucesso!");
        
        response.sendRedirect("loginOng.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
