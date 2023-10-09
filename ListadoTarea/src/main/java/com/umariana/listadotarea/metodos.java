/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.listadotarea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
    public static void leerUsuario(ServletContext contexto,ArrayList<usuario> nUsuario){
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
    public static ArrayList<usuario> cargarUsuario(ServletContext contexto) throws IOException, ClassNotFoundException{
     
        ArrayList<usuario> nUsuario = new ArrayList<>();
        String p="/data.txt";
        String dataPath= contexto.getRealPath(p);
        File arc = new File(dataPath);
        System.out.println("este archivo esta guardado en: " +dataPath);
        
        try {
            FileInputStream in = new FileInputStream(dataPath);
            ObjectInputStream ois = new ObjectInputStream(in);
            
            nUsuario = (ArrayList<usuario>) ois.readObject();
            ois.close();
            System.out.println("se ha leido correctamente");
        } catch (FileNotFoundException ex) {
            System.out.println("hubo un error al leer");
        }
        return nUsuario;     
    }
    
            
    }


