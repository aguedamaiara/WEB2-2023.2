<%-- 
    Document   : ongs
    Created on : Nov 12, 2023, 4:25:54 PM
    Author     : agued
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ongs Cadastradas</h1>

        <%
            List<Ong> ongs = (List) session.getAttribute("ongs");
        %>
        <h2>${msg}</h2>

        <%
            session.removeAttribute("msg");
        %>  

        <table border="1">

            <tr><th>Login</th><th>e-mail</th><th>Endereco</th><th>Ações</th></tr>

            <%
                for (Ong o : ongs) {
            %>

            <tr>
                <td>
                    <%= o.getLogin()%>
                </td>
                <td>
                    <%= o.getEmail()%>
                </td>
                <td>
                    <%=o.getEndereco()%>
                </td>
                <td>
                    <a href="detalharOng.jsp?i=<%= ongs.indexOf(o)%>">detalhar </a>
                    <a href="OngServlet?c=<%= o.getCodigo()%>&op=editar">editar </a>
                    <a href="OngServlet?c=<%= o.getCodigo()%>&op=deletar">deletar</a>
                </td>
            </tr>

            <%
                }
            %>

        </table>
        <a href="index.html">home</a><br/>

    </body>
</html>