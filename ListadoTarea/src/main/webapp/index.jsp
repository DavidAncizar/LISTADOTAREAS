<%-- 
    Document   : Index
    Created on : 9 oct 2023, 22:50:09
    Author     : usuario
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"><!-- comment -->
        <style>
            .divider:after,
            .divider:before {
                content: "";
                flex: 1;
                height: 1px;
                background: #eee;
            }
            .h-custom {
                height: calc(100% - 73px);
            }
            @media (max-width: 450px) {
                .h-custom {
                    height: 100%;
                }
            }
        </style>
    </head>
    <body>
        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <img src="https://img.freepik.com/vector-premium/lista-tareas-hoja-papel-documento-tarea-grande-casilla-verificacion-plano_277904-1087.jpg"
                             class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">

                        <form action = "SvVerificacion" method = "POST">        

                        <form>        

                            <h1>Iniciar sesion</h1>
                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form3Example3" >Nombre </label> 

                                <input type="text" name="nombre" id="form3Example3" class="form-control form-control-lg"

                                <input type="text" id="form3Example3" class="form-control form-control-lg"

                                       placeholder="ingrese su nombre" required >
                                
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-3">
                                 <label class="form-label" for="form3Example4">Contraseña </label>

                                <input type="password" name="contrasenia" id="form3Example4" class="form-control form-control-lg"

                                <input type="password" id="form3Example4" class="form-control form-control-lg"

                                       placeholder="ingrese su contraseña" required >
                               
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
           
                            <div class="d-flex justify-content-between align-items-center">

                                </div>           
                            </div>

                            <div class="text-center text-lg-start mt-4 pt-2">
                             
                                <button type="submit" class="btn btn-primary btn-lg" href = "Tareas.jsp" class="link-danger"> Iniciar Sesion  </button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">¿No tienes cuenta? <a href="registrar.jsp"
                                                                                              class="link-danger">Registrate</a></p>
                            </div>


                        </form>
                    </div>
                </div>
            </div>
            <div
                class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
            </div>

        </section>  

        </section>

        <!-- Section: Design Block -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    </body>
</html>
