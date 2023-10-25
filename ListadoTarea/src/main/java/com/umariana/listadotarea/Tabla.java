
/**
 *
 * @author ancizar y Juan David
 */

package com.umariana.listadotarea;
import java.util.Date;


public class Tabla {
    private int ni;
    private String titulo;
    private String descripcion;
    private Date fechaVencer;
    
    public Tabla(int ni , String titulo, String descripcion, Date fechaVencer) {
        this.ni = ni;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencer = fechaVencer;
    }

    public Tabla() {
    }

    public int getNi() {
        return ni;
    }

    public void setNi(int ni) {
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

