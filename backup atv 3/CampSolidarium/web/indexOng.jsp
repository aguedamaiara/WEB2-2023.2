<%-- 
    Document   : indexOng
    Created on : Nov 25, 2023, 7:38:25 AM
    Author     : agued
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:directive.include file="menuOng.jsp"/>
         <h1>Bem Vindo ${sessionScope.ongLogada.login}</h1>
       


    </body>
</html>
