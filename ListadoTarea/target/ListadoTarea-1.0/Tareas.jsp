<%-- 
    Document   : Tareas
    Created on : 9 oct 2023, 22:48:04
    Author     : Ancizar y Juan David
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.umariana.listadotarea.MetodosTabla"%>
<%@include file= "diseño/header.jsp" %>
<%String usuario = request.getParameter("usuario");%>
<!-- header estilado -->

<div> 
    <div class="card text-center">
        <div class="card-header">
        </div>
        
        <div class="card-body">
            <h5>Bienvenido al listado de tareas!!, <%out.println(request.getParameter("nombre"));%></h5>
            <div class="card text-side">
              <a href="index.jsp" class="btn btn-outline-primary" > Cerrar sesion</a>
        </div>  
        </div>

        <div class="card-footer text-body-secondary">
        </div>
    </div>
</div>

<!-- Codigo para la tabla -->

<div class="container p-4"> 
    <div class="row">
        <div class="col-md-4">  
            <div class="card card-body">
                <form action="SvAcciones" method="POST" onsubmit="return buscarNi()">
                    
                    <!-- NUMERO DE TAREA IDENTIFICACION-->

                    <div class="input-group mb-3">
                        <label class="input-group-text" for="id">Id:</label>
                        <input type="text" name="id" class="form-control" required pattern="\d+" />
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="titulo">Titulo:</label>
                        <input type="text" name="titulo" class="form-control" required />
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="descripcion">Descripción:</label>
                        <textarea name="descripcion" class="form-control" required></textarea>
                    </div>
                    <div class="input-group mb-3">
                        <label class="input-group-text" for="fecha">Fecha:</label>
                        <input type="date" name="fecha" class="form-control" required />
                    </div>

                    <button type="submit" class="btn btn-primary">Agregar Tarea</button>
                </form>
                 <br>
            </div>

        </div> 

        <div class="col-md-8">
            <!-- Ingresamos la tabla-->

            <div class="card card-body">
                <table class="table table-striped table-hover">
           
                    <thead>
                        <tr>
                            <th scope="col">NI Tarea</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Operacion</th>
                        </tr>                       
                    </thead>    
                     <!-- Se coloca el contenido de la tabla -->
                    <tbody>
                       <%MetodosTabla tareas = (MetodosTabla) session.getAttribute("tareas");

                        if (tareas != null) {
                            MetodosTabla.Nodo current = tareas.cabeza;
                            while (current != null) {
                    %>
                    <tr>
                        <td><%= current.nTarea.getNi()%></td>
                        <td><%= current.nTarea.getTitulo()%></td>
                        <td><%= current.nTarea.getDescripcion()%></td>
                        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(current.nTarea.getFechaVencer())%></td>
                        <td>
                            <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tareaModal"
                               onclick="showTareaDetails(<%= current.nTarea.getNi()%>, '<%= current.nTarea.getTitulo()%>', 
                               '<%= current.nTarea.getDescripcion()%>', '<%= new SimpleDateFormat("yyyy-MM-dd").format(current.nTarea.getFechaVencer())%>')">
                                <i class="fas fa-eye"></i> </a>

                            <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#editarModal"
                               data-ni="<%= current.nTarea.getNi()%>"
                               data-titulo="<%= current.nTarea.getTitulo()%>"
                               data-descripcion="<%= current.nTarea.getDescripcion()%>"
                               data-fecha="<%= new SimpleDateFormat("yyyy-MM-dd").format(current.nTarea.getFechaVencer())%>">
                                <i class="fas fa-pencil-alt"></i>
                            </a>

                            <a href="#" class="btn btn-danger" onclick="eliminarTarea(<%= current.nTarea.getNi()%>)"><i class="fas fa-trash-alt"></i></a>

                        </td>
                    </tr>
                    <%
                                current = current.siguiente;
                            }
                        } else {
                            out.println("No hay tareas disponibles.");
                        }
                    %>
                   </tbody>
                </table>
            </div>
        </div>
    </div></div>
      <!-- Creamos un modal para eliminar la tarea-->
 
     
      <!--<!-- Implementamos lenguaje JavaSCript para utilizar el required de diferente forma -->

     

<%@include file= "diseño/footer.jsp" %>
<script>
    function showTareaDetails(ni, titulo, descripcion, fecha) {
        var modal = $('#tareaModal');
        modal.find('#tarea-id').text(ni);
        modal.find('#tarea-titulo').text(titulo);
        modal.find('#tarea-descripcion').text(descripcion);
        modal.find('#tarea-fecha').text(fecha);
    }
</script>
<script>
    function eliminarTarea(ni) {
        if (confirm("¿Estas seguro de que quieres eliminar la tarea?")) {
            location.href = "SvAcciones?tipo=delete&ni=" + ni;
        }
    }
</script>