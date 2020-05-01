package mvc.view;

import mvc.model.Colectivo;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelVerColectivo extends JPanel implements ActionListener {

    private MiGUI gui;

    private Colectivo c;

    private JLabel l1 = new JLabel("Colectivo");
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    private JLabel l4 = new JLabel("Representante: ");
    private JLabel rep = new JLabel("No name");

    private JLabel l5 = new JLabel("Descripcion: ");
    private JLabel desc = new JLabel("No descripcion");

    private JButton back = new JButton("Volver");
    private JButton cierraSesion = new JButton("Salir");
    private JButton unirse = new JButton("Unirse al colectivo");
    private JButton abandonar = new JButton("Abandonar el colectivo");

    public PanelVerColectivo(MiGUI gui) {
        this.gui = gui;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(124, 150, 197));


        this.setBounds(100, 100, 400, 350);
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

        /*Logo*/
        JLabel icon1 = new JLabel(" ");
        ImageIcon icono = new ImageIcon("image/logoTemis.png");
        Image imagen = icono.getImage();
        ImageIcon iconScaled = new ImageIcon(imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        icon1.setIcon(iconScaled);
        icon1.setBounds(820, 10, 100, 100);
        this.add(icon1);

        l4.setFont(l4.getFont().deriveFont(13f));
        l4.setBounds(200, 250, 10, 40);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());
        this.add(l4);

        rep.setFont(rep.getFont().deriveFont(13f));
        rep.setBounds(350, 250, 10, 40);
        rep.setForeground(Color.black);
        rep.setSize(rep.getPreferredSize());
        this.add(rep);

        l5.setFont(l5.getFont().deriveFont(13f));
        l5.setBounds(200, 300, 10, 40);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        this.add(l5);

        desc.setFont(desc.getFont().deriveFont(13f));
        desc.setBounds(200, 350, 10, 400);
        desc.setBackground(Color.white);
        desc.setForeground(Color.black);
        desc.setSize(desc.getPreferredSize());
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

        back.setFont(back.getFont().deriveFont(16f));
        back.setBounds(20, 160, 160, 25);
        back.addActionListener(this);
        this.add(back);

        unirse.setFont(unirse.getFont().deriveFont(16f));
        unirse.setBounds(200, 400, 200, 40);
        unirse.addActionListener(this);
        this.add(unirse);

        abandonar.setFont(abandonar.getFont().deriveFont(16f));
        abandonar.setBounds(200, 400, 200, 40);
        abandonar.addActionListener(this);
        this.add(abandonar);
    }

    public void asignarData() {
        if(gui.getController().getLoggedUser() != null){
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            if(c != null) {
                rep.setText(gui.getController().getRepColectivo(c));
                desc.setText(gui.getController().getDescripcion(c));
            }

            if(gui.getController().isRepCol(c)) {
                unirse.setVisible(false);
                abandonar.setVisible(false);
            }
            else if(gui.getController().enColectivo(c)) {
                unirse.setVisible(false);
                abandonar.setVisible(true);
            }
            else {
                abandonar.setVisible(false);
                unirse.setVisible(true);
            }
        }
    }

    public void setColectivo(Colectivo c) {
        this.c = c;
        l1.setText(gui.getController().getNombreColectivo(c));
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
        else if(e.getSource() == unirse) {
            gui.getController().unirseCol(c);
            this.c = gui.getController().getColectivo(c);
            gui.verColectivo(this);
        }
        else if(e.getSource() == abandonar) {
            gui.getController().abandonarCol(c);
            this.c = gui.getController().getColectivo(c);
            gui.verColectivo(this);
        }
        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
