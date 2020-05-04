package mvc.model;


import java.io.*;
import java.util.*;


/**
 * Clase que define la aplicación Temis
 *
 * @author Daniel Prieto Fernández
 * Silvia Tomey Prieto
 * Patricia Matos Meza
 * @version 3/03/2020
 */
@SuppressWarnings("unused")
public class Temis {
    private static Temis pTemis;
    private String usuarioAdmin = "admon";
    private String contrasenaAdmin = "adminadmin";
    private Usuario usuarioConectado = null;
    private boolean representanteLegal = false;
    private Colectivo colRepresentado = null;
    private boolean adminFlag = false;
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<String, Colectivo> colectivos = new HashMap<>();
    private Map<String, Proyecto> proyectos = new HashMap<>();
    private Map<String, Notificacion> notificaciones = new HashMap<>();
    List<Proyecto> solicitudes = new ArrayList<>();
    private List<String> distritos = new ArrayList<>();


    /**
     * Constructor de la clase Temis
     */
    private Temis() {
    }

    /**
     * Devuelve instancia del singleton
     *
     * @return pTemis instancia de Temis
     */
    public static Temis getInstance() {
        if (pTemis == null) {
            pTemis = new Temis();
        }
        return pTemis;
    }

    /**
     * Funcion que devuelve el usuario del admin
     * @return usuario del admin
     */
    public String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    /**
     * Funcio que devuelve solicitudes
     * @return solicitudes
     */
    public List<Proyecto> getSolicitudes() {
        return solicitudes;
    }

