package mvc.view;

import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class PanelCrearColectivo extends JPanel implements ActionListener {

    private MiGUI gui;

    private JTextField nombre = new JTextField(20);
    private JTextField descripcion = new JTextField(500);

    private JLabel l1 = new JLabel("Crear colectivo" );
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    private JLabel l4 = new JLabel("Nombre del colectivo:");
    private JLabel l5 = new JLabel("Descripción del colectivo:");

    private JButton back = new JButton("Volver");
    private JButton cierraSesion = new JButton("Salir");
    private JButton crear = new JButton("Crear colectivo");

    public PanelCrearColectivo(MiGUI gui) {
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
        this.add(l1);

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

        cierraSesion.setFont(cierraSesion.getFont().deriveFont(16f));
        cierraSesion.setBounds(830, 130, 75, 25);
        cierraSesion.setForeground(Color.black);
        cierraSesion.setOpaque(false);
        cierraSesion.setContentAreaFilled(false);
        cierraSesion.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        cierraSesion.addActionListener(this);
        cierraSesion.setVisible(true);
        this.add(cierraSesion);

        /* Campo nombre */
        l4.setFont(l4.getFont().deriveFont(13f));
        l4.setBounds(200, 250, 340, 40);
        l4.setForeground(Color.black);
        l4.setSize(l4.getPreferredSize());
        this.add(l4);

        nombre.setBounds(400, 250, 400, 30);
        this.add(nombre);

        /* Campo descripcion */
        l5.setFont(l5.getFont().deriveFont(13f));
        l5.setBounds(200, 300, 400, 40);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        this.add(l5);

        descripcion.setBounds(400, 300, 400, 100);
        this.add(descripcion);

        crear.addActionListener(this);
        crear.setBounds(400, 550, 100, 40);
        this.add(crear);

    }

    public void asignarData() {
        if(gui.getController().getLoggedUser() != null) {
            nombre.setText("");
            descripcion.setText("");
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);
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

        if(e.getSource() == crear) {
            if(gui.getController().getRepresentante()) {
                try {
                    gui.getController().crearColectivo(descripcion.getText(), nombre.getText());

                    JOptionPane.showMessageDialog(this, nombre.getText() +" creado correctamente\n"
                            ,"Exito creando el colectivo"
                            , JOptionPane.PLAIN_MESSAGE);

                    gui.irColectivos(this);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),"Error al crear",
                            JOptionPane.PLAIN_MESSAGE);
                    gui.crearColectivos(this);
                }
            }
            else {
                try {
                    gui.getController().crearColectivo(descripcion.getText(), nombre.getText());

                    JOptionPane.showMessageDialog(this, nombre.getText() +" creado correctamente\n"
                            ,"Exito creando el colectivo"
                            , JOptionPane.PLAIN_MESSAGE);

                    gui.irColectivos(this);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),"Error al crear",
                            JOptionPane.PLAIN_MESSAGE);
                    gui.crearColectivos(this);
                }
            }
            try {
                pTemis.escribirFichero();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
