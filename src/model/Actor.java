package model;

import java.util.ArrayList;

/**
 * Clase abstracta que engloba usuarios y colectivos
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 *
 * @version 3/03/2020
 */
public abstract class Actor {
    private String nombre;
    private ArrayList<Proyecto> listaProyecto = new ArrayList<Proyecto>();

    public Actor(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Esta función es para añadir un proyecto a la lista de proyectos
     *
     * @param proyecto que se añadira a la lista de proyectos
     */
    public void anadirProyecto(Proyecto proyecto) {
        this.listaProyecto.add(proyecto);
    }
}
