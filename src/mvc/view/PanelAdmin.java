package mvc.view;


import mvc.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;


/**
 * Clase que define el Panel del admin
 *
 * @author Daniel Prieto Fernández
 *         Silvia Tomey Prieto
 *         Patricia Matos Meza
 * @version 10/04/2020
 */
public class PanelAdmin extends JPanel implements ActionListener {


    private MiGUI gui;
    private List<Usuario> pendientes = new ArrayList<>();

    //Fields
    private JButton sRegistro = new JButton("Solicitudes de registro");
    private JButton sProyecto = new JButton("Solicitudes de proyecto");
    private JButton usuarios = new JButton("Lista de usuarios");
    private JButton eFinanciacion = new JButton("Espera de financiación");
    private JButton cierraSesion = new JButton("Salir");

    //Label
    private JLabel l1 = new JLabel("Perfil del administrador");
    private JLabel l2 = new JLabel("");
    private JLabel l3 = new JLabel("");
    private JLabel l4 = new JLabel("");


    /**
     * Constructor de la clase
     * @param gui de la aplicacion
     */
    public PanelAdmin(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));


        this.setBounds(100,100,400,350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

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

        l1.setFont(l1.getFont().deriveFont(20f));
        l1.setBounds(20, 110, 100, 25);
        l1.setForeground(Color.black);
        l1.setSize(l1.getPreferredSize());
        this.add(l1);

        sRegistro.setFont(sRegistro.getFont().deriveFont(14f));
        sRegistro.setBounds(400, 240, 200, 40);
        sRegistro.addActionListener(this);
        this.add(sRegistro);

        sProyecto.setFont(sProyecto.getFont().deriveFont(14f));
        sProyecto.setBounds(400, 290, 200, 40);
        sProyecto.addActionListener(this);
        this.add(sProyecto);

        usuarios.setFont(usuarios.getFont().deriveFont(14f));
        usuarios.setBounds(400, 340, 200, 40);
        usuarios.addActionListener(this);
        this.add(usuarios);

        eFinanciacion.setFont(eFinanciacion.getFont().deriveFont(14f));
        eFinanciacion.setBounds(400, 390, 200, 40);
        eFinanciacion.addActionListener(this);
        this.add(eFinanciacion);



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

        } else if(e.getSource() == sRegistro){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);

            this.assingRegistros();

            l2.setText("Nombre: " + this.pendientes.get(0).getNombre());
            l2.setBounds(120, 110, 100, 25);
            l1.setForeground(Color.black);
            l1.setSize(l1.getPreferredSize());
            this.add(l1);

        } else if(e.getSource() == sProyecto){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);


        } else if(e.getSource() == usuarios){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);

        } else if(e.getSource() == eFinanciacion){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);

        }


        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void assingRegistros(){
        this.pendientes = gui.getController().registrosPendientes();
    }

    /*public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.black);
        g2.drawRect(40, 40, 100, 100);
    }*/

}
