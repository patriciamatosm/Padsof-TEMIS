package mvc.view;

import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelProyectoSocial extends JPanel implements ActionListener {

    private MiGUI gui;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosR = new JButton("Todos los proyectos");
    private JButton votar = new JButton("votar");
    private JButton back = new JButton("Volver");

    /*labels*/
    private JLabel l1 = new JLabel("Proyecto social" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    public PanelProyectoSocial(MiGUI gui) {
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

        votar.addActionListener(this);
        votar.setBounds(400, 300, 90, 40);
        this.add(votar);

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
            gui.irPaginaPrincipal(this);
        } /*else if(e.getSource() == colectivos){
            gui.irColectivos(this);
        }*/ else if(e.getSource() == proyectos){
            gui.irProyectos(this);
        } else if(e.getSource() == proyectosR){
            gui.irProyectos(this);
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
        }
    }

}