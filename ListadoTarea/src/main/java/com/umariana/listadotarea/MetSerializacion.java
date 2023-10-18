/**
 *
 * @Ancizar y Juan David
 */
package com.umariana.listadotarea;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;

public class MetSerializacion {
     public static void ingresarArchivo(MetodosTabla listaTareas, ServletContext context) throws FileNotFoundException, IOException {
        // Ingresamos la ruta la cual se va a colocar los archivos ingresados
        String rutaTareas= "/data/NuevasTareas.ser";
        String rutaFinal= context.getRealPath(rutaTareas);
        //Creamos el file para ingresar los datos 
        File archivo = new File(rutaFinal);

        try (FileOutputStream bx = new FileOutputStream(archivo); ObjectOutputStream bc = new ObjectOutputStream(bx)) {
            // Serializar y escribir la lista enlazada en el archivo
            bc.writeObject(listaTareas);
        } catch (IOException e) {
            System.out.println("Los archivos del texto no han sido registrados");
        }
    }

    public static MetodosTabla leerTareas(ServletContext context) throws IOException, ClassNotFoundException {
        MetodosTabla tasks = null;
        // Ruta relativa y absoluta del archivo de datos serializados
        String rutaRelativa = "/data/NuevasTareas.ser";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        if (archivo.exists() && archivo.isFile()) {
            try (FileInputStream ola = new FileInputStream(archivo); ObjectInputStream bb = new ObjectInputStream(ola)) {
                // Leer y deserializar la lista enlazada desde el archivo
              tasks = (MetodosTabla) bb.readObject();
              //Agregamos excepciones
            } catch (EOFException e) {  
                System.out.println("El archivo no contiene ninguna información.");
            } catch (IOException e) {
                System.out.println("No se pudo leer los archivos.");
            }
        } else {
            System.out.println("No hay ningun archivo de datos.");
        }

        return tasks;
    }

}
