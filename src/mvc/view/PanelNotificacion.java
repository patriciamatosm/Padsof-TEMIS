package mvc.view;

import mvc.model.Notificacion;
import mvc.model.Proyecto;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelNotificacion extends JPanel implements ActionListener {

    private MiGUI gui;

    private Notificacion n;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton cierraSesion = new JButton("Salir");

    /*labels*/
    private JLabel l1 = new JLabel("Noticia" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");
    private JLabel l4 = new JLabel("Descripción:");

    private JTextArea titulo = new JTextArea("No title",1, 50);
    private JTextArea desc = new JTextArea("No desc", 40, 50);

    public PanelNotificacion(MiGUI gui){
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));

        this.setBounds(100, 100, 400, 350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

        /*Titulo*/
        l1.setFont(l1.getFont().deriveFont(30f));
        l1.setBounds(350, 100, 450, 45);
        l1.setForeground(Color.black);
        this.add(l1);

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

        /*Logo*/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);

        /*Textos*/
        l2.setFont(l2.getFont().deriveFont(16f));
        l2.setBounds(20, 110, 100, 25);
        l2.setForeground(Color.black);
        l2.setSize(l2.getPreferredSize());

        l3.setFont(l3.getFont().deriveFont(16f));
        l3.setBounds(110, 110, 10, 25);
        l3.setForeground(Color.black);
        l3.setSize(l3.getPreferredSize());

        l4.setFont(l4.getFont().deriveFont(13f));
        l4.setBounds(200, 250, 10, 25);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());

        this.add(l2);
        this.add(l3);
        this.add(l4);

        titulo.setFont(titulo.getFont().deriveFont(16f));
        titulo.setBounds(200, 200, 50, 40);
        titulo.setForeground(Color.black);
        titulo.setSize(titulo.getPreferredSize());
        Font boldFont = new Font(titulo.getFont().getName(), Font.BOLD, titulo.getFont().getSize());
        titulo.setFont(boldFont);
        titulo.setOpaque(false);
        titulo.setEditable(false);
        titulo.setLineWrap(true);
        titulo.setWrapStyleWord(true);
        this.add(titulo);

        desc.setFont(desc.getFont().deriveFont(13f));
        desc.setBackground(new Color(124, 150, 197));
        desc.setEditable(false);
        desc.setForeground(Color.black);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setBounds(300, 250, 500, 100);
        desc.setVisible(true);
        this.add(desc);

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

    public void setNotificacion(Notificacion n) {
        this.n = n;
        l1.setText(gui.getController().getEmisor(n));
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

        if (e.getSource() == pagPrinc) {
            gui.irPaginaPrincipal(this);
        } else if (e.getSource() == colectivos) {
            gui.irColectivos(this);
        } else if (e.getSource() == proyectos) {
            gui.irProyectos(this);
        }else if (e.getSource() == cierraSesion) {
            gui.volverAlLogin(this);
        }

        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void asignarData() {
        if (gui.getController().getLoggedUser() != null) {
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            if (n != null) {
                titulo.setText(gui.getController().getEmisor(n));
                desc.setText(gui.getController().getMensaje(n));
            }
        }
    }
}