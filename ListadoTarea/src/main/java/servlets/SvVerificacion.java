/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.listadotarea.metodos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cuati
 */
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
         //llamamos todo lo que hay en el context servlet
           ServletContext context = getServletContext();
        //llamamos todos los parametros
            String nombre = request.getParameter("nombre");
            String cedula = request.getParameter("cedula");
            String contrasenia = request.getParameter("contrasenia"); 
              //Creamos el metodo en forma de String para llamar el metodo con sus parametros, van en orden 
              String usuarioNombre = metodos.ValidarUsuario( context, nombre, contrasenia);
              if(usuarioNombre!=null){
                       System.out.println("aqui estoy ");
                       String script = "<script>alert('Se verificó correctamente.'); window.location.href = 'Tareas.jsp?nombre=" + usuarioNombre + "';</script>";
                       response.setContentType("text/html");
                       response.getWriter().write(script);
                   }else{
                       System.out.println("usuario incorrecto");
                       String script = "<script>alert('El usuario ingresado no esta registrado'); window.location.href = 'index.jsp?nombre=" + usuarioNombre + "';</script>";
                       response.setContentType("text/html");
                       response.getWriter().write(script);
                       
                   }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
