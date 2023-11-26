<%-- 
    Document   : vizualizarCampanhas
    Created on : Nov 25, 2023, 3:59:27 PM
    Author     : agued
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.List"%>
<%@page import="br.web2.maiara.atividade1.negocio.Campanha"%>
<%@page import="br.web2.maiara.atividade1.repositorios.RepositorioCampanha"%>
<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
<%@page import="br.web2.maiara.atividade1.repositorios.RepositorioOng"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="menuOng.jsp"/>
 <h1>Campanhas da ONG</h1>
    
  <% 
        // Obtém a ONG logada a partir da sessão
        HttpSession sessionObj = request.getSession();
        Ong ongLogada = (Ong) sessionObj.getAttribute("ongLogada");
        
        // Obtém todas as campanhas registradas pela ONG logada
        List<Campanha> campanhas = RepositorioCampanha.readCampanha(ongLogada);
        
        // Verifica se há campanhas para exibir
        if (campanhas != null && !campanhas.isEmpty()) {
    %>
            <table border="1">
                <tr>
                    <th>Código</th>
                    <th>Data Início</th>
                    <th>Data Fim</th>
                    <th>Objetivo</th>
                    <th>Ativa</th>
                    <th>Localização</th>
                    <th>Descrição</th>
                </tr>
                <% for (Campanha campanha : campanhas) { %>
                    <tr>
                        <td><%= campanha.getCodigo() %></td>
                        <td><%= campanha.getDataInicio() %></td>
                        <td><%= campanha.getDataFim() %></td>
                        <td><%= campanha.getObjetivo() %></td>
                        <td><%= campanha.isAtiva() %></td>
                        <td><%= campanha.getLocalizacao() %></td>
                        <td><%= campanha.getDescricao() %></td>
                    </tr>
                <% } %>
            </table>
    <% 
        } else {
            // Caso não haja campanhas registradas pela ONG logada
    %>
            <p>Nenhuma campanha registrada pela ONG.</p>
    <% } %>
</body>
</html>