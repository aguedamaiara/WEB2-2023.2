package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.web2.maiara.atividade1.negocio.Insumo;
import br.web2.maiara.atividade1.negocio.Insumo.CategoriaInsumo;

public final class cadastroInsumo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <h1>Cadastro de Novo Insumo</h1>\n");
      out.write("\n");
      out.write("        <a href=\"index.html\">home</a><br/>\n");
      out.write("        <form method=\"post\" action=\"InsumoServlet\">\n");
      out.write("            \n");
      out.write("            <input type=\"hidden\" name=\"op\" value=\"cadastro\"/>\n");
      out.write("            Código: <input type=\"text\" name=\"codigo\"/><br/>\n");
      out.write("            Nome: <input type=\"text\" name=\"nome\"/><br/>\n");
      out.write("            Marca: <input type=\"text\" name=\"marca\"/><br/>\n");
      out.write("            Categoria: \n");
      out.write("            <select name=\"categoria\">\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.ALIMENTO );
      out.write("\">Alimento</option>\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.VESTUARIO );
      out.write("\">Vestuário</option>\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.BRINQUEDO );
      out.write("\">Brinquedo</option>\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.MEDICAMENTOS );
      out.write("\">Medicamentos</option>\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.HIGIENE );
      out.write("\">Higiene</option>\n");
      out.write("                <option value=\"");
      out.print( CategoriaInsumo.MATERIAL_DE_CONSTRUCAO );
      out.write("\">Material de Construção</option>\n");
      out.write("            </select><br/>\n");
      out.write("            <input type=\"submit\" value=\"cadastrar\"/>\n");
      out.write("            \n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
