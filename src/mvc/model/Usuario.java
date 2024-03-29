package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que define a un Usuario
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 6/03/2020
 */
@SuppressWarnings("unused")
public class Usuario extends Actor implements Serializable {
    private String dni;
    private String contrasena;
    private List<Colectivo> colectivosPropios;
    private List<Colectivo> suscritoNoticias;
    private List<Notificacion> notificaciones;
    private boolean logueado = false;
    private EstadoUsuario estado;


    /**
     * Constructor de la clase Usuario
     *
     * @param nombre     nombre dekl usuario
     * @param dni        dni del usuario
     * @param contrasena contraseña del usuario
     */
    public Usuario(String nombre, String dni, String contrasena) {
        super(nombre);
        this.dni = dni;
        this.contrasena = contrasena;
        this.suscritoNoticias = new ArrayList<>();
        this.colectivosPropios = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.estado = EstadoUsuario.EN_ESPERA;
    }

    /**
     * Enumeracion que indica el estado del usuario
     */
    public enum EstadoUsuario {
        ACTIVO, EN_ESPERA, BLOQUEADO
    }

    /**
     * Funcion que devuelve los colectivos que ha creado el usuario
     * @return lista de colectivos
     */
    public List<Colectivo> getColectivosPropios() {
        return colectivosPropios;
    }

    /**
     * Añadir colectivo propio
     * @param c colectivo a ser añadido
     */
    public void addColectivosPropios(Colectivo c) {
        if (!this.colectivosPropios.contains(c)) {
            this.colectivosPropios.add(c);
        }
    }

    /**
     * Funcion que devuelve la lista de notificaciones del usuario
     * @return Lista de tipo Notificacion
     */
    public List<Notificacion> getNotificaciones(){ return notificaciones;}

    /**
     * Funcion que añade una Notificacion a la lista del Usuario
     * @param n Notificacion
     */
    public void addNotificacion(Notificacion n){
        if(!this.notificaciones.contains(n)){
            this.notificaciones.add(n);
        }
    }

    /**
     * Funcion que devuelve el estado de los usuarios
     * @return estado estado del usuario
     */
    public EstadoUsuario getEstado() {
        return estado;
    }

    /**
     * Funcion que devuelve el dni del usuario
     *
     * @return dni del usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * Funcion que devuelve la contrasena del usuario
     *
     * @return contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Funcion que devuelve el array que contiene los colectivos a los que esta suscrito el usuario
     * @return Array de colectivos a los que se está suscrito
     */
    public List<Colectivo> getSuscritoNoticias() {
        return suscritoNoticias;
    }

    /**
     * Funcion que añade un colectivo al que este suscrito el usuario
     * @param c Colectivo al que esta suscrito el usuario
     */
    public void addSuscritoNoticias(Colectivo c) {
        if (!this.suscritoNoticias.contains(c)) {
            this.suscritoNoticias.add(c);
        }
    }

    /**
     * Funcion que indica si el usuario está logueado en la aplicación
     * @return True/False dependiendo de si está logueado o no
     */
    public boolean isLogueado() {
        return logueado;
    }

    /**
     * Funcion que te permite cambiar el flag de logueado
     * @param logueado valor al que se va a cambiar el flag
     */
    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    /**
     * Funcion que cambia el estado del usuario a BLOQUEADO
     */
    public void bloquearUsuario(){
        this.estado = EstadoUsuario.BLOQUEADO;
    }


    /**
     * Funcion que cambia el estado del usuario a ACTIVO
     */
    public void desbloquearUsuario(){
        if(this.estado == EstadoUsuario.BLOQUEADO) this.estado = EstadoUsuario.ACTIVO;
    }


    /**
     * Funcion que activa un usuario
     */
    public void aceptarRegistro(){
       if(this.estado == EstadoUsuario.EN_ESPERA) this.estado = EstadoUsuario.ACTIVO;
    }

    /**
     * Funcion que no activa un usuario
     */
    public void noAceptarRegistro(){
        this.estado = EstadoUsuario.BLOQUEADO;
    }

    @Override
    public String toString() {
        return "Nombre: " + super.getNombre() + ", Estado: " + this.getEstado().name() + "Contraseña: " + this.contrasena;
    }


}