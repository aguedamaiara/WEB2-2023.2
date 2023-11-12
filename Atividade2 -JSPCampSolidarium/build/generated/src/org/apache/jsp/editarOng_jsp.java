package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.web2.maiara.atividade1.negocio.Ong;

public final class editarOng_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page - Edição de ONG</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    \n");
      out.write("        <h1>Editar ONG</h1>\n");
      out.write("\n");
      out.write("        <a href=\"index.html\">home</a><br/>\n");
      out.write("        <form method=\"post\" action=\"OngServlet\">\n");
      out.write("            \n");
      out.write("            <input type=\"hidden\" name=\"op\" value=\"editar\"/>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("               <!-- Campos que não devem ser editados -->\n");
      out.write("            Código: <input type=\"text\" name=\"codigo\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.codigo}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" readonly/><br/>\n");
      out.write("            CNPJ: <input type=\"text\" name=\"cnpj\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.cnpj}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" readonly/><br/>\n");
      out.write("            Login: <input type=\"text\" name=\"login\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.login}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" readonly/><br/>\n");
      out.write("            <br>\n");
      out.write("            <br>\n");
      out.write("            \n");
      out.write("            Nome: <input type=\"text\" name=\"nome\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.nome}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/><br/>\n");
      out.write("            Endereco: <input type=\"text\" name=\"endereco\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.endereco}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/><br/>\n");
      out.write("            Email: <input type=\"text\" name=\"email\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ong.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/><br/>\n");
      out.write("            \n");
      out.write("         \n");
      out.write("            <input type=\"submit\" value=\"editar\"/>\n");
      out.write("            \n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
