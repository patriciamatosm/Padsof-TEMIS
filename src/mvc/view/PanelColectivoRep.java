package mvc.view;

import mvc.model.Colectivo;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelColectivoRep extends JPanel implements ActionListener {

    private MiGUI gui;

    private Colectivo c;

    private JTextArea l1 = new JTextArea("Colectivo", 1, 50);
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");

    private JLabel l4 = new JLabel("Representante: ");
    private JLabel rep = new JLabel("No name");

    private JLabel l5 = new JLabel("Descripcion: ");
    private JTextArea desc = new JTextArea("No desc", 40, 50);
    private JScrollPane scrollDesc = new JScrollPane(desc,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private JButton back = new JButton("Página Principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivosP = new JButton("Colectivos");
    private JButton cierraSesion = new JButton("Salir");
    private JButton colectivos = new JButton("Ver colectivos");

    private JButton crear = new JButton("Crear subcolectivo");

    private JButton padre = new JButton("");

    public PanelColectivoRep(MiGUI gui) {
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
        Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize());
        l1.setFont(boldFont);
        l1.setOpaque(false);
        l1.setEditable(false);
        l1.setLineWrap(true);
        l1.setWrapStyleWord(true);
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
        desc.setBackground(new Color(124, 150, 197));
        desc.setEditable(false);
        desc.setForeground(Color.black);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);

        scrollDesc.setViewportView(desc);
        scrollDesc.setBackground(new Color(124, 150, 197));
        scrollDesc.setBounds(200, 350, 500, 100);
        scrollDesc.setVisible(true);
        this.add(scrollDesc, BorderLayout.EAST);

        padre.setBounds(200, 300, 400, 40);
        padre.addActionListener(this);
        this.add(padre);

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

        proyectos.addActionListener(this);
        proyectos.setFont(proyectos.getFont().deriveFont(16f));
        proyectos.setBounds(20, 186, 160, 25);
        this.add(proyectos);

        colectivosP.addActionListener(this);
        colectivosP.setFont(colectivosP.getFont().deriveFont(16f));
        colectivosP.setBounds(20, 212, 160, 25);
        this.add(colectivosP);

        crear.addActionListener(this);
        crear.setBounds(450, 600, 300, 40);
        this.add(crear);

        colectivos.addActionListener(this);
        colectivos.setBounds(150, 600, 300, 40);
        this.add(colectivos);
    }

    public void asignarData() {
        if(gui.getController().getLoggedUser() != null) {
            l3.setText(gui.getController().getLoggedUserName());
            l3.setVisible(true);
            this.add(l3);

            if (c != null) {

                rep.setText(gui.getController().getRepColectivo(c));
                desc.setText(gui.getController().getDescripcion(c));

                Colectivo aux = gui.getController().getPadre(c);

                if (aux != null) {
                    padre.setVisible(true);
                    this.padre.setText(aux.getNombre());
                } else {
                    padre.setVisible(false);
                }
            }
            else {
                padre.setVisible(false);
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
        else if (e.getSource() == colectivosP) {
            gui.irColectivos(this);
        }
        else if (e.getSource() == proyectos) {
            gui.irProyectos(this);
        }
        else if(e.getSource() == colectivos) {
            gui.irColectivos(this);
        }
        else if(e.getSource() == crear) {
            gui.crearColectivos(this);
        }
        try {
            pTemis.escribirFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
