<%-- 
    Document   : indexUsuarioComun.jsp
    Created on : Nov 27, 2023, 9:27:14 AM
    Author     : agued
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>
<%@page import="br.web2.maiara.atividade1.negocio.Campanha"%>
<%@page import="br.web2.maiara.atividade1.repositorios.RepositorioCampanha"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                padding: 20px;
            }

            .campanha-panel {
                max-width: 800px;
                margin: 0 auto;
            }

            .campanha-card {
                margin-bottom: 20px;
            }

            .card-header {
                background-color: #007bff;
                color: #ffffff;
            }

            .card-body {
                padding: 20px;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:directive.include file="menuUsuarioComum.jsp"/>
        <h1>Bem Vindo ${sessionScope.ongLogada.login}</h1>
 
        <%
            List<Campanha> campanhasOrdenadas = RepositorioCampanha.readCampanhaOrdenada();
            request.setAttribute("campanhasOrdenadas", campanhasOrdenadas);

            // Verifica se há campanhas para exibir
            if (campanhasOrdenadas != null && !campanhasOrdenadas.isEmpty()) {
        %>
        <h2>Campanhas Registradas</h2>

        <c:forEach var="campanha" items="${campanhasOrdenadas}">
            <div>
                <strong>Código:</strong> ${campanha.codigo}<br>
                <strong>Data de Início:</strong> ${campanha.getDataInicioFormatada()}<br>
                <strong>Objetivo:</strong> ${campanha.objetivo}<br>
            </div>
            <hr>
        </c:forEach>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <%
        } else {
            // Caso não haja campanhas registradas pela ONG logada
        %>
        <p>Nenhuma campanha registrada pela ONG.</p>
        <% }%>

    </body>
</html>
