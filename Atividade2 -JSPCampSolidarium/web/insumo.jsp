<%-- 
    Document   : insumos
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
        <h1>Insumos Cadastrados</h1>

        <%
            List<Insumos> insumos = (List) session.getAttribute( insumos");
        %>
        <h2>${msg}</h2>

        <%
            session.removeAttribute("msg");
        %>  

        <table border="1">

            <tr><th>Código</th><th>Nome</th><th>Ações</th></tr>

            <%
                for (Insumo insumo : insumos) {
            %>

            <tr>
                <td>
                    <%= insumo.getCodigo()%>
                </td>
                <td>
                    <%= insumo.getNome()%>
                </td> 
                <td>
                    <a href="detalharInsumo.jsp?i=<%= insumos.indexOf(insumo)%>">detalhar </a>
                    <a href="InsumoServlet?c=<%= insumo.getCodigo()%>&op=editar">editar </a>
                    <a href="InsumoServlet?c=<%= insumo.getCodigo()%>&op=deletar">deletar</a>
                </td>
            </tr>


            <%
                }
            %>

        </table>


    </body>
</html>