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
    private List<Proyecto> proyectos = new ArrayList<>();
    private List<Proyecto> proyectosF = new ArrayList<>();
    private boolean proyecto = false;
    private boolean registro = false;
    private boolean finan = false;
    //Fields
    private JButton sRegistro = new JButton("Solicitudes de registro");
    private JButton sProyecto = new JButton("Solicitudes de proyecto");
    private JButton usuarios = new JButton("Lista de usuarios");
    private JButton eFinanciacion = new JButton("Espera de financiación");
    private JButton cierraSesion = new JButton("Salir");
    private JButton volver = new JButton("< Volver");
    private JButton aceptar = new JButton("Aceptar");
    private JButton rechazar = new JButton("Rechazar");

    //Label
    private JLabel icon1 = new JLabel("");
    private JLabel l1 = new JLabel("Perfil del administrador");
    private JLabel l2 = new JLabel("");
    private JLabel l3 = new JLabel("");
    private JLabel l4 = new JLabel("");
    private JLabel l5 = new JLabel("");

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
        l4.setVisible(false);
        l5.setVisible(false);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);

        volver.setVisible(false);
        this.add(volver);
        aceptar.setVisible(false);
        this.add(aceptar);
        rechazar.setVisible(false);
        this.add(rechazar);


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
                JOptionPane.showMessageDialog(this, "No hay más registros que revisar! Buen trabajo!",
                        "" ,JOptionPane.PLAIN_MESSAGE);

            } else {
                System.out.println(this.pendientes);
                this.registro = true;

                this.muestraPendientes();

            }

        } else if(e.getSource() == sProyecto){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);

            this.assignProyectos();

            volver.setFont(volver.getFont().deriveFont(16f));
            volver.setBounds(40, 200, 75, 25);
            volver.setForeground(Color.black);
            volver.setOpaque(false);
            volver.setContentAreaFilled(false);
            volver.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            volver.addActionListener(this);
            volver.setVisible(true);
            this.add(volver);

            if (proyectos.isEmpty()){
                JOptionPane.showMessageDialog(this, "No hay más proyectos que revisar! Buen trabajo!",
                        "" ,JOptionPane.PLAIN_MESSAGE);

            } else {
                System.out.println(this.proyectos);
                this.proyecto = true;
                this.muestraProyectosPendientes();
            }

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

            this.assignProyectosF();

            volver.setFont(volver.getFont().deriveFont(16f));
            volver.setBounds(40, 200, 75, 25);
            volver.setForeground(Color.black);
            volver.setOpaque(false);
            volver.setContentAreaFilled(false);
            volver.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            volver.addActionListener(this);
            volver.setVisible(true);
            this.add(volver);

            if (proyectosF.isEmpty()){
                JOptionPane.showMessageDialog(this, "No hay más proyectos a financiacion que revisar! Buen trabajo!",
                        "" ,JOptionPane.PLAIN_MESSAGE);

            } else {
                System.out.println(this.proyectosF);
                this.finan = true;
                this.muestraProyectosPendientesF();
            }


        } else if(e.getSource() == volver){
            this.proyecto = false;
            this.finan = false;
            this.registro = false;
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
        } else if(e.getSource() == aceptar){
            if(registro){
                if(!pendientes.isEmpty()){
                    gui.getController().aceptarRegistro(this.pendientes.get(0));
                    this.assingRegistros();
                    this.muestraPendientes();
                }
            } else if(proyecto){
                if(!proyectos.isEmpty()){
                    gui.getController().aceptarProyecto(this.proyectos.get(0));
                    this.assignProyectos();
                    this.muestraProyectosPendientes();
                }
            } else if(finan){
                if(!proyectosF.isEmpty()){
                    gui.getController().pedirFinanciacion(this.proyectosF.get(0));
                    this.assignProyectosF();
                    this.muestraProyectosPendientesF();
                }
            }

        } else if(e.getSource() == rechazar){
            if(registro){
                if(!pendientes.isEmpty()){
                    gui.getController().noAceptarRegistro(this.pendientes.get(0));
                    this.assingRegistros();
                    this.muestraPendientes();
                }
            } else if(proyecto){
                if(!proyectos.isEmpty()){
                    gui.getController().noAceptarProyecto(this.proyectos.get(0));
                    this.assignProyectos();
                    this.muestraProyectosPendientes();
                }
            } else if(finan){
                if(!proyectos.isEmpty()){
                    gui.getController().noPedirFinanciacion(this.proyectosF.get(0));
                    this.assignProyectosF();
                    this.muestraProyectosPendientesF();
                }
            }
        }

        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Asigna registros pendientes
     */
    private void assingRegistros(){
        this.pendientes = gui.getController().registrosPendientes();
    }

    /**
     * Asigna proyectos pendientes
     */
    private void assignProyectos(){
        this.proyectos = gui.getController().proyectosPendientes();
    }

    /**
     * Asigna proyectos pendientes financiacion
     */
    private void assignProyectosF(){
        this.proyectosF = gui.getController().proyectosPendientesF();
    }

    /**
     * Muestra registros pendientes
     */
    private void muestraPendientes(){

        if(!pendientes.isEmpty()) {
            l4.setText("Registros Pendientes");
            l4.setFont(l4.getFont().deriveFont(16f));
            l4.setBounds(440, 160, 400, 80);
            l4.setForeground(Color.black);
            l4.setSize(l4.getPreferredSize());
            l4.setVisible(true);
            this.add(l4);

            l2.setText("Nombre:        " + gui.getController().getNombre(this.pendientes.get(0)));
            l2.setFont(l2.getFont().deriveFont(16f));
            l2.setBounds(420, 280, 100, 25);
            l2.setForeground(Color.black);
            l2.setSize(l2.getPreferredSize());
            l2.setVisible(true);
            this.add(l2);

            l3.setText("Estado:          " + gui.getController().getEstado(this.pendientes.get(0)));
            l3.setFont(l3.getFont().deriveFont(16f));
            l3.setBounds(420, 330, 100, 25);
            l3.setForeground(Color.black);
            l3.setSize(l3.getPreferredSize());
            l3.setVisible(true);
            this.add(l3);

            rechazar.setFont(rechazar.getFont().deriveFont(14f));
            rechazar.setBounds(400, 375, 100, 25);
            rechazar.addActionListener(this);
            rechazar.setVisible(true);
            this.add(rechazar);

            aceptar.setFont(aceptar.getFont().deriveFont(14f));
            aceptar.setBounds(520, 375, 100, 25);
            aceptar.addActionListener(this);
            aceptar.setVisible(true);
            this.add(aceptar);
        } else {
            JOptionPane.showMessageDialog(this, "No hay más registros que revisar! Buen trabajo!",
                    "" ,JOptionPane.PLAIN_MESSAGE);
        }

    }

    /**
     * Muestra proyectos pendientes
     */
    private void muestraProyectosPendientes(){
        if(!proyectos.isEmpty()) {
            l4.setText("Proyectos Pendientes");
            l4.setFont(l4.getFont().deriveFont(16f));
            l4.setBounds(440, 160, 400, 80);
            l4.setForeground(Color.black);
            l4.setSize(l4.getPreferredSize());
            l4.setVisible(true);
            this.add(l4);

            l2.setText("Título:        " + gui.getController().getTitulo(this.proyectos.get(0)));
            l2.setFont(l2.getFont().deriveFont(16f));
            l2.setBounds(420, 280, 100, 25);
            l2.setForeground(Color.black);
            l2.setSize(l2.getPreferredSize());
            l2.setVisible(true);
            this.add(l2);

            l3.setText("Estado:          " + gui.getController().getEstado(this.proyectos.get(0)));
            l3.setFont(l3.getFont().deriveFont(16f));
            l3.setBounds(420, 330, 100, 25);
            l3.setForeground(Color.black);
            l3.setSize(l3.getPreferredSize());
            l3.setVisible(true);
            this.add(l3);

            l5.setText("Creador:          " + gui.getController().getNombreCreador(this.proyectos.get(0)));
            l5.setFont(l5.getFont().deriveFont(16f));
            l5.setBounds(420, 380, 100, 25);
            l5.setForeground(Color.black);
            l5.setSize(l5.getPreferredSize());
            l5.setVisible(true);
            this.add(l5);

            rechazar.setFont(rechazar.getFont().deriveFont(14f));
            rechazar.setBounds(400, 425, 100, 25);
            rechazar.addActionListener(this);
            rechazar.setVisible(true);
            this.add(rechazar);

            aceptar.setFont(aceptar.getFont().deriveFont(14f));
            aceptar.setBounds(540, 425, 100, 25);
            aceptar.addActionListener(this);
            aceptar.setVisible(true);
            this.add(aceptar);
        } else {
            JOptionPane.showMessageDialog(this, "No hay más proyectos que revisar! Buen trabajo!",
                    "" ,JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Muestra proyectos pendientes financiacion
     */
    private void muestraProyectosPendientesF(){
        if(!proyectosF.isEmpty()) {
            l4.setText("Proyectos Pendientes para financiación");
            l4.setFont(l4.getFont().deriveFont(16f));
            l4.setBounds(440, 160, 400, 80);
            l4.setForeground(Color.black);
            l4.setSize(l4.getPreferredSize());
            l4.setVisible(true);
            this.add(l4);

            l2.setText("Título:        " + gui.getController().getTitulo(this.proyectosF.get(0)));
            l2.setFont(l2.getFont().deriveFont(16f));
            l2.setBounds(420, 280, 100, 25);
            l2.setForeground(Color.black);
            l2.setSize(l2.getPreferredSize());
            l2.setVisible(true);
            this.add(l2);

            l3.setText("Estado:          " + gui.getController().getEstado(this.proyectosF.get(0)));
            l3.setFont(l3.getFont().deriveFont(16f));
            l3.setBounds(420, 330, 100, 25);
            l3.setForeground(Color.black);
            l3.setSize(l3.getPreferredSize());
            l3.setVisible(true);
            this.add(l3);

            l5.setText("Cumple con el número de votos:          " +
                    gui.getController().cumpleNumVotos(this.proyectosF.get(0)));
            l5.setFont(l5.getFont().deriveFont(16f));
            l5.setBounds(420, 380, 100, 25);
            l5.setForeground(Color.red);
            l5.setSize(l5.getPreferredSize());
            l5.setVisible(true);
            this.add(l5);

            rechazar.setFont(rechazar.getFont().deriveFont(14f));
            rechazar.setBounds(400, 425, 100, 25);
            rechazar.addActionListener(this);
            rechazar.setVisible(true);
            this.add(rechazar);

            aceptar.setFont(aceptar.getFont().deriveFont(14f));
            aceptar.setBounds(540, 425, 100, 25);
            aceptar.addActionListener(this);
            aceptar.setVisible(true);
            this.add(aceptar);
        } else {
            JOptionPane.showMessageDialog(this, "No hay más proyectos a financiar que revisar! Buen trabajo!",
                    "" ,JOptionPane.PLAIN_MESSAGE);
        }
    }

    /*public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.black);
        g2.drawRect(40, 40, 100, 100);
    }*/

}
