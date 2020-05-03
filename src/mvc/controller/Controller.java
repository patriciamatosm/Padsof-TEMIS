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
    public void logout(){ pTemis.cierraSesion(); }

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
     * Funcion que devuelve dni
     * @param u usuario
     * @return dni
     */
    public String getDNI(Usuario u){
        return u.getDni();
    }

    /**
     * Funcion que devuelve numero de votos de un proyecto
     * @param p Proyecto
     * @return numero de votos
     */
    public String getNumVotos(Proyecto p) { return p.getNumVotos().toString();}

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

    /**
     * Funcion que devuelve la lista de usuarios
     * @return lista de usuarios
     */
    public List<Usuario> listaUsuarios(){
        List<Usuario> us = new ArrayList<>();

        for(Usuario u : pTemis.getUsuarios().values()){
            if(u.getEstado() == Usuario.EstadoUsuario.EN_ESPERA) continue;
            us.add(u);
        }
        return us;
    }

    /**
     * Funcion que bloquea un usuario
     * @param u usuario
     */
    public void bloquear(Usuario u){
        for(Usuario a : pTemis.getUsuarios().values()){
            if(a.getDni().equals(u.getDni()))
                a.bloquearUsuario();
        }
    }

    /**
     * Funcion que desbloquea un usuario
     * @param u usuario
     */
    public void desbloquear(Usuario u){
        for(Usuario a : pTemis.getUsuarios().values()){
            if(a.getDni().equals(u.getDni()))
                a.desbloquearUsuario();
        }
    }

    /**
     * Funcion que llama al constructor de Proyecto Social
     * @return Proyecto social
     */
    public void nuevoProyectoSocial(String titulo, String descripcion, Integer importe,
                                        Actor creador, String grupoEtnico, boolean nacional) {

        Proyecto p = new ProyectoSocial(titulo, descripcion, importe, creador, grupoEtnico, nacional);
        pTemis.anadirProyecto(p);
    }

    /**
     * Funcion que llama al constructor de Proyecto de Infraestructura
     * @return Proyecto Infraestructura
     */
    public void nuevoProyectoInfra(String titulo, String descripcion, Integer importe,
                                       Actor creador, String distrito, String urlCroquis,
                                    String descripcionEspecifica) {

        Proyecto p = new ProyectoInfraestructura(titulo, descripcion, importe,
                creador, distrito, urlCroquis, descripcionEspecifica);
        pTemis.anadirProyecto(p);
    }

    /**
     * Funcion que devuelve lista con los proyectos
     * @return lista de proyectos
     */
    public ArrayList<Proyecto> listaProyectos(){
        ArrayList<Proyecto> proyectos = new ArrayList<>();

        for(Proyecto p : pTemis.getProyectos().values() ){
            if(p.getEstado() == Proyecto.Estado.ACTIVO || p.getEstado() == Proyecto.Estado.CADUCADO ||
                p.getEstado() == Proyecto.Estado.ESPERA_FINANC)
            proyectos.add(p);
        }
        return proyectos;
    }

    /**
     * Funcion que devuelve una lista con los proyectos apoyados por el usuario
     * @return Lista con los proyectos apoyados
     */
    public ArrayList<Proyecto> listaProyectosApoyo() {
        ArrayList<Proyecto> proyectos = new ArrayList<>();

        for(Proyecto p : pTemis.getProyectos().values()){
            for(Usuario u : pTemis.getUsuarios().values()){
                if(u.getDni().equals(this.getLoggedUser().getDni())) {
                    if (u.getListaProyecto().contains(p)) {
                        proyectos.add(p);
                    }
                }
            }
        }
        return proyectos;
    }

    /**
     * Funcion que llama a la funcion de calcular popularidad.
     * @param p Proyecto
     * @return String con el numero de votos del proyecto
     */
    public String calcularPopularidad(Proyecto p){
        return p.calcularPopularidad(this.listaUsuarios()).toString();
    }

    public ArrayList<Colectivo> listaColectivosPropios() {
        ArrayList<Colectivo> colectivos = new ArrayList<>();

        for(Usuario user : pTemis.getUsuarios().values()){
            if(pTemis.getUsuarioConectado().getDni().equals(user.getDni())){
                colectivos.addAll(user.getColectivosPropios());
            }
        }
        return colectivos;
    }

    public ArrayList<Usuario> listaUsuariosRepresentados(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        for(Colectivo col : listaColectivosPropios()){
            for(Usuario user : col.getListaUsuario()){
                usuarios.add(user);
            }
        }
        return usuarios;
    }

    public ArrayList<Colectivo> listaColectivosSeguidos() {
        ArrayList<Colectivo> colectivos = new ArrayList<>();

        for(Colectivo c : pTemis.getColectivos().values()) {
            for(Usuario u : c.getListaUsuario()) {
                if(u.getDni().equals(pTemis.getUsuarioConectado().getDni())) {
                    colectivos.add(c);
                }
            }
        }
        return colectivos;
    }

    public void setColRep(Colectivo c) { pTemis.setColRepresentado(c); }

    public ArrayList<Colectivo> listaColectivos() {
        return new ArrayList<>(pTemis.getColectivos().values());
    }

    public void crearColectivo(String descripcion, String nombre) throws Exception {
        if(getRepresentante()) {
            pTemis.crearSubColectivo(descripcion, nombre);
        }
        else {
            Colectivo c = pTemis.crearCol(nombre, descripcion);
            pTemis.anadirColectivo(c);
        }
    }

    public String getNombreColectivo(Colectivo c) { return c.getNombre(); }

    public String getRepColectivo(Colectivo c) {
        return c.getRepresentante().getNombre();
    }

    public boolean isRepCol(Colectivo c) {
        return c.getRepresentante().getDni().equals(pTemis.getUsuarioConectado().getDni());
    }

    public String getDescripcion(Colectivo c) { return c.getDescripcion(); }

    public void unirseCol(Colectivo c) {
        pTemis.unirse(c);
    }

    public void abandonarCol(Colectivo c) {
        pTemis.abandonar(c);
    }

    public boolean enColectivo(Colectivo c) {
        for(Usuario u : c.getListaUsuario()) {
            if(u.getDni().equals(pTemis.getUsuarioConectado().getDni())) {
                return true;
            }
        }
        return false;
    }

    public Colectivo getColectivo(Colectivo c) {
        for(Colectivo col : pTemis.getColectivos().values()) {
            if(col.getNombre().equals(c.getNombre())) {
                return col;
            }
        }
        return null;
    }

    public Colectivo getPadre(Colectivo c) {
        for(Colectivo col : pTemis.getColectivos().values()) {
            if(col.getNombre().equals(c.getNombre())) {
                return col.getPadre();
            }
        }
        return null;
    }

    /**
     * Funcion que llama a la funcion que devuelve la flag de representante
     * @return true o false
     */
    public boolean getRepresentante() { return pTemis.isRepresentanteLegal(); }

    /**
     * Funcion que llama a la funcion que establece la flag de representante
     * @param bool true o false
     */
    public void setRepresentante(boolean bool){ pTemis.setRepresentanteLegal(bool);}

    /**
     * Funcion que llama a la funcion que devuelve la descripcion de un proyecto
     * @param p Proyecto
     * @return String con descripcion del proyecto
     */
    public String getDescProy(Proyecto p) { return  p.getProjectDescription();}

    /**
     * Funcion que llama a la funcion que devuelve el grupo al que va dirigido un
     * proyecto social.
     * @param p Proyecto social
     * @return String con grupo al que va dirigido el proyecto
     */
    public String getGrupo(ProyectoSocial p) { return p.getGrupoEtnico();}

    /**
     * Funcion que llama a la funcion que devuelve true/false dependiendo de si el
     * proyecto es a nivel nacional o no
     * @param p Proyecto social
     * @return String nacional/no nacional
     */
    public String isNacional(ProyectoSocial p) {
        if(p.isNacional() == true) return "Ámbito nacional";
        return "No nacional";
    }

    /**
     * Funcion que llama a la funcion que devuelve el distrito al que va dirigido un
     * proyecto de infraestructura.
     * @param p Proyecto infraestructura
     * @return String con nombre de distrito
     */
    public String getDistrito(ProyectoInfraestructura p) { return p.getDistrito();}

    /**
     * Funcion que llama a la funcion que devuelve la url del croquis de un
     * proyecto de infraestructura.
     * @param p Proyecto infraestructura
     * @return String con url del croquis
     */
    public String getUrl(ProyectoInfraestructura p) { return  p.getUrlCroquis();}


    /**
     * Funcion que llama a la funcion votar
     * @param p Proyecto a votar
     */
    public void votar(Proyecto p) { pTemis.votar(p);}

    /**
     * Funcion que llama a la funcion votarUsuarios
     * @param p Proyecto a votar
     */
    public void votarUsuarios(Proyecto p) {
        pTemis.votarUsuarios(this.listaUsuariosRepresentados(), p);
    }

    /**
     * Funcion que comprueba si un usuario ha votado ya o no un proyecto
     * @param p Proyecto votado
     * @return true si ya ha votado, false si aun no lo ha hecho
     */
    public boolean haVotado(Proyecto p){
        return pTemis.haVotado(p);
    }

    /**
     * Funcion que comprueba si el usuario es representante de algun colectivo
     * @param u Usuario
     * @return true/false dependiendo de si el usuario es o no representante
     */
    public boolean esRepresentante(Usuario u){
        if(u.getColectivosPropios().isEmpty()) return false;
        return true;
    }

    /**
     * Funcion que llama al setter de votos minimos
     * @param p Proyecto donde se establecen los votos
     * @param min numero de votos a establecer
     */
    public void setMinVotos(Proyecto p, int min){
        p.setMinVotos(min);
    }

    /**
     * Funcion que devuelve el emisor (titulo) de una Notificacion
     * @param n Notificacion
     * @return Emisor de la notificacion
     */
    public String getEmisor(Notificacion n){
        return n.getEmisor().getProjectTitle();
    }

    /**
     * Funcion que devuelve el emisor (nombre) de una Notificacion
     * @param n Notificacion
     * @return Receptor de la notificacion
     */
    public String getReceptor(Notificacion n){
        return n.getReceptor().getNombre();
    }

    /**
     * Funcion que devuelve el mensaje de una notificacion
     * @param n Notificacion
     * @return Mensaje de la notificacion
     */
    public String getMensaje(Notificacion n){ return n.getMensaje();}

    /**
     * Funcion que devuelve los distritos
     * @return distritos
     */
    public List<String> getDistritos(){return Temis.getInstance().getDistritos();}
}
