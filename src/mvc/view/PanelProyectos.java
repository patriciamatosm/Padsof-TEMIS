package mvc.view;

import mvc.model.Colectivo;
import mvc.model.Proyecto;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelProyectos extends JPanel implements ActionListener {

    private MiGUI gui;

    private List<Proyecto> actuales = new ArrayList<>();
    private List<Proyecto> todos = new ArrayList<>();
    private int posicion = 0;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página Principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosApoyo = new JButton("Proyectos que apoyas");
    private JButton proponerProyecto = new JButton("Proponer nuevo proyecto");
    private JButton cierraSesion = new JButton("Salir");

    /* Botones lista proyectos */
    private JButton siguientes = new JButton("Siguiente >");
    private JButton anteriores = new JButton("< Anterior");

    private JButton bc1 = new JButton("");
    private JButton bc2 = new JButton("");
    private JButton bc3 = new JButton("");

    /*labels*/
    private JLabel l1 = new JLabel("Proyectos" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    public PanelProyectos(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));

        this.setBounds(100, 100, 400, 350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

        /*Titulo*/
        l1.setFont(l1.getFont().deriveFont(40f));
        l1.setBounds(400, 100, 250, 55);
        l1.setForeground(Color.black);
        this.add(l1);

        /*Logo*/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon (imagen.getScaledInstance(100,100,Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);

        /*Botones*/
        pagPrinc.addActionListener(this);
        pagPrinc.setFont(pagPrinc.getFont().deriveFont(16f));
        pagPrinc.setBounds(20, 160, 160, 25);
        this.add(pagPrinc);

        proyectos.addActionListener(this);
        proyectos.setFont(proyectos.getFont().deriveFont(16f));
        proyectos.setBounds(20, 186, 160, 25);
        this.add(proyectos);

        colectivos.addActionListener(this);
        colectivos.setFont(colectivos.getFont().deriveFont(16f));
        colectivos.setBounds(20, 212, 160, 25);
        this.add(colectivos);

        proyectosApoyo.addActionListener(this);
        proyectosApoyo.setBounds(550, 600, 300, 40);
        this.add(proyectosApoyo);

        proponerProyecto.addActionListener(this);
        proponerProyecto.setBounds(150, 600, 300, 40);
        this.add(proponerProyecto);

        siguientes.setFont(siguientes.getFont().deriveFont(16f));
        siguientes.setBounds(400, 420, 200, 45);
        siguientes.setVisible(false);
        siguientes.addActionListener(this);
        this.add(siguientes);

        anteriores.setFont(anteriores.getFont().deriveFont(16f));
        anteriores.setBounds(200, 420, 200, 45);
        anteriores.setVisible(false);
        anteriores.addActionListener(this);
        this.add(anteriores);

        /*Textos*/
        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(20, 110, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());

        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(110, 110, 10, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());

        this.add(l2);
        this.add(l3);

        cierraSesion.setFont(cierraSesion.getFont().deriveFont(16f));
        cierraSesion.setBounds(830, 130, 75, 25);
        cierraSesion.setForeground(Color.black);
        cierraSesion.setOpaque(false);
        cierraSesion.setContentAreaFilled(false);
        cierraSesion.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        cierraSesion.addActionListener(this);
        cierraSesion.setVisible(true);
        this.add(cierraSesion);

        this.setSize(new Dimension(981, 725));
        this.setVisible(true);

    }

    public void mostrarProyectos() {

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
            if(gui.getController().getEstado(this.actuales.get(0))== Proyecto.Estado.CADUCADO){
                this.bc1.setText(this.actuales.get(0).getProjectTitle()+"   (Caducado)");
            } else {
                this.bc1.setText(this.actuales.get(0).getProjectTitle());
            }
            bc1.setVisible(true);
            bc1.setBounds(200, 300, 400, 40);
            bc1.addActionListener(this);
            this.add(bc1);

            if(this.actuales.size() > 1) {
                if(gui.getController().getEstado(this.actuales.get(1))== Proyecto.Estado.CADUCADO){
                    this.bc2.setText(this.actuales.get(1).getProjectTitle()+"   (Caducado)");
                } else {
                    this.bc2.setText(this.actuales.get(1).getProjectTitle());
                }
                bc2.setVisible(true);
                bc2.setBounds(200, 340, 400, 40);
                bc2.addActionListener(this);
                this.add(bc2);

                if (this.actuales.size() > 2) {
                    if(gui.getController().getEstado(this.actuales.get(2))== Proyecto.Estado.CADUCADO){
                        this.bc3.setText(this.actuales.get(2).getProjectTitle()+"   (Caducado)");
                    } else {
                        this.bc3.setText(this.actuales.get(2).getProjectTitle());
                    }
                    bc3.setVisible(true);
                    bc3.setBounds(200, 380, 400, 40);
                    bc3.addActionListener(this);
                    this.add(bc3);
                }
            }
        }
    }

    public void siguienteProyectos() {
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

    public void anteriorProyectos() {
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

        if(e.getSource() == pagPrinc){
            gui.irPaginaPrincipal(this);
        } else if(e.getSource() == colectivos){
            gui.irColectivos(this);
        } else if(e.getSource() == proyectos){
            gui.irProyectos(this);
        } else if(e.getSource() == proyectosApoyo){
            gui.irProyectosApoyo(this);
        } else if(e.getSource() == proponerProyecto){
            gui.irProponerProyecto(this);
        } else if(e.getSource() == siguientes) {
            this.siguienteProyectos();
            this.mostrarProyectos();
        }
        else if(e.getSource() == anteriores) {
            this.anteriorProyectos();
            this.mostrarProyectos();
        }
        else if(e.getSource() == bc1) {
            gui.asignarProyecto(this.actuales.get(0));
            gui.verProyecto(this);
        }
        else if(e.getSource() == bc2) {
            gui.asignarProyecto(this.actuales.get(1));
            gui.verProyecto(this);
        }
        else if(e.getSource() == bc3) {
            gui.asignarProyecto(this.actuales.get(2));
            gui.verProyecto(this);
        }
        else if(e.getSource() == cierraSesion) {
            gui.volverAlLogin(this);
        }

        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void asignarData(){

        if(gui.getController().getLoggedUser() != null){
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            this.posicion = 1;
            this.actuales.clear();

            this.todos = gui.getController().listaProyectos();

            if (this.todos.size() > 2) {
                for (int i = 0; i < 3; i++) {
                    this.actuales.add(this.todos.get(i));
                }
            } else {
                this.actuales.addAll(this.todos);
            }
            this.mostrarProyectos();

        }
    }

}
