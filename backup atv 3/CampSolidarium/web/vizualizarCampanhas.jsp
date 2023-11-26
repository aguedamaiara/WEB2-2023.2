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
      
        
         <h2>Campanhas Registradas</h2>

        <c:forEach var="campanha" items="${sessionScope.campanhas}">
            <div>
                <strong>Código:</strong> ${campanha.codigo}<br>
                <strong>Data de Início:</strong> ${campanha.dataInicio}<br>
                <strong>Data de esperada do fim da campanha:</strong> ${campanha.dataFim}<br>
                <strong>Objetivo:</strong> ${campanha.objetivo}<br>
                <strong>Ativa:</strong> ${campanha.ativa ? 'Sim' : 'Não'}<br>
                <strong>Localização:</strong> ${campanha.localizacao}<br>
                <strong>Descrição:</strong> ${campanha.descricao}<br>
<!--                <strong>Autor:</strong> ${campanha.autor.getNome()}<br>-->
                <strong>ONG autora da campanha: </strong> ${campanha.autor != null ? campanha.autor.getNome() : 'Sem autor'}<br>


                <strong>Insumos que a campanha precisa:</strong>
                <c:forEach var="insumo" items="${campanha.insumos}">
                    ${insumo.categoria}<br>
                    <!-- Adicione mais campos conforme necessário -->
                </c:forEach>
                <!-- Adicione mais campos conforme necessário -->
                
                <strong>Tipo de Emergência:</strong> ${campanha.emergencia.tipo}<br>
            </div>
            <hr>
        </c:forEach>
        
        
        <%
        } else {
            // Caso não haja campanhas registradas pela ONG logada
        %>
        <p>Nenhuma campanha registrada pela ONG.</p>
        <% }%>
    </body>
</html>