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
    private JButton volver = new JButton("< Volver");

    //Label
    private JLabel icon1 = new JLabel("");
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

        l2.setVisible(false);
        l3.setVisible(false);
        this.add(l2);
        this.add(l3);

        volver.setVisible(false);
        this.add(volver);


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

            volver.setFont(volver.getFont().deriveFont(16f));
            volver.setBounds(40, 200, 75, 25);
            volver.setForeground(Color.black);
            volver.setOpaque(false);
            volver.setContentAreaFilled(false);
            volver.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            volver.addActionListener(this);
            volver.setVisible(true);
            this.add(volver);

            if (pendientes.isEmpty()){


            } else {
                System.out.println(this.pendientes);

                l2.setText("Nombre:          " + gui.getController().getNombre(this.pendientes.get(0)));
                l2.setFont(l2.getFont().deriveFont(20f));
                l2.setBounds(400, 240, 100, 25);
                l2.setForeground(Color.black);
                l2.setSize(l2.getPreferredSize());
                l2.setVisible(true);
                this.add(l2);

                l3.setText("Estado:          " + gui.getController().getEstado(this.pendientes.get(0)));
                l3.setFont(l3.getFont().deriveFont(20f));
                l3.setBounds(400, 290, 100, 25);
                l3.setForeground(Color.black);
                l3.setSize(l3.getPreferredSize());
                l3.setVisible(true);
                this.add(l3);
            }

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

        } else if(e.getSource() == volver){
            this.removeAll();
            this.repaint();

            sRegistro.setVisible(true);
            sProyecto.setVisible(true);
            eFinanciacion.setVisible(true);
            usuarios.setVisible(true);
            l1.setVisible(true);
            cierraSesion.setVisible(true);
            icon1.setVisible(true);
            this.add(sRegistro);
            this.add(sProyecto);
            this.add(eFinanciacion);
            this.add(usuarios);
            this.add(l1);
            this.add(cierraSesion);
            this.add(icon1);
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
