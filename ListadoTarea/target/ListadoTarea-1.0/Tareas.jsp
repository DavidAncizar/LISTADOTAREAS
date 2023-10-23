<%-- 
    Document   : Tareas
    Created on : 9 oct 2023, 22:48:04
    Author     : Ancizar y Juan David
--%>



<%@page import="com.umariana.listadotarea.MetSerializacion"%>
<%@page import="com.umariana.listadotarea.MetodosTabla"%>
<%@include file= "dise�o/header.jsp" %>
<%String usuario = request.getParameter("usuario");%>
<link rel="stylesheet" href="style.jsp">
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
                <form class="row g-3 needs-validation" action="Tareas.jsp" method="GET" novalidate >
                    
                    <!-- NUMERO DE TAREA IDENTIFICACION-->

                    <div class="input-group">
                        <label for="validationCustomUsername" class="form-label">NI</label>
                        <div class="input-group">                          
                            <input type="number" name="id" class="form-control" min="0" step="1"id="validationCustomUsername" aria-describedby="inputGroupPrepend" required>
                            
                            <!-- Verificacion -->
                            <div class="valid-feedback">
                                Excelente!
                            </div>
                            <!-- En caso de no tener nada -->
                            <div class="invalid-feedback">
                                El NI esta vacio, ingresa uno porfavor
                            </div> 
                        </div>
                    </div>


                    <div class="input-group">
                         <label for="validationCustomUsername" class="form-label">Titulo</label>
                        <div class="input-group">                         
                            <formu class="form-floating">                               
                                <input type="text" class="form-control" id="floatingInputValue" name="titulo" placeholder="Ingrese el Titulo" value="" required="">
                                
                                <!-- Verificar -->
                                <div class="valid-feedback">
                                   Excelente!
                                </div>
                                <!-- Si esta vacio -->
                                <div class="invalid-feedback">
                                  El titulo esta vacio, ingresa uno porfavor.
                                </div> 
                            </formu></div>
                    </div>

           
                    <div class="input-group">
                        <label for="descripcion" class="form-label">Descripci�n</label>
                        <div class="input-group">                           
                            <textarea class="form-control" name="descripcion" id="validationTextarea" placeholder="ingrese una descripcion" required></textarea>
                            <!-- Verificar -->
                            <div class="valid-feedback">
                                Excelente!
                            </div>
                            <!-- Si esta vacio -->
                            <div class="invalid-feedback">
                                Ingresa una descripcion para entender mejor tu tarea porfavor.
                            </div> 
                        </div>    
                    </div>

                    <!-- Fecha -->  
                    <div class="input-group">
                        <label for="validationCustom05" class="form-label">Fecha</label>
                        <div class="input-group">
                            <input type="date" class="form-control"  name="fecha" id="validationCustom05" required>
                            <!-- Verificar -->
                            <div class="valid-feedback">
                                Excelente!
                            </div>
                            <!-- Si esta vacio-->
                            <div class="invalid-feedback">
                                La fecha esta vacia, ingresala porfavor.
                            </div> 
                        </div>
                         
                    </div>

                    <!-- Para agregar una nueva tarea -->
                    <center>
                        <button type="submit" class="btn btn-outline-primary">Agregar Tarea</button>
                    </center>
                </form>
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
                            <th scope="col">Descripci�n</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Operacion</th>
                        </tr>                       
                    </thead>    
                     <!-- Se coloca el contenido de la tabla -->
                    <tbody>
                        <%
                            
                            MetodosTabla listaTareas = new MetodosTabla();
                            // Obtener el contexto del servlet
                            ServletContext context = getServletContext();

                            listaTareas = MetSerializacion.lecturaTarea(context);
                            if (listaTareas == null) {
                                listaTareas = new MetodosTabla();
                            }

                            String tablaHTML = listaTareas.TablaCreada();
                        %>

                    <div>    
                        <%= tablaHTML%>
                    </div>
                    
                   </tbody>
                </table>
            </div>
        </div>
    </div></div>
      <!-- Creamos un modal para eliminar la tarea-->
<div class="modal fade" id="eliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h2>Al aceptar, eliminar�s permanentemente esta Tarea, �Est�s Seguro?</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalConfirm" onclick="descartarTarea()"> Borrar</button>
            </div>
        </div>
    </div>
</div>  
     
      <!--<!-- Implementamos lenguaje JavaSCript para utilizar el required de diferente forma -->
<script>
    // Funci�n de flecha que se ejecuta inmediatamente.
    (() => {
        'use strict'; // Modo estricto para un c�digo m�s seguro y eficiente.

        // Selecciona todos los elementos con la clase "needs-validation" y los almacena en la variable "forms".
        const forms = document.querySelectorAll('.needs-validation');

        // Itera sobre cada formulario encontrado.
        Array.from(forms).forEach(form => {
            // Agrega un evento "submit" a cada formulario.
            form.addEventListener('submit', event => {
                // Verifica si el formulario no es v�lido.
                if (!form.checkValidity()) {
                    event.preventDefault(); // Previene el env�o del formulario si no es v�lido.
                    event.stopPropagation(); // Detiene la propagaci�n del evento para evitar otros manejadores de eventos.
                }
                form.classList.add('was-validated'); // Agrega la clase "was-validated" al formulario.
            }, false);
        });
    })();
    
       var id = "";

    /**
     * Esta funci�n se encarga de mostrar el modal de confirmaci�n antes de eliminar una tarea.
     */
    $('#eliminar').on('show.bs.modal', function (event) {
        // Obtiene el bot�n que desencaden� el evento de mostrar el modal
        var button = $(event.relatedTarget);

        // Obtiene el id de la tarea desde el atributo 'data-nombre' del bot�n
        var idTarea = button.data('nombre');

        // Obtiene el modal actual
        var modal = $(this);

        // Almacena el nombre de la tarea en la variable global 'id'
        id = idTarea;

    });
    
     function descartarTarea() {
        // Obtiene el NI de la variable que ya se estableci�
        var niTarea = ni;

        // Realiza una solicitud AJAX al servlet 'SvEliminarEditar' para eliminar la tarea
        $.ajax({
            url: 'SvAcciones?ni=' + niTarea, // URL del servlet con el par�metro 'id' para la eliminaci�n
            method: 'GET', // M�todo HTTP utilizado para la solicitud (GET en este caso)
            success: function (data) {
                // En caso de �xito en la solicitud:

                // Cierra el modal de eliminaci�n
                $('#eliminar').modal('hide');

                // Recarga la p�gina actual para reflejar los cambios
                location.reload();
            },
            error: function () {
                // En caso de error en la solicitud:

                // Registra un mensaje de error en la consola (para fines de depuraci�n)
                console.log('Error al eliminar el perro.');
            }
        });
    }

</script>
     

<%@include file= "dise�o/footer.jsp" %>