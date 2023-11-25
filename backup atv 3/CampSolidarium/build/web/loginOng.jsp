<%-- 
    Document   : loginOng
    Created on : Nov 24, 2023, 6:35:39 PM
    Author     : agued
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login de ONG</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <!-- Estilos customizados -->
        <style>
            body {
                background-color: #f8f9fa;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                margin: 0;
            }

            .card {
                max-width: 400px;
                padding: 20px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .form-group {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <c:if test="${sessionScope.ongLogada ne null}">
            <script>
                location.href = 'IndexOng.jsp';
            </script>
        </c:if>


        <h4><i>${sessionScope.msg}</i></h4>

        <div class="card">
            <h2 class="text-center mb-4">Login de ONG</h2>
            <form method="post" action="loginOngServlet">
                <div class="form-group">
                    <label for="cnpj">CNPJ:</label>
                    <input type="text" class="form-control" id="cnpj" placeholder="Digite o CNPJ" required>
                </div>

                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input type="password" class="form-control" id="senha" placeholder="Digite a senha" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Entrar</button>
            </form>

            <div class="text-center mt-3">
                <!-- Adicionando modal de registro -->
                <button type="button" class="btn btn-link" data-toggle="modal" data-target="#registroModal">
                    Ainda não possui uma conta? Registrar-se
                </button>
            </div>
        </div>

        <!-- Modal de Registro -->
        <div class="modal fade" id="registroModal" tabindex="-1" role="dialog" aria-labelledby="registroModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="registroModalLabel">Registro de ONG</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <%@ include file="cadastroOng.jsp" %>
                    </div>


                </div>
            </div>
        </div>

        <!-- Bootstrap JS e jQuery (opcional) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    </body>
</html>
