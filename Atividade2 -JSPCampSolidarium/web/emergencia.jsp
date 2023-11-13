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
        <h1>Emergências Cadastradas</h1>

        <%
            List<Emergencia> emergencias = (List) session.getAttribute("emergencias");
        %>
        <h2>${msg}</h2>

        <%
            session.removeAttribute("msg");
        %>  

        <table border="1">

            <tr>
                <th>Código</th>
                <th>Local</th>
              
                <th>Ações</th>
            </tr>

            <%
                for (Emergencia emergencia : emergencias) {
            %>

            <tr>
                <td>
                    <%= emergencia.getCodigo()%>
                </td>
                <td>
                    <%= emergencia.getLocal()%>
                </td>
                <td>
                    <a href="detalharEmergencia.jsp?i=<%= emergencias.indexOf(emergencia)%>">detalhar </a>
                    <a href="EmergenciaServlet?c=<%= emergencia.getCodigo()%>&op=editar">editar </a>
                    <a href="EmergenciaServlet?c=<%= emergencia.getCodigo()%>&op=deletar">deletar</a>
                </td>
            </tr>

            <%
                }
            %>

        </table>
    </body>
</html>
