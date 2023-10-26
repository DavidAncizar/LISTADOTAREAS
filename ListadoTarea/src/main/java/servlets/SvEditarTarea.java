/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.listadotarea.MetodosTabla;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cuati
 */
@WebServlet(name = "SvEditarTarea", urlPatterns = {"/SvEditarTarea"})
public class SvEditarTarea extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();

        // Obtén los parámetros enviados desde el formulario de edición
        String ni = request.getParameter("ni");
        String nuevoTitulo = request.getParameter("titulo");
        String nuevaDescripcion = request.getParameter("descripcion");
        String nuevaFechaStr = request.getParameter("fecha");

        // Obtén la lista de tareas desde la sesión
        MetodosTabla tarea = (MetodosTabla) session.getAttribute("tarea");

        if (tarea != null) {
            // Realiza validaciones, por ejemplo, verifica si la tarea con el ID proporcionado existe
            if (tarea.localizarNi(ni) != null) {
                // Actualiza la tarea en tu lista de tareas
                tarea.editarTarea(nuevoTitulo, nuevoTitulo, nuevaDescripcion, nuevaFechaStr);

                // Guarda la lista actualizada en la sesión
                session.setAttribute("tarea", tarea);

                // Guarda la lista actualizada en el archivo de texto
                MetodosTabla.guardarTabla(tarea, getServletContext());

                // Después de editar la tarea exitosamente en tu servlet
                session.setAttribute("tareaEditadaExitosamente", true);
            }
        }

        // Redirige a la página de tareas (o la página que desees)
        response.sendRedirect("Tareas.jsp");
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
