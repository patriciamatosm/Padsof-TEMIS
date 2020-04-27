package mvc.view;

import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelProponerProyecto extends JPanel implements ActionListener {

    private MiGUI gui;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosR = new JButton("Todos los proyectos");
    private JButton proponer = new JButton("Proponer");
    private JButton back = new JButton("Volver");
    private JRadioButton social = new JRadioButton("Social");
    private JRadioButton infra = new JRadioButton("Infraestructura");
    private ButtonGroup group = new ButtonGroup();
    private JTextField titulo = new JTextField(25);
    private JTextField descripcion = new JTextField(500);
    private JTextField grupos = new JTextField(200);
    private JCheckBox nacional = new JCheckBox("Nacional");
    private String[] array2 = {"Distrito1", "Distrito2"};
    private JComboBox distrito = new JComboBox(array2);
    private JButton url = new JButton("Adjuntar imagen");

    /*labels*/
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");
    private JLabel l4 = new JLabel("¿Qué clase de proyecto quieres proponer?");
    private JLabel l5 = new JLabel("Título del proyecto:    ");
    private JLabel l6 = new JLabel("Descripción:    ");
    private JLabel l7 = new JLabel("¿A qué grupo(s) beneficiaría este proyecto?");
    private JLabel l8 = new JLabel("¿A nivel nacional o internacional?");
    private JLabel l9 = new JLabel("Selecciona un distrito: ");
    private JLabel l10 = new JLabel("Adjunta, al menos, una imagen que represente la idea que propones: ");

    public PanelProponerProyecto(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));

        this.setBounds(100, 100, 400, 350);
        this.setLayout(null);
        this.setBackground(new Color(124, 150, 197));

        /*Logo*/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);

        /*Botones*/
        pagPrinc.addActionListener(this);
        pagPrinc.setBounds(30, 160, 100, 40);
        this.add(pagPrinc);

        proyectos.addActionListener(this);
        proyectos.setBounds(30, 201, 100, 40);
        this.add(proyectos);

        colectivos.addActionListener(this);
        colectivos.setBounds(30, 242, 100, 40);
        this.add(colectivos);

        proyectosR.addActionListener(this);
        proyectosR.setBounds(550, 600, 300, 40);
        this.add(proyectosR);

        proponer.addActionListener(this);
        proponer.setBounds(400, 550, 100, 40);
        this.add(proponer);

        social.addActionListener(this);
        social.setBounds(250, 170, 80, 40);
        social.setContentAreaFilled(false);
        this.add(social);
        infra.addActionListener(this);
        infra.setBounds(350, 170, 120, 40);
        infra.setContentAreaFilled(false);
        this.add(infra);
        group.add(social);
        group.add(infra);

        nacional.addActionListener(this);
        nacional.setBounds(450, 500, 150, 30);
        nacional.setContentAreaFilled(false);
        nacional.setVisible(false);
        this.add(nacional);

        distrito.addActionListener(this);
        distrito.setBounds(350, 420, 150, 30);
        distrito.setVisible(false);
        this.add(distrito);

        url.addActionListener(this);
        url.setBounds(670, 465, 140, 30);
        url.setVisible(false);
        this.add(url);

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
        l4.setBounds(200, 145, 300, 40);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());

        l5.setFont(l5.getFont().deriveFont(13f));
        l5.setBounds(200, 250, 300, 40);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        l5.setVisible(false);

        l6.setFont(l6.getFont().deriveFont(13f));
        l6.setBounds(200, 300, 300, 40);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        l6.setVisible(false);

        l7.setFont(l7.getFont().deriveFont(13f));
        l7.setBounds(200, 420, 300, 40);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        l7.setVisible(false);

        l8.setFont(l8.getFont().deriveFont(13f));
        l8.setBounds(200, 500, 300, 40);
        l8.setForeground(Color.black);
        l8.setSize(l8.getPreferredSize());
        l8.setVisible(false);

        l9.setFont(l9.getFont().deriveFont(13f));
        l9.setBounds(200, 420, 300, 40);
        l9.setForeground(Color.black);
        l9.setSize(l9.getPreferredSize());
        l9.setVisible(false);

        l10.setFont(l10.getFont().deriveFont(13f));
        l10.setBounds(200, 470, 300, 40);
        l10.setForeground(Color.black);
        l10.setSize(l10.getPreferredSize());
        l10.setVisible(false);

        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);
        this.add(l8);
        this.add(l9);
        this.add(l10);

        /*Cuadros de texto*/
        titulo.setBounds(350, 250, 400, 30);
        titulo.setVisible(false);
        this.add(titulo);
        descripcion.setBounds(350, 300, 400, 100);
        descripcion.setVisible(false);
        this.add(descripcion);
        grupos.setBounds(200, 450, 550, 30);
        grupos.setVisible(false);
        this.add(grupos);


        back.setFont(back.getFont().deriveFont(16f));
        back.setBounds(750, 720, 75, 25);
        back.setForeground(Color.black);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        back.addActionListener(this);
        back.setVisible(true);
        this.add(back);

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

        if(e.getSource() == pagPrinc){
            social.setSelected(false);
            infra.setSelected(false);
            nacional.setSelected(false);
            gui.irPaginaPrincipal(this);
        } /*else if(e.getSource() == colectivos){
            social.setSelected(false);
            infra.setSelected(false);
            nacional.setSelected(false);
            gui.irColectivos(this);
        }*/ else if(e.getSource() == proyectos){
            social.setSelected(false);
            infra.setSelected(false);
            nacional.setSelected(false);
            gui.irProyectos(this);
        } else if(e.getSource() == proyectosR){
            social.setSelected(false);
            infra.setSelected(false);
            nacional.setSelected(false);
            gui.irProyectos(this);
        } else if(e.getSource() == social){
            l5.setVisible(true);
            l6.setVisible(true);
            titulo.setVisible(true);
            descripcion.setVisible(true);
            l7.setVisible(true);
            l8.setVisible(true);
            nacional.setVisible(true);
            grupos.setVisible(true);
            l9.setVisible(false);
            l10.setVisible(false);
            distrito.setVisible(false);
            url.setVisible(false);
        } else if(e.getSource() == infra){
            l5.setVisible(true);
            l6.setVisible(true);
            titulo.setVisible(true);
            descripcion.setVisible(true);
            l9.setVisible(true);
            l10.setVisible(true);
            distrito.setVisible(true);
            url.setVisible(true);
            l7.setVisible(false);
            l8.setVisible(false);
            nacional.setVisible(false);
            grupos.setVisible(false);
        } else if(e.getSource() == proponer){
            if(social.isSelected()){
                try {
                    gui.getController().nuevoProyectoSocial(titulo.getText(),
                            descripcion.getText(), 1000, gui.getController().getLoggedUser(),
                            grupos.getText(), nacional.isSelected());
                    JOptionPane.showMessageDialog(this, "Muchas gracias por su interés en hacer de\n" +
                            "esta una comunidad mejor para todos. Su\n" +
                            "propuesta será remitida al administrador,\n" +
                            "que le comunicará su aceptación tan\n" +
                            "pronto como sea posible.", "Formulario completado correctamente", JOptionPane.PLAIN_MESSAGE);
                    gui.irProyectos(this);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),"Error al crear",
                            JOptionPane.PLAIN_MESSAGE);
                    gui.crearProyectos(this);
                }
            } else if(infra.isSelected()){
                try {
                    gui.getController().nuevoProyectoInfra(titulo.getText(),
                            descripcion.getText(), 1000, gui.getController().getLoggedUser(),
                            distrito.getName(), url.getText(), descripcion.getText());
                    JOptionPane.showMessageDialog(this, "Muchas gracias por su interés en hacer de\n" +
                            "esta una comunidad mejor para todos. Su\n" +
                            "propuesta será remitida al administrador,\n" +
                            "que le comunicará su aceptación tan\n" +
                            "pronto como sea posible.", "Formulario completado correctamente", JOptionPane.PLAIN_MESSAGE);
                    gui.irProyectos(this);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),"Error al crear",
                            JOptionPane.PLAIN_MESSAGE);
                    gui.crearProyectos(this);
                }
            }
            try {
                pTemis.escribirFichero();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void asignarData(){

        if(gui.getController().getLoggedUser() != null){
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            /*Resetear la informacion de los campos*/
            social.setSelected(false);
            infra.setSelected(false);
            titulo.setText("");
            descripcion.setText("");
            grupos.setText("");
            nacional.setSelected(false);
        }
    }

}