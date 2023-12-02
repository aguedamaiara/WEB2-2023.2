<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="post" action="OngServlet">
            
            <input type="hidden" name="op" value="cadastro"/>
            Codigo: <input type="text" name="codigo"/><br/><br> 
            CNPJ: <input type="text" name="cnpj"/><br/><br> 
            Senha: <input type="password" name="senha"/><br/><br> 
            Nome: <input type="text" name="nome"/><br/><br> 
            Endereco: <input type="text" name="endereco"/><br/><br> 
            Email: <input type="text" name="email"/><br/><br> 
            Login: <input type="text" name="login"/><br/><br> <br> 
            <input type="submit" class="btn btn-primary btn-block" value="cadastrar"/>
            
        </form>
    </body>
</html>