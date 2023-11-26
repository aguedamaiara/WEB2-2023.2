
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:directive.include file="menuOng.jsp"/>


        <h2>Criar Nova Campanha</h2>
        <form action = "CampanhaServlet" method="post">
            <!-- Campos do formulário -->
            <label for="dataInicio">Data de Início:</label>
            <input type="text" name="dataInicio" id="dataInicio" placeholder="yyyy-MM-dd" required>

            <label for="dataFim">Data de Fim:</label>
            <input type="text" name="dataFim" id="dataFim" placeholder="yyyy-MM-dd" required>

            <label for="objetivo">Objetivo:</label>
            <input type="text" name="objetivo" id="objetivo" required>

            <label for="ativa">Ativa:</label>
            <input type="checkbox" name="ativa" id="ativa">

            <label for="localizacao">Localização:</label>
            <input type="text" name="localizacao" id="localizacao">

            <label for="descricao">Descrição:</label>
            <textarea name="descricao" id="descricao" rows="4"></textarea>

            <!-- Adicione mais campos conforme necessário -->

            <!--         Botão de envio 
                    <input type="submit" value="Criar Campanha">-->

            <button type="submit" value="Criar Campanha" >inserir campanha</button>

        </form>
    </body>
</html>
