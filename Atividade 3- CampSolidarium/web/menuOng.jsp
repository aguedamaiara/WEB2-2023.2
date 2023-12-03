<%-- 
    Document   : menuOng
    Created on : Nov 25, 2023, 7:49:29 AM
    Author     : agued
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${sessionScope.ongLogada eq null}">
            <c:redirect url="loginOng.jsp"/>
        </c:if>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.html">CampSolidarium</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="indexOng.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="campanha.jsp">Registrar uma Nova Campanha</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="vizualizarCampanhas.jsp">Ver detalhes das campanhas registradas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="loginOngServlet?op=logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <p class="h3">${sessionScope.msg}</p>
        <c:remove var="msg" scope="session"/>
    </body>
</html>
