<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
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
            
            List<Ong> ongs = (List)session.getAttribute("ongs");
               
            Ong ong = ongs.get(i);
        %>
        
        <h1>Dados da ONG <%= ong.getLogin()%></h1>
        
        CNPJ: <%= ong.getCnpj() %><br/>
        Nome: <%= ong.getNome() %><br/>
        Endereço: <%= ong.getEndereco() %><br/>
        E-mail: <%= ong.getEmail() %><br/>
        Login: <%= ong.getLogin() %><br/>
        
        <a href="ongs.jsp">voltar</a>
    </body>
</html>
