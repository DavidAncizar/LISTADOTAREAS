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
 //Creamos una instancia para llamar los metodos de la clase "MetodosTabla" que no sean tipo Static.
        MetodosTabla instancia = new MetodosTabla ();
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
    }

    Tabla tareasHechas = new Tabla();
    //FALTA ARREGLAR DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
           
          
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        System.out.println("Eliminacion en proceso...");
       try {
            tareasHechas = MetSerializacion.leerTareas(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtiene el nombre del perro a eliminar desde los parámetros de la solicitud
        String niEliminar = request.getParameter("niEliminacion");

        System.out.println(idEliminar);

        int eliminar = Integer.parseInt(idEliminar);

        tareasHechas

        MetSerializacion.ingresarArchivo(instancia, context);

            // Redireccionar a la página de destino
        response.sendRedirect("login.jsp?usuarioI=" + request.getParameter("usuarioI"));

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
