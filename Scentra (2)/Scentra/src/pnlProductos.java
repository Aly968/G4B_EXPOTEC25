
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
//Alison Del Rosario Vicente Coroy
public class pnlProductos extends JPanel implements ActionListener {

    JSeparator linea;
    JButton btnDatos;
    private JTextField txtIngreso;
    private JTextField txtFabricacion;
    private JTextField txtSerie;
    private JTextField txtEmpleado;
    private JTextField txtCantidad;
    private JTable tablaProductos;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;

    //Sub
    Font bt = new Font("Segoe IU", Font.PLAIN, 14);
//titulos
    Font bt2 = new Font("Segoe IU", Font.BOLD, 15);

    public pnlProductos() {
        setLayout(null);
        setBackground(new Color(208, 201, 208)); // Color de fondo
        obj();
        agr();
        posicionar();
    }

    public void posicionar() {
//        pnlTextos.setBounds(40, 150, 350, 225);
        pnlTextos.setBounds(300, 40, 350, 225);
        txtIngreso.setBounds(20, 20, 310, 30);
        txtFabricacion.setBounds(20, 70, 145, 30);
        txtSerie.setBounds(20, 120, 145, 30);
        txtCantidad.setBounds(185, 70, 145, 30);
        txtEmpleado.setBounds(185, 120, 145, 30);
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
        pnlTextos.add(txtFabricacion);
        pnlTextos.add(txtIngreso);
        pnlTextos.add(txtSerie);
        pnlTextos.add(txtEmpleado);
        pnlTextos.add(txtCantidad);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(scTabla);

        btnDatos.addActionListener(this);

    }

    public void Tb() {
        String[] columnas = {"ID", "Fecha Fabricación", "Fecha Ingreso", "Empleado", "Cantidad Exi.", "Número de Serie"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaProductos = new JTable(mdTabla);
        tablaProductos.setFillsViewportHeight(true);
        scTabla = new JScrollPane(tablaProductos);

        tablaProductos.setFont(bt);
        tablaProductos.setBackground(new Color(249, 247, 250));
        tablaProductos.setForeground(Color.BLACK); // Texto negro
        tablaProductos.setRowHeight(28);
        tablaProductos.setShowGrid(true);
        tablaProductos.setGridColor(Color.BLACK);

        titulos = tablaProductos.getTableHeader();
        titulos.setFont(bt2);
        titulos.setBackground(new Color(116, 119, 147));
        titulos.setForeground(Color.WHITE);
        titulos.setReorderingAllowed(false);

        //Ancho de columnas
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(400);
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(400);
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(250);
        tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(275);
        tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(285);
        
        //Registros 
        String[] r1 = {"001", "2025-06-01", "2025-06-03", "Lucía Martínez", "150", "VEL-BL-0001"};
        String[] r2 = {"002", "2025-06-02", "2025-06-04", "Carlos Pérez", "100", "VEL-RJ-0002"};
        String[] r3 = {"003", "2025-06-03", "2025-06-05", "Ana Gómez", "80", "VEL-AM-0003"};

        mdTabla.addRow(r1);
        mdTabla.addRow(r2);
        mdTabla.addRow(r3);
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

        txtFabricacion = new JTextField(" Fecha de Fabricación");
        txtCantidad = new JTextField(" Cantidad Existente");
        txtIngreso = new JTextField(" Fecha de Ingreso");
        txtSerie = new JTextField(" Número de Serie");
        txtEmpleado = new JTextField(" Empleado");
        diseñotxf(txtFabricacion);
        diseñotxf(txtCantidad);
        diseñotxf(txtIngreso);
        diseñotxf(txtSerie);
        diseñotxf(txtEmpleado);
    }

    public void diseñotxf(JTextField txf) {

        txf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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
