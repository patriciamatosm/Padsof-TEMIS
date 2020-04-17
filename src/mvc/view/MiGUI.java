package mvc.view;
import mvc.controller.Controller;

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
    //private PanelNotificacion panelNotificacion;
    private PanelProyectos panelProyectos;
    private PanelProyectosApoyo panelProyectosApoyo;
    private PanelProponerProyecto panelProponerProyecto;
    private PanelProyectoSocial panelProyectoSocial;
    private PanelProyectoInfra panelProyectoInfra;
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
       // this.panelNotificacion = new PanelNotificacion(this);
        this.panelProyectos = new PanelProyectos(this);
        this.panelProyectosApoyo = new PanelProyectosApoyo(this);
        this.panelProponerProyecto = new PanelProponerProyecto(this);
        this.panelProyectoSocial = new PanelProyectoSocial(this);
        this.panelProyectoInfra = new PanelProyectoInfra(this);

        //añadir SOLO login
        container.add(panelLogin);

        //poner los demas en false
        panelLogin.setVisible(true);
        panelPrincipal.setVisible(false);
        panelAdmin.setVisible(false);


        //panelNotificacion.setVisible(false);
        panelProyectos.setVisible(false);
        panelProyectosApoyo.setVisible(false);
        panelProponerProyecto.setVisible(false);
        panelProyectoSocial.setVisible(false);
        panelProyectoInfra.setVisible(false);

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

    /*public void irColectivos(JPanel panel){
        panel.setVisible(false);

        panelColectivos.asignarData();
        container.add(panelColectivos);
        panelColectivos.setVisible(true);
    }*/

    public void irProyectos(JPanel panel){
        panel.setVisible(false);

        if(panelProyectos == null) this.panelProyectos = new PanelProyectos(this);
        panelProyectos.asignarData();
        container.add(panelProyectos);
        panelProyectos.setVisible(true);
    }

    /*public void irNotificacion(JPanel panel){
        panel.setVisible(false);

        panelNotificacion.asignarData();
        container.add(panelNotificacion);
        panelNotificacion.setVisible(true);
    }*/

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

    public void irProyectoSocial(JPanel panel){
        panel.setVisible(false);

        if(panelProyectoSocial == null) this.panelProyectoSocial = new PanelProyectoSocial(this);
        panelProyectoSocial.asignarData();
        container.add(panelProyectoSocial);
        panelProyectoSocial.setVisible(true);
    }

    public void irProyectoInfra(JPanel panel){
        panel.setVisible(false);

        if(panelProyectoInfra == null) this.panelProyectoInfra = new PanelProyectoInfra(this);
        panelProyectoInfra.asignarData();
        container.add(panelProyectoInfra);
        panelProyectoInfra.setVisible(true);
    }

}
