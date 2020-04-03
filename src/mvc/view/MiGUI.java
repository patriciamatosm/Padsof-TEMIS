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

        //añadirlos aqui
        container.add(panelLogin);

        //poner los demas en false
        panelLogin.setVisible(true);


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
}
