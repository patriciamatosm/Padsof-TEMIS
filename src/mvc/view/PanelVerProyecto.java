package mvc.view;

import es.uam.eps.sadp.grants.GrantRequest;
import mvc.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**************************************************
 * SCROLLBAR TAMAÑO TEXTAREA                      *
 * NOTIFICACIONES              <-------           *
 * COMPROBAR USUARIO LOGUEADO  <-------           *
 **************************************************/

public class PanelVerProyecto extends JPanel implements ActionListener {

    private MiGUI gui;

    private Proyecto p;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosR = new JButton("Todos los proyectos");
    private JButton votar = new JButton("votar");
    private JButton cierraSesion = new JButton("Salir");

    /*labels*/
    private JLabel l1 = new JLabel("Proyecto");
    private JLabel l2 = new JLabel("Perfil de ");
    private JLabel l3 = new JLabel("No name");
    private JLabel l4 = new JLabel("Descripción:");
    private JLabel l5 = new JLabel("Grupo:");
    private JLabel l6 = new JLabel("Distrito:");
    private JLabel l7 = new JLabel("Croquis:");
    private JLabel l8 = new JLabel("Ya has votado por este proyecto");
    private JLabel l9 = new JLabel("Numero de votos:");

    private JTextArea titulo = new JTextArea("No title", 1, 50);
    private JTextArea desc = new JTextArea("No desc", 40, 50);
    private JScrollPane scrollDesc = new JScrollPane(desc,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JTextArea grupo = new JTextArea("No group", 1, 100);
    private JTextArea nacional = new JTextArea("Nacional", 1, 100);
    private JTextArea url = new JTextArea("No url", 1, 100);
    private JTextArea distrito = new JTextArea("No district", 1, 100);
    private JLabel numVotos = new JLabel("No votes");
    private JLabel caducado = new JLabel("(CADUCADO)");

    public PanelVerProyecto(MiGUI gui) {
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

        proyectosR.addActionListener(this);
        proyectosR.setBounds(550, 600, 300, 40);
        this.add(proyectosR);

        votar.addActionListener(this);
        votar.setBounds(400, 500, 90, 40);
        votar.setEnabled(false);
        this.add(votar);

        caducado.setFont(l2.getFont().deriveFont(18f));
        caducado.setBounds(650, 500, 300, 40);
        caducado.setForeground(Color.black);
        caducado.setSize(caducado.getPreferredSize());
        caducado.setVisible(false);
        this.add(caducado);

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

        l5.setFont(l5.getFont().deriveFont(13f));
        l5.setBounds(200, 350, 10, 25);
        l5.setForeground(Color.black);
        l5.setSize(l5.getPreferredSize());
        l5.setVisible(false);

        l6.setFont(l6.getFont().deriveFont(13f));
        l6.setBounds(200, 375, 10, 25);
        l6.setForeground(Color.black);
        l6.setSize(l6.getPreferredSize());
        l6.setVisible(false);

        l7.setFont(l7.getFont().deriveFont(13f));
        l7.setBounds(200, 400, 10, 25);
        l7.setForeground(Color.black);
        l7.setSize(l7.getPreferredSize());
        l7.setVisible(false);

        l8.setFont(l8.getFont().deriveFont(11f));
        l8.setBounds(500, 515, 10, 25);
        l8.setForeground(Color.black);
        l8.setSize(l8.getPreferredSize());
        l8.setVisible(false);

        l9.setFont(l9.getFont().deriveFont(11f));
        l9.setBounds(300, 550, 10, 25);
        l9.setForeground(Color.black);
        l9.setSize(l9.getPreferredSize());

        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);
        this.add(l8);
        this.add(l9);

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

        scrollDesc.setViewportView(desc);
        scrollDesc.setBackground(new Color(124, 150, 197));
        scrollDesc.setBounds(300, 250, 500, 100);
        scrollDesc.setVisible(true);
        this.add(scrollDesc, BorderLayout.EAST);

        grupo.setFont(grupo.getFont().deriveFont(13f));
        grupo.setBounds(300, 350, 300, 40);
        grupo.setForeground(Color.black);
        grupo.setSize(grupo.getPreferredSize());
        grupo.setOpaque(false);
        grupo.setEditable(false);
        grupo.setLineWrap(true);
        grupo.setWrapStyleWord(true);
        grupo.setVisible(false);
        this.add(grupo);

        nacional.setFont(nacional.getFont().deriveFont(13f));
        nacional.setBounds(300, 400, 100, 100);
        ;
        nacional.setForeground(Color.black);
        nacional.setSize(nacional.getPreferredSize());
        nacional.setOpaque(false);
        nacional.setEditable(false);
        nacional.setLineWrap(true);
        nacional.setWrapStyleWord(true);
        nacional.setVisible(false);
        this.add(nacional);

        distrito.setFont(distrito.getFont().deriveFont(13f));
        distrito.setBounds(300, 375, 100, 40);
        distrito.setForeground(Color.black);
        distrito.setSize(distrito.getPreferredSize());
        distrito.setOpaque(false);
        distrito.setEditable(false);
        distrito.setLineWrap(true);
        distrito.setWrapStyleWord(true);
        distrito.setVisible(false);
        this.add(distrito);

        url.setFont(url.getFont().deriveFont(13f));
        url.setBounds(300, 400, 200, 40);
        url.setForeground(Color.black);
        url.setSize(url.getPreferredSize());
        url.setOpaque(false);
        url.setEditable(false);
        url.setLineWrap(true);
        url.setWrapStyleWord(true);
        url.setVisible(false);
        this.add(url);

        numVotos.setFont(numVotos.getFont().deriveFont(11f));
        numVotos.setBounds(400, 550, 200, 400);
        numVotos.setBackground(Color.white);
        numVotos.setForeground(Color.black);
        numVotos.setSize(numVotos.getPreferredSize());
        this.add(numVotos);

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

    public void setProyecto(Proyecto p) {
        this.p = p;
        l1.setText(gui.getController().getTitulo(p));
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
        } else if (e.getSource() == proyectosR) {
            gui.irProyectos(this);
        } else if (e.getSource() == votar) {

            if (!gui.getController().getRepresentante()) {
                if (JOptionPane.showConfirmDialog(null, "No podrás retirar tu voto",
                        "Estás seguro?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    gui.getController().votar(p);
                    numVotos.setText(gui.getController().calcularPopularidad(p));
                    votar.setEnabled(false);
                    l8.setVisible(true);
                    if (gui.getController().cumpleNumVotos(p) == true) {
                        gui.getController().pedirFinanciacion(p);
                        for (Colectivo c : gui.getController().listaColectivos()) {
                            if (c.getProyectosApoyados().contains(p)) {
                                for (Usuario u : c.getListaUsuario()) {
                                    if (u.getSuscritoNoticias().contains(c)) {
                                        gui.getController().nuevaNotificacion(p, c, "El proyecto " +
                                                p.getProjectTitle() + " ha conseguido votos suficientes" +
                                                "para pedir financiacion.");
                                        //System.out.println(u.getNotificaciones().get(0).getMensaje());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "No podrás retirar tu voto",
                        "Estás seguro?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    gui.getController().votarUsuarios(p, gui.getController().getColRep());
                    gui.getController().colectivoApoyaProyecto(p, gui.getController().getColRep());

                    numVotos.setText(gui.getController().calcularPopularidad(p));
                    votar.setEnabled(false);
                    l8.setVisible(true);
                    if (gui.getController().cumpleNumVotos(p)) {
                        gui.getController().pedirFinanciacion(p);
                        for (Colectivo c : gui.getController().listaColectivos()) {
                            if (c.getProyectosApoyados().contains(p)) {
                                for (Usuario u : c.getListaUsuario()) {
                                    if (u.getSuscritoNoticias().contains(c)) {
                                        gui.getController().nuevaNotificacion(p, c, "El proyecto " +
                                                p.getProjectTitle() + " ha conseguido votos suficientes" +
                                                "para pedir financiacion.");
                                        System.out.println(u.getNotificaciones().get(0).getMensaje());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (e.getSource() == cierraSesion) {
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

            if (p != null) {
                titulo.setText("Creador: " + gui.getController().getNombreCreador(p));
                desc.setText(gui.getController().getDescProy(p));
                numVotos.setText(gui.getController().calcularPopularidad(p));
                caducado.setVisible(false);
                votar.setEnabled(false);
                if (gui.getController().haVotado(p) == true) {
                    votar.setEnabled(false);
                    l8.setVisible(true);
                } else if (gui.getController().haVotado(p) == false) {
                    votar.setEnabled(true);
                    l8.setVisible(false);
                }
                if (p instanceof ProyectoSocial) {
                    ProyectoSocial pSoc = (ProyectoSocial) p;
                    l5.setVisible(true);
                    l6.setVisible(false);
                    l7.setVisible(false);
                    l8.setVisible(false);
                    url.setVisible(false);
                    distrito.setVisible(false);
                    grupo.setText(gui.getController().getGrupo(pSoc));
                    grupo.setVisible(true);
                    nacional.setText(gui.getController().isNacional(pSoc));
                    nacional.setVisible(true);
                } else if (p instanceof ProyectoInfraestructura) {
                    ProyectoInfraestructura pInfra = (ProyectoInfraestructura) p;
                    l5.setVisible(false);
                    l6.setVisible(true);
                    l7.setVisible(true);
                    l8.setVisible(false);
                    grupo.setVisible(false);
                    nacional.setVisible(false);
                    distrito.setText(gui.getController().getDistrito(pInfra));
                    distrito.setVisible(true);
                    url.setText(gui.getController().getUrl(pInfra));
                    url.setVisible(true);
                }
                if (gui.getController().getEstado(p) == Proyecto.Estado.CADUCADO) {
                    votar.setEnabled(false);
                    l8.setVisible(false);
                    caducado.setVisible(true);
                }
            }

        }
    }

}

