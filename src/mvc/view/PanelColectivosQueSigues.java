package mvc.view;

import mvc.model.Colectivo;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelColectivosQueSigues extends JPanel implements ActionListener {

    private MiGUI gui;

    private List<Colectivo> actuales = new ArrayList<>();
    private List<Colectivo> todos = new ArrayList<>();
    private int posicion = 0;

    private JLabel l1 = new JLabel("Colectivos" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    private JButton back = new JButton("Volver");
    private JButton cierraSesion = new JButton("Salir");
    private JButton siguientes = new JButton("Siguiente >");
    private JButton anteriores = new JButton("< Anterior");

    /* Botones de los colectivos */
    private JButton bc1 = new JButton("");
    private JButton bc2 = new JButton("");
    private JButton bc3 = new JButton("");

    private JButton crearColectivo = new JButton("Crear un nuevo colectivo");
    private JButton tusColectivos = new JButton("Ver tus colectivos");

    public PanelColectivosQueSigues(MiGUI gui) {
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
        l1.setSize(l1.getPreferredSize());
        this.add(l1);

        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(20, 110, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());

        this.add(l2);

        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(110, 110, 10, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());

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

        /*Logo*/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon (imagen.getScaledInstance(100,100,Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);

        back.setFont(back.getFont().deriveFont(16f));
        back.setBounds(20, 160, 160, 25);
        back.addActionListener(this);
        this.add(back);

        /* Colectivos */
        crearColectivo.addActionListener(this);
        crearColectivo.setBounds(150, 600, 300, 40);
        this.add(crearColectivo);

        tusColectivos.addActionListener(this);
        tusColectivos.setBounds(450, 600, 300, 40);
        this.add(tusColectivos);

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

    }

    public void mostrarColectivos() {

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
            this.bc1.setText(this.actuales.get(0).getNombre());
            bc1.setVisible(true);
            bc1.setBounds(200, 300, 400, 40);
            bc1.addActionListener(this);
            this.add(bc1);

            if(this.actuales.size() > 1) {
                this.bc2.setText(this.actuales.get(1).getNombre());
                bc2.setVisible(true);
                bc2.setBounds(200, 340, 400, 40);
                bc2.addActionListener(this);
                this.add(bc2);

                if (this.actuales.size() > 2) {
                    this.bc3.setText(this.actuales.get(2).getNombre());
                    bc3.setVisible(true);
                    bc3.setBounds(200, 380, 400, 40);
                    bc3.addActionListener(this);
                    this.add(bc3);
                }
            }
        }
    }

    public void siguienteColectivos() {
        int numCol = this.todos.size();
        int contador;

        if(!this.todos.isEmpty() && (this.posicion + 3) <= numCol) {
            this.posicion = this.posicion + 3;
            this.actuales.clear();

            if ((this.posicion + 3) <= numCol) {
                for (contador = 0; contador < 3; contador++) {
                    this.actuales.add(this.todos.get(this.posicion));
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

    public void anteriorColectivos() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Temis pTemis = Temis.getInstance();
        try {
            pTemis.leerFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if(e.getSource() == cierraSesion) {
            gui.getController().logout();
            gui.volverAlLogin(this);
        }
        else if(e.getSource() == back) {
            gui.irPaginaPrincipal(this);
        }
        else if(e.getSource() == crearColectivo) {
            gui.crearColectivos(this);
        }
        else if(e.getSource() == tusColectivos) {
            gui.colectivoPropio(this);
        }
        else if(e.getSource() == siguientes) {
            this.siguienteColectivos();
            this.mostrarColectivos();
        }
        else if(e.getSource() == anteriores) {
            this.anteriorColectivos();
            this.mostrarColectivos();
        }
        else if(e.getSource() == bc1) {
            gui.asignarColectivo(this.actuales.get(0));
            gui.verColectivo(this);
        }
        else if(e.getSource() == bc2) {
            gui.asignarColectivo(this.actuales.get(1));
            gui.verColectivo(this);
        }
        else if(e.getSource() == bc3) {
            gui.asignarColectivo(this.actuales.get(2));
            gui.verColectivo(this);
        }
    }

    public void asignarData() {
        if(gui.getController().getLoggedUser() != null){
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            this.posicion = 1;
            this.actuales.clear();

            this.todos = gui.getController().listaColectivosSeguidos();

            if (this.todos.size() > 2) {
                for (int i = 0; i < 3; i++) {
                    this.actuales.add(this.todos.get(i));
                }
            } else {
                this.actuales.addAll(this.todos);
            }
            this.mostrarColectivos();
        }
    }
}
