package mvc.view;

import mvc.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Clase que define el Panel principal
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 9/04/2020
 */
public class PanelPrincipal extends JPanel implements ActionListener {


    private MiGUI gui;

    //Others
    private boolean representanteFlag = false;

    //Fields
    private JButton usuario = new JButton("Usuario");
    private JButton representante = new JButton("Representante");
    private JButton cierraSesion = new JButton("Salir");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");



    //Label
    private JLabel l1 = new JLabel("¿Cómo quieres iniciar sesión?" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");


    /**
     * Constructor de la clase
     * @param gui de la aplicacion
     */
    public PanelPrincipal(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));


        this.setBounds(100,100,400,350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

        /*Titulo*/
        l1.setFont(l1.getFont().deriveFont(20f));
        l1.setBounds(380, 300, 250, 25);
        l1.setForeground(Color.black);
        l1.setSize(l1.getPreferredSize());
        this.add(l1);

        usuario.setFont(usuario.getFont().deriveFont(16f));
        usuario.setBounds(380, 350, 90, 25);
        usuario.addActionListener(this);
        this.add(usuario);

        representante.setFont(representante.getFont().deriveFont(16f));
        representante.setBounds(520, 350, 160, 25);
        representante.addActionListener(this);
        this.add(representante);


        /***********************
         * LOGO
         **********************/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon (imagen.getScaledInstance(100,100,Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);


        cierraSesion.setFont(cierraSesion.getFont().deriveFont(16f));
        cierraSesion.setBounds(830, 130, 75, 25);
        cierraSesion.setForeground(Color.black);
        cierraSesion.setOpaque(false);
        cierraSesion.setContentAreaFilled(false);
        cierraSesion.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        cierraSesion.addActionListener(this);
        cierraSesion.setVisible(true);
        this.add(cierraSesion);

        l2.setFont(l2.getFont().deriveFont(20f));
        l2.setBounds(20, 110, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());
        this.add(l2);

        l3.setFont(l3.getFont().deriveFont(20f));
        l3.setBounds(110, 110, 100, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());
        this.add(l3);

        proyectos.setFont(proyectos.getFont().deriveFont(16f));
        proyectos.setBounds(20, 160, 160, 25);
        proyectos.addActionListener(this);
        proyectos.setVisible(false);
        this.add(proyectos);

        colectivos.setFont(colectivos.getFont().deriveFont(16f));
        colectivos.setBounds(20, 205, 160, 25);
        colectivos.addActionListener(this);
        colectivos.setVisible(false);
        this.add(colectivos);


        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(981, 725));
        this.setVisible(true);

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

        if(e.getSource() == cierraSesion){
            gui.getController().logout();
            gui.volverAlLogin(this);
        } else if(e.getSource() == usuario){
            l1.setVisible(false);
            colectivos.setVisible(true);
            proyectos.setVisible(true);
            usuario.setVisible(false);
            representante.setVisible(false);
            representanteFlag = false;
        } else if(e.getSource() == representante){
            l1.setVisible(false);
            colectivos.setVisible(true);
            proyectos.setVisible(true);
            usuario.setVisible(false);
            representante.setVisible(false);
            representanteFlag = true;
        } else if(e.getSource() == proyectos){
            if(representanteFlag){
                //mostrar proyectos apoyados por los colectivos creados
                gui.irProyectos(this);
            } else {
                // mostrar a los que pertenece el usuario
                gui.irProyectos(this);
            }

        } else if(e.getSource() == colectivos){
            if(representanteFlag){
                gui.irColectivos(this);
            } else {
                gui.irColectivos(this);
            }

        }


        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Funcion que puebla de datos el panel en cuestión
     */
    public void asignarData(){

        if(gui.getController().getLoggedUser() != null){
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);


            if(!gui.getController().esRepresentante(gui.getController().getLoggedUser())){
                l1.setVisible(false);
                usuario.setVisible(false);
                representante.setVisible(false);
                colectivos.setVisible(true);
                proyectos.setVisible(true);
            } else {
                l1.setVisible(true);
                usuario.setVisible(true);
                representante.setVisible(true);
            }
            this.add(l1);
            this.add(usuario);
            this.add(representante);
            this.add(proyectos);
            this.add(colectivos);
        }
    }
}
