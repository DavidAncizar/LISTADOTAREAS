/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.listadotarea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author cuati
 */
public class metodos {
   
    ArrayList<usuario> nUsuario = new ArrayList<>();
    
     public void setnUsuario(ArrayList<usuario> nUsuario) {
        this.nUsuario = nUsuario;
    }

    public ArrayList<usuario> getnUsuario() {
        return nUsuario;
    }
    /**
     *
     * @param contexto
     * @param nUsuario
     */
    public static void registrarUsuario(ServletContext contexto,ArrayList<usuario> nUsuario){
        String dataPath = contexto.getRealPath("/data.txt");
        File arc = new File(dataPath);
        try {
            FileOutputStream fw = new FileOutputStream(dataPath);
            ObjectOutputStream pw = new ObjectOutputStream(fw);
            pw.writeObject(nUsuario);
            pw.close();
            System.out.println("has cargado los datos" + dataPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ups hubo un error"+ e.getMessage());
        }
    }
    }


