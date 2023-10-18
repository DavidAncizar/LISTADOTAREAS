<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Creamos la interfaz por medio de boostrap para la pagina principal-->
    <head>
       <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <!--Hacemos un CSS unico para la interfaz de registrar -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #003EFF;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
        h1{
            text-align: center;
        }
    </style>
    </head>
    <body>

        <!-- Colocamos las variables funcionales para el guardado de usuarios  -->
<form action="SvRegis" method="POST">

       
<form action="#" method="post">

     <h2> Bienvenido a tu registro, porfavor completa tus datos para continuar: </h2>
        <label for="nombre">Nombre:</label>
        <input type="text" id="username" name="nombre" required>

        <label for="cedula">Cédula:</label>
        <input type="text" id="username" name="cedula" required>

        <label for="password">Contraseña:</label>

        <input type="password" id="password" name="contrasenia" required>
        


        <button  type="submit">Registrarse</button>
    </form>

    </body>
</html>
