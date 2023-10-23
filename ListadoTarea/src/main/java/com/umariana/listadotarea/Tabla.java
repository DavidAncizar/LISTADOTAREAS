
/**
 *
 * @author ancizar y Juan David
 */

package com.umariana.listadotarea;
import java.io.Serializable;
import java.util.Date;


public class Tabla implements Serializable {
       private String ni;
    private String titulo;
    private String descripcion;
    private Date fechaVencer;
    
    public Tabla(String ni , String titulo, String descripcion, Date fechaVencer) {
        this.ni = ni;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencer = fechaVencer;
    }

    public Tabla() {
    }

    public String getNi() {
        return ni;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVencer() {
        return fechaVencer;
    }

    public void setFechaVencer(Date fechaVencer) {
        this.fechaVencer= fechaVencer;
    }
    
}

