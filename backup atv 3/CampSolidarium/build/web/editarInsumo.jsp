<%@page import="br.web2.maiara.atividade1.negocio.Insumo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page - Edição de Insumo</title>
</head>
<body>

    <h1>Editar Insumo</h1>

    <a href="index.html">home</a><br/>
    <form method="post" action="InsumoServlet">

        <input type="hidden" name="op" value="editar"/>

        <!-- Campo que não deve ser editado -->
        Código: <input type="text" name="codigo" value="${insumo.codigo}" readonly/><br/>

        Nome: <input type="text" name="nome" value="${insumo.nome}"/><br/>
        Marca: <input type="text" name="marca" value="${insumo.marca}"/><br/>
        Categoria: 
        <select name="categoria">
            <option value="ALIMENTO" ${insumo.categoria == 'ALIMENTO' ? 'selected' : ''}>Alimento</option>
            <option value="VESTUARIO" ${insumo.categoria == 'VESTUARIO' ? 'selected' : ''}>Vestuário</option>
            <option value="BRINQUEDO" ${insumo.categoria == 'BRINQUEDO' ? 'selected' : ''}>Brinquedo</option>
            <option value="MEDICAMENTOS" ${insumo.categoria == 'MEDICAMENTOS' ? 'selected' : ''}>Medicamentos</option>
            <option value="HIGIENE" ${insumo.categoria == 'HIGIENE' ? 'selected' : ''}>Higiene</option>
            <option value="MATERIAL_DE_CONSTRUCAO" ${insumo.categoria == 'MATERIAL_DE_CONSTRUCAO' ? 'selected' : ''}>Material de Construção</option>
        </select><br/>

        <input type="submit" value="editar"/>

    </form>
</body>
</html>
