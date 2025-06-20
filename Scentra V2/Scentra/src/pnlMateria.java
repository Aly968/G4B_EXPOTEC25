import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
//Alison Del Rosario Vicente Coroy
public class pnlMateria extends JPanel implements ActionListener {

    JSeparator linea;
    JButton btnDatos;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtMaterial;
    private JTextField txtEstado;
    private JTextField txtStock;
    private JTextField txtFechaIngreso;
    private JTable tablaMateria;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;

    //Sub
    Font bt = new Font("Segoe IU", Font.PLAIN, 14);
//titulos
    Font bt2 = new Font("Segoe IU", Font.BOLD, 15);
    
    
    public pnlMateria() {
        setLayout(null);
        setBackground(new Color(208, 201, 208));
        obj();
        agr();
        posicionar();
    }

    public void posicionar() {
        pnlTextos.setBounds(300, 40, 350, 245);
        txtNombre.setBounds(20, 20, 310, 30);
        txtCodigo.setBounds(20, 60, 145, 30);
        txtMaterial.setBounds(185, 60, 145, 30);
        txtEstado.setBounds(20, 100, 145, 30);
        txtStock.setBounds(185, 100, 145, 30);
        txtFechaIngreso.setBounds(20, 140, 310, 30);
        linea.setBounds(20, 180, 310, 6);
        btnDatos.setBounds(130, 200, 80, 30);
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
        pnlTextos.add(txtCodigo);
        pnlTextos.add(txtMaterial);
        pnlTextos.add(txtEstado);
        pnlTextos.add(txtStock);
        pnlTextos.add(txtFechaIngreso);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(scTabla);
        btnDatos.addActionListener(this);
    }

    public void Tb() {
        String[] columnas = {"Código", "Nombre Objeto", "Material", "Estado", "Stock", "Fecha de ingreso"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaMateria = new JTable(mdTabla);
        tablaMateria.setFillsViewportHeight(true);
        scTabla = new JScrollPane(tablaMateria);

        tablaMateria.setFont(bt);
        tablaMateria.setBackground(new Color(249, 247, 250));
        tablaMateria.setForeground(Color.BLACK);
        tablaMateria.setRowHeight(28);
        tablaMateria.setShowGrid(true);
        tablaMateria.setGridColor(Color.BLACK);

        titulos = tablaMateria.getTableHeader();
        titulos.setFont(bt2);
        titulos.setBackground(new Color(116, 119, 147));
        titulos.setForeground(Color.WHITE);
        titulos.setReorderingAllowed(false);
        
        //Ancho de columnas
        tablaMateria.getColumnModel().getColumn(0).setPreferredWidth(120);
        tablaMateria.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaMateria.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaMateria.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaMateria.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablaMateria.getColumnModel().getColumn(5).setPreferredWidth(130);

        // Registros
        Object[] r1 = {"MAT001", "Caja de cartón", "Cartón", "Disponible", 120, "2025-06-01"};
        Object[] r2 = {"MAT002", "Bolsa plástica", "Plástico", "En revisión", 200, "2025-06-03"};

        mdTabla.addRow(r1);
        mdTabla.addRow(r2);
    }

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
        txtNombre = new JTextField(" Nombre Objeto");
        txtCodigo = new JTextField(" Código");
        txtMaterial = new JTextField(" Material");
        txtEstado = new JTextField(" Estado");
        txtStock = new JTextField(" Stock");
        txtFechaIngreso = new JTextField(" Fecha de ingreso");

        diseñotxf(txtNombre);
        diseñotxf(txtCodigo);
        diseñotxf(txtMaterial);
        diseñotxf(txtEstado);
        diseñotxf(txtStock);
        diseñotxf(txtFechaIngreso);
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