    /**
     * Funcion que devuelve la contrasena del admin
     * @return contrasena del admin
     */
    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }

    /**
     * Funcion que devuelve los distritos
     * @return distritos
     */
    public List<String> getDistritos() {
        return distritos;
    }

    /**
     * Funcion que devuelve el usuario conectado
     * @return Usuario el usuario conectado
     */
    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    /**
     * Funcion que devuelve el colectivo representado
     * @return Colectivo del que representa el usuario
     */
    public Colectivo getColRepresentado() {
        return colRepresentado;
    }

    /**
     * Funcion que devuelve los usuarios de la aplicacion
     * @return mapa con los usuarios de la aplicacion
     */
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Funcion que devuelve los colectivos de la aplicacion
     * @return mapa con los colectivos de la aplicacion
     */
    public Map<String, Colectivo> getColectivos() {
        return colectivos;
    }

    /**
     * Funcion que devuelve los proyectos de la aplicacion
     * @return mapa con los proyectos de la aplicacion
     */
    public Map<String, Proyecto> getProyectos() {
        return proyectos;
    }

    /**
     * Funcion que devuelve las notificaciones de la aplicacion
     * @return mapa con las notificaciones de la aplicacion
     */
    public Map<String, Notificacion> getNotificaciones() { return notificaciones; }

    /**
     * Funcion que devuelve el flag de representante
     * @return true si es representante
     */
    public boolean isRepresentanteLegal() { return representanteLegal; }

    /**
     * Funcion que modifica el campo de representanteLegal
     * @param representanteLegal boolean
     */
    public void setRepresentanteLegal(boolean representanteLegal) {
        if(!representanteLegal) { this.colRepresentado = null; }
        this.representanteLegal = representanteLegal;
    }

    /**
     * Funcion que modifica el campo de colRepresentado
     * @param c Colectivo que representa
     */
    public void setColRepresentado(Colectivo c) {
        this.colRepresentado = c;
    }

    /**
     * Funcion que guarda en el archivo Temis.txt los usuarios, los colectivos y
     * los proyectos existente en la aplicación.
     *
     * @throws IOException en caso de que ocurra algun error
     */
    public void escribirFichero() throws IOException {
        this.caducarProyectos(pTemis.proyectos.values());
        FileOutputStream fos = new FileOutputStream("Temis.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);


        // Escribe usuarios

        oos.writeObject(this.usuarios);

        // Escribe usuarios

        oos.writeObject(this.colectivos);

        // Escribe proyectos

        oos.writeObject(this.proyectos);

        // Escribe notificaciones

        oos.writeObject(this.notificaciones);


        oos.close();
    }

    /**
     * Funcion que lee del archivo Temis.txt todos los objetos existentes de la aplicación
     *
     * @throws IOException en caso de que ocurra algun error
     */
    @SuppressWarnings("unchecked")
    public void leerFichero() throws IOException {
        FileInputStream fis = new FileInputStream("Temis.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object objLeido;
        Map<String, Usuario> usuarioMap;
        Map<String, Colectivo> colectivoMap;
        Map<String, Proyecto> proyectoMap;
        Map<String, Notificacion> notificacionMap;

        /* Leer componentes */
        try {
            // Leer usuarios

            objLeido = ois.readObject();
            usuarioMap = (Map<String, Usuario>) objLeido;
            pTemis.usuarios = usuarioMap;

            // Leer colectivos

            objLeido = ois.readObject();
            colectivoMap = (Map<String, Colectivo>) objLeido;
            pTemis.colectivos = colectivoMap;

            // Leer proyectos

            objLeido = ois.readObject();
            proyectoMap = (Map<String, Proyecto>) objLeido;
            pTemis.proyectos = proyectoMap;

            // Leer notificaciones

            objLeido = ois.readObject();
            notificacionMap = (Map<String, Notificacion>) objLeido;
            pTemis.notificaciones = notificacionMap;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();

        /* Leer distritos */
        try {
            Scanner input = new Scanner(new File("distritos.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                this.distritos.add(line);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Funcion que permite al usuario registrarse
     *
     * @param dni        dni del usuario
     * @param nombre     nombre del usuario
     * @param contrasena contrasena del usuario
     * @return True/False dependiendo del éxito
     */
    public boolean registrarse(String dni, String nombre, String contrasena) {
        Usuario usuario;

        if (this.usuarios.containsKey(dni)) { //comprobar nombre si existe
            return false;
        }
        usuario = new Usuario(nombre, dni, contrasena);
        this.usuarios.put(dni, usuario);
        return true;
    }

    /**
     * Funcion que le permite al usuario iniciar sesion en la aplicación
     *
     * @param id         nombre o dni del usuario
     * @param contrasena del usuario
     * @return True/False dependiendo del éxito
     */
    public boolean iniciaSesion(String id, String contrasena) {
        if (id.equals("") || contrasena.equals("")) return false;

        if (id.length() == 9) {
            if (Character.isLetter(id.charAt(8))) {
                if (this.usuarios.containsKey(id)) {
                    if(contrasena.equals(this.usuarios.get(id).getContrasena())){
                        this.usuarioConectado = this.usuarios.get(id);
                        this.usuarios.get(id).setLogueado(true);
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } else {
            try {
                Integer.parseInt(id);
                return false;
            } catch (NumberFormatException e) {
                for (Usuario user : this.usuarios.values()) {
                    if (id.equals(user.getNombre())) {
                        if(contrasena.equals(user.getContrasena())){
                            this.usuarioConectado = user;
                            this.usuarios.get(user.getDni()).setLogueado(true);
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
        }
    }

    /**
     * Funcion que se encarga del inicio de sesión del admin
     * @param id del usuario
     * @param contrasena del usuario
     * @return True/False dependiendo del éxito
     */
    public boolean iniciaSesionAdmin(String id, String contrasena){
        if(id.equals(usuarioAdmin) && contrasena.equals(contrasenaAdmin)){
            this.adminFlag = true;
            return true;
        }
        return false;
    }

    /**
     * Funcion que permite al usuario cerrar sesión
     */
    public void cierraSesion() {
        if (this.usuarioConectado != null) {
            this.usuarioConectado.setLogueado(false);
            this.usuarioConectado = null;
        }
    }

    /**
     * Funcion que permite añadir un proyecto a la aplicacion
     * @param p Proyecto a añadir
     */
    public void anadirProyecto(Proyecto p) {
        this.proyectos.put(p.getProjectTitle(), p);
        System.out.println(this.proyectos);
    }

    /**
     * Funcion que permite añadir una notificacion a la aplicacion
     * @param n Notificacion a añadir
     */
    public void anadirNotificacion(Notificacion n) {
        this.notificaciones.put(n.getEmisor().getProjectTitle(), n);
        System.out.println(this.notificaciones);
    }

    /**
     * Funcion que llama a la funcion votar
     * @param p Proyecto a votar
     */
    public void votar(Proyecto p) {
        for(Proyecto proyecto : this.proyectos.values()) {
            if(proyecto.getProjectTitle().equals(p.getProjectTitle())) {
                for(Usuario u : this.usuarios.values()){
                    if(u.getNombre().equals(this.getUsuarioConectado().getNombre())) {
                        proyecto.votar(u);
                    }
                }
            }
        }
    }

    /**
     * Funcion que llama a la funcion votarUsuarios
     * @param p Proyecto a votar
     */
    public void votarUsuarios(Proyecto p, Colectivo c) {
        for(Proyecto proyecto : this.proyectos.values()) {
            if(proyecto.getProjectTitle().equals(p.getProjectTitle())) {
                for(Usuario u : this.usuarios.values()){
                    if(u.getNombre().equals(this.getUsuarioConectado().getNombre())) {
                        for(Colectivo col : this.colectivos.values()){
                            if(col.getNombre().equals(c.getNombre())){
                                proyecto.votarUsuarios(col.getListaUsuario());
                                this.votar(p);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcion que determina si un usuario ha votado ya o no un proyecto
     * @param p Proyecto votado
     * @return true/false dependiendo de si el usuario ha votado ya o no
     */
    public boolean haVotado(Proyecto p){
        for(Proyecto proyecto : this.proyectos.values()) {
            if(proyecto.getProjectTitle().equals(p.getProjectTitle())) {
                for(Usuario u : this.usuarios.values()){
                    if(u.getNombre().equals(this.getUsuarioConectado().getNombre())){
                        if(u.getListaProyecto().contains(p) == true){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Funcion que llama a la funcion caducado para cada uno de los proyectos existentes
     * @param proyectos existentes en el programa
     */
    public void caducarProyectos(Collection<Proyecto> proyectos){
        for(Proyecto p : proyectos) {
            if (p.getEstado() != Proyecto.Estado.CADUCADO || p.getEstado() != Proyecto.Estado.RECHAZADO) {
                if (p.caducado()) {
                    for (Colectivo c : this.getColectivos().values()) {
                        System.out.println("apoyados " + c.getProyectosApoyados());
                        for (Proyecto proy1 : c.getProyectosApoyados()) {
                            System.out.println("proy1 " + proy1);
                            System.out.println("p " + p);
                            if (proy1.getProjectTitle().equals(p.getProjectTitle())) {
                                Notificacion n = new Notificacion(p, c, "¡El proyecto " +
                                        p.getProjectTitle() + " ha caducado!");
                                this.anadirNotificacion(n);
                                c.addNotificacion(n);
                                System.out.println("caducarProyectos" + c.getNotificacionesRecibidas());
                            }

                        }
                    }
                }
            }

        }
    }

    /**
     * Función que permite añadir un colectivo a la aplicación
     * @param c Colectivo a añadir
     */
    public void anadirColectivo(Colectivo c){
        this.colectivos.put(c.getNombre(), c);
    }

    /**
     * Funcion que crea un colectivo y comprueba que no existe uno con el mismo nombre
     * @param nombre del colectivo
     * @param desc del colectivo
     * @return nuevo colectivo
     * @throws Exception si ya existe uno con el mismo nombre o el crear el colectivo manda una excepcion
     */
    public Colectivo crearCol(String nombre, String desc) throws Exception {
        for(Colectivo c : this.colectivos.values()) {
            if(c.getNombre().equals(nombre)) {
                throw new Exception("Ya existe un colectivo con ese nombre");
            }
        }
        Colectivo c = null;
        for(Usuario u : this.usuarios.values()) {
            if(u.getDni().equals(this.getUsuarioConectado().getDni())) {
                c = new Colectivo(desc, nombre, u);
            }
        }
        return c;
    }

    /**
     * Funcion que crea un subcolectivo del colectivo que se esta representando
     * @param descripcion del nuevo colectivo
     * @param nombre del nuevo colectivo
     * @throws Exception si ya existe un colectivo con ese nombre o el constructor de colectivo manda una
     */
    public void crearSubColectivo(String descripcion, String nombre) throws Exception {
        for(Colectivo c : this.colectivos.values()) {
            if(c.getNombre().equals(nombre)) {
                throw new Exception("Ya existe un colectivo con ese nombre");
            }
        }
        for(Colectivo c : this.colectivos.values()) {
            if(c.getNombre().equals(colRepresentado.getNombre())) {
                c.crearSubcolectivo(nombre, descripcion);
            }
        }
    }

    /**
     * Funcion que une al usuario conectado al colectivo c
     * @param c colectivo al que se quiere unir
     */
    public void unirse(Colectivo c) {
        for(Colectivo col : this.colectivos.values()) {
            if(col.getNombre().equals(c.getNombre())) {
                for(Usuario u : this.usuarios.values()){
                    if(u.getNombre().equals(this.getUsuarioConectado().getNombre())) {
                        col.unirse(u);
                    }
                }
            }
        }
    }

    /**
     * Funcion que hace que el usuario conectado deje de seguir al colectivo
     * @param c colectivo que abandonar
     */
    public void abandonar(Colectivo c) {
        for(Colectivo col : this.colectivos.values()) {
            if(col.getNombre().equals(c.getNombre())) {
                for(Usuario u : this.usuarios.values()){
                    if(u.getNombre().equals(this.getUsuarioConectado().getNombre())) {
                        col.abandonar(u);
                    }
                }
            }
        }
    }

    /**
     * Funcion que llama a subscribirseNoticias
     * @param c Colectivo a cuyas noticias se subscribe el usuario
     */
    public void subscribirseNoticias(Colectivo c){
        for(Usuario u : this.getUsuarios().values()){
            if(u.getDni().equals(this.getUsuarioConectado().getDni())){
                System.out.println("usuario encontrado");
                if(!c.subscribirseNoticias(u)) System.out.println("ERROR");
                System.out.println(u.getSuscritoNoticias());
            }
        }
    }

    /**
     * Funcion que devuelve el array que contiene los colectivos a los que esta suscrito el usuario
     * @return Array de colectivos a los que se está suscrito
     */
    public List<Colectivo> getSuscritoNoticias(Usuario user){
        return user.getSuscritoNoticias();
    }


    /**
     * Funcion que llama a la funcion que añade un proyecto a la lista de proyectos
     * apoyados por el colectivo
     * @param p Proyecto apoyado
     * @param c Colectivo
     */
    public void addProyectoApoyado(Proyecto p, Colectivo c) { c.addProyectoApoyado(p);}
}
