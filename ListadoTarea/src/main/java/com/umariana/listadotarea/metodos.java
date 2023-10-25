/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.listadotarea;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author cuati
 */
public class metodos {
   

    
    /**
     *
     * @param contexto
     * @param nUsuario
     */
    //Guardar usuarios creando una ruta con "relativePath"
    public static String leerUsuario(int cedula, String contrasenia, ServletContext context) throws IOException{
        ArrayList<usuario> nUsuario = new ArrayList<>();
        MetPersistencia.lecturaArchivo(nUsuario, context); //Llenamos el array con el txt
        
        for (usuario user: nUsuario){ //Recorremos el array 
            //En caso de hallar coincidencias de cedula y contraseña en un mismo objeto
            if (user.getCedula() == (cedula) && user.getContrasenia().equals(contrasenia)){
                return user.getNombre(); //Se envia el nombre
            }
        }
        return "No encontrado"; //Si no se encontró coincidencias se envia el no encontrado.
    }
    public static  boolean cargarUsuario(int cedula,ServletContext context ) throws IOException {
         ArrayList<usuario> nUsuario = new ArrayList<>(); //Creamos array que se llenara con el txt

        MetPersistencia.lecturaArchivo(nUsuario, context);//Llenamos el array con el txt;
        
        for (usuario user: nUsuario ){ //Recorremos el array
             //En caso de hallar una cedula igual
            if (user.getCedula()==(cedula)){
                return false; //Se devuelve false
            }
        }
        return true; //Si no se encontró coincidencias se envia true.
    }

    }
          
    
            
    


