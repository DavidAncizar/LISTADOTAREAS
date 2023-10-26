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
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;

public class MetodosTabla {
    public Nodo cabeza = null;
    public Nodo pies = null;

    // Clase interna Nodo que representa un elemento de la lista
    public class Nodo {

        public Tabla nTarea;
        public Nodo siguiente;

        public Nodo(Tabla nTarea) {
            this.nTarea = nTarea;
            this.siguiente = null;
        }
    }
    
    
    // Boolean que verifica si la lista esta vacia
    public boolean verificarContenido() {
        return cabeza == null;
    }

    // Método para agregar una nueva tarea al comienzo de la lista
    public void agregarInicio(Tabla nTarea) {
        Nodo nuevoNodo = new Nodo(nTarea);

        if (cabeza == null) {
            // Si la lista está vacía, el nuevo nodo es tanto el inicio como el fin
            cabeza = nuevoNodo;
            cabeza = nuevoNodo;
        } else {
            // Si no está vacía, el nuevo nodo se agrega al comienzo y se actualiza el inicio
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }

    // Método para agregar una nueva tarea al final de la lista
    public void agregarFinal(Tabla nTarea) {
        Nodo nuevoNodo = new Nodo(nTarea);

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

    /**
     * Adiciona una tarea a la lista de tareas antes de la tarea con el id
     * especificado.
     *
     * @param id El id de la tarea antes de la cual se va a insertar la nueva
     * tarea.
     * @param tarea La tarea que se va a agregar.
     */
    public void agregarAntesDe(String ni, Tabla nTarea) {
        if (cabeza == null) {
            // Puedes manejar esto de alguna manera, por ejemplo, lanzar una excepción o manejar el caso especial.
            // throw new NoExisteException(id);
            return;
        } else if (ni.equals(cabeza.nTarea.getNi())) {
            Nodo nuevoNodo = new Nodo(nTarea);
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            Nodo anterior = localizarNi(ni);
            if (anterior == null) {
                // Puedes manejar esto de alguna manera, por ejemplo, lanzar una excepción o manejar el caso especial.
                // throw new NoExisteException(id);
                return;
            }
            Nodo nuevoNodo = new Nodo(nTarea);
            nuevoNodo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevoNodo;
        }
    }

    /**
     * Adiciona una tarea a la lista de tareas después de la tarea con el id
     * especificado.
     *
     * @param id El id de la tarea después de la cual se va a insertar la nueva
     * tarea.
     * @param tarea La tarea que se va a agregar.
     */
    public void agregarDespuesDe(String ni, Tabla nTarea) {
        Nodo anterior = buscarNi(ni);

        if (anterior == null) {
            // Puedes manejar esto de alguna manera, por ejemplo, lanzar una excepción o manejar el caso especial.
            // throw new NoExisteException(id);
            return;
        } else {
            Nodo nuevoNodo = new Nodo(nTarea);
            nuevoNodo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevoNodo;
        }
    }

    /**
     * Busca la tarea con el id dado en la lista de tareas.
     *
     * @param id El id de la tarea que se va a buscar.
     * @return La tarea con el id especificado. Si la tarea no existe, se
     * retorna null.
     */
    public Nodo buscarNi(String ni) {
        Nodo actual = cabeza;
        while (actual != null && actual.nTarea.getNi() != ni) {
            actual = actual.siguiente;
        }
        return actual;
    }

    /**
     * Busca la tarea anterior a la tarea con el id especificado.
     *
     * @param id El id de la tarea de la cual se desea encontrar la tarea
     * anterior.
     * @return La tarea anterior a la tarea con el id dado. Se retorna null si
     * la tarea con el id dado no existe o si es la primera de la lista.
     */
    public Nodo localizarNi(String ni) {
        Nodo anterior = null;
        Nodo actual = cabeza;

        while (actual != null && actual.nTarea.getNi() != ni) {
            anterior = actual;
            actual = actual.siguiente;
        }

        return (actual != null) ? anterior : null;
    }

    //Metodo que elimina una tarea
    public void eliminarTarea(String ni) {
        if (cabeza == null) {
            System.out.println("La lista de tareas está vacía, no se pudo eliminar la tarea con id: " + ni);
            return;
        }

        if (ni == cabeza.nTarea.getNi()) {
            // La tarea es la primera de la lista
            cabeza = cabeza.siguiente;
        } else {
            // La tarea es un elemento intermedio de la lista
            Nodo anterior = localizarNi(ni);
            if (anterior == null) {
                System.out.println("No se encontró una tarea con id: " + ni + " para eliminar.");
                return;
            }
            anterior.siguiente = anterior.siguiente.siguiente; // Desconectar la tarea
        }

    }

    // Este metodo verifica la existencia de una tarea con una ID
    public boolean tareaExistente(String ni) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.nTarea.getNi() == ni) {
                return true; // La tarea con el ID proporcionado ya existe
            }
            actual = actual.siguiente;
        }

        return false; // No se encontró una tarea con el ID proporcionado

    }
    
    
    //Metodo que permite editar los datos de la Tarea con excepcion de la ID
    public void editarTarea(String ni, String nTitulo, String nDescripcion, String nFecha) {
        Nodo tareaExistente = buscarNi(ni);

        if (tareaExistente != null) {
            // Actualiza los atributos de la tarea
            tareaExistente.nTarea.setTitulo(nTitulo);
            tareaExistente.nTarea.setDescripcion(nDescripcion);

            // Convierte la cadena de fecha en un objeto Date
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date nuevaFecha = dateFormat.parse(nFecha);
                tareaExistente.nTarea.setFechaVencer(nuevaFecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para guardar la lista en un archivo de texto
    public static void guardarTabla(MetodosTabla listaActualizada, ServletContext context) {
        // Ruta relativa
        String rutaRelativa = "/data/nuevaTareas.txt";

        // Ruta absoluta
        String rutaAbsoluta = context.getRealPath(rutaRelativa);

        File file = new File(rutaAbsoluta);

        try (PrintWriter writer = new PrintWriter(file)) {
            Nodo temp = listaActualizada.cabeza;
            while (temp != null) {
                Tabla tarea = temp.nTarea;
                // Guarda los atributos de la tarea en el archivo separados por un punto y coma
                writer.println(tarea.getNi() + ";" + tarea.getTitulo() + ";" + tarea.getDescripcion() + ";" + tarea.getFechaVencer());
                temp = temp.siguiente;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para leer una lista desde un archivo de texto
    public static MetodosTabla leerTabla(ServletContext context) {
        // Ruta relativa
        String rutaRelativa = "/data/nuevaTareas.txt";

        // Ruta absoluta
        String rutaAbsoluta = context.getRealPath(rutaRelativa);

        File file = new File(rutaAbsoluta);
        MetodosTabla lista = new MetodosTabla();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] atributos = line.split(";");
                if (atributos.length == 4) {
                    String ni = atributos[0];
                    String titulo = atributos[1];
                    String descripcion = atributos[2];
                    String fechaVStr = atributos[3];

                    // Realizar el parsing de la fecha desde la cadena
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaV = dateFormat.parse(fechaVStr);

                    Tabla tarea = new Tabla(ni, titulo, descripcion, fechaV);
                    lista.agregarInicio(tarea);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

