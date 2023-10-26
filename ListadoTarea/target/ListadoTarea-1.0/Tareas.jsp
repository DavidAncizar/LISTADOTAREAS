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
     <div class="card text-center ">
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
<!-- mostrar los datos de la tarea -->
<div class="modal fade" id="tareaModal" tabindex="-1" aria-labelledby="tareaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" ni="tareaModalLabel">Detalles de la Tarea</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="tarea-details">
                    <p><strong>NI:</strong> <span id="tarea-ni"></span></p>
                    <p><strong>Título:</strong> <span id="tarea-titulo"></span></p>
                    <p><strong>Descripción:</strong> <span id="tarea-descripcion"></span></p>
                    <p><strong>Fecha:</strong> <span id="tarea-fecha"></span></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<!-- editar los datos -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarModalLabel">Editar la Tarea</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="SvEditarTarea" method="POST">
                    <!-- almacena el id que se va a realizar los cambios -->
                    <input type="hidden" name="id" id="editar-tarea-ni" value="">
                    <!-- editar el titulo -->
                    <div class="mb-3">
                        <label for="titulo" class="form-label">Título</label>
                        <input type="text" class="form-control" id="editar-tarea-titulo" name="titulo">
                    </div>
                    <!-- editar la descripcion -->
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" id="editar-tarea-descripcion" name="descripcion"></textarea>
                    </div>
                    <!-- editar la fecha -->
                    <div class="mb-3">
                        <label for="fecha" class="form-label">Fecha</label>
                        <input type="date" class="form-control" id="editar-tarea-fecha" name="fecha">
                    </div>
                    <!-- boton para guardar lo que editamos -->
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#editarModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Botón que desencadenó el evento
        var ni = button.data('ni'); // Obtén el ID de la tarea
        var titulo = button.data('titulo'); // Obtén el título de la tarea
        var descripcion = button.data('descripcion'); // Obtén la descripción de la tarea
        var fecha = button.data('fecha'); // Obtén la fecha de la tarea

        // Rellena los campos del formulario de edición con los datos de la tarea
        $('#editar-tarea-id').val(ni);
        $('#editar-tarea-titulo').val(titulo);
        $('#editar-tarea-descripcion').val(descripcion);
        $('#editar-tarea-fecha').val(fecha);
    });
</script>