package mvc.view;

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

    /*Fields*/
    private JButton pagPrinc = new JButton("Página principal");
    private JButton proyectos = new JButton("Proyectos");
    private JButton colectivos = new JButton("Colectivos");
    private JButton proyectosApoyo = new JButton("Proyectos que apoyas");
    private JButton proponerProyecto = new JButton("Proponer nuevo proyecto");
    private DefaultListModel proy = new DefaultListModel();
    private List<Proyecto> listaAux = new ArrayList<Proyecto>();
    private JButton back = new JButton("Volver");

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

        proyectosApoyo.addActionListener(this);
        proyectosApoyo.setBounds(550, 600, 300, 40);
        this.add(proyectosApoyo);

        proponerProyecto.addActionListener(this);
        proponerProyecto.setBounds(150, 600, 300, 40);
        this.add(proponerProyecto);

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

        /*Lista proyectos*/
        /*if(!gui.getController().listaProyectos().isEmpty()) {
            this.listaAux = gui.getController().listaProyectos();
            for (int i = 0; i < listaAux.size(); i++) {
                //Añadir cada elemento del ArrayList en el modelo de la lista
                proy.add(i, listaAux.get(i));
            }
            JList lista = new JList(proy);
            lista.setBounds(200, 200, 400, 400);
            this.add(lista);
        } else {
            JOptionPane.showMessageDialog(this, "Actualmente, aún no existen proyectos.",
                    "Lista vacía", JOptionPane.PLAIN_MESSAGE);
        }*/

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
        } else if(e.getSource() == proyectosApoyo){
            gui.irProyectosApoyo(this);
        } else if(e.getSource() == proponerProyecto){
            gui.irProponerProyecto(this);
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
