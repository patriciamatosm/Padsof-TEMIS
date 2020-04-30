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
    private List<Usuario> siguiente = new ArrayList<>();
    private List<Usuario> anterior = new ArrayList<>();
    private List<Proyecto> proyectos = new ArrayList<>();
    private List<Proyecto> proyectosF = new ArrayList<>();
    private boolean proyecto = false;
    private boolean registro = false;
    private boolean finan = false;
    private boolean ant = false;
    private boolean sig = false;
    private Integer mostrados = 0;
    //Fields
    private JButton sRegistro = new JButton("Solicitudes de registro");
    private JButton sProyecto = new JButton("Solicitudes de proyecto");
    private JButton usuarios = new JButton("Lista de usuarios");
    private JButton eFinanciacion = new JButton("Espera de financiación");
    private JButton cierraSesion = new JButton("Salir");
    private JButton volver = new JButton("< Volver");
    private JButton aceptar = new JButton("Aceptar");
    private JButton rechazar = new JButton("Rechazar");
    private JButton bloquear1 = new JButton("Bloquear");
    private JButton bloquear2 = new JButton("Bloquear");
    private JButton bloquear3 = new JButton("Bloquear");
    private JButton a = new JButton("< Anterior");
    private JButton s = new JButton("Siguiente >");
    //Label
    private JLabel icon1 = new JLabel("");
    private JLabel l1 = new JLabel("Perfil del administrador");
    private JLabel l2 = new JLabel("");
    private JLabel l3 = new JLabel("");
    private JLabel l4 = new JLabel("");
    private JLabel l5 = new JLabel("");
    private JLabel l6 = new JLabel("");
    private JLabel l7 = new JLabel("");
    private JLabel l8 = new JLabel("");
    private JLabel l9 = new JLabel("");
    private JLabel l10 = new JLabel("");
    private JLabel l11 = new JLabel("");

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
        l6.setVisible(false);
        l7.setVisible(false);
        l8.setVisible(false);
        l9.setVisible(false);
        l10.setVisible(false);
        l11.setVisible(false);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);
        this.add(l8);
        this.add(l9);
        this.add(l10);
        this.add(l11);

        volver.setVisible(false);
        this.add(volver);
        aceptar.setVisible(false);
        this.add(aceptar);
        rechazar.setVisible(false);
        this.add(rechazar);
        bloquear1.setVisible(false);
        this.add(bloquear1);
        bloquear2.setVisible(false);
        this.add(bloquear2);
        bloquear3.setVisible(false);
        this.add(bloquear3);
        a.setVisible(false);
        this.add(a);
        s.setVisible(false);
        this.add(s);

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

                this.proyecto = true;
                this.muestraProyectosPendientes();
            }

        } else if(e.getSource() == usuarios){
            sRegistro.setVisible(false);
            sProyecto.setVisible(false);
            eFinanciacion.setVisible(false);
            usuarios.setVisible(false);

            this.assignUsuarios();

            volver.setFont(volver.getFont().deriveFont(16f));
            volver.setBounds(40, 200, 75, 25);
            volver.setForeground(Color.black);
            volver.setOpaque(false);
            volver.setContentAreaFilled(false);
            volver.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            volver.addActionListener(this);
            volver.setVisible(true);
            this.add(volver);

            a.setFont(a.getFont().deriveFont(14f));
            a.setBounds(40, 240, 100, 25);
            a.setForeground(Color.black);
            a.setOpaque(false);
            a.setContentAreaFilled(false);
            a.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            a.addActionListener(this);
            a.setVisible(true);
            this.add(a);


            s.setFont(s.getFont().deriveFont(14f));
            s.setBounds(40, 280, 100, 25);
            s.setForeground(Color.black);
            s.setOpaque(false);
            s.setContentAreaFilled(false);
            s.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
            s.addActionListener(this);
            s.setVisible(true);
            this.add(s);


            if(siguiente.isEmpty() && anterior.isEmpty()){
                JOptionPane.showMessageDialog(this, "No hay usuarios que revisar! Buen trabajo!",
                        "" ,JOptionPane.PLAIN_MESSAGE);
            } else {
                System.out.println(this.siguiente);
                this.muestraUsuariosSig();
            }

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
        } else if(e.getSource() == s){
            if(siguiente.isEmpty()){
                JOptionPane.showMessageDialog(this, "No hay más usuarios que revisar! Buen trabajo!",
                        "" ,JOptionPane.PLAIN_MESSAGE);
            } else {
                this.muestraUsuariosSig();
            }
        } else if(e.getSource() == a){
            if(anterior.isEmpty()){
                JOptionPane.showMessageDialog(this, "No puedes ir más hacia atrás",
                        "" ,JOptionPane.PLAIN_MESSAGE);
            } else {
                this.muestraUsuariosAnt();
            }
        } else if(e.getSource() == bloquear1){

            if(bloquear1.getText().equals("Bloquear")) {

                if (sig) {
                    gui.getController().bloquear(this.anterior.get(this.anterior.size() - this.mostrados));
                } else {
                    gui.getController().bloquear(this.siguiente.get(this.siguiente.size() - this.mostrados));
                }
            } else {
                if (sig) {
                    gui.getController().desbloquear(this.anterior.get(this.anterior.size() - this.mostrados));
                } else {
                    gui.getController().desbloquear(this.siguiente.get(this.siguiente.size() - this.mostrados));
                }
            }

        } else if(e.getSource() == bloquear2){

            if(bloquear2.getText().equals("Bloquear")) {
                if (sig) {
                    gui.getController().bloquear(this.anterior.get(this.anterior.size() - (this.mostrados - 1)));
                } else {
                    gui.getController().bloquear(this.siguiente.get(this.siguiente.size() - (this.mostrados - 1)));
                }
            } else {
                if (sig) {
                    gui.getController().desbloquear(this.anterior.get(this.anterior.size() - (this.mostrados - 1)));
                } else {
                    gui.getController().desbloquear(this.siguiente.get(this.siguiente.size() - (this.mostrados - 1)));
                }
            }

        } else if(e.getSource() == bloquear3){

            if(bloquear3.getText().equals("Bloquear")) {
                if (sig) {
                    gui.getController().bloquear(this.anterior.get(this.anterior.size() - (this.mostrados - 2)));
                } else {
                    gui.getController().bloquear(this.siguiente.get(this.siguiente.size() - (this.mostrados - 2)));
                }
            } else {
                if (sig) {
                    gui.getController().desbloquear(this.anterior.get(this.anterior.size() - (this.mostrados - 2)));
                } else {
                    gui.getController().desbloquear(this.siguiente.get(this.siguiente.size() - (this.mostrados - 2)));
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

    private void assignUsuarios(){
        this.siguiente = gui.getController().listaUsuarios();
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

    /**
     * Muestra usuarios en el sistema
     */
    private void muestraUsuariosSig(){
        this.sig = true;
        this.ant = false;
        this.reseteaInterfaz();
        l4.setText("Lista de usuarios");
        l4.setFont(l4.getFont().deriveFont(16f));
        l4.setBounds(440, 160, 400, 80);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());
        l4.setVisible(true);
        this.add(l4);

        /*****
         * 1
         ****/
        System.out.println("s" +this.siguiente);
        if(this.siguiente == null || this.siguiente.isEmpty() || this.siguiente.size() <= 0) return;
        System.out.println("1 " +this.siguiente.get(0));

        l2.setText("Nombre:        " + gui.getController().getNombre(this.siguiente.get(0)));
        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(420, 280, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());
        l2.setVisible(true);
        this.add(l2);

        l3.setText("Dni:          " + gui.getController().getDNI(this.siguiente.get(0)));
        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(420, 310, 100, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());
        l3.setVisible(true);
        this.add(l3);

        l5.setText("Estado:          " +
                gui.getController().getEstado(this.siguiente.get(0)));
        l5.setFont(l5.getFont().deriveFont(16f));
        l5.setBounds(420, 340, 100, 25);
        l5.setForeground(Color.red);
        l5.setSize(l5.getPreferredSize());
        l5.setVisible(true);
        this.add(l5);

        if(gui.getController().getEstado(this.siguiente.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear1.setText("Desbloquear");
        else bloquear1.setText("Bloquear");
        bloquear1.setFont(bloquear1.getFont().deriveFont(14f));
        bloquear1.setBounds(640, 310, 100, 25);
        bloquear1.addActionListener(this);
        bloquear1.setVisible(true);
        this.add(bloquear1);

        this.anterior.add(this.siguiente.get(0));
        this.siguiente.remove(0);
        this.mostrados++;

        /*****
         * 2
         ****/
        System.out.println("s" +this.siguiente);
        if(this.siguiente == null ||this.siguiente.isEmpty() || this.siguiente.size() <= 0) return;
        System.out.println("2 " +this.siguiente.get(0));
        l6.setText("Nombre:        " + gui.getController().getNombre(this.siguiente.get(0)));
        l6.setFont(l6.getFont().deriveFont(16f));
        l6.setBounds(420, 405, 100, 25);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        l6.setVisible(true);
        this.add(l6);

        l7.setText("Dni:          " + gui.getController().getDNI(this.siguiente.get(0)));
        l7.setFont(l7.getFont().deriveFont(16f));
        l7.setBounds(420, 435, 100, 25);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        l7.setVisible(true);
        this.add(l7);

        l8.setText("Estado:          " +
                gui.getController().getEstado(this.siguiente.get(0)));
        l8.setFont(l8.getFont().deriveFont(16f));
        l8.setBounds(420, 465, 100, 25);
        l8.setForeground(Color.red);
        l8.setSize(l8.getPreferredSize());
        l8.setVisible(true);
        this.add(l8);

        if(gui.getController().getEstado(this.siguiente.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear2.setText("Desbloquear");
        else bloquear2.setText("Bloquear");
        bloquear2.setFont(bloquear2.getFont().deriveFont(14f));
        bloquear2.setBounds(640, 435, 100, 25);
        bloquear2.addActionListener(this);
        bloquear2.setVisible(true);
        this.add(bloquear2);

        this.anterior.add(this.siguiente.get(0));
        this.siguiente.remove(0);
        this.mostrados++;

        /*****
         * 3
         ****/
        System.out.println("s" +this.siguiente);
        if(this.siguiente == null || this.siguiente.isEmpty() || this.siguiente.size() <= 0) return;
        System.out.println("3 " +this.siguiente.get(0));
        l9.setText("Nombre:        " + gui.getController().getNombre(this.siguiente.get(0)));
        l9.setFont(l9.getFont().deriveFont(16f));
        l9.setBounds(420, 530, 100, 25);
        l9.setForeground(Color.black);
        l9.setSize(l9.getPreferredSize());
        l9.setVisible(true);
        this.add(l9);

        l10.setText("Dni:          " + gui.getController().getDNI(this.siguiente.get(0)));
        l10.setFont(l10.getFont().deriveFont(16f));
        l10.setBounds(420, 560, 100, 25);
        l10.setForeground(Color.black);
        l10.setSize(l10.getPreferredSize());
        l10.setVisible(true);
        this.add(l10);

        l11.setText("Estado:          " +
                gui.getController().getEstado(this.siguiente.get(0)));
        l11.setFont(l11.getFont().deriveFont(16f));
        l11.setBounds(420, 590, 100, 25);
        l11.setForeground(Color.red);
        l11.setSize(l11.getPreferredSize());
        l11.setVisible(true);
        this.add(l11);

        if(gui.getController().getEstado(this.siguiente.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear3.setText("Desbloquear");
        else bloquear3.setText("Bloquear");
        bloquear3.setFont(bloquear3.getFont().deriveFont(14f));
        bloquear3.setBounds(640, 560, 100, 25);
        bloquear3.addActionListener(this);
        bloquear3.setVisible(true);
        this.add(bloquear3);

        this.anterior.add(this.siguiente.get(0));
        this.siguiente.remove(0);
        this.mostrados++;


    }

    /**
     * Muestra usuarios en el sistema
     */
    private void muestraUsuariosAnt(){
        this.sig = false;
        this.ant = true;
        this.reseteaInterfaz();
        l4.setText("Lista de usuarios");
        l4.setFont(l4.getFont().deriveFont(16f));
        l4.setBounds(440, 160, 400, 80);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());
        l4.setVisible(true);
        this.add(l4);

        /*****
         * 1
         ****/
        System.out.println("a" +this.anterior);
        if(this.anterior == null || this.anterior.isEmpty() || this.anterior.size() <= 0) return;
        l2.setText("Nombre:        " + gui.getController().getNombre(this.anterior.get(0)));
        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(420, 280, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());
        l2.setVisible(true);
        this.add(l2);

        l3.setText("Dni:          " + gui.getController().getDNI(this.anterior.get(0)));
        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(420, 310, 100, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());
        l3.setVisible(true);
        this.add(l3);

        l5.setText("Estado:          " +
                gui.getController().getEstado(this.anterior.get(0)));
        l5.setFont(l5.getFont().deriveFont(16f));
        l5.setBounds(420, 340, 100, 25);
        l5.setForeground(Color.red);
        l5.setSize(l5.getPreferredSize());
        l5.setVisible(true);
        this.add(l5);

        if(gui.getController().getEstado(this.anterior.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear1.setText("Desbloquear");
        else bloquear1.setText("Bloquear");
        bloquear1.setFont(bloquear1.getFont().deriveFont(14f));
        bloquear1.setBounds(640, 310, 100, 25);
        bloquear1.addActionListener(this);
        bloquear1.setVisible(true);
        this.add(bloquear1);

        this.siguiente.add(this.anterior.get(0));
        this.anterior.remove(0);
        this.mostrados++;


        /*****
         * 2
         ****/
        System.out.println("a" +this.anterior);
        if(this.anterior == null || this.anterior.isEmpty() || this.anterior.size() <= 0) return;
        l6.setText("Nombre:        " + gui.getController().getNombre(this.anterior.get(0)));
        l6.setFont(l6.getFont().deriveFont(16f));
        l6.setBounds(420, 405, 100, 25);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        l6.setVisible(true);
        this.add(l6);

        l7.setText("Dni:          " + gui.getController().getDNI(this.anterior.get(0)));
        l7.setFont(l7.getFont().deriveFont(16f));
        l7.setBounds(420, 435, 100, 25);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        l7.setVisible(true);
        this.add(l7);

        l8.setText("Estado:          " +
                gui.getController().getEstado(this.anterior.get(0)));
        l8.setFont(l8.getFont().deriveFont(16f));
        l8.setBounds(420, 465, 100, 25);
        l8.setForeground(Color.red);
        l8.setSize(l8.getPreferredSize());
        l8.setVisible(true);
        this.add(l8);

        if(gui.getController().getEstado(this.anterior.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear2.setText("Desbloquear");
        else bloquear2.setText("Bloquear");
        bloquear2.setFont(bloquear2.getFont().deriveFont(14f));
        bloquear2.setBounds(640, 435, 100, 25);
        bloquear2.addActionListener(this);
        bloquear2.setVisible(true);
        this.add(bloquear2);

        this.siguiente.add(this.anterior.get(0));
        this.anterior.remove(0);
        this.mostrados++;

        /*****
         * 3
         ****/
        System.out.println("a" + this.anterior);
        if(this.anterior == null || this.anterior.isEmpty() || this.anterior.size() <= 0) return;
        l9.setText("Nombre:        " + gui.getController().getNombre(this.anterior.get(0)));
        l9.setFont(l9.getFont().deriveFont(16f));
        l9.setBounds(420, 530, 100, 25);
        l9.setForeground(Color.black);
        l9.setSize(l9.getPreferredSize());
        l9.setVisible(true);
        this.add(l9);

        l10.setText("Dni:          " + gui.getController().getDNI(this.anterior.get(0)));
        l10.setFont(l10.getFont().deriveFont(16f));
        l10.setBounds(420, 560, 100, 25);
        l10.setForeground(Color.black);
        l10.setSize(l10.getPreferredSize());
        l10.setVisible(true);
        this.add(l10);

        l11.setText("Estado:          " +
                gui.getController().getEstado(this.anterior.get(0)));
        l11.setFont(l11.getFont().deriveFont(16f));
        l11.setBounds(420, 590, 100, 25);
        l11.setForeground(Color.red);
        l11.setSize(l11.getPreferredSize());
        l11.setVisible(true);
        this.add(l11);

        if(gui.getController().getEstado(this.anterior.get(0)) == Usuario.EstadoUsuario.BLOQUEADO)
            bloquear3.setText("Desbloquear");
        else bloquear3.setText("Bloquear");
        bloquear3.setFont(bloquear3.getFont().deriveFont(14f));
        bloquear3.setBounds(640, 560, 100, 25);
        bloquear3.addActionListener(this);
        bloquear3.setVisible(true);
        this.add(bloquear3);

        this.siguiente.add(this.anterior.get(0));
        this.anterior.remove(0);
        this.mostrados++;

    }

    /** Funcion que resetea la interfaz **/
    public void reseteaInterfaz(){
        l2.setVisible(false);
        l3.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        l8.setVisible(false);
        l9.setVisible(false);
        l10.setVisible(false);
        l11.setVisible(false);
        bloquear1.setVisible(false);
        bloquear2.setVisible(false);
        bloquear3.setVisible(false);
        this.mostrados = 0;

    }


}
