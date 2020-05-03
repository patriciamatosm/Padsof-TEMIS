package mvc.view;
import mvc.controller.Controller;
import mvc.model.Colectivo;
import mvc.model.Notificacion;
import mvc.model.Proyecto;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que define el GUI
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 4/04/2020
 */
public class MiGUI extends JFrame{
    private Controller controller;
    private PanelLogin panelLogin;
    private PanelPrincipal panelPrincipal;
    private PanelAdmin panelAdmin;
    private PanelNotificacion panelNotificacion;
    private PanelProyectos panelProyectos;
    private PanelProyectosApoyo panelProyectosApoyo;
    private PanelProponerProyecto panelProponerProyecto;
    private PanelVerProyecto panelVerProyecto;
    private PanelColectivo panelColectivo;
    private PanelColectivoPropio panelColectivoPropio;
    private PanelColectivosQueSigues panelColectivosQueSigues;
    private PanelCrearColectivo panelCrearColectivo;
    private PanelVerColectivo panelVerColectivo;
    private Container container = this.getContentPane();

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public MiGUI(String title) throws HeadlessException {
        super(title);

        container.setLayout(new BorderLayout());

        container.setBackground(Color.black);

        //crear paneles
        this.panelLogin = new PanelLogin(this);
        this.panelPrincipal = new PanelPrincipal(this);
        this.panelAdmin = new PanelAdmin(this);
        this.panelNotificacion = new PanelNotificacion(this);
        this.panelProyectos = new PanelProyectos(this);
        this.panelProyectosApoyo = new PanelProyectosApoyo(this);
        this.panelProponerProyecto = new PanelProponerProyecto(this);
        this.panelVerProyecto = new PanelVerProyecto(this);
        this.panelColectivo = new PanelColectivo(this);
        this.panelColectivoPropio = new PanelColectivoPropio(this);
        this.panelCrearColectivo = new PanelCrearColectivo(this);
        this.panelVerColectivo = new PanelVerColectivo(this);

        //añadir SOLO login
        container.add(panelLogin);

        //poner los demas en false
        panelLogin.setVisible(true);
        panelPrincipal.setVisible(false);
        panelAdmin.setVisible(false);


        panelNotificacion.setVisible(false);
        panelProyectos.setVisible(false);
        panelProyectosApoyo.setVisible(false);
        panelProponerProyecto.setVisible(false);
        panelVerProyecto.setVisible(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(981, 724);

        this.setVisible(true);

    }


    /**
     * Funcion que devuelve el controller
     * @return el controller
     */
    public Controller getController() {
        return this.controller;
    }

    /**
     * Funcion que modifica el controller
     * @param c controlador
     */
    public void setController(Controller c) {
        this.controller = c;
    }


    /**
     * Function que te permite volver al panel de login
     * @param panel  panel
     */
    public void volverAlLogin(JPanel panel) {
        panel.setVisible(false);
        panel = null;

        if(panelLogin == null) this.panelLogin = new PanelLogin(this);
        panelLogin.setVisible(true);
        panelLogin.repaint();
    }

    /**
     * Funcion que permite volver a la pagina principal
     * @param panel panel
     */
    public void irPaginaPrincipal(JPanel panel){
        panel.setVisible(false);

        if(panelPrincipal == null) this.panelPrincipal = new PanelPrincipal(this);
        panelPrincipal.asignarData();
        container.add(panelPrincipal);
        panelPrincipal.setVisible(true);
    }

    /**
     * Funcion que permite ir a la vista del admin
     * @param panel panel
     */
    public void irAdmin(JPanel panel){
        panel.setVisible(false);
        if(panelAdmin == null) this.panelAdmin = new PanelAdmin(this);
        container.add(panelAdmin);
        panelAdmin.setVisible(true);

    }

    public void irProyectos(JPanel panel){
        panel.setVisible(false);

        if(panelProyectos == null) this.panelProyectos = new PanelProyectos(this);
        panelProyectos.asignarData();
        container.add(panelProyectos);
        panelProyectos.setVisible(true);
    }

    public void irNotificacion(JPanel panel){
        panel.setVisible(false);

        panelNotificacion.asignarData();
        container.add(panelNotificacion);
        panelNotificacion.setVisible(true);
    }

    public void irProyectosApoyo(JPanel panel){
        panel.setVisible(false);

        if(panelProyectosApoyo == null) this.panelProyectosApoyo = new PanelProyectosApoyo(this);
        panelProyectosApoyo.asignarData();
        container.add(panelProyectosApoyo);
        panelProyectosApoyo.setVisible(true);
    }

    public void irProponerProyecto(JPanel panel){
        panel.setVisible(false);

        if(panelProponerProyecto == null) this.panelProponerProyecto = new PanelProponerProyecto(this);
        panelProponerProyecto.asignarData();
        container.add(panelProponerProyecto);
        panelProponerProyecto.setVisible(true);
    }

    public void irProyecto(JPanel panel){
        panel.setVisible(false);

        if(panelVerProyecto == null) this.panelVerProyecto = new PanelVerProyecto(this);
        panelVerProyecto.asignarData();
        container.add(panelVerProyecto);
        panelVerProyecto.setVisible(true);
    }

    public void crearProyectos(JPanel panel) {
        panel.setVisible(false);

        if(panelProponerProyecto == null) this.panelProponerProyecto = new PanelProponerProyecto(this);
        panelProponerProyecto.asignarData();
        container.add(panelProponerProyecto);
        panelProponerProyecto.setVisible(true);
    }


    public void irColectivos(JPanel panel) {
        panel.setVisible(false);

        if(panelColectivo == null) this.panelColectivo = new PanelColectivo(this);
        panelColectivo.asignarData();
        container.add(panelColectivo);
        panelColectivo.setVisible(true);
    }

    public void colectivoPropio(JPanel panel) {
        panel.setVisible(false);

        if(panelColectivoPropio == null) this.panelColectivoPropio = new PanelColectivoPropio(this);
        panelColectivoPropio.asignarData();
        container.add(panelColectivoPropio);
        panelColectivoPropio.setVisible(true);
    }

    public void colectivosQueSigues(JPanel panel) {
        panel.setVisible(false);

        if(panelColectivosQueSigues == null) this.panelColectivosQueSigues = new PanelColectivosQueSigues(this);
        panelColectivosQueSigues.asignarData();
        container.add(panelColectivosQueSigues);
        panelColectivosQueSigues.setVisible(true);
    }

    public void crearColectivos(JPanel panel) {
        panel.setVisible(false);

        if(panelCrearColectivo == null) this.panelCrearColectivo = new PanelCrearColectivo(this);
        panelCrearColectivo.asignarData();
        container.add(panelCrearColectivo);
        panelCrearColectivo.setVisible(true);
    }

    public void verColectivo(JPanel panel) {
        panel.setVisible(false);

        if(panelVerColectivo == null) this.panelVerColectivo = new PanelVerColectivo(this);
        panelVerColectivo.asignarData();
        container.add(panelVerColectivo);
        panelVerColectivo.setVisible(true);
    }

    public void asignarColectivo(Colectivo c) {
        if(panelVerColectivo == null) this.panelVerColectivo = new PanelVerColectivo(this);
        panelVerColectivo.setColectivo(c);
    }

    public void asignarProyecto(Proyecto p) {
        if(panelVerProyecto == null) this.panelVerProyecto = new PanelVerProyecto(this);
        panelVerProyecto.setProyecto(p);
    }

    public void asignarNotificacion(Notificacion n) {
        if(panelNotificacion == null) this.panelNotificacion = new PanelNotificacion(this);
        panelNotificacion.setNotificacion(n);
    }

    public void verProyecto(JPanel panel) {
        panel.setVisible(false);

        if(panelVerProyecto == null) this.panelVerProyecto = new PanelVerProyecto(this);
        panelVerProyecto.asignarData();
        container.add(panelVerProyecto);
        panelVerProyecto.setVisible(true);
    }

    public void verNotificacion(JPanel panel) {
        panel.setVisible(false);

        if(panelNotificacion == null) this.panelNotificacion = new PanelNotificacion(this);
        panelNotificacion.asignarData();
        container.add(panelNotificacion);
        panelNotificacion.setVisible(true);
    }
}
