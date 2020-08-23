/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab09_ada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

/**
 *
 *
 */
public class PruebaMochila extends JFrame {
    private JPanel p1,p2,p3;
    private JLabel bSalir;
    private JLabel bMinimizar;
    private JLabel lTitulo;
    private JLabel l1,l2;
    private JTextArea elegido;
    private JScrollPane scroll;

    DefaultTableModel modelo = new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
    };
    int xx;
    int xy;

    int peso[]={1,2,5,6,7}; //Pesos de los objetos
    int valor[] ={1,6,18,22,28}; //Valores de los objetos
    int capacidad = 11;  // Capacidad de la mochila
    int valorMaximo; // Máximo valor de objetos elegidos
    int n = peso.length; // Número de objetos
    Mochila miMochila;
    public PruebaMochila(){
        miMochila= new Mochila(peso, valor, capacidad);
        valorMaximo = miMochila.elegirObjetos();
        miMochila.Componer();
        this.setSize(420, 685);
        this.setLocationRelativeTo((Component)null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(new BoxLayout(this.getContentPane(), 1));
        this.paneles();
        this.pack();
        this.setVisible(true);

    }
    public void paneles(){
        this.p1 = new JPanel();
        this.p1.setBackground(new Color(50, 50, 50));
        this.p1.setSize(620, 40);
        this.p1.setLayout(new BoxLayout(this.p1, 2));
        this.p1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.p1.setPreferredSize(new Dimension(620, 40));
        this.p1.setMinimumSize(new Dimension(620, 40));
        this.p1.setMaximumSize(new Dimension(620, 40));
        this.panelSup();
        this.add(this.p1);


        /*this.p2 = new JPanel();
        this.p2.setBackground(new Color(81, 82, 82));
        this.p2.setSize(420, 200);
        this.p2.setLayout(null);
        this.p2.setPreferredSize(new Dimension(620, 100));
        this.p2.setMinimumSize(new Dimension(620, 100));
        this.p2.setMaximumSize(new Dimension(620, 100));
        this.panelCentral();
        this.add(this.p2);*/

        this.p3 = new JPanel();
        this.p3.setBackground(new Color(81, 82, 82));
        this.p3.setSize(620, 620);
        this.p3.setLayout(null);
        this.p3.setPreferredSize(new Dimension(620, 420));
        this.p3.setMinimumSize(new Dimension(620, 420));
        this.p3.setMaximumSize(new Dimension(620, 420));
        panelResultadosMochila();
        this.add(this.p3);

    }
    public void panelSup() {
        this.bSalir = new JLabel();
        this.bSalir.setSize(29, 30);
        this.bSalir.setLocation(385, 5);
        this.bSalir.setIcon(new ImageIcon("src/imagenes/salir.png"));
        this.p1.add(this.bSalir);
        this.bMinimizar = new JLabel();
        this.bMinimizar.setSize(29, 30);
        this.bMinimizar.setLocation(351, 5);
        this.bMinimizar.setIcon(new ImageIcon("src/imagenes/minim.png"));
        this.p1.add(this.bMinimizar);
        this.bMinimizar.setVisible(true);
        this.bSalir.setVisible(true);
        this.lTitulo = new JLabel("Mochila fraccional                                          ");
        this.lTitulo.setSize(300, 30);
        this.p1.add(this.lTitulo);
        this.lTitulo.setVisible(true);
        this.lTitulo.setFont(new Font("Gotham", 0, 18));
        this.lTitulo.setForeground(new Color(161, 162, 163));
        this.bSalir.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            public void mousePressed(MouseEvent e) {
                PruebaMochila.this.bSalir.setIcon(new ImageIcon("src/imagenes/salirPressed.png"));
            }

            public void mouseReleased(MouseEvent e) {
                PruebaMochila.this.bSalir.setIcon(new ImageIcon("src/imagenes/salir.png"));
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
                PruebaMochila.this.bSalir.setIcon(new ImageIcon("src/imagenes/salir.png"));
            }
        });
        this.bMinimizar.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                PruebaMochila.this.setExtendedState(1);
            }

            public void mousePressed(MouseEvent e) {
                PruebaMochila.this.bMinimizar.setIcon(new ImageIcon("src/imagenes/minimPressed.png"));
            }

            public void mouseReleased(MouseEvent e) {
                PruebaMochila.this.bMinimizar.setIcon(new ImageIcon("src/imagenes/minim.png"));
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
                PruebaMochila.this.bMinimizar.setIcon(new ImageIcon("src/imagenes/minim.png"));
            }
        });
        this.p1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                PruebaMochila.this.xx = e.getX();
                PruebaMochila.this.xy = e.getY();
            }
        });
        this.p1.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                PruebaMochila.this.setLocation(x - PruebaMochila.this.xx, y - PruebaMochila.this.xy);
            }
        });
    }

    public void panelCentral(){

        //para modificar cantidades pasar a constructor (y añadir parametros a constructor) - Despues de modificar añadir
        // boton que llame a metodo completar tabla
        //Aca poner los JTextField / Botones, etc añadir en panel 2




    }

    public void panelResultadosMochila(){

        l1 = new JLabel("Tabla de valores maximos");

        this.l1.setBounds(210, 10, 280, 24);
        this.l1.setFont(new Font("Gotham", 0, 18));
        this.l1.setForeground(new Color(161, 162, 163));
        p3.add(l1);
        this.l1.setVisible(true);
        for(int i = 0; i <= capacidad; i++){
            modelo.addColumn(i);
        }
        JTable tabla =  new JTable();
        JScrollPane sc = new JScrollPane(tabla);
        tabla.setModel(modelo);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(35);
        for(int i = 1; i <= capacidad; i++){
            columnModel.getColumn(i).setPreferredWidth(42);
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sc.setBounds(65, 50, 500, 200);
        sc.setVisible(true);
        p3.add(sc);
        completarDatos();

        l2 = new JLabel("Objetos elegidos");
        this.l2.setBounds(250, 264, 280, 24);
        this.l2.setFont(new Font("Gotham", 0, 18));
        this.l2.setForeground(new Color(161, 162, 163));
        p3.add(l2);
        this.l2.setVisible(true);

        String texto = "";
        for (int i = 1; i<=n; i++){
            if (miMochila.getElegido(i)==1){
                texto = texto + "       Objeto " + i + " \tPeso = " + miMochila.getPeso(i) + ", Valor = " + miMochila.getValor(i) + "\n";

            }
        }

        elegido = new JTextArea();

        this.elegido.setBounds(120, 294, 400, 24);
        this.elegido.setFont(new Font("Gotham", 0, 18));
        this.elegido.setForeground(new Color(161, 162, 163));
        elegido.setEditable(false);
        elegido.setText(texto);

        scroll = new JScrollPane(elegido);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scroll.setBounds(130, 294, 380, 60);

        p3.add(scroll);
        this.scroll.setVisible(true);

    }
    public void completarDatos(){
        modelo.setRowCount(0);;
        for (int i=1; i<=n; i++){
            Object [] temporal = new Object[capacidad+1];
            temporal[0] = i;
            for (int j = 1; j<=capacidad; j++) {
                temporal[j] = miMochila.getV(i, j);
            }
            modelo.addRow(temporal);
        }
    }

    public static void main(String[] args) {
        new PruebaMochila();
    }
}

