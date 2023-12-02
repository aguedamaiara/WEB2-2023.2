<%-- 
    Document   : detalharCampanha
    Created on : Dec 2, 2023, 9:31:31 AM
    Author     : agued
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <!-- Adicione os links do Bootstrap abaixo -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:directive.include file="menuUsuarioComum.jsp"/>
        <div class="container">

            <%
                List<Campanha> campanhasOrdenadas = RepositorioCampanha.readCampanhaOrdenada();
                request.setAttribute("campanhasOrdenadas", campanhasOrdenadas);

                // Verifica se há campanhas para exibir
                if (campanhasOrdenadas != null && !campanhasOrdenadas.isEmpty()) {
            %>
            <div class="mt-4">

                <h2>Campanhas Registradas</h2>

                <c:forEach var="campanha" items="${campanhasOrdenadas}">
                    <div class="card mt-3">
                        <div class="card-body">
                            <strong>Código:</strong> ${campanha.codigo}<br>

                            <strong>Data de Início:</strong> ${campanha.getDataInicioFormatada()}<br>
                            <strong>Data de esperada do fim da campanha:</strong> ${campanha.getDataFimFormatada()}<br>

                            <strong>Objetivo:</strong> ${campanha.objetivo}<br>
                            <strong>Ativa:</strong> ${campanha.ativa ? 'Sim' : 'Não'}<br>
                            <strong>Localização:</strong> ${campanha.localizacao}<br>
                            <strong>Descrição:</strong> ${campanha.descricao}<br>
                            <strong>ONG autora da campanha: </strong> ${campanha.autor != null ? campanha.autor.getNome() : 'Sem autor'}<br>


                            <strong>Insumos que a campanha precisa:</strong>
                            <c:forEach var="insumo" items="${campanha.insumos}">
                                ${insumo.categoria}<br>
                                <!-- Adicione mais campos conforme necessário -->
                            </c:forEach>
                            <!-- Adicione mais campos conforme necessário -->

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