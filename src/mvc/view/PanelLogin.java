package mvc.view;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import mvc.model.Temis;


import java.awt.*;
import java.awt.event.*;

/**
 * Clase que define el Panel del login
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 4/04/2020
 */
public class PanelLogin extends JPanel implements ActionListener{

    private MiGUI gui;

    //Fields
    private JTextField usuario = new JTextField(20);
    private JPasswordField contrasena = new JPasswordField(20);
    private JButton login = new JButton("Inicia sesión!");

    //Label
    private JLabel l1 = new JLabel("¡Bienvenido a Temis!" );
    private JLabel l2 = new JLabel("La aplicación oficial del ayuntamiento mediante la cual los ciudadanos");
    private JLabel l3 = new JLabel("pueden formar colectivos, proponer proyectos y votar dichas propuestas para " +
            "hacer");
    private JLabel l4 = new JLabel("de este un barrio para todos.");
    private JLabel l5 = new JLabel("Por favor, inicia sesión o regístrate.");
    private JLabel l6 = new JLabel("Usuario o NIF");
    private JLabel l7 = new JLabel("Contraseña");

    /**
     * Constructor de la clase
     * @param gui de la aplicacion
     */
    public PanelLogin(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));


        this.setBounds(100,100,400,350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

        /*Titulo*/
        l1.setFont(l1.getFont().deriveFont(20f));
        l1.setBounds(400, 100, 250, 25);
        l1.setForeground(Color.black);
        this.add(l1);

        /*Descripcion*/
        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(230, 200, 10, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());

        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(180, 220, 10, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());

        l4.setFont(l4.getFont().deriveFont(16f));
        l4.setBounds(380, 240, 10, 25);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());

        this.add(l2);
        this.add(l3);
        this.add(l4);

        /* Descripcion2 */
        l5.setFont(l5.getFont().deriveFont(16f));
        l5.setBounds(360, 320, 10, 25);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        this.add(l5);

        /* Campos */
        l6.setFont(l6.getFont().deriveFont(14f));
        l6.setBounds(440, 400, 10, 25);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        this.add(l6);

        usuario.setBounds(400, 440, 180, 25);
        this.add(usuario);


        l7.setFont(l7.getFont().deriveFont(14f));
        l7.setBounds(445, 500, 10, 25);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        this.add(l7);

        contrasena.setBounds(400, 540, 180, 25);
        this.add(contrasena);

        login.addActionListener(this);
        login.setBounds(440, 580, 100, 40);

        this.add(login);




    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
