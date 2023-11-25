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
            Código: <input type="text" name="codigo" value="${emergencia != null ? emergencia.codigo : ''}" readonly/><br/>

            Local: <input type="text" name="local" value="${emergencia != null ? emergencia.local : ''}"/><br/>

            Tipo: 
            <select name="tipo">
                <option value="DESLIZAMENTO" ${emergencia != null && emergencia.tipo == 'DESLIZAMENTO' ? 'selected' : ''}>Deslizamento</option>
                <option value="ALAGAMENTO" ${emergencia != null && emergencia.tipo == 'ALAGAMENTO' ? 'selected' : ''}>Alagamento</option>
                <option value="INCENDIO" ${emergencia != null && emergencia.tipo == 'INCENDIO' ? 'selected' : ''}>Incêndio</option>
                <option value="QUEIMADA" ${emergencia != null && emergencia.tipo == 'QUEIMADA' ? 'selected' : ''}>Queimada</option>
                <option value="FURACAO" ${emergencia != null && emergencia.tipo == 'FURACAO' ? 'selected' : ''}>Furacão</option>
            </select><br/>


            Descrição: <textarea name="descricao">${emergencia != null ? emergencia.descricao : ''}</textarea><br/>

            <input type="submit" value="editar"/>

        </form>
    </body>
</html>
