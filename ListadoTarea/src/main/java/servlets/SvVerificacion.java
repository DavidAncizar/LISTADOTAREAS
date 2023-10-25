
/**
 *
 * @author Ancizar y Juan David
 */
package servlets;

import com.umariana.listadotarea.MetPersistencia;
import com.umariana.listadotarea.metodos;
import com.umariana.listadotarea.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvVerificacion", urlPatterns = {"/SvVerificacion"})
public class SvVerificacion extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();

        ArrayList<usuario> nUsuario = new ArrayList<>(); //Array a cargar con el txt

        //Cargar la lista de perros desde un archivo
        MetPersistencia.lecturaArchivo(nUsuario, context);

        //Obtener datos del formulario enviados por POST
        String cedula =request.getParameter("cedula");

        String nombre = request.getParameter("nombre");

        String contrasenia = request.getParameter("contrasenia");
        
        /**
         * Verificar si el usuario ya existe, en caso de hacerlo no se añade y muestra mensaje.
         */
        
        if(metodos.cargarUsuario(Integer.parseInt(cedula ), context)) {
            /**
             * Si el usuario no existe, se añade.
             */ 
            usuario us = new usuario (nombre, Integer.parseInt(cedula), contrasenia);//Añadimos un objeto de tipo Usuarios
            
            nUsuario.add(us); //Lo agregamos al array
            
            MetPersistencia.datosArchivo(nUsuario, context);//Actualizamos el txt
            
            //Enviamos bandera que indica que se añadio exitosamente
            String registrado = "si";
            request.setAttribute("registrado", registrado);
            
        } else if(!metodos.cargarUsuario(Integer.parseInt(cedula), context)){
            /**
             * Si el usuario existe, no se añade.
             */ 
             
            //Enviamos bandera que indica que NO se añadio exitosamente
            String registrado = "no";
            request.setAttribute("registrado", registrado);
        }

        // Redireccionar a la página de destino internamente en el servidor
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
