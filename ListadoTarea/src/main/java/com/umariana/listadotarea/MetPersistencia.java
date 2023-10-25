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
        String ruta = "usuariosGuardados.txt";
        String rutaFinal = context.getRealPath(ruta);
        File archivo = new File(rutaFinal);

        try (PrintWriter pluma = new PrintWriter(archivo)) {
            //Se escriben los datos en le archivo
            for (usuario UsuariosG : nUsuario) {
                // Imprimir los datos en un solo renglon.
                String Datos = UsuariosG.getCedula() + "," +  UsuariosG.getNombre() + "," + UsuariosG.getContrasenia();
                pluma.println(Datos);
            }
            //Excepcion 
        } catch (FileNotFoundException e) {
            System.out.println("No se ha econtrado ningun archivo con los datos D:.");
        }
    
}
          public static void lecturaArchivo (ArrayList<usuario> nUsuario, ServletContext context) throws FileNotFoundException, IOException {

        // Ubicación del archivo de datos
        String rutaRelativa = "usuariosGuardados.txt";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        try (FileReader fr = new FileReader(archivo); BufferedReader lector = new BufferedReader(fr)) {

            String linea;
            // Leer cada línea del archivo y procesar los datos

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                //funcion para saltar de espacio, el trim se usa para evitar problemas de escritura con espacios
                String cedula = datos[0].trim();
                String nombre = datos[1].trim();
                String contrasenia = datos[2].trim();

                // Se crea y se enlista los datos ingresados
                usuario usuarioo = new usuario(nombre, Integer.parseInt(cedula) , contrasenia);
                nUsuario.add(usuarioo);
            }
        

        } catch (FileNotFoundException e) {
            System.out.println("No se ha econtrado ningun archivo con los datos :(.");
        } catch (IOException e) {
            System.out.println("Vaya, parece que ocurrio un error en la lectura de datos!");
        }
    }

}
