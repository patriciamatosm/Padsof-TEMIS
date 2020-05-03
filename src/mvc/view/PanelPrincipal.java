package mvc.view;

import mvc.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private java.util.List<Notificacion> actuales = new ArrayList<>();
    private List<Notificacion> todos = new ArrayList<>();
    private int posicion = 0;

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
    private JLabel l4 = new JLabel("Noticias");

    /* Botones lista notificaciones */
    private JButton siguientes = new JButton("Siguiente >");
    private JButton anteriores = new JButton("< Anterior");

    private JButton bc1 = new JButton("");
    private JButton bc2 = new JButton("");
    private JButton bc3 = new JButton("");


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

        l4.setFont(l4.getFont().deriveFont(30f));
        l4.setBounds(350, 145, 300, 40);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());
        l4.setVisible(false);
        this.add(l4);

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

    public void mostrarNotificaciones() {

        if((this.posicion + 3) <= this.todos.size()) {
            siguientes.setVisible(true);
        }
        else {
            siguientes.setVisible(false);
        }

        if(this.posicion > 3) {
            anteriores.setVisible(true);
        }
        else {
            anteriores.setVisible(false);
        }

        bc1.setVisible(false);
        bc2.setVisible(false);
        bc3.setVisible(false);

        if(this.actuales.size() > 0){
            this.bc1.setText(this.actuales.get(0).getEmisor().getProjectTitle());

            bc1.setVisible(true);
            bc1.setBounds(200, 300, 400, 40);
            bc1.addActionListener(this);
            this.add(bc1);

            if(this.actuales.size() > 1) {
                this.bc2.setText(this.actuales.get(1).getEmisor().getProjectTitle());

                bc2.setVisible(true);
                bc2.setBounds(200, 340, 400, 40);
                bc2.addActionListener(this);
                this.add(bc2);

                if (this.actuales.size() > 2) {
                    this.bc3.setText(this.actuales.get(2).getEmisor().getProjectTitle());

                    bc3.setVisible(true);
                    bc3.setBounds(200, 380, 400, 40);
                    bc3.addActionListener(this);
                    this.add(bc3);
                }
            }
        }
    }

    public void siguienteNotificaciones() {
        int numCol = this.todos.size();
        int contador;

        if(!this.todos.isEmpty() && (this.posicion + 3) <= numCol) {
            this.posicion = this.posicion + 3;
            this.actuales.clear();

            if ((this.posicion + 3) <= numCol) {
                for (contador = -1; contador < 2; contador++) {
                    this.actuales.add(this.todos.get(this.posicion + contador));
                }
            }
            else {
                contador = this.posicion - 1;
                for (; contador < numCol; contador++) {
                    this.actuales.add(this.todos.get(contador));
                }
            }
        }
    }

    public void anteriorNotificaciones() {
        int contador;

        if(!this.todos.isEmpty()) {
            if(this.posicion - 3 > 0){
                this.actuales.clear();

                for(contador = 4; contador > 1; contador--) {
                    this.actuales.add(this.todos.get(this.posicion - contador));
                }
                this.posicion = this.posicion - 3;
            }
        }
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
            l4.setVisible(true);
            colectivos.setVisible(true);
            proyectos.setVisible(true);
            usuario.setVisible(false);
            representante.setVisible(false);
            representanteFlag = false;
            gui.getController().setRepresentante(false);
            if(e.getSource() == siguientes) {
                this.siguienteNotificaciones();
                this.mostrarNotificaciones();
            }
            else if(e.getSource() == anteriores) {
                this.anteriorNotificaciones();
                this.mostrarNotificaciones();
            }
            else if(e.getSource() == bc1) {
                //gui.asignarNotificacion(this.actuales.get(0));
                //gui.verNotificacion(this);
            }
            else if(e.getSource() == bc2) {
                //gui.asignarNotificacion(this.actuales.get(1));
                //gui.verNotificacion(this);
            }
            else if(e.getSource() == bc3) {
                //gui.asignarNotificacion(this.actuales.get(2));
                //gui.verNotificacion(this);
            }
        } else if(e.getSource() == representante){
            l1.setVisible(false);
            l4.setVisible(true);
            colectivos.setVisible(true);
            proyectos.setVisible(true);
            usuario.setVisible(false);
            representante.setVisible(false);
            representanteFlag = true;
            gui.representante(this);
            gui.getController().setRepresentante(true);
            if(e.getSource() == siguientes) {
                this.siguienteNotificaciones();
                this.mostrarNotificaciones();
            }
            else if(e.getSource() == anteriores) {
                this.anteriorNotificaciones();
                this.mostrarNotificaciones();
            }
            else if(e.getSource() == bc1) {
                //gui.asignarNotificacion(this.actuales.get(0));
                //gui.verNotificacion(this);
            }
            else if(e.getSource() == bc2) {
                //gui.asignarNotificacion(this.actuales.get(1));
                //gui.verNotificacion(this);
            }
            else if(e.getSource() == bc3) {
                //gui.asignarNotificacion(this.actuales.get(2));
                //gui.verNotificacion(this);
            }
        } else if(e.getSource() == proyectos){
            if(representanteFlag){
                gui.irProyectos(this);
            } else {
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

            this.posicion = 1;
            this.actuales.clear();

            this.todos = gui.getController().listaNotificacionesSubs(gui.getController().getLoggedUser());

            if (this.todos.size() > 2) {
                for (int i = 0; i < 3; i++) {
                    this.actuales.add(this.todos.get(i));
                }
            } else {
                this.actuales.addAll(this.todos);
            }
            this.mostrarNotificaciones();

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
