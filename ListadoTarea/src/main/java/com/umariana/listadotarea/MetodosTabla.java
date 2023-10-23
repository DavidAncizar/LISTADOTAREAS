/**
 *
 * @author Ancizar y Juan David
 */
package com.umariana.listadotarea;

import java.io.Serializable;
import java.util.Date;

public class MetodosTabla implements Serializable {
    //Creamos el nodo principal
    private Nodo cabeza;
    //Creamos la clase del mismo Nodo
    private class Nodo implements Serializable {
        public Tabla tareaG;
    public Nodo siguiente = null;
    
    public Nodo(Tabla tareaG){
        this.tareaG = tareaG;  
    }
  
    }
    //REALIZAMOS TODOS  LOS METODOS PARA QUE PUEDA REALIZAR EL RECORRIDO DE LOS DATOS REGISTRADOS EN LA TABLA
    //Clase para la cabezera del Nodo
       public void InicioNodo(Tabla tareaG) {
        Nodo nodo = new Nodo(tareaG);
        nodo.siguiente = cabeza;
        cabeza = nodo;
    }
        public void finalNodo(Tabla tarea) {
        Nodo nodo = new Nodo(tarea);
        nodo.siguiente = cabeza;
        cabeza = nodo;
    }
    
       
      //Creamos un metodo para establecer un valor a cabeza y agregar el ultimo dato ingresado a el nodo  
    public void finalIngreso(Tabla tarea) {
        Nodo nodo = new Nodo(tarea);
        if (cabeza == null) {
            cabeza = nodo;  
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nodo;  
        }
    }
    
       
    //Metodo en el cual remplaza el dato del nodo anterior al nuevo
    public void finalDespues (int anteriorNi, Tabla newTask) {
        Nodo newNodo = new Nodo(newTask);
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.tareaG.getNi().equals(anteriorNi)) {
                newNodo.siguiente = actual.siguiente;
                actual.siguiente = newNodo;
                return; 
            }
            actual = actual.siguiente;
        }
    }
   
   
    //Metodo que actualiza el nodo y el ni de las tareas registradas
    public void finalAntes int anteriorNi, Tabla newTask) {
        Nodo newNodo = new Nodo(newTask);

        if (cabeza == null) {
        
            cabeza = newNodo;
            return;
        }
        
        if (cabeza.tareaG.getNi().equals(anteriorNi)) {
           
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
            return;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.tareaG.getNi() == anteriorNi) {
           
                anterior.siguiente = nuevoNodo;
                nuevoNodo.siguiente = actual;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }
    
       //Medimos la longitud del Nodo
    public int longitudNodo() {
        Nodo Presente = cabeza;
        int longitud = 0;
        while (Presente  != null) {
            Tabla tareaG = Presente.tareaG;
            longitud = longitud + 1;
            Presente  = Presente .siguiente;
        }
        return longitud;
    }
    //Creamos le metodo para eliminar la Tarea
    public void descartarTarea(int ni) {
        if (cabeza != null) {
            // Se elimina la primera tarea
            if (cabeza.tareaG.getNi().equals(ni)) {
                Nodo primer = cabeza;
                cabeza = cabeza.siguiente;
                primer.siguiente = null;
                //Retornamos para dejar de usar el metodo
                return;
            }
            //Creamos 2 nodos, uno viejo y otro nuevo
            Nodo anterior = cabeza;
            Nodo Presente  = cabeza.siguiente;

            while (Presente != null) {
                if (Presente.tareaG.getNi().equals(ni)) {
                    // Se hace el recorrido para entender la tarea
                    anterior.siguiente = Presente.siguiente;
                    Presente .siguiente = null;
                    //Retornamos
                    return; 
                }
                anterior = Presente ;
                Presente  = Presente .siguiente;
            }
        }
    }
            //Metodo para mostrar la Tarea registrada
    public  void mostrarTarea() {
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
        Nodo Presente = cabeza;

        while (Presente != null) {
            Tabla tareaG = Presente.tareaG;
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(tareaG.getNi()).append("</td>");
            tablaHTML.append("<td>").append(tareaG.getTitulo()).append("</td>");
            tablaHTML.append("<td>").append(tareaG.getDescripcion()).append("</td>");
            tablaHTML.append("<td>").append(tareaG.getFechaVencer()).append("</td>");

            // Iconos para agregar y elmininar
            tablaHTML.append("<a href=\"#\" class=\"btn btn-warning\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-nombre=\"<%= perro.getNombre()%>\"><i class=\"fa-solid fa-eye\"></i></a>");
            tablaHTML.append("<a href=\"#\" class=\"btn btn-warning eliminar\"  data-borrar=\"<%= perro.getNombre()%>\"><i class=\"fa-solid fa-trash\"></i></a>");
            
            tablaHTML.append("</tr>");
           Presente = Presente.siguiente;
        }
        tablaHTML.append("</table>"); 

        return tablaHTML.toString();
    }
 public boolean niExistente (int ni) {
        Nodo actual = cabeza;
        boolean encontrado = false;
        while (actual != null && !encontrado) {

            Tabla tareaG = actual.tareaG;
            if (tareaG.getNi().equals(ni)) {
                encontrado = true;
            }

            actual = actual.siguiente;
        }

        return encontrado;
    }

    public void tituloEdit(int ni, String titulo) {
         Nodo actual = cabeza;
        boolean encontrado = false;
        while (actual != null && !encontrado) {

            Tabla tareaG = actual.tareaG;
            if (tareaG.getNi().equals(ni)) {
                tareaG.setTitulo(titulo);
                encontrado = true;
            }
            actual = actual.siguiente;
        }

    }
    public void descripcionEdit(int ni, String descripcion) {
         Nodo actual = cabeza;
        boolean encontrado = false;
        while (actual != null && !encontrado) {

            Tabla tareaG = actual.tareaG;
            if (tareaG.getNi().equals(ni)) {
                tareaG.setDescripcion(descripcion);
                encontrado = true;
            }

            actual = actual.siguiente;
        }

    }
    public void fechaEdit(int ni, Date fecha) {
         Nodo actual = cabeza;
        boolean encontrado = false;
        while (actual != null && !encontrado) {

           Tabla tareaG = actual.tareaG;
            if (tareaG.getNi().equals(ni)) {
                tareaG.setFechaVencer(fecha);
                encontrado = true;
            }

            actual = actual.siguiente;
        }

    }
}

