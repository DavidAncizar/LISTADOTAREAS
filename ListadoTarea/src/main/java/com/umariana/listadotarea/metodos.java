/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.listadotarea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author cuati
 */
public class metodos {
   public static ArrayList<usuario> nUsuario = new ArrayList<>();


    /**
     *
     * @param contexto
     * @param nUsuario
     */
    //Guardar usuarios creando una ruta con "relativePath"
    public static void leerUsuario(ServletContext context, ArrayList<usuario> nUsuario) throws IOException{
        String relativePath = "usuariosGuardados.txt";
        // Crear una ruta global 
        String Path = context.getRealPath(relativePath);
        //Variable de tipo file donde manejamos el archivo en codigo
        File archivo = new File (Path);
        PrintWriter pluma = new PrintWriter (new FileWriter (archivo, true ));
        for (usuario objUsuario: nUsuario ){
            pluma.println("nombre: " + objUsuario.getNombre());
            pluma.println("cedula: " + objUsuario.getCedula());
            pluma.println("contraseña: " + objUsuario.getContrasenia());

        }
         pluma.close();
    }
   
    public static  void cargarUsuario(ServletContext context, ArrayList<usuario> nUsuario ) {
     String relativePath =  "usuariosGuardados.txt";
     String Path = context.getRealPath(relativePath);
        File archivo = new File(Path);
        if (archivo.length()!=0) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo)) ) {
                String nombre= null;
                String cedula=null;
                String contrasenia=null;

                String leer;
                while ((leer = br.readLine()) != null) {
                    if (leer.startsWith("nombre:")) {
                        nombre = leer.substring(leer.indexOf(":") + 1).trim();
                    } else if (leer.startsWith("cedula:")) {
                        cedula = leer.substring(leer.indexOf(":") + 1).trim();
                    } else if (leer.startsWith("contraseña:")) {
                        contrasenia = leer.substring(leer.indexOf(":") + 1).trim();

                        // Crea un nuevo usuario y agrégalo a la lista de usuarios
                        usuario nuevoUsuario = new usuario(nombre, cedula , contrasenia);
                        nUsuario.add(nuevoUsuario);

                        // Restablece las variables para el siguiente usuario
                        nombre = null;
                        cedula = null;
                        contrasenia = null;
                    }

            }
         } catch (Exception e) {

                e.getMessage();
         }

    }
    }
     public static String ValidarUsuario( String nombre, String contrasenia, ServletContext context ) throws IOException{
              cargarUsuario(context);
              for (usuario objUsuario: nUsuario ) {
              if (objUsuario.getNombre().equals(nombre) && objUsuario.getContrasenia().equals(contrasenia)) {
                System.out.println("Se puede verificar en la consola de esta forma:" + objUsuario.getNombre());
                return objUsuario.getNombre();
              }
              return null;
              
              }
}
}           
          
    
            
    


