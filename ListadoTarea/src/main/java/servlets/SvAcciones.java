/**
 *
 * @author Ancizar y Juan David
 */
package servlets;


import com.umariana.listadotarea.MetSerializacion;
import com.umariana.listadotarea.MetodosTabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAcciones", urlPatterns = {"/SvAcciones"})
public class SvAcciones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    MetodosTabla Tasklist = new MetodosTabla();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        //Obtener el contexto del servlet
        ServletContext context = getServletContext();

        System.out.println("Corriendo metodo de eliminar");
        
        try {
            Tasklist = MetSerializacion.leerTareas(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtiene el nombre de la tarea que se elimina
        String niEliminar = request.getParameter("Ni");

        System.out.println(niEliminar);

        int eliminar = Integer.parseInt(niEliminar);

          Tasklist.descartarTarea(eliminar);

        MetSerializacion.ingresarArchivo(Tasklist, context);

            // Redireccionar a la página de destino
            response.sendRedirect("Tareas.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
