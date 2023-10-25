/**
 *
 * @author Ancizar y Juan David
 */
package servlets;

import com.umariana.listadotarea.MetodosTabla;
import com.umariana.listadotarea.Tabla;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvAcciones", urlPatterns = {"/SvAcciones"})
public class SvAcciones extends HttpServlet {
 //Creamos una instancia para llamar los metodos de la clase "MetodosTabla" que no sean tipo Static.
         private MetodosTabla tareas;
        
   public void init() throws ServletException {
        // Inicializa la lista de tareas al cargar el servlet
        tareas = MetodosTabla.leerTabla(getServletContext());
    }

   
    //FALTA ARREGLAR DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
           
        

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ni = request.getParameter("ni");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        String posicion = request.getParameter("posicion"); // Obtén el valor del radio button
        String niAntesDe = request.getParameter("niAntesDe"); // Obtén la id antes de la cual agregar
        String niDespuesDe = request.getParameter("niDespuesDe"); // Obtén la id después de la cual agregar

        // Realizar el cast de la fecha
        Date fechaVencimiento = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaVencimiento = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtén la lista actualizada desde la sesión
        HttpSession session = request.getSession();
        MetodosTabla tareas = (MetodosTabla) session.getAttribute("tareas");

        if (tareas == null  ) {
            tareas = new MetodosTabla();
            // Guárdala en la sesión
            session.setAttribute("tareas", tareas);
        }

        // Verifica si ya existe una tarea con el mismo ID
        if (tareas.tareaExistente(Integer.parseInt(ni))) {
            // Tarea con el mismo ID ya existe, muestra una alerta
            request.setAttribute("tareaExistente", true);

            // Redirige nuevamente a la página tareas.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("Tareas.jsp");
            dispatcher.forward(request, response);
        } else {
            Tabla nuevaTarea = new Tabla(Integer.parseInt(ni), titulo, descripcion, fechaVencimiento);

            if (null == posicion) {
                // Por defecto o si se selecciona "primero", agregar al comienzo
                tareas.agregarInicio(nuevaTarea);
            } else {
                switch (posicion) {
                    case "ultimo":
                        // Agregar la tarea al final de la lista
                        tareas.agregarFinal(nuevaTarea);
                        break;
                    case "antesDe":
                        if (niAntesDe != null && !niAntesDe.isEmpty()) {
                            // Agregar la tarea antes de la tarea con la ID especificada
                            tareas.agregarAntesDe(Integer.parseInt(niAntesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID antes de la cual agregar, agregar al comienzo
                            tareas.agregarInicio(nuevaTarea);
                        }
                        break;
                    case "despuesDe":
                        if (niDespuesDe != null && !niDespuesDe.isEmpty()) {
                            // Agregar la tarea después de la tarea con la ID especificada
                            tareas.agregarDespuesDe(Integer.parseInt(niDespuesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID después de la cual agregar, agregar al final
                            tareas.agregarFinal(nuevaTarea);
                        }
                        break;
                    default:
                        // Por defecto o si se selecciona "primero", agregar al comienzo
                        tareas.agregarInicio(nuevaTarea);
                        break;
                }
            }

            // Guarda la tarea en el archivo
            MetodosTabla.guardarTabla(tareas, getServletContext());

            // Después de agregar una tarea exitosamente en tu servlet
            request.setAttribute("registroExitoso", true);

            // Redirige a la página tareas.jsp con el parámetro "registroExitoso"
            response.sendRedirect("Tareas.jsp?registroExitoso=true");
        }
    }
        
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}