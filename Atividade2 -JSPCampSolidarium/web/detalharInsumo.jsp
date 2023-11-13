<%@page import="br.web2.maiara.atividade1.negocio.Insumo"%>
<%@page import="java.util.List"%>
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
            
            List<Insumo> insumos = (List)session.getAttribute("insumos");
               
            Insumo insumo = insumos.get(i);
        %>
        
        <h1>Dados do Insumo <%= insumo.getNome()%></h1>
        
        Código: <%= insumo.getCodigo() %><br/>
        Nome: <%= insumo.getNome() %><br/>
        Marca: <%= insumo.getMarca() %><br/>
        Categoria: <%= insumo.getCategoria() %><br/>
        
        <a href="insumo.jsp">voltar</a>
    </body>
</html>
