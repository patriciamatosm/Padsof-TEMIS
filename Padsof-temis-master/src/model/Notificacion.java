package model;

/**
 * Clase que define una notificacion
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 27/03/2020
 */
@SuppressWarnings("unused")
public class Notificacion {
    private Proyecto emisor;
    private Colectivo receptor;
    private String mensaje;

    /**
     * Constructor de la clase
     * @param emisor emisor de la notificacion
     * @param receptor receptor de la notificacion
     * @param mensaje mensaje de la notificacion
     */
    public Notificacion(Proyecto emisor, Colectivo receptor, String mensaje) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
    }

    /**
     * Funcion que devuelve el emisor
     * @return emisor
     */
    public Proyecto getEmisor() {
        return emisor;
    }

    /**
     * Funcion que devuelve el receptor
     * @return receptor
     */
    public Colectivo getReceptor() {
        return receptor;
    }

    /**
     * Funcion que devuelve el mensaje
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Funcion que envia una notificiacion
     */
    public void enviarNotificacion() {
        this.receptor.getNotificacionesRecibidas().add(this);
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "emisor=" + emisor +
                ", receptor=" + receptor +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
