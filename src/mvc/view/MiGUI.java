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
    private PanelNotificacion panelNotificacion;
    private PanelProyectos panelProyectos;
    private PanelProyectosApoyo panelProyectosApoyo;
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
        this.panelNotificacion = new PanelNotificacion(this);
        this.panelProyectos = new PanelProyectos(this);
        this.panelProyectosApoyo = new PanelProyectosApoyo(this);

        //añadir SOLO login
        container.add(panelLogin);

        //poner los demas en false
        panelLogin.setVisible(true);
        panelPrincipal.setVisible(false);
        panelNotificacion.setVisible(false);
        panelProyectos.setVisible(false);
        panelProyectosApoyo.setVisible(false);

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

        panelLogin.setVisible(true);
        panelLogin.repaint();
    }

    /**
     * Funcion que permite volver a la pagina principal
     * @param panel panel
     */
    public void irPaginaPrincipal(JPanel panel){
        panel.setVisible(false);

        panelPrincipal.asignarData();
        container.add(panelPrincipal);
        panelPrincipal.setVisible(true);
    }

    /*public void irColectivos(JPanel panel){
        panel.setVisible(false);

        panelColectivos.asignarData();
        container.add(panelColectivos);
        panelColectivos.setVisible(true);
    }*/

    public void irProyectos(JPanel panel){
        panel.setVisible(false);

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

        panelProyectosApoyo.asignarData();
        container.add(panelProyectosApoyo);
        panelProyectosApoyo.setVisible(true);
    }

}
