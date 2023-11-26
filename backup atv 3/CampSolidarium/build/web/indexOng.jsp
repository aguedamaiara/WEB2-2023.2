<%-- 
    Document   : indexOng
    Created on : Nov 25, 2023, 7:38:25 AM
    Author     : agued
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <!-- Estilos customizados -->
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

        <jsp:directive.include file="menuOng.jsp"/>
        <h1>Bem Vindo ${sessionScope.ongLogada.login}</h1>


        <h2>Campanhas Registradas</h2>

        <c:forEach var="campanha" items="${sessionScope.campanhas}">
            <div>
                <strong>Código:</strong> ${campanha.codigo}<br>
                <strong>Data de Início:</strong> ${campanha.dataInicio}<br>
                <strong>Data de Fim:</strong> ${campanha.dataFim}<br>
                <strong>Objetivo:</strong> ${campanha.objetivo}<br>
                <strong>Ativa:</strong> ${campanha.ativa ? 'Sim' : 'Não'}<br>
                <strong>Localização:</strong> ${campanha.localizacao}<br>
                <strong>Descrição:</strong> ${campanha.descricao}<br>
<!--                <strong>Autor:</strong> ${campanha.autor.getNome()}<br>-->
                <strong>ONG autora da campanha: </strong> ${campanha.autor != null ? campanha.autor.getNome() : 'Sem autor'}<br>

                <!-- Adicione mais campos conforme necessário -->
            </div>
            <hr>
        </c:forEach>

        <!-- Bootstrap JS e jQuery (opcional) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>



    </body>
</html>
