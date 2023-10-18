/**
 *
 * @author Ancizar y Juan David
 */
package com.umariana.listadotarea;

import java.io.Serializable;

public class MetodosTabla implements Serializable {
    //Creamos el nodo principal
    private Nodo cabeza;
    //Creamos la clase del mismo Nodo
    private class Nodo implements Serializable {
        public TareaGeneral tareaG;
    public Nodo siguiente = null;
    
    public Nodo(TareaGeneral tareaG){
        this.tareaG = tareaG;  
    }
  
    }
    //Clase para la cabezera del Nodo
       public void InicioNodo(TareaGeneral tareaG) {
        Nodo nodo = new Nodo(tareaG);
        nodo.siguiente = cabeza;
        cabeza = nodo;
    }
       //Medimos la longitud del Nodo
    public int longitudNodo() {
        Nodo Presente = cabeza;
        int longitud = 0;
        while (Presente  != null) {
            TareaGeneral tareaG = Presente.tareaG;
            longitud = longitud + 1;
            Presente  = Presente .siguiente;
        }
        return longitud;
    }
    //Creamos le metodo para eliminar la Tarea
    public void descartarTarea(int ni) {
        if (cabeza != null) {
            // Se elimina la primera tarea
            if (cabeza.tareaG.getNi() == ni) {
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
                if (Presente.tareaG.getNi() == ni) {
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
    public void mostrarTarea() {
        Nodo Presente  = cabeza;
        //Imprimimos cada variable y el mientras es para especificar que cuando muestre, solo sea en el caso en que el nodo no este vacio
        while (Presente  != null) {
            TareaGeneral tareaG = Presente.tareaG;
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
            TareaGeneral tareaG = Presente.tareaG;
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

}

