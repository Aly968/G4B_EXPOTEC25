import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
//Alison Del Rosario Vicente Coroy
public class pnlInventario extends JPanel implements ActionListener {

    JSeparator linea;
    JButton btnDatos;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtCosto;
    private JTextField txtCantidadMin;
    private JTextField txtUnidad;
    private JTable tablaInventario;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;
//Sub
    Font bt = new Font("Segoe IU", Font.PLAIN, 14);
//titulos
    Font bt2 = new Font("Segoe IU", Font.BOLD, 15);

    public pnlInventario() {
        setLayout(null);
        setBackground(new Color(208, 201, 208)); // Color de fondo
        obj();
        agr();
        posicionar();
    }

    public void posicionar() {
//        pnlTextos.setBounds(40, 150, 350, 225);
        pnlTextos.setBounds(300, 40, 350, 225);
        txtNombre.setBounds(20, 20, 310, 30);
        txtCantidad.setBounds(20, 70, 145, 30);
        txtCosto.setBounds(20, 120, 145, 30);
        txtCantidadMin.setBounds(185, 70, 145, 30);
        txtUnidad.setBounds(185, 120, 145, 30);
        linea.setBounds(20, 170, 310, 6);
        btnDatos.setBounds(130, 180, 80, 30);
//        scTabla.setBounds(410, 250, 600, 200);
        scTabla.setBounds(35, 305, 880, 200);

    }

    public void obj() {
        pnl();
        btn();
        txf();
        linea();
        Tb();
    }

    public void agr() {
        add(pnlTextos);
        pnlTextos.add(txtNombre);
        pnlTextos.add(txtCantidad);
        pnlTextos.add(txtCosto);
        pnlTextos.add(txtCantidadMin);
        pnlTextos.add(txtUnidad);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(scTabla);

        btnDatos.addActionListener(this);
    
    }

    public void Tb() {
        String[] columnas = {"ID", "Nombre Objeto", "Cantidad Dis.", "Cantidad min.", "Costo/Precio", "Unidad de medida"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaInventario = new JTable(mdTabla);
        tablaInventario.setFillsViewportHeight(true);
        scTabla = new JScrollPane(tablaInventario);

        tablaInventario.setFont(bt);
        tablaInventario.setBackground(new Color(249, 247, 250));
        tablaInventario.setForeground(Color.BLACK); // Texto negro
        tablaInventario.setRowHeight(28);
        tablaInventario.setShowGrid(true);
        tablaInventario.setGridColor(Color.BLACK);

        titulos = tablaInventario.getTableHeader();
        titulos.setFont(bt2);
        titulos.setBackground(new Color(116, 119, 147));
        titulos.setForeground(Color.WHITE);
        titulos.setReorderingAllowed(false);

        //Ancho de columnas
        tablaInventario.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaInventario.getColumnModel().getColumn(1).setPreferredWidth(400);
        tablaInventario.getColumnModel().getColumn(2).setPreferredWidth(400);
        tablaInventario.getColumnModel().getColumn(3).setPreferredWidth(250);
        tablaInventario.getColumnModel().getColumn(4).setPreferredWidth(275);
        tablaInventario.getColumnModel().getColumn(5).setPreferredWidth(285);
        
        //registros
        Object[] velaBlanca = {"3013", "Vela Blanca", 50, 10, "Q.12.00", " Pieza "};
        Object[] velaRoja = {"3022", "Vela Roja", 35, 10, "Q.13.50", " Pieza "};
        Object[] velaAmarilla = {"3033", "Vela Amarilla", 20, 10, "Q.11.75", " Pieza "};


        mdTabla.addRow(velaBlanca);
        mdTabla.addRow(velaRoja);
        mdTabla.addRow(velaAmarilla);
    }

    //pnl.setBounds(0, 0, 180, 100);
    public void linea() {
        linea = new JSeparator(JSeparator.HORIZONTAL);
        linea.setForeground(Color.decode("#6A7793"));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    }

    public void pnl() {
        pnlTextos = new JPanel();
        pnlTextos.setLayout(null);
        pnlTextos.setBackground(new Color(180, 168, 170));
    }

    public void txf() {

        txtNombre = new JTextField(" Nombre objeto");
        txtCantidad = new JTextField(" Cantidad dis.");
        txtCosto = new JTextField(" Costo/Precio");
        txtCantidadMin = new JTextField(" Cantidad min.");
        txtUnidad = new JTextField(" Unidad de medida");
        diseñotxf(txtNombre);
        diseñotxf(txtCantidad);
        diseñotxf(txtCosto);
        diseñotxf(txtCantidadMin);
        diseñotxf(txtUnidad);
    }

    public void diseñotxf(JTextField txf) {

        txf.setFont(bt);
        txf.setBackground(new Color(249, 247, 250));
        txf.setForeground(Color.GRAY);
        txf.setBorder(null);
        txf.setCaretColor(Color.BLACK);

    }

    public void diseñobtn(JButton btn) {
        btn.setBackground(new Color(106, 119, 147));
        btn.setForeground(Color.WHITE);
        forma(btn);
    }

    public void btn() {

        btnDatos = new JButton("Añadir");
        diseñobtn(btnDatos);

    }

    //metodo que maneja la forma redondeada de los botones
    public void forma(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);

        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(boton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
