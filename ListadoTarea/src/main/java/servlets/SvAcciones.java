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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvAcciones", urlPatterns = {"/SvAcciones"})
public class SvAcciones extends HttpServlet {


    private MetodosTabla listaTareas;

    @Override
    public void init() throws ServletException {
        try {
            // Inicializa la lista de tareas al cargar el servlet
            listaTareas = MetodosTabla.leerTarea(getServletContext());
        } catch (ParseException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        if (tipo != null && tipo.equals("delete")) {
            String idToDelete = request.getParameter("id");
            if (idToDelete != null && !idToDelete.isEmpty()) {
                HttpSession session = request.getSession();
                MetodosTabla listaTareas = (MetodosTabla) session.getAttribute("listaTareas");

                if (listaTareas != null) {
                    try {
                        int id = Integer.parseInt(idToDelete);
                        listaTareas.eliminarTarea(id);
                        // Guarda la lista actualizada en el archivo
                        MetodosTabla.guardarTarea(listaTareas,getServletContext());

                        // Agrega un atributo para indicar la eliminación exitosa
                        session.setAttribute("tareaEliminada", true);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se proporciona un ID válido
                        e.printStackTrace();
                    }
                }
            }
        }
        // Después de eliminar una tarea con éxito en tu servlet
        response.sendRedirect("Tareas.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ni = request.getParameter("ni");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaVencer = request.getParameter("fecha");
        String posicion = request.getParameter("posicion"); // Obtén el valor del radio button
        String niAntesDe = request.getParameter("idAntesDe"); // Obtén la id antes de la cual agregar
        String niDespuesDe = request.getParameter("idDespuesDe"); // Obtén la id después de la cual agregar

        // Realizar el cast de la fecha
        Date fechaVencimiento = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaVencimiento = dateFormat.parse(fechaVencer);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtén la lista actualizada desde la sesión
        HttpSession session = request.getSession();
        MetodosTabla listaTareas = (MetodosTabla) session.getAttribute("listaTareas");

        if (listaTareas == null) {
            listaTareas = new MetodosTabla();
            // Guárdala en la sesión
            session.setAttribute("listaTareas", listaTareas);
        }

        // Verifica si ya existe una tarea con el mismo ID
        if (listaTareas.tareaExiste(Integer.parseInt(ni))) {
            // Tarea con el mismo ID ya existe, muestra una alerta
            request.setAttribute("tareaExistente", true);

            // Redirige nuevamente a la página tareas.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("tareas.jsp");
            dispatcher.forward(request, response);
        } else {
            Tabla nuevaTarea = new Tabla(Integer.parseInt(ni), titulo, descripcion, fechaVencimiento);

            if (null == posicion) {
                // Por defecto o si se selecciona "primero", agregar al comienzo
                listaTareas.inicioNodo(nuevaTarea);
            } else {
                switch (posicion) {
                    case "ultimo":
                        // Agregar la tarea al final de la lista
                        listaTareas.agregarFinal(nuevaTarea);
                        break;
                    case "antesDe":
                        if (niAntesDe != null && !niAntesDe.isEmpty()) {
                            // Agregar la tarea antes de la tarea con la ID especificada
                            listaTareas.agregarAntes(Integer.parseInt(niAntesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID antes de la cual agregar, agregar al comienzo
                            listaTareas.inicioNodo(nuevaTarea);
                        }
                        break;
                    case "despuesDe":
                        if (niDespuesDe != null && !niDespuesDe.isEmpty()) {
                            // Agregar la tarea después de la tarea con la ID especificada
                            listaTareas.agregarDespues(Integer.parseInt(niDespuesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID después de la cual agregar, agregar al final
                            listaTareas.agregarFinal(nuevaTarea);
                        }
                        break;
                    default:
                        // Por defecto o si se selecciona "primero", agregar al comienzo
                        listaTareas.inicioNodo(nuevaTarea);
                        break;
                }
            }

            // Guarda la tarea en el archivo
            MetodosTabla.guardarTarea(listaTareas, getServletContext());

            // Después de agregar una tarea exitosamente en tu servlet
            request.setAttribute("registroExitoso", true);

            // Redirige a la página tareas.jsp con el parámetro "registroExitoso"
            response.sendRedirect("Tareas.jsp?registroExitoso=true");
        }
    }
}

