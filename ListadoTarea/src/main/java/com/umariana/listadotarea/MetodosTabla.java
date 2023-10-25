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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;

public class MetodosTabla {
    //Creamos el nodo principal
    public Nodo cabeza = null;
    public Nodo pies = null;
    //Creamos la clase del mismo Nodo
    private class Nodo  {
    public Tabla tareaG;
    public Nodo siguiente;
    
    public Nodo(Tabla tareaG){
        this.tareaG = tareaG;  
        this.siguiente = null;
    }
  
    }
    public boolean verificarContenido() {
        return cabeza == null;
    }
    //REALIZAMOS TODOS  LOS METODOS PARA QUE PUEDA REALIZAR EL RECORRIDO DE LOS DATOS REGISTRADOS EN LA TABLA
    //Clase para la cabezera del Nodo
       public void inicioNodo(Tabla tareaG) {
        Nodo nuevoNodo = new Nodo(tareaG);

        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo es tanto el inicio como el fin
            cabeza = nuevoNodo;
            pies = nuevoNodo;
        } else {
            // Si no está vacía, el nuevo nodo se agrega al comienzo y se actualiza el inicio
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }
         
      //Creamos un metodo para establecer un valor a cabeza y agregar el ultimo dato ingresado a el nodo  
   
    
    public void agregarInicio(Tabla tareaG) {
        Nodo nuevoNodo = new Nodo(tareaG);

        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo es tanto el inicio como el fin
            cabeza = nuevoNodo;
            pies = nuevoNodo;
        } else {
            // Si no está vacía, el nuevo nodo se agrega al comienzo y se actualiza el inicio
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }
      public void agregarFinal(Tabla tareaG) {
        Nodo nuevoNodo = new Nodo(tareaG);

        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo es tanto el inicio como el fin
            cabeza = nuevoNodo;
            pies = nuevoNodo;
        } else {
            // Si no está vacía, el nuevo nodo se agrega al final y se actualiza el fin
            pies.siguiente = nuevoNodo;
            pies = nuevoNodo;
        }
    }
     public void agregarDespues(int ni, Tabla tareaG) {
        Nodo anterior = localizarNi(ni);

        if (anterior == null) {
           
            return;
        } else {
            Nodo nuevoNodo = new Nodo(tareaG);
            nuevoNodo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevoNodo;
        }
    }
      public void agregarAntes(int ni, Tabla tareaG) {
        if (cabeza == null) {
            // Puedes manejar esto de alguna manera, por ejemplo, lanzar una excepción o manejar el caso especial.
            // throw new NoExisteException(id);
            return;
        } else if (ni == cabeza.tareaG.getNi()) {
            Nodo nuevoNodo = new Nodo(tareaG);
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            Nodo anterior = localizarAnterior(ni);
            if (anterior == null) {
                // Puedes manejar esto de alguna manera, por ejemplo, lanzar una excepción o manejar el caso especial.
                // throw new NoExisteException(id);
                return;
            }
            Nodo nuevoNodo = new Nodo(tareaG);
            nuevoNodo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevoNodo;
        }
    }
   public Nodo localizarNi(int ni) {
        Nodo actual = cabeza;
        while (actual != null && actual.tareaG.getNi() != ni) {
            actual = actual.siguiente;
        }
        return actual;
    }
   
   
      public Nodo localizarAnterior(int ni) {
        Nodo anterior = null;
        Nodo actual = cabeza;

        while (actual != null && actual.tareaG.getNi() != ni) {
            anterior = actual;
            actual = actual.siguiente;
        }

        return (actual != null) ? anterior : null;
    }
       //Medimos la longitud del Nodo
    
    public void eliminarTarea(int ni) {
        if (cabeza == null) {
            System.out.println("La lista de tareas está vacía, no se pudo eliminar la tarea con id: " + ni);
            return;
        }

        if (ni == cabeza.tareaG.getNi()) {
            // La tarea es la primera de la lista
            cabeza = cabeza.siguiente;
        } else {
            // La tarea es un elemento intermedio de la lista
            Nodo anterior = localizarAnterior(ni);
            if (anterior == null) {
                System.out.println("No se encontró una tarea con id: " + ni + " para eliminar.");
                return;
            }
            anterior.siguiente = anterior.siguiente.siguiente; // Desconectar la tarea
        }

    }
            //Metodo para mostrar la Tarea registrada
    public  void cargarTarea() {
        Nodo Presente  = cabeza;
        //Imprimimos cada variable y el mientras es para especificar que cuando muestre, solo sea en el caso en que el nodo no este vacio
        while (Presente  != null) {
            Tabla tareaG = Presente.tareaG;
            System.out.println("NI: " + tareaG.getNi());
            System.out.println("Título: " + tareaG.getTitulo());
            System.out.println("Descripción: " + tareaG.getDescripcion());
            System.out.println("Fecha de Vencimiento: " + tareaG.getFechaVencer());
            System.out.println("-----------");
            Presente  = Presente.siguiente;
        }
    }
    
    public String TablaCreada() {
        StringBuilder tablaHTML = new StringBuilder();
        Nodo actual = cabeza;
        if(actual!= null){
        while (actual != null) {
            Tabla tarea = actual.tareaG;
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(tarea.getNi()).append("</td>");
            tablaHTML.append("<td>").append(tarea.getTitulo()).append("</td>");
            tablaHTML.append("<td>").append(tarea.getDescripcion()).append("</td>");
            tablaHTML.append("<td>").append(tarea.getFechaVencer()).append("</td>");

            // Botones
           tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editar\" data-nombre=\"" + tarea.getNi() + "\"><i class=\"fa-solid fa-pen-clip\"></i></a>");
           tablaHTML.append("<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + tarea.getNi() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>");

            tablaHTML.append("</tr>");
            actual = actual.siguiente;
        }} else if(actual==null){
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>No hay tareas registradas</td>");       
            tablaHTML.append("<td></td>");  
            tablaHTML.append("<td></td>");  
            tablaHTML.append("<td></td>");  
            tablaHTML.append("<td><a href=\"#\" type=\"button\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-pen-clip\"></i></a>");
           tablaHTML.append("<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></a></td>");
            tablaHTML.append("</tr>");
        }
        tablaHTML.append("</table>"); 

        return tablaHTML.toString();
    }
  public boolean tareaExiste(int ni) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.tareaG.getNi() == ni) {
                return true; // La tarea con el ID proporcionado ya existe
            }
            actual = actual.siguiente;
        }

        return false; // No se encontró una tarea con el ID proporcionado

    }

    // Método para guardar la lista en un archivo de texto
    public static void guardarTarea(MetodosTabla tablaG , ServletContext context) {
        // Ruta relativa
        String rutaRelativa = "/data/NuevaTarea.txt";

        // Ruta absoluta
        String rutaAbsoluta = context.getRealPath(rutaRelativa);

        File file = new File(rutaAbsoluta);

        try (PrintWriter writer = new PrintWriter(file)) {
            Nodo temp = tablaG.cabeza;
            while (temp != null) {
                Tabla tareaG = temp.tareaG;
                // Guarda los atributos de la tarea en el archivo separados por un punto y coma
                writer.println(tareaG.getNi() + ";" + tareaG.getTitulo() + ";" + tareaG.getDescripcion() + ";" + tareaG.getFechaVencer());
                temp = temp.siguiente;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para leer una lista desde un archivo de texto
    public static MetodosTabla leerTarea(ServletContext context) throws ParseException {
        // Ruta relativa
        String rutaRelativa = "/data/NuevaTarea.txt";

        // Ruta absoluta
        String rutaAbsoluta = context.getRealPath(rutaRelativa);

        File file = new File(rutaAbsoluta);
        MetodosTabla tarea = new MetodosTabla();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] atributos = line.split(";");
                if (atributos.length == 4) {
                    int ni = Integer.parseInt(atributos[0]);
                    String titulo = atributos[1];
                    String descripcion = atributos[2];
                    String fechaVStr = atributos[3];

                    // Realizar el parsing de la fecha desde la cadena
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaVencer = dateFormat.parse(fechaVStr);

                    Tabla tareaG = new Tabla(ni, titulo, descripcion, fechaVencer);
                    tarea.inicioNodo(tareaG);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tarea;
    }
}

