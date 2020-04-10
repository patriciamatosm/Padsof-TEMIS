package mvc.controller;

import mvc.model.*;
import mvc.view.*;

/**
 * Clase que define el controlador
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 4/04/2020
 */
public class Controller {
    private MiGUI gui;
    private Temis pTemis = Temis.getInstance();

    /**
     * Constructor de la clase
     * @param gui GUI de la app
     */
    public Controller(MiGUI gui) {
        this.gui = gui;
    }

    /**
     * Funcion que llama a la funcion de iniciar sesion
     * @param id usuario o dni del usuario
     * @param pwd contrasena
     * @return true/false dependiendo del resultado
     */
    public boolean login(String id, String pwd) {
        return pTemis.iniciaSesion(id, pwd);
    }

    /**
     * Funcion que llama a la funcion de registrarse
     * @param usuario nombre del usuario
     * @param nif dni del usuario
     * @param pwd contrasena del usuario
     * @return true/false dependiendo del resultado
     */
    public boolean register(String usuario, String nif, String pwd){
        return pTemis.registrarse(usuario, nif, pwd);
    }

    /**
     * Funcion que llama a la funcion que nos devuelve el usuario conectado
     * @return usuario conectado
     */
    public Usuario getLoggedUser(){
        return pTemis.getUsuarioConectado();
    }

    /**
     * Funcion que llama a la funcion que nos devuelve el nombre del usuario conectado
     * @return nombre del usuario conectado
     */
    public String getLoggedUserName(){ return pTemis.getUsuarioConectado().getNombre();}

    /**
     * Funcion que llama a la funcion de cerrar sesión
     */
    public void logout(){ pTemis.cierraSesion();}

}
