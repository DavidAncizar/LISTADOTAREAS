/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.listadotarea.metodos;
import com.umariana.listadotarea.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ancizar y Juan David
 */
@WebServlet(name = "SvRegis", urlPatterns = {"/SvRegis"})
public class SvRegis extends HttpServlet {
    ArrayList<usuario> datosUsuario = new ArrayList<>();
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
     
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    //Almacenamos los parametros en el post por seguridad
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          /**
         * Obtenemos variables
         */
        String cedula = request.getParameter("cedula");
        String contrasenia = request.getParameter("contrasenia");

        ServletContext context = getServletContext();// Contexto de servlet para obtener la PATH

        /**
         * Llamamos al metodo para verificar si coincide la cedula y contraseña
         * con los usuarios guardados
         */
        String user = metodos.leerUsuario(Integer.parseInt(cedula), contrasenia, context);
        // Verificar si ingresa o no 
        if (user.equals("No encontrado")) {

            request.getRequestDispatcher("index.jsp?valido=" + "false").forward(request, response);// Redirigimos al index con la variable no valida para mostrar mensaje al usuario.

        } else if (!user.equals("No encontrado")) {

            request.getRequestDispatcher("Tareas.jsp?usuario=" + user).forward(request, response);// Redirigimos al login con el nombre de usuario para mostrar el mensaje personalizado.
        }

    }

        

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
