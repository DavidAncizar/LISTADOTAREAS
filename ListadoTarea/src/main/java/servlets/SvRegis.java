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
        //llamamos los parametros de la clase usuario 
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String contrasenia = request.getParameter("contrasenia");
        //llamamos todo lo que hay en el context servlet
        ServletContext context = getServletContext();
        //objeto en el que llamamos todos los usuarios, creacion de objeto usando el constructor colocando todos los parametros 
        usuario objUsuario = new usuario (nombre, cedula, contrasenia );
        //Agregar la lista con el array que hicimos arriba
        datosUsuario.add(objUsuario);
        //metodo para leer los usuarios almacenados en el array
        metodos.leerUsuario(context, datosUsuario);
        // redireccionamos a la pagina principal despues de registrar 
        request.getRequestDispatcher("index.jsp").forward(request, response );
        
    }
        

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
