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
     * @return
     */
    public boolean login(String id, String pwd) {
        return pTemis.iniciaSesion(id, pwd);
    }
}
