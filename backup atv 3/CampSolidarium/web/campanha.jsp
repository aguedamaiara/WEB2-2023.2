
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
            <!--            <label for="dataInicio">Data de Início:</label>
                        <input type="text" name="dataInicio" id="dataInicio" placeholder="yyyy-MM-dd" required>
            
                        <label for="dataFim">Data de Fim:</label>
                        <input type="text" name="dataFim" id="dataFim" placeholder="yyyy-MM-dd" required>-->


            <label for="dataInicio">Data de Início:</label>
            <input type="date" name="dataInicio" id="dataInicio" required>

            <label for="dataFim">Data de Fim:</label>
            <input type="date" name="dataFim" id="dataFim" required>
            
            
            <label for="objetivo">Objetivo:</label>
            <input type="text" name="objetivo" id="objetivo" required>

            <label for="ativa">Ativa:</label>
            <input type="checkbox" name="ativa" id="ativa">

            <label for="localizacao">Localização:</label>
            <input type="text" name="localizacao" id="localizacao">

            <label for="descricao">Descrição:</label>
            <textarea name="descricao" id="descricao" rows="4"></textarea>


            <fieldset>
                <legend>Selecione os Insumos que a campanha precisa:</legend>

                <label><input type="checkbox" name="insumos" value="ALIMENTO"> Alimento</label>
                <label><input type="checkbox" name="insumos" value="VESTUARIO"> Vestuário</label>
                <label><input type="checkbox" name="insumos" value="BRINQUEDO"> Brinquedo</label>
                <label><input type="checkbox" name="insumos" value="MEDICAMENTOS"> Medicamentos</label>
                <label><input type="checkbox" name="insumos" value="HIGIENE"> Higiene</label>
                <label><input type="checkbox" name="insumos" value="MATERIAL_DE_CONSTRUCAO"> Material de Construção</label>
                <!-- Adicione mais opções conforme necessário -->
            </fieldset>

            <label for="tipoEmergencia">Selecione o tipo de Emergência que esta campanha quer ajudar:</label>
            <select name="tipoEmergencia" required>
                <option value="DESLIZAMENTO">Deslizamento</option>
                <option value="ALAGAMENTO">Alagamento</option>
                <option value="INCENDIO">Incêndio</option>
                <option value="QUEIMADA">Queimada</option>
                <option value="FURACAO">Furacão</option>

            </select>

            <!--         Botão de envio 
                    <input type="submit" value="Criar Campanha">-->

            <button type="submit" value="Criar Campanha" >criar campanha</button>

        </form>
    </body>
</html>
