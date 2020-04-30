package mvc.view;

import es.uam.eps.sadp.grants.GrantRequest;
import mvc.model.Proyecto;
import mvc.model.ProyectoInfraestructura;
import mvc.model.ProyectoSocial;
import mvc.model.Temis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**********************************************
 * VOTAR COLECTIVOS Y PROYECTO CADUCADO       *
 * URL, DISTRITO, ACTUAR COMO REPRESENTANTE   *
 **********************************************/

public class PanelVerProyecto extends JPanel implements ActionListener {

    private MiGUI gui;

    private Proyecto p;

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosR = new JButton("Todos los proyectos");
    private JButton votar = new JButton("votar");
    private JButton back = new JButton("Volver");

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

    private JLabel titulo = new JLabel("No title");
    private JLabel desc = new JLabel("No desc");
    private JLabel grupo = new JLabel("No group");
    private JLabel nacional = new JLabel("Nacional");
    private JLabel url = new JLabel("No url");
    private JLabel distrito = new JLabel("No district");
    private JLabel numVotos = new JLabel("No votes");

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
        votar.setBounds(400, 500, 90, 40);
        this.add(votar);

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
        l6.setBounds(200, 350, 10, 25);
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
        titulo.setBounds(200, 200, 10, 40);
        titulo.setForeground(Color.black);
        titulo.setSize(titulo.getPreferredSize());
        this.add(titulo);

        desc.setFont(desc.getFont().deriveFont(13f));
        desc.setBounds(300, 250, 300, 400);
        desc.setBackground(Color.white);
        desc.setForeground(Color.black);
        desc.setSize(desc.getPreferredSize());
        this.add(desc);

        grupo.setFont(grupo.getFont().deriveFont(13f));
        grupo.setBounds(300, 350, 300, 40);
        grupo.setBackground(Color.white);
        grupo.setForeground(Color.black);
        grupo.setSize(grupo.getPreferredSize());
        grupo.setVisible(false);
        this.add(grupo);

        nacional.setFont(nacional.getFont().deriveFont(13f));
        nacional.setBounds(300, 400, 100, 40);
        nacional.setBackground(Color.white);
        nacional.setForeground(Color.black);
        nacional.setSize(nacional.getPreferredSize());
        nacional.setVisible(false);
        this.add(nacional);

        distrito.setFont(distrito.getFont().deriveFont(13f));
        distrito.setBounds(300, 350, 100, 40);
        distrito.setBackground(Color.white);
        distrito.setForeground(Color.black);
        distrito.setSize(distrito.getPreferredSize());
        distrito.setVisible(false);
        this.add(distrito);

        url.setFont(url.getFont().deriveFont(13f));
        url.setBounds(300, 400, 200, 40);
        url.setBackground(Color.white);
        url.setForeground(Color.black);
        url.setSize(url.getPreferredSize());
        url.setVisible(false);
        this.add(url);

        numVotos.setFont(numVotos.getFont().deriveFont(11f));
        numVotos.setBounds(400, 550, 200, 400);
        numVotos.setBackground(Color.white);
        numVotos.setForeground(Color.black);
        numVotos.setSize(numVotos.getPreferredSize());
        this.add(numVotos);

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
            if(gui.getController().getRepresentante() == false) {
                gui.getController().votar(gui.getController().getLoggedUser(), p);
                votar.setEnabled(false);
                l8.setVisible(true);
                if (gui.getController().cumpleNumVotos(p) == true) {
                    gui.getController().pedirFinanciacion(p);
                }
            } else if(gui.getController().getRepresentante() == true){
                gui.getController().votarUsuarios(gui.getController().listaUsuariosRepresentados(gui.getController().getLoggedUser()), p);
                votar.setEnabled(false);
                l8.setVisible(true);
                if (gui.getController().cumpleNumVotos(p) == true) {
                    gui.getController().pedirFinanciacion(p);
                }
            }
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

            if(p != null) {
                titulo.setText(gui.getController().getTitulo(p));
                desc.setText(gui.getController().getDescProy(p));
                numVotos.setText(gui.getController().getNumVotos(p));
                if(p instanceof ProyectoSocial) {
                    ProyectoSocial pSoc = (ProyectoSocial) p;
                    l5.setVisible(true);
                    l6.setVisible(false);
                    l7.setVisible(false);
                    url.setVisible(false);
                    distrito.setVisible(false);
                    grupo.setText(gui.getController().getGrupo(pSoc));
                    grupo.setVisible(true);
                    nacional.setText(gui.getController().isNacional(pSoc));
                    nacional.setVisible(true);
                } else if(p instanceof ProyectoInfraestructura) {
                    ProyectoInfraestructura pInfra = (ProyectoInfraestructura) p;
                    l5.setVisible(false);
                    l6.setVisible(true);
                    l7.setVisible(true);
                    grupo.setVisible(false);
                    nacional.setVisible(false);
                    distrito.setText(gui.getController().getDistrito(pInfra));
                    distrito.setVisible(true);
                    url.setText(gui.getController().getUrl(pInfra));
                    url.setVisible(true);
                }
            }

            if(gui.getController().votar(gui.getController().getLoggedUser(), p) == false) {
                votar.setEnabled(false);
                l8.setVisible(true);
            }

        }
    }

}

