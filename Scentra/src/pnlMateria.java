
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

public class pnlMateria extends JPanel implements ActionListener {

    JSeparator linea;
    JButton btnDatos;
    private JTextField txtNombre;
    private JTextField txtIdMateriaPrima;
    private JTextField txtIdEmpresa;
    private JTextField txtUnidadDeMedida;
    private JTextField txtCantidadDisponible;
    private JTextField txtCantidadMinima;
    private JTextField txtPrecioUnitario;
    private JTextField txtUltimaActualizacionInv;

    private JTable tablaMateria;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;
    private String rolUsuario;
    private int idEmpresaUsuario;
    //Sub
    Font bt = new Font("Segoe IU", Font.PLAIN, 14);
    //titulos
    Font bt2 = new Font("Segoe IU", Font.BOLD, 15);

    public pnlMateria() {
        setLayout(null);
        setBackground(new Color(208, 201, 208));
        SesionUsuario sesion = SesionUsuario.getInstance();
                this.rolUsuario = sesion.getRol();
        this.idEmpresaUsuario = sesion.getIdEmpresa();
        obj();
        agr();
        posicionar();
        cargarDatos(); // Cargar datos al inicializar el panel

        // Listener para seleccionar fila y rellenar campos
        tablaMateria.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaMateria.getSelectedRow() != -1) {
                int selectedRow = tablaMateria.getSelectedRow();
                txtIdMateriaPrima.setText(mdTabla.getValueAt(selectedRow, 0).toString());
                txtIdEmpresa.setText(mdTabla.getValueAt(selectedRow, 1).toString());
                txtNombre.setText(mdTabla.getValueAt(selectedRow, 2).toString());
                txtUnidadDeMedida.setText(mdTabla.getValueAt(selectedRow, 3).toString());
                txtCantidadDisponible.setText(mdTabla.getValueAt(selectedRow, 4).toString());
                txtCantidadMinima.setText(mdTabla.getValueAt(selectedRow, 5).toString());
                txtPrecioUnitario.setText(mdTabla.getValueAt(selectedRow, 6).toString());
                txtUltimaActualizacionInv.setText(mdTabla.getValueAt(selectedRow, 7) != null ? mdTabla.getValueAt(selectedRow, 7).toString() : "");
                resetPlaceholder(txtIdMateriaPrima, "ID Materia Prima");
                resetPlaceholder(txtIdEmpresa, "ID Empresa");
                resetPlaceholder(txtNombre, "Nombre");
                resetPlaceholder(txtUnidadDeMedida, "Unidad de Medida");
                resetPlaceholder(txtCantidadDisponible, "Cantidad Disponible");
                resetPlaceholder(txtCantidadMinima, "Cantidad Mínima");
                resetPlaceholder(txtPrecioUnitario, "Precio Unitario");
                resetPlaceholder(txtUltimaActualizacionInv, "Última Actualización");
            }
        });
    }

    public void posicionar() {
        pnlTextos.setBounds(300, 40, 350, 300); // Aumentado el tamaño del panel de textos para acomodar más campos
        // Posiciones de los campos de texto
        txtIdMateriaPrima.setBounds(20, 20, 145, 30);
        txtIdEmpresa.setBounds(185, 20, 145, 30);
        txtNombre.setBounds(20, 70, 310, 30);
        txtUnidadDeMedida.setBounds(20, 120, 145, 30);
        txtCantidadDisponible.setBounds(185, 120, 145, 30);
        txtCantidadMinima.setBounds(20, 170, 145, 30);
        txtPrecioUnitario.setBounds(185, 170, 145, 30);
        txtUltimaActualizacionInv.setBounds(20, 220, 310, 30); // Ocupa todo el ancho
        txtUltimaActualizacionInv.setEditable(false); // No editable por el usuario
        linea.setBounds(20, 260, 310, 6); // Ajustar posición de la línea
        btnDatos.setBounds(130, 265, 80, 30); // Ajustar posición del botón
        scTabla.setBounds(35, 370, 880, 170); // Ajustar posición y tamaño de la tabla
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
        pnlTextos.add(txtIdMateriaPrima);
        pnlTextos.add(txtIdEmpresa);
        pnlTextos.add(txtNombre);
        pnlTextos.add(txtUnidadDeMedida);
        pnlTextos.add(txtCantidadDisponible);
        pnlTextos.add(txtCantidadMinima);
        pnlTextos.add(txtPrecioUnitario);
        pnlTextos.add(txtUltimaActualizacionInv);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(scTabla);
        btnDatos.addActionListener(this);
    }

    public void Tb() {
        // Columnas de la tabla
        String[] columnas = {"ID Materia", "ID Empresa", "Nombre", "Unidad de Medida",
            "Cant. Disponible", "Cant. Mínima", "Precio Unitario", "Última Actualización"};
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
        tablaMateria.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaMateria.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaMateria.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaMateria.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaMateria.getColumnModel().getColumn(4).setPreferredWidth(120);
        tablaMateria.getColumnModel().getColumn(5).setPreferredWidth(120);
        tablaMateria.getColumnModel().getColumn(6).setPreferredWidth(100);
        tablaMateria.getColumnModel().getColumn(7).setPreferredWidth(150);
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
        txtIdMateriaPrima = createStyledTextField("ID Materia Prima");
        txtIdEmpresa = createStyledTextField("ID Empresa");
        txtNombre = createStyledTextField("Nombre");
        txtUnidadDeMedida = createStyledTextField("Unidad de Medida");
        txtCantidadDisponible = createStyledTextField("Cantidad Disponible");
        txtCantidadMinima = createStyledTextField("Cantidad Mínima");
        txtPrecioUnitario = createStyledTextField("Precio Unitario");
        txtUltimaActualizacionInv = createStyledTextField("Última Actualización");
        txtUltimaActualizacionInv.setEditable(false); // Este campo no debe ser editable por el usuario
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
        // Listener para manejar el placeholder
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
        btnDatos = new JButton("Añadir"); // Este botón se manejará en la clase Inventarios
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
        mdTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (con != null) {
            String sql = "SELECT idMateria_Prima, idEmpresa, Nombre, Unidad_de_medida, Cantidad_disponible, Cantidad_minima, Precio_unitario, Ultima_actualización_inv FROM Materia_Prima";
            try {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    // Añadir una fila a la tabla con los datos de la base de datos
                    mdTabla.addRow(new Object[]{
                        rs.getInt("idMateria_Prima"),
                        rs.getInt("idEmpresa"),
                        rs.getString("Nombre"),
                        rs.getString("Unidad_de_medida"),
                        rs.getInt("Cantidad_disponible"),
                        rs.getInt("Cantidad_minima"),
                        rs.getDouble("Precio_unitario"),
                        rs.getTimestamp("Ultima_actualización_inv")
                    });
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar datos de Materia Prima: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
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
                        con.close(); // Cerrar la conexión
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
        }
    }

    // Métodos para obtener los datos de los campos de texto
    public Object[] getDatosMateriaPrima() {
        // Validación básica para evitar espacios en blanco
        try {
            int idMateriaPrima = txtIdMateriaPrima.getText().trim().equals("ID Materia Prima") || txtIdMateriaPrima.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIdMateriaPrima.getText().trim());
            int idEmpresa = txtIdEmpresa.getText().trim().equals("ID Empresa") || txtIdEmpresa.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIdEmpresa.getText().trim());
            String nombre = txtNombre.getText().trim().equals("Nombre") || txtNombre.getText().trim().isEmpty() ? null : txtNombre.getText().trim();
            String unidadMedida = txtUnidadDeMedida.getText().trim().equals("Unidad de Medida") || txtUnidadDeMedida.getText().trim().isEmpty() ? null : txtUnidadDeMedida.getText().trim();
            int cantidadDisponible = txtCantidadDisponible.getText().trim().equals("Cantidad Disponible") || txtCantidadDisponible.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCantidadDisponible.getText().trim());
            int cantidadMinima = txtCantidadMinima.getText().trim().equals("Cantidad Mínima") || txtCantidadMinima.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCantidadMinima.getText().trim());
            double precioUnitario = txtPrecioUnitario.getText().trim().equals("Precio Unitario") || txtPrecioUnitario.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtPrecioUnitario.getText().trim());
            if ((idMateriaPrima == 0) && (!txtIdMateriaPrima.getText().trim().equals("ID Materia Prima") && !txtIdMateriaPrima.getText().trim().isEmpty())) {
                JOptionPane.showMessageDialog(this, "El ID de Materia Prima no puede ser 0 o vacío.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (nombre == null || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre de la Materia Prima no puede estar vacío.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return new Object[]{idMateriaPrima, idEmpresa, nombre, unidadMedida,
                cantidadDisponible, cantidadMinima, precioUnitario};
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para ID, Cantidad y Precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void clearFields() {
        txtIdMateriaPrima.setText("ID Materia Prima");
        txtIdMateriaPrima.setForeground(Color.GRAY);
        txtIdEmpresa.setText("ID Empresa");
        txtIdEmpresa.setForeground(Color.GRAY);
        txtNombre.setText("Nombre");
        txtNombre.setForeground(Color.GRAY);
        txtUnidadDeMedida.setText("Unidad de Medida");
        txtUnidadDeMedida.setForeground(Color.GRAY);
        txtCantidadDisponible.setText("Cantidad Disponible");
        txtCantidadDisponible.setForeground(Color.GRAY);
        txtCantidadMinima.setText("Cantidad Mínima");
        txtCantidadMinima.setForeground(Color.GRAY);
        txtPrecioUnitario.setText("Precio Unitario");
        txtPrecioUnitario.setForeground(Color.GRAY);
        txtUltimaActualizacionInv.setText("Última Actualización");
        txtUltimaActualizacionInv.setForeground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
