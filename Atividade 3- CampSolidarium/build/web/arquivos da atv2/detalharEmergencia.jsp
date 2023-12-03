<%@page import="java.util.List"%>
<%@page import="br.web2.maiara.atividade1.negocio.Emergencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int i = Integer.parseInt(request.getParameter("i"));
            
            List<Emergencia> emergencias = (List)session.getAttribute("emergencias");
               
            Emergencia emergencia = emergencias.get(i);
        %>
        
        <h1>Dados da Emergência <%= emergencia.getLocal()%></h1>
        
        Código: <%= emergencia.getCodigo() %><br/>
        Local: <%= emergencia.getLocal() %><br/>
        Tipo: <%= emergencia.getTipo() %><br/>
        Descrição: <%= emergencia.getDescricao() %><br/>
        
        <a href="emergencia.jsp">voltar</a>
    </body>
</html>