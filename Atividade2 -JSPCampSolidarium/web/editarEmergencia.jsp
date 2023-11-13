<%@page import="br.web2.maiara.atividade1.negocio.Emergencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page - Edição de Emergência</title>
    </head>
    <body>
    
        <h1>Editar Emergência</h1>

        <a href="index.html">home</a><br/>
        <form method="post" action="EmergenciaServlet">
            
            <input type="hidden" name="op" value="editar"/>
            
            <!-- Campos que não devem ser editados -->
            Código: <input type="text" name="codigo" value="${emergencia.codigo}" readonly/><br/>
            
            Local: <input type="text" name="local" value="${emergencia.local}"/><br/>
            
            Tipo: 
            <select name="tipo">
                <option value="<%= Emergencia.TipoEmergencia.DESLIZAMENTO %>" <%= emergencia.tipo == Emergencia.TipoEmergencia.DESLIZAMENTO ? "selected" : "" %>>Deslizamento</option>
                <option value="<%= Emergencia.TipoEmergencia.ALAGAMENTO %>" <%= emergencia.tipo == Emergencia.TipoEmergencia.ALAGAMENTO ? "selected" : "" %>>Alagamento</option>
                <option value="<%= Emergencia.TipoEmergencia.INCENDIO %>" <%= emergencia.tipo == Emergencia.TipoEmergencia.INCENDIO ? "selected" : "" %>>Incêndio</option>
                <option value="<%= Emergencia.TipoEmergencia.QUEIMADA %>" <%= emergencia.tipo == Emergencia.TipoEmergencia.QUEIMADA ? "selected" : "" %>>Queimada</option>
                <option value="<%= Emergencia.TipoEmergencia.FURACAO %>" <%= emergencia.tipo == Emergencia.TipoEmergencia.FURACAO ? "selected" : "" %>>Furacão</option>
            </select><br/>
            
            Descrição: <textarea name="descricao">${emergencia.descricao}</textarea><br/>
            
            <input type="submit" value="editar"/>
            
        </form>
    </body>
</html>
