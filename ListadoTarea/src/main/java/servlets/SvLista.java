/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;


import com.umariana.listadotarea.MetSerializacion;
import com.umariana.listadotarea.MetodosTabla;
import java.io.IOException;
import com.umariana.listadotarea.Tabla;
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


/**
 *
 * @author cuati
 */
@WebServlet(name = "SvLista", urlPatterns = {"/SvLista"})
public class SvLista extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     MetodosTabla instancia = new MetodosTabla ();
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("usuario");
        
        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        try {
            instancia = MetSerializacion.lecturaTarea(context);
            if(instancia == null){
                instancia = new MetodosTabla();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvLista.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Recibir los parámetros del formulario
        int ni = Integer.parseInt(request.getParameter("ni"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaVencer = request.getParameter("fecha");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        String ubicar=request.getParameter("opcion");
        String niUbi=request.getParameter("niEd");
        String niUbi2=request.getParameter("niEd2");
        
        try {
            fecha = sdf.parse(fechaVencer);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejo de error en caso de que la fecha no sea válida
        };
        
        String an="";
        
        if(!instancia.niExistente(ni)){
            Tabla tarea = new Tabla(ni, titulo, descripcion, fecha);
            switch(ubicar){
                case "prin":
                    instancia.InicioNodo(tarea);
                    an="si";
                    break;
                case "ant":
                    if(niUbi != null){
                       instancia.finalAntes(Integer.parseInt(niUbi), tarea);
                        an="si"; 
                    } else{
                        an="no";
                    }
                    break;
                case "fin":
                    instancia.finalIngreso(tarea);
                    an="si";
                    break;
                case "desp":
                    System.out.println("===============>"+niUbi);
                    if(niUbi2 != null){
                       instancia.finalDespues(Integer.parseInt(niUbi2), tarea);
                        an="si"; 
                    } else{
                        an="no";
                    }
                    break;
                    
            }
            MetSerializacion.ingresarArchivo(instancia, context);
            
            instancia.mostrarTarea();
            
        } else {
            an="no";
        }


        // Redireccionar a la página de destino internamente en el servidor
            // Redireccionar a la página de destino
        response.sendRedirect("Tareas.jsp?usuario="+nombre+"&add="+an);
    }


       
        
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
