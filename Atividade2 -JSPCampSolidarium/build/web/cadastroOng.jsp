<%@page import="br.web2.maiara.atividade1.negocio.Ong"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Cadastro de Nova ONG</h1>

        <a href="index.html">home</a><br/>
        <form method="post" action="OngServlet">
            
            <input type="hidden" name="op" value="cadastro"/>
            Codigo: <input type="text" name="codigo"/><br/>
            CNPJ: <input type="text" name="cnpj"/><br/>
            Senha: <input type="password" name="senha"/><br/>
            Nome: <input type="text" name="nome"/><br/>
            Endereco: <input type="text" name="endereco"/><br/>
            Email: <input type="text" name="email"/><br/>
            Login: <input type="text" name="login"/><br/>
            <input type="submit" value="cadastrar"/>
            
        </form>
    </body>
</html>
