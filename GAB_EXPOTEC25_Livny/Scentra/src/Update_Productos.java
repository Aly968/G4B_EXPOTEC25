import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Update_Productos extends JPanel implements ActionListener {

    JButton btnActualizar;
    JTable tablaProductos;
    DefaultTableModel mdTabla;
    JTextField txtFabricacion, txtIngreso, txtEmpleado, txtCantidad, txtSerie;
    JPanel pnlTextos;

    public Update_Productos() {
        setLayout(null);
        initComponents();
    }

    public void initComponents() {
        pnlTextos = new JPanel();
        pnlTextos.setLayout(null);
        pnlTextos.setBounds(20, 20, 400, 250);
        add(pnlTextos);

        // Campos de texto
        txtFabricacion = new JTextField();
        txtIngreso = new JTextField();
        txtEmpleado = new JTextField();
        txtCantidad = new JTextField();
        txtSerie = new JTextField();

        txtFabricacion.setBounds(20, 20, 150, 25);
        txtIngreso.setBounds(20, 50, 150, 25);
        txtEmpleado.setBounds(20, 80, 150, 25);
        txtCantidad.setBounds(20, 110, 150, 25);
        txtSerie.setBounds(20, 140, 150, 25);

        pnlTextos.add(txtFabricacion);
        pnlTextos.add(txtIngreso);
        pnlTextos.add(txtEmpleado);
        pnlTextos.add(txtCantidad);
        pnlTextos.add(txtSerie);

        // Botón Actualizar
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(20, 180, 120, 30);
        btnActualizar.addActionListener(this);
        pnlTextos.add(btnActualizar);

        // Tabla
        String[] columnas = {"ID", "Fabricación", "Ingreso", "Empleado", "Cantidad", "Serie"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaProductos = new JTable(mdTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(450, 20, 500, 200);
        add(scroll);

        // Datos
        String[] fila1 = {"001", "2025-06-01", "2025-06-03", "Lucía", "150", "VEL-001"};
        mdTabla.addRow(fila1);

        tablaProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int fila = tablaProductos.getSelectedRow();
                if (fila >= 0) {
                    txtFabricacion.setText((String) tablaProductos.getValueAt(fila, 1));
                    txtIngreso.setText((String) tablaProductos.getValueAt(fila, 2));
                    txtEmpleado.setText((String) tablaProductos.getValueAt(fila, 3));
                    txtCantidad.setText((String) tablaProductos.getValueAt(fila, 4));
                    txtSerie.setText((String) tablaProductos.getValueAt(fila, 5));
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnActualizar) {
            int fila = tablaProductos.getSelectedRow();
            if (fila >= 0) {
                mdTabla.setValueAt(txtFabricacion.getText(), fila, 1);
                mdTabla.setValueAt(txtIngreso.getText(), fila, 2);
                mdTabla.setValueAt(txtEmpleado.getText(), fila, 3);
                mdTabla.setValueAt(txtCantidad.getText(), fila, 4);
                mdTabla.setValueAt(txtSerie.getText(), fila, 5);
                JOptionPane.showMessageDialog(this, "Actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila.");
            }
        }
    }
}
