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
     public static void ingresarArchivo(MetodosTabla tareasHechas, ServletContext context) throws FileNotFoundException, IOException {
        // Ingresamos la ruta la cual se va a colocar los archivos ingresados
        String rutaTareas= "/datos/tareasAgregadas.ser";
        String rutaFinal= context.getRealPath(rutaTareas);
        //Creamos el file para ingresar los datos 
        File archivo = new File(rutaFinal);

        try (FileOutputStream bx = new FileOutputStream(archivo); ObjectOutputStream bc = new ObjectOutputStream(bx)) {
            // Aqui se realiza la serializacion y se escriben los datos
            bc.writeObject(tareasHechas);
        } catch (IOException e) {
            System.out.println("Los archivos del texto no han sido registrados");
        }
    }

    public static MetodosTabla lecturaTarea(ServletContext context) throws IOException, ClassNotFoundException {
         MetodosTabla tareasHechas = null;
        // Ruta relativa y absoluta del archivo de datos serializados
        String rutaTareas= "/datos/tareasAgregadas.ser";
        String rutaFinal = context.getRealPath(rutaTareas);
        File archivo = new File(rutaFinal);

        if (archivo.exists() && archivo.isFile()) {
            try (FileInputStream ola = new FileInputStream(archivo); ObjectInputStream bb = new ObjectInputStream(ola)) {
                // Aqui se deserializa y hace lectible los datos
              tareasHechas = (MetodosTabla) bb.readObject();
              //Agregamos excepciones
            } catch (EOFException e) {  
                System.out.println("El archivo no contiene ninguna información.");
            } catch (IOException e) {
                System.out.println("No se pudo leer los archivos.");
            }
        } else {
            System.out.println("No hay ningun archivo de datos.");
        }

        return tareasHechas; 
    }

}
