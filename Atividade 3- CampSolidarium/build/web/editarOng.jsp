<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page - Edição de ONG</title>
    </head>
    <body>
    
        <h1>Editar ONG</h1>

        <a href="index.html">home</a><br/>
        <form method="post" action="OngServlet">
            
            <input type="hidden" name="op" value="editar"/>
            
            
               <!-- Campos que não devem ser editados -->
            
            Código: <input type="text" name="codigo" value="${ong.codigo}" readonly/><br/>
            CNPJ: <input type="text" name="cnpj" value="${ong.cnpj}" readonly/><br/>
            Login: <input type="text" name="login" value="${ong.login}" readonly/><br/>
            <br> <br>
            Nome: <input type="text" name="nome" value="${ong.nome}"/><br/>
            Endereco: <input type="text" name="endereco" value="${ong.endereco}"/><br/>
            Email: <input type="text" name="email" value="${ong.email}"/><br/>
            
         
            <input type="submit" value="editar"/>
            
        </form>
    </body>
</html>
