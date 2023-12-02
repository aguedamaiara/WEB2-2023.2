<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Adicione o CSS do Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
    </head>
    <body>
        <jsp:directive.include file="menuOng.jsp"/>

        <div class="container mt-4"> <!-- Adicione a classe de contêiner do Bootstrap -->
            <h2>Criar Nova Campanha</h2>
            <form action="CampanhaServlet" method="post">

                <!-- Data de Início -->
                <div class="form-group">
                    <label for="dataInicio">Data de Início:</label>
                    <input type="date" class="form-control" name="dataInicio" id="dataInicio" required>
                </div>

                <!-- Data de Fim -->
                <div class="form-group">
                    <label for="dataFim">Data de Fim:</label>
                    <input type="date" class="form-control" name="dataFim" id="dataFim" required>
                </div>

                <!-- Objetivo -->
                <div class="form-group">
                    <label for="objetivo">Objetivo:</label>
                    <input type="text" class="form-control" name="objetivo" id="objetivo" required>
                </div>

                <!-- Ativa -->
                <div class="form-group">
                    <label for="ativa">Ativa:</label>
                    <input type="checkbox" name="ativa" id="ativa">
                </div>

                <!-- Localização -->
                <div class="form-group">
                    <label for="localizacao">Localização:</label>
                    <input type="text" class="form-control" name="localizacao" id="localizacao">
                </div>

                <!-- Descrição -->
                <div class="form-group">
                    <label for="descricao">Descrição:</label>
                    <textarea class="form-control" name="descricao" id="descricao" rows="4"></textarea>
                </div>

                <!-- Insumos -->
                <fieldset class="form-group">
                    <legend>Selecione os Insumos que a campanha precisa:</legend>
                 
                    <label><input type="checkbox" name="insumos" value="ALIMENTO"> Alimento</label>
                    <label><input type="checkbox" name="insumos" value="VESTUARIO"> Vestuário</label>
                    <label><input type="checkbox" name="insumos" value="BRINQUEDO"> Brinquedo</label>
                    <label><input type="checkbox" name="insumos" value="MEDICAMENTOS"> Medicamentos</label>
                    <label><input type="checkbox" name="insumos" value="HIGIENE"> Higiene</label>
                    <label><input type="checkbox" name="insumos" value="MATERIAL_DE_CONSTRUCAO"> Material de Construção</label>
                </fieldset>

                <!-- Tipo de Emergência -->
                <div class="form-group">
                    <label for="tipoEmergencia">Selecione o tipo de Emergência que esta campanha quer ajudar:</label>
                    <select class="form-control" name="tipoEmergencia" required>
                          <option value="DESLIZAMENTO">Deslizamento</option>
                    <option value="ALAGAMENTO">Alagamento</option>
                    <option value="INCENDIO">Incêndio</option>
                    <option value="QUEIMADA">Queimada</option>
                    <option value="FURACAO">Furacão</option>
                    </select>
                </div>

                <!-- Botão de Envio -->
                <button type="submit" class="btn btn-primary">Criar Campanha</button>
            </form>
        </div>

        <!-- Adicione o JS do Bootstrap e o Popper.js (necessário para alguns componentes do Bootstrap) -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
    </body>
</html>
