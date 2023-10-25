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

   
    //FALTA ARREGLAR DO GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
           
          
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        System.out.println("Eliminacion en proceso...");
       try {
          instancia = MetSerializacion.lecturaTarea(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        String niEliminar = request.getParameter("ni");
        String usuario = request.getParameter("usuario");

        System.out.println(niEliminar);

        int eliminar = Integer.parseInt(niEliminar);

        instancia.descartarTarea(eliminar);

        MetSerializacion.ingresarArchivo(instancia, context);

            // Redireccionar a la página de destino
        response.sendRedirect("Tareas.jsp?usuario="+usuario);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext();
        String nombre = request.getParameter("usuario");
        
        try {
            instancia = MetSerializacion.lecturaTarea(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAcciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        String edit = request.getParameter("edicion");
        int ni=Integer.parseInt( request.getParameter("niEdicion"));
        
        switch(edit){
            case"newtitule":
                String titulo = request.getParameter("tituloNuevo"); 
                instancia.tituloEdit(ni, titulo);
                break;
            case "newdesc":
                String descripcion = request.getParameter("descripcionNueva"); 
               instancia.descripcionEdit(ni, descripcion);
                break;
            case "newfecha":
                String fechaEdicion = request.getParameter("fechaNueva");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = null;

                try {
                    fecha = sdf.parse(fechaEdicion);
                } catch (ParseException e) {
                    e.printStackTrace(); // Manejo de error en caso de que la fecha no sea válida
                };
                instancia.fechaEdit(ni, fecha);
                break;
        }
                
        MetSerializacion.ingresarArchivo(instancia, context);
        
        response.sendRedirect("Tareas.jsp?usuario="+nombre);
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
