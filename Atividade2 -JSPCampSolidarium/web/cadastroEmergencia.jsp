<%@page import="br.web2.maiara.atividade1.negocio.Emergencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Cadastro de Nova Emergência</h1>

        <a href="index.html">home</a><br/>
        <form method="post" action="EmergenciaServlet">
            
            <input type="hidden" name="op" value="cadastro"/>
            
            Código: <input type="text" name="codigo"/><br/>
            Local: <input type="text" name="local"/><br/>
            Tipo: 
            <select name="tipo">
                <option value="<%= Emergencia.TipoEmergencia.DESLIZAMENTO %>">Deslizamento</option>
                <option value="<%= Emergencia.TipoEmergencia.ALAGAMENTO %>">Alagamento</option>
                <option value="<%= Emergencia.TipoEmergencia.INCENDIO %>">Incêndio</option>
                <option value="<%= Emergencia.TipoEmergencia.QUEIMADA %>">Queimada</option>
                <option value="<%= Emergencia.TipoEmergencia.FURACAO %>">Furacão</option>
            </select><br/>
            Descrição: <textarea name="descricao"></textarea><br/>
            
            <input type="submit" value="cadastrar"/>
            
        </form>
    </body>
</html>
