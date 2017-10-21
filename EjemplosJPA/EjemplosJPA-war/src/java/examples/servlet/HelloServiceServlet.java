package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.ejb.EJB;

import examples.stateless.HelloService;

@WebServlet(name="HelloServiceServlet", 
            urlPatterns="/HelloServiceServlet")
public class HelloServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Capitulo 3: Ejemplo de Bean de Sesion Sin Estado (Staless Session Bean)";
        
    
    private final String DESCRIPTION = 
        "Este ejemplo demuestra las bases de definir y acceder" +
        "a un Bean de Sesión Sin Estado.</br>" +
        "Introduzca un nombre y pulse 'Ir'. Esto disparará un cliente servlet que habla " +
        "a un Bean de Sesion Sin Estado para crear una cadena 'Hola' que es entonces " +
        "mostrada en el navegador.";

    @EJB
    HelloService service;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        // Si hubo un nombre submitido, muestra la cadena hola con el nombre
        String name = request.getParameter("name");
        if (name != null) {
            // usa el servicio para imprimir la cadena 'hola' en el flujo html
            out.println(service.sayHello(name));
        }
        printHtmlFooter(out);
    }
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("</hr>");
        out.println("<form action=\"HelloServiceServlet\" method=\"POST\">");
        out.println("<table><tbody>");
        out.println("<tr><td>Nombre:</td><td><input type=\"text\" name=\"Nombre\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<input name=\"action\" type=\"submit\" value=\"Ir\"/>");
        out.println("</form>");
        out.println("<hr/>");
    }
        
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
