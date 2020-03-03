package model;

import java.util.ArrayList;

/**
 * Clase que define a un colectivo
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 *
 * @version 3/03/2020
 */
public class Colectivo extends actor {
    private String descripcion;
    private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

    public Colectivo(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Función que devuelve la descripción de un colectivo
     *
     * @return String con la descripción del colectivo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Función que modifica la descripción de un colectivo
     *
     * @param descripcion del proyecto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Función que añade un usuario a un colectivo
     *
     * @param usuario que se unira al colectivo si no estaba anteriormente
     *
     * @return true si se añade el usuario a la lista
     *         false si ya estaba en el colectivo
     */
    public boolean unirse(Usuario usuario) {
        if (this.listaUsuario.contains(usuario)) {
            return false;
        }
        else {
            this.listaUsuario.add(usuario);
            return true;
        }
    }

    /**
     * Función que quita a un usuario de la lista de usuarios del colectivo
     *
     * @param usuario que se quiere eliminar de un colectivo
     *
     * @return true si se ha eliminado el usuario de la lista
     *         false si no estaba en la lista
     */
    public boolean abandonar(Usuario usuario) {
        if (this.listaUsuario.contains(usuario)) {
            this.listaUsuario.remove(usuario);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Función que añade a un colectivo a los colectivos de los que quiere recibir noticias un usuario
     *
     * @param usuario que recibira las noticias del colectivo
     *
     * @return true si se añade el usuario correctamente
     *         false si el usuario no esta en el colectivo o no se puede añadir a la lista
     */
    public boolean subscribirseNoticias(Usuario usuario) {
        if (this.listaUsuario.contains(usuario)) {
            return usuario.anadirColectivoSubscrito(this);
        }
        else {
            return false;
        }
    }
}
