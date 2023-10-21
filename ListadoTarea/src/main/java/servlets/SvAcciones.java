/**
 *
 * @author Ancizar y Juan David
 */
package servlets;


import com.umariana.listadotarea.MetSerializacion;
import com.umariana.listadotarea.MetodosTabla;
import com.umariana.listadotarea.Tabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        

    }

    Tabla listaTareas = new Tabla();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
           
          String nombre = request.getParameter("usuarioI");
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        try {
            listaTareas = MetSerializacion.leerTareas(context);
            if(listaTareas == null){
                listaTareas = new Tabla();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Recibir los parámetros del formulario
        int ni = Integer.parseInt(request.getParameter("ni"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaVencer = request.getParameter("fecha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;

        try {
            fecha = sdf.parse(fechaVencer);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejo de error en caso de que la fecha no sea válida
        };
        
        String an="";
        
        if(!listaTareas.existenNi(ni)){
            Tabla nuevaTarea = new Tabla(ni, titulo, descripcion, fecha);

            listaTareas.insertarPrincipio(nuevaTarea);

            MetSerializacion.ingresarArchivo(listaTareas, context);
            
            listaTareas.mostrarTareas();
            an="si";
        } else {
            an="no";
        }


        // Redireccionar a la página de destino internamente en el servidor
            // Redireccionar a la página de destino
            response.sendRedirect("Tareas.jsp?usuarioI="+nombre+"&add="+an);
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
