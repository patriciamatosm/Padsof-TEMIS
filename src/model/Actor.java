package model;

import java.io.Serializable;
import java.util.*;

/**
 * Clase abstracta que engloba usuarios y colectivos
 *
 * @author Daniel Prieto Fernández
 * Silvia Tomey Prieto
 * Patricia Matos Meza
 * @version 3/03/2020
 */
public abstract class Actor implements Serializable {
    private String nombre;
    private List<Proyecto> listaProyecto = new ArrayList<>();

    public Actor(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Función que devuelve el nombre del actor
     * @return String con el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Funcion que devuelve una lista de proyectos apoyados por el actor
     * @return ArrayList<Proyecto> con los proyectos apoyados
     */
    public List<Proyecto> getListaProyecto() {
        return listaProyecto;
    }

    /**
     * Funcion que cambia el valor de la lista proyecto
     * @param listaProyecto a cambiar
     */
    public void setListaProyecto(List<Proyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    /**
     * Esta función es para añadir un proyecto a la lista de proyectos
     * @param proyecto que se añadira a la lista de proyectos
     */
    public void anadirProyecto(Proyecto proyecto) {
        this.listaProyecto.add(proyecto);
    }

}
