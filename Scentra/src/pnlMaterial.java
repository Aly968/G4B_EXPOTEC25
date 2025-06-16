
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

//Alison Del Rosario Vicente Coroy
public class pnlMaterial extends JPanel implements ActionListener {

    JSeparator linea;
    JButton btnDatos;
    private JTextField txtNombre;
    private JTextField txtIdEmpaque;
    private JTextField txtIdEmpresa;
    private JTextField txtUnidadDeMedida;
    private JTextField txtCantidadActual;
    private JTextField txtPrecioUnitario;
    private JTextField txtUltimaActualizacionEmpq;
    private JTable tablaMaterial;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;
    //Sub
    Font bt = new Font("Segoe IU", Font.PLAIN, 14);
    //titulos
    Font bt2 = new Font("Segoe IU", Font.BOLD, 15);
    private String rolUsuario;
    private int idEmpresaUsuario;

    public pnlMaterial() {
        setLayout(null);
        setBackground(new Color(208, 201, 208));
        SesionUsuario sesion = SesionUsuario.getInstance();
                this.rolUsuario = sesion.getRol();
        this.idEmpresaUsuario = sesion.getIdEmpresa();
        obj();
        agr();
        posicionar();
        cargarDatos();

        tablaMaterial.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaMaterial.getSelectedRow() != -1) {
                int selectedRow = tablaMaterial.getSelectedRow();
                txtIdEmpaque.setText(mdTabla.getValueAt(selectedRow, 0).toString());
                txtIdEmpresa.setText(mdTabla.getValueAt(selectedRow, 1).toString());
                txtNombre.setText(mdTabla.getValueAt(selectedRow, 2).toString());
                txtUnidadDeMedida.setText(mdTabla.getValueAt(selectedRow, 3).toString());
                txtCantidadActual.setText(mdTabla.getValueAt(selectedRow, 4).toString());
                txtPrecioUnitario.setText(mdTabla.getValueAt(selectedRow, 5).toString());
                txtUltimaActualizacionEmpq.setText(mdTabla.getValueAt(selectedRow, 6) != null ? mdTabla.getValueAt(selectedRow, 6).toString() : "");

                resetPlaceholder(txtIdEmpaque, "ID Empaque");
                resetPlaceholder(txtIdEmpresa, "ID Empresa");
                resetPlaceholder(txtNombre, "Nombre");
                resetPlaceholder(txtUnidadDeMedida, "Unidad de Medida");
                resetPlaceholder(txtCantidadActual, "Cantidad Actual");
                resetPlaceholder(txtPrecioUnitario, "Precio Unitario");
                resetPlaceholder(txtUltimaActualizacionEmpq, "Última Actualización");
            }
        });
    }

    public void posicionar() {
        pnlTextos.setBounds(300, 40, 350, 300);

        txtIdEmpaque.setBounds(20, 20, 145, 30);
        txtIdEmpresa.setBounds(185, 20, 145, 30);
        txtNombre.setBounds(20, 70, 310, 30);
        txtUnidadDeMedida.setBounds(20, 120, 145, 30);
        txtCantidadActual.setBounds(185, 120, 145, 30);
        txtPrecioUnitario.setBounds(20, 170, 145, 30);
        txtUltimaActualizacionEmpq.setBounds(185, 170, 145, 30);
        txtUltimaActualizacionEmpq.setEditable(false);

        linea.setBounds(20, 230, 310, 6);
        btnDatos.setBounds(130, 240, 80, 30);

        scTabla.setBounds(35, 360, 880, 170);
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
        pnlTextos.add(txtIdEmpaque);
        pnlTextos.add(txtIdEmpresa);
        pnlTextos.add(txtNombre);
        pnlTextos.add(txtUnidadDeMedida);
        pnlTextos.add(txtCantidadActual);
        pnlTextos.add(txtPrecioUnitario);
        pnlTextos.add(txtUltimaActualizacionEmpq);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(scTabla);
        btnDatos.addActionListener(this);
    }

    public void Tb() {
        String[] columnas = {"ID Empaque", "ID Empresa", "Nombre", "Unidad de Medida", "Cantidad", "Precio Unitario", "Última Actualización"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaMaterial = new JTable(mdTabla);
        tablaMaterial.setFillsViewportHeight(true);
        scTabla = new JScrollPane(tablaMaterial);

        tablaMaterial.setFont(bt);
        tablaMaterial.setBackground(new Color(249, 247, 250));
        tablaMaterial.setForeground(Color.BLACK);
        tablaMaterial.setRowHeight(28);
        tablaMaterial.setShowGrid(true);
        tablaMaterial.setGridColor(Color.BLACK);
        titulos = tablaMaterial.getTableHeader();
        titulos.setFont(bt2);
        titulos.setBackground(new Color(116, 119, 147));
        titulos.setForeground(Color.WHITE);
        titulos.setReorderingAllowed(false);
        tablaMaterial.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaMaterial.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaMaterial.getColumnModel().getColumn(2).setPreferredWidth(180);
        tablaMaterial.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaMaterial.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaMaterial.getColumnModel().getColumn(5).setPreferredWidth(100);
        tablaMaterial.getColumnModel().getColumn(6).setPreferredWidth(150);
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
        txtIdEmpaque = createStyledTextField("ID Empaque");
        txtIdEmpresa = createStyledTextField("ID Empresa");
        txtNombre = createStyledTextField("Nombre");
        txtUnidadDeMedida = createStyledTextField("Unidad de Medida");
        txtCantidadActual = createStyledTextField("Cantidad Actual");
        txtPrecioUnitario = createStyledTextField("Precio Unitario");
        txtUltimaActualizacionEmpq = createStyledTextField("Última Actualización");
        txtUltimaActualizacionEmpq.setEditable(false);
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setFont(bt);
        textField.setBackground(new Color(249, 247, 250));
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textField.setCaretColor(Color.BLACK);
        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private void resetPlaceholder(JTextField field, String placeholder) {
        if (field.getText().equals(placeholder)) {
            field.setForeground(Color.GRAY);
        } else if (!field.getText().isEmpty()) {
            field.setForeground(Color.BLACK);
        }
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

    public void cargarDatos() {
        mdTabla.setRowCount(0);
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT idEmpaque, idEmpresa, Nombre, Unidad_de_medida, Cantidad, Precio_unitario, Ultima_actualizacion_empq FROM Material_empaque";
            try {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    mdTabla.addRow(new Object[]{
                        rs.getInt("idEmpaque"),
                        rs.getInt("idEmpresa"),
                        rs.getString("Nombre"),
                        rs.getString("Unidad_de_medida"),
                        rs.getInt("Cantidad"),
                        rs.getDouble("Precio_unitario"),
                        rs.getTimestamp("Ultima_actualizacion_empq")
                    });
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar datos de Material de Empaque: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
        }
    }

    public Object[] getDatosMaterialEmpaque() {
        try {
            int idEmpaque = txtIdEmpaque.getText().trim().equals("ID Empaque") || txtIdEmpaque.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIdEmpaque.getText().trim());
            int idEmpresa = txtIdEmpresa.getText().trim().equals("ID Empresa") || txtIdEmpresa.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIdEmpresa.getText().trim());
            String nombre = txtNombre.getText().trim().equals("Nombre") || txtNombre.getText().trim().isEmpty() ? null : txtNombre.getText().trim();
            String unidadMedida = txtUnidadDeMedida.getText().trim().equals("Unidad de Medida") || txtUnidadDeMedida.getText().trim().isEmpty() ? null : txtUnidadDeMedida.getText().trim();
            int cantidad = txtCantidadActual.getText().trim().equals("Cantidad Actual") || txtCantidadActual.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCantidadActual.getText().trim());
            double precioUnitario = txtPrecioUnitario.getText().trim().equals("Precio Unitario") || txtPrecioUnitario.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrecioUnitario.getText().trim());

            if ((idEmpaque == 0) && (!txtIdEmpaque.getText().trim().equals("ID Empaque") && !txtIdEmpaque.getText().trim().isEmpty())) {
                JOptionPane.showMessageDialog(this, "El ID de Empaque no puede ser 0 o vacío.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (nombre == null || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del Material de Empaque no puede estar vacío.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new Object[]{idEmpaque, idEmpresa, nombre, unidadMedida, cantidad, precioUnitario};
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para ID, Cantidad y Precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void clearFields() {
        txtIdEmpaque.setText("ID Empaque");
        txtIdEmpaque.setForeground(Color.GRAY);
        txtIdEmpresa.setText("ID Empresa");
        txtIdEmpresa.setForeground(Color.GRAY);
        txtNombre.setText("Nombre");
        txtNombre.setForeground(Color.GRAY);
        txtUnidadDeMedida.setText("Unidad de Medida");
        txtUnidadDeMedida.setForeground(Color.GRAY);
        txtCantidadActual.setText("Cantidad Actual");
        txtCantidadActual.setForeground(Color.GRAY);
        txtPrecioUnitario.setText("Precio Unitario");
        txtPrecioUnitario.setForeground(Color.GRAY);
        txtUltimaActualizacionEmpq.setText("Última Actualización");
        txtUltimaActualizacionEmpq.setForeground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
