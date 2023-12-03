<%-- 
    Document   : vizualizarCampanhas
    Created on : Nov 25, 2023, 3:59:27 PM
    Author     : agued
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:directive.include file="menuOng.jsp"/>
        <div class="container">

            <%
                // Obtém a ONG logada a partir da sessão
                HttpSession sessionObj = request.getSession();
                Ong ongLogada = (Ong) sessionObj.getAttribute("ongLogada");

                // Obtém todas as campanhas registradas pela ONG logada
                List<Campanha> campanhas = RepositorioCampanha.readCampanha(ongLogada);

                // Verifica se há campanhas para exibir
                if (campanhas != null && !campanhas.isEmpty()) {
            %>
            <div class="mt-4">

                <h2>Campanhas Registradas</h2>

                <c:forEach var="campanha" items="${sessionScope.campanhas}">
                    <div class="card mt-3">
                        <div class="card-body">
                            <strong>Código:</strong> ${campanha.codigo}<br>
                            
                            <strong>Data de Início:</strong> ${campanha.dataInicioFormatada}<br>
                            <strong>Data de esperada do fim da campanha:</strong> ${campanha.dataFimFormatada}<br>

                            <strong>Objetivo:</strong> ${campanha.objetivo}<br>
                            <strong>Ativa:</strong> ${campanha.ativa ? 'Sim' : 'Não'}<br>
                            <strong>Localização:</strong> ${campanha.localizacao}<br>
                            <strong>Descrição:</strong> ${campanha.descricao}<br>
                            <strong>ONG autora da campanha: </strong> ${campanha.autor != null ? campanha.autor.getNome() : 'Sem autor'}<br>


                            <strong>Insumos que a campanha precisa:</strong>
                            <c:forEach var="insumo" items="${campanha.insumos}">
                                ${insumo.categoria}<br>
                            </c:forEach>

                            <strong>Tipo de Emergência:</strong> ${campanha.emergencia.tipo}<br>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <%
            } else {
                // Caso não haja campanhas registradas pela ONG logada
            %>
            <p class="mt-4" >Nenhuma campanha registrada pela ONG.</p>
            <% }%>
        </div>

    </body>
</html>