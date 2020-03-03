package model;


import java.io.*;
import java.util.*;

/**
 *  Clase que define la aplicación Temis
 *
 *  @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 *
 *  @version 3/03/2020
 */
public class Temis {
    private static Temis pTemis;
    private String usuarioAdmin = "admon";
    private String contrasenaAdmin = "adminadmin";
    private Usuario usuarioConectado = null;
    private boolean representanteLegal = false;
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Colectivo> colectivos = new HashMap<>();
    private Map<Integer, Proyecto> proyectos = new HashMap<>();


    /**
     *  Constructor de la clase Temis
     */
    private Temis() {
    }

    /**
     *  Devuelve instancia del singleton
     *  @return pTemis instancia de Temis
     */
    public Temis getInstance(){
        if(pTemis == null){
            pTemis = new Temis();
        }
        return pTemis;
    }

    /**
     *  Funcion que guarda en el archivo Temis.txt los usuarios, los colectivos y
     *  los proyectos existente en la aplicación.
     *  @throws IOException
     */
    public void escribirFichero() throws IOException {
        FileOutputStream fos = new FileOutputStream("Temis.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);


        // Escribe usuarios

        oos.writeObject(this.usuarios);

        // Escribe usuarios

        oos.writeObject(this.colectivos);

        // Escribe proyectos

        oos.writeObject(this.proyectos);

        oos.close();
    }

    /**
     *  Funcion que lee del archivo Temis.txt todos los objetos existentes de la aplicación
     *  @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void leerFichero() throws IOException {
        FileInputStream fis = new FileInputStream("Temis.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object objLeido;
        Map<Integer, Usuario> usuarioMap;
        Map<Integer, Colectivo> colectivoMap;
        Map<Integer, Proyecto> proyectoMap;


        try {
            // Leer usuarios

            objLeido = ois.readObject();
            usuarioMap = (Map<Integer, Usuario>) objLeido;
            pTemis.usuarios = usuarioMap;

            // Leer colectivos

            objLeido = ois.readObject();
            colectivoMap = (Map<Integer, Colectivo>) objLeido;
            pTemis.colectivos = colectivoMap;

            // Leer proyectos

            objLeido = ois.readObject();
            proyectoMap = (Map<Integer, Proyecto>) objLeido;
            pTemis.proyectos = proyectoMap;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
    }





}
