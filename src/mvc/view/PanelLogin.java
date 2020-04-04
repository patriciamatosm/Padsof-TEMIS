package mvc.view;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import mvc.model.Temis;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
    private JTextField usuarioR = new JTextField(20);
    private JPasswordField contrasena = new JPasswordField(20);
    private JButton login = new JButton("Inicia sesión!");
    private JButton registro = new JButton("Regístrate");
    private JButton registroR = new JButton("Regístrate");

    //Label
    private JLabel l1 = new JLabel("¡Bienvenido a Temis!" );
    private JLabel l2 = new JLabel("La aplicación oficial del ayuntamiento mediante la cual los ciudadanos");
    private JLabel l3 = new JLabel("pueden formar colectivos, proponer proyectos y votar dichas propuestas para " +
            "hacer");
    private JLabel l4 = new JLabel("de este un barrio para todos.");
    private JLabel l5 = new JLabel("Por favor, inicia sesión o regístrate.");
    private JLabel l6 = new JLabel("Usuario o NIF");
    private JLabel l7 = new JLabel("Contraseña");
    private JLabel l8 = new JLabel("¿Aún no eres miembro?");
    private JLabel l9 = new JLabel("Usuario");

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

        /***********************
         * LOGO
         **********************/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon (imagen.getScaledInstance(200,200,Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(400, 120, 200, 200);
        this.add(icon1);

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

        /*this.add(l2);
        this.add(l3);
        this.add(l4);*/

        /* Descripcion2 */
        l5.setFont(l5.getFont().deriveFont(16f));
        l5.setBounds(370, 320, 10, 25);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        this.add(l5);

        /* Campos */
        l6.setFont(l6.getFont().deriveFont(14f));
        l6.setBounds(450, 400, 10, 25);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        this.add(l6);

        usuario.setBounds(410, 440, 180, 25);
        this.add(usuario);


        l7.setFont(l7.getFont().deriveFont(14f));
        l7.setBounds(455, 500, 10, 25);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        this.add(l7);

        contrasena.setBounds(410, 540, 180, 25);
        this.add(contrasena);

        login.addActionListener(this);
        login.setBounds(450, 580, 100, 40);

        this.add(login);

        l8.setFont(l8.getFont().deriveFont(14f));
        l8.setBounds(380, 635, 10, 25);
        l8.setForeground(Color.black);
        l8.setSize(l8.getPreferredSize());
        this.add(l8);

        registro.addActionListener(this);
        registro.setOpaque(true);
        registro.setBorder(null);
        registro.setBounds(540, 635, 80, 20);
        registro.setBackground(new Color(124, 150, 197));
        registro.setForeground(Color.blue);
        this.add(registro);

        l9.setFont(l9.getFont().deriveFont(14f));
        l9.setBounds(470, 360, 180, 10);
        l9.setForeground(Color.black);
        l9.setSize(l9.getPreferredSize());
        this.add(l9);
        l9.setVisible(false);

        registroR.addActionListener(this);
        registroR.setBounds(450, 630, 100, 40);
        this.add(registroR);
        registroR.setVisible(false);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Temis pTemis = Temis.getInstance();
        try {
            pTemis.leerFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(e.getSource() == login){
            String pwd = String.valueOf(this.contrasena.getPassword());

            if(usuario.getText().equals(pTemis.getUsuarioAdmin())){
                if(pwd.equals(pTemis.getContrasenaAdmin())) {
                    gui.getController().login(usuario.getText(), pwd);
                    //gui.afterLogin(false);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Ha habido un error, por favor revise su usuario y su contraseña.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso!",
                        "¡Bienvenido a Temis!" ,JOptionPane.OK_OPTION);

            } else if (gui.getController().login(usuario.getText(), pwd)) {

                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso!",
                        "¡Bienvenido a Temis!" ,JOptionPane.OK_OPTION);

                //gui.afterLogin(true);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Ha habido un error, por favor revise su usuario y su contraseña.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }


        } else if(e.getSource() == registro){
            l8.setVisible(false);
            registro.setVisible(false);
            login.setVisible(false);

            contrasena.setBounds(410, 595, 180, 25);
            l7.setBounds(460, 560, 80, 25);

            usuario.setBounds(410, 500, 180, 25);
            l6.setBounds(485, 460, 80, 25);
            l6.setText("NIF");

            usuarioR.setBounds(410, 400, 180, 25);
            this.add(usuarioR);
            l9.setVisible(true);
            registroR.setVisible(true);

        }

        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
