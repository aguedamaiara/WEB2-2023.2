<%@page import="br.web2.maiara.atividade1.negocio.Insumo"%>
<%@page import="br.web2.maiara.atividade1.negocio.Insumo.CategoriaInsumo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Cadastro de Novo Insumo</h1>

        <a href="index.html">home</a><br/>
        <form method="post" action="InsumoServlet">
            
            <input type="hidden" name="op" value="cadastro"/>
            Código: <input type="text" name="codigo"/><br/>
            Nome: <input type="text" name="nome"/><br/>
            Marca: <input type="text" name="marca"/><br/>
            Categoria: 
            <select name="categoria">
                <option value="<%= CategoriaInsumo.ALIMENTO %>">Alimento</option>
                <option value="<%= CategoriaInsumo.VESTUARIO %>">Vestuário</option>
                <option value="<%= CategoriaInsumo.BRINQUEDO %>">Brinquedo</option>
                <option value="<%= CategoriaInsumo.MEDICAMENTOS %>">Medicamentos</option>
                <option value="<%= CategoriaInsumo.HIGIENE %>">Higiene</option>
                <option value="<%= CategoriaInsumo.MATERIAL_DE_CONSTRUCAO %>">Material de Construção</option>
            </select><br/>
            <input type="submit" value="cadastrar"/>
            
        </form>
    </body>
</html>