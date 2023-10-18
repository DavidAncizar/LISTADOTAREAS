/**
 *
 * @author Ancizar y Juan David
 */
package com.umariana.listadotarea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class MetPersistencia {
        public static void datosArchivo(ArrayList<usuario> nUsuario, ServletContext context) throws FileNotFoundException {

        // Ubicación del archivo de datos
        String ruta = "/data/usuariosGuardados.txt";
        String rutaFinal = context.getRealPath(ruta);
        File archivo = new File(rutaFinal);

        try (PrintWriter pluma = new PrintWriter(archivo)) {
            //Se escriben los datos en le archivo
            for (usuario UsuariosG : nUsuario) {
                // Imprimir los datos en un solo renglon.
                String Datos = UsuariosG.getCedula() + "," +  UsuariosG.getNombre() + "," + UsuariosG.getContrasenia();
                pluma.println(Datos);
            }
            //Excepcion común
        } catch (FileNotFoundException e) {
            System.out.println("No se ha econtrado ningun archivo con los datos :(.");
        }
    
}
}
