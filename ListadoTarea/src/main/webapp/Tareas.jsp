<%-- 
    Document   : Tareas
    Created on : 9 oct 2023, 22:48:04
    Author     : usuario
--%>



<!-- Inclución de la plantilla de header -->
<%@include file= "diseño/header.jsp" %>
<%String usuario = request.getParameter("usuario");%>

<!-- Navegador importado de bootstrap -->




<!-- Mensaje personalizado al ingresar -->

<div class="container p-4"> 
    <div class="card text-center">
        <div class="card-header">
        </div>

        <div class="card-body">
            <h5>Disfruta de tu estadía!, <%out.println(request.getParameter("nombre"));%></h5>
             <a href="index.jsp" class="btn btn-outline-primary" > Cerrar sesion</a>
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
                        <label for="validationCustomUsername" class="form-label">Numero identificativo de la Tarea</label>
                        <div class="input-group">
                            <span class="input-group-text" id="inputGroupPrepend"><i class="fa-solid fa-id-card"></i></span>
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

                    <!-- Titulo -->

                    <div class="input-group">
                        <div class="input-group">
                            <formu class="form-floating">
                                <input type="text" class="form-control" id="floatingInputValue" name="titulo" placeholder="Ingrese el Titulo" value="" required="">
                                <label for="floatingInputValue">Titulo</label>
                                <!-- Verificacion -->
                                <div class="valid-feedback">
                                   Excelente!
                                </div>
                                <!-- En caso de no tener nada -->
                                <div class="invalid-feedback">
                                  El titulo esta vacio, ingresa uno porfavor.
                                </div> 
                            </formu></div>
                    </div>

                    <!-- Descripcion -->

                    <div class="input-group">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fa-solid fa-circle-info"></i></span>
                            <textarea class="form-control" name="descripcion" id="validationTextarea" placeholder="Required example textarea" required></textarea>
                            <!-- Verificacion -->
                            <div class="valid-feedback">
                                Excelente!
                            </div>
                            <!-- En caso de no tener nada -->
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
                            <!-- Verificacion -->
                            <div class="valid-feedback">
                                Se mira bien!
                            </div>
                            <!-- En caso de no tener nada -->
                            <div class="invalid-feedback">
                                La fecha esta vacia, ingresala porfavor.
                            </div> 
                        </div>
                         
                    </div>

                    <!-- Boton centrado -->
                    <center>
                        <button type="submit" class="btn btn-outline-primary">Nueva Tarea</button>
                    </center>
                </form>
            </div>

        </div> 

        <div class="col-md-8">
            <!-- poner tabla aquí-->

            <div class="card card-body">
                <table class="table table-striped table-hover">
                    <!-- Primera fila -->   
                    <thead>
                        <tr>
                            <th scope="col">NI Tarea</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Evidencia</th>
                        </tr>
                       
                    </thead>

            
                  
                        
                    <div>
                   
                    </div>

                    </tbody>
                </table>
            </div>
        </div>
    </div></div>

<!-- Modal eliminar tarea-->
<div class="modal fade" id="eliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h2>¿Estás seguro de que deseas eliminar esta tarea?</h2>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalConfirm" onclick="eliminarTarea()">Eliminar</button>
            </div>
        </div>
    </div>
</div>

<!-- Inclución de la plantilla de footer -->
<%@include file= "diseño/footer.jsp" %>