package mvc.controller;

import mvc.model.*;
import mvc.view.*;

import java.util.ArrayList;
import java.util.List;

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
     * Funcion que llama a la funcion de iniciar sesion del admin
     * @param id usuario o dni del usuario
     * @param pwd contrasena
     * @return true/false dependiendo del resultado
     */
    public boolean loginAdmin(String id, String pwd) {
        return pTemis.iniciaSesionAdmin(id, pwd);
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

    /**
     * Funcion que devuelve lista con registros pendientes
     * @return lista de usuarios
     */
    public List<Usuario> registrosPendientes(){
        List<Usuario> usuarios = new ArrayList<>();

        for(Usuario u : pTemis.getUsuarios().values()){
            if(u.getEstado() == Usuario.EstadoUsuario.EN_ESPERA)
                usuarios.add(u);
        }

        return usuarios;
    }

    /**
     * Funcion que devuelve lista con proyectos pendientes
     * @return lista de proyectos
     */
    public List<Proyecto> proyectosPendientes(){
        List<Proyecto> ps = new ArrayList<>();

        for(Proyecto p : pTemis.getProyectos().values()){
            if(p.getEstado() == Proyecto.Estado.EN_ESPERA)
                ps.add(p);
        }

        return ps;
    }

    /**
     * Funcion que devuelve lista con proyectos pendientes financiacion
     * @return lista de proyectos
     */
    public List<Proyecto> proyectosPendientesF(){
        List<Proyecto> ps = new ArrayList<>();

        for(Proyecto p : pTemis.getSolicitudes()){
            ps.add(p);
        }

        return ps;
    }

    /**
     * Funcion que devuelve el nombre de un usuario
     * @param u Usuario
     * @return nombre del usuario
     */
    public String getNombre(Usuario u){
        return u.getNombre();
    }

    /**
     * Funcion que devuelve el titulo de un proyecto
     * @param p Proyecto
     * @return titulo del proyecto
     */
    public String getTitulo(Proyecto p){
        return p.getProjectTitle();
    }

    /**
     * Funcion que devuelve el estado de un proyecto
     * @param p proyecto
     * @return estado del proyecto
     */
    public Proyecto.Estado getEstado(Proyecto p){
        return p.getEstado();
    }

    /**
     * Funcion que devuelve el estado de un usuario
     * @param u usuario
     * @return estado del usuario
     */
    public Usuario.EstadoUsuario getEstado(Usuario u){
        return u.getEstado();
    }

    /**
     * Funcion que devuelve el nombre del creador del proyecto
     * @param p proyecto
     * @return nombre del creador
     */
    public String getNombreCreador(Proyecto p){
        return p.getCreador().getNombre();
    }

    /**
     * Función que acepta registro
     * @param u usuario
     */
    public void aceptarRegistro(Usuario u){

        for (Usuario a : pTemis.getUsuarios().values()){
            if(a.getDni().equals(u.getDni()))
                a.aceptarRegistro();
        }
    }

    /**
     * Función que acepta proyecto
     * @param p proyecto
     */
    public void aceptarProyecto(Proyecto p){

        for (Proyecto a : pTemis.getProyectos().values()){
            if(a.getProjectTitle().equals(p.getProjectTitle()))
                a.aceptarProyecto();
        }
    }


    /**
     * Función que no acepta registro
     * @param u usuario
     */
    public void noAceptarRegistro(Usuario u){

        for (Usuario a : pTemis.getUsuarios().values()){
            if(a.getDni().equals(u.getDni()))
                a.noAceptarRegistro();
        }
    }

    /**
     * Función que no acepta proyecto
     * @param p proyecto
     */
    public void noAceptarProyecto(Proyecto p){

        for (Proyecto a : pTemis.getProyectos().values()){
            if(a.getProjectTitle().equals(p.getProjectTitle()))
                a.noAceptarProyecto();
        }
    }

    /**
     * Funcion que calcula si llega al num Votos
     * @param p proyecto
     * @return true/false
     */
    public boolean cumpleNumVotos(Proyecto p){
        return p.esperarFinanc();
    }

    /**
     * Funcion para pedir financiacion
     * @param p proyecto
     */
    public void pedirFinanciacion(Proyecto p){
        for (Proyecto a : pTemis.getProyectos().values()){
            if(a.getProjectTitle().equals(p.getProjectTitle())){
                a.pedirFinanciacion();
            }
        }
    }

    /**
     * Funcion para negar solicitud financiacion
     * @param p proyecto
     */
    public void noPedirFinanciacion(Proyecto p){
        for (Proyecto a : pTemis.getProyectos().values()){
            if(a.getProjectTitle().equals(p.getProjectTitle())){
                a.denegarSolicitud();
            }
        }
    }

}
