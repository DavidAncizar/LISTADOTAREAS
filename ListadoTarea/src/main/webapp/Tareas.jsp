<%@include file= "diseño/header.jsp" %>
<%String usuario = request.getParameter("usuario");%>
<link rel="stylesheet" href="style.jsp">
<!-- header estilado -->
<div> 
    <div class="card text-center">
        <div class="card-header">
        </div>

        <div class="card-body">
            <h5>Bienvenido al listado de tareas!!, <%out.println(request.getParameter("nombre"));%></h5>
            
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
                <form class="row g-3 needs-validation" action="SvLoginCheck" method="GET" novalidate >
                    
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
                                El id esta vacio, ingresa uno porfavor
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
                        <label for="descripcion" class="form-label">Descripción</label>
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
                                Se mira bien!
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
                            <th scope="col">Descripción</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Operacion</th>
                        </tr>                       
                    </thead>                       
                    <div>                   
                    </div>
                   </tbody>
                </table>
            </div>
        </div>
    </div></div>
<div> 
    <div class="card text-side">
        <div class="card-body">
            <a href="index.jsp" class="btn btn-outline-primary" > Cerrar sesion</a>
        </div>
    </div>
</div>
<!-- Modal eliminar tarea-->
<div class="modal fade" id="eliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h2>¿Quieres eliminar la tarea?</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalConfirm" onclick="eliminarTarea()">Confirmar</button>
            </div>
        </div>
    </div>
</div>
<%@include file= "diseño/footer.jsp" %>