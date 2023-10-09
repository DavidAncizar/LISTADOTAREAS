/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.listadotarea;

import java.io.Serializable;

/**
 *
 * @author cuati
 */
public class usuario implements Serializable{
    //declarar variables
    private String nombre;
    private String cedula;
    private String contrasenia;

    public usuario(){ 
    
}
//constructor para inicializar los atributos
    public usuario(String nombre,String cedula, String contrasenia){
        this.nombre= nombre;
        this.cedula= cedula;
        this.contrasenia = contrasenia;        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

}
