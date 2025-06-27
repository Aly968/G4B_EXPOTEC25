
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.math.*;

public class Historialsg {

    Conexion conn;
    private int idEmpresaUsuario;

    public Historialsg() {
        conn = new Conexion();
        idEmpresaUsuario = SesionUsuario.getInstance().getIdEmpresa();
    }

    public DefaultTableModel obtenerlosmovimientos() {
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("IDMovimiento");
        mdl.addColumn("Tipo Movimiento");
        mdl.addColumn("Tipo Item");
        mdl.addColumn("Nombre Item");
        mdl.addColumn("Cantidad");
        mdl.addColumn("Fecha Movimiento");
        mdl.addColumn("Usuario");
        mdl.addColumn("Costo Unitario");
        mdl.addColumn("Costo Total");
        mdl.addColumn("Comentarios");

        String muchotexto = "SELECT mi.idMovimiento, mi.Tipo_movimiento, mi.Tipo_Item, " +
                            "CASE " +
                            "    WHEN mi.Tipo_Item = 'Materia_Prima' THEN mp.Nombre " +
                            "    WHEN mi.Tipo_Item = 'Productos_Terminados' THEN pt.Nombre " +
                            "    WHEN mi.Tipo_Item = 'Material_Empaque' THEN me.Nombre " +
                            "END as Nombre_Item, " +
                            "mi.Cantidad, mi.FechaMovimiento, u.Nombre as Nombre_Usuario, mi.Costo_Unitario, " +
                            "mi.Costo_Total, mi.Comentarios_detalles " +
                            "FROM Movimientos_Inventario mi " +
                            "LEFT JOIN Materia_Prima mp ON mi.idMateriaPrima_FK = mp.idMateria_Prima " +
                            "LEFT JOIN Productos_Terminados pt ON mi.idProductoTerminado_FK = pt.idProductos " +
                            "LEFT JOIN Material_empaque me ON mi.idMaterialEmpaque_FK = me.idEmpaque " +
                            "JOIN Usuarios u ON mi.idUsuario = u.idUsuarios ";
        //Inicio cambio
        // Añadir la cláusula WHERE para filtrar por idEmpresa
        if (idEmpresaUsuario != -1) { // Asegúrate de que el ID de la empresa es válido
            muchotexto += "WHERE (mp.idEmpresa = ? OR pt.idEmpresa = ? OR me.idEmpresa = ?) " +
                          "OR (mi.idMateriaPrima_FK IS NULL AND mi.idProductoTerminado_FK IS NULL AND mi.idMaterialEmpaque_FK IS NULL AND u.idEmpresa = ?) "; // Incluir movimientos de usuarios sin items directos si aplica
        }
        muchotexto += "ORDER BY mi.FechaMovimiento DESC";

        try (Connection connection = conn.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(muchotexto)) { // Usar PreparedStatement para el ID de empresa

            if (idEmpresaUsuario != -1) {
                pstmt.setInt(1, idEmpresaUsuario);
                pstmt.setInt(2, idEmpresaUsuario);
                pstmt.setInt(3, idEmpresaUsuario);
                pstmt.setInt(4, idEmpresaUsuario); // Para movimientos directamente asociados al usuario si los FKS son NULL
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] fc = new Object[10];
                    fc[0] = rs.getInt("idMovimiento");
                    fc[1] = rs.getString("Tipo_movimiento");
                    fc[2] = rs.getString("Tipo_Item");
                    fc[3] = rs.getString("Nombre_Item");
                    fc[4] = rs.getInt("Cantidad");
                    fc[5] = rs.getTimestamp("FechaMovimiento");
                    fc[6] = rs.getString("Nombre_Usuario");
                    fc[7] = rs.getBigDecimal("Costo_Unitario");
                    fc[8] = rs.getBigDecimal("Costo_Total");
                    fc[9] = rs.getString("Comentarios_detalles");
                    mdl.addRow(fc);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos generales: " + e.getMessage());
            e.printStackTrace();
        }
        return mdl;
    }

    /**
     * Obtiene los movimientos de inventario dentro de un rango de fechas específico y por empresa.
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return DefaultTableModel con los movimientos filtrados.
     */
    public DefaultTableModel obtenerlosmovimientosPorRango(Timestamp fechaInicio, Timestamp fechaFin) {
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("IDMovimiento");
        mdl.addColumn("Tipo Movimiento");
        mdl.addColumn("Tipo Item");
        mdl.addColumn("Nombre Item");
        mdl.addColumn("Cantidad");
        mdl.addColumn("Fecha Movimiento");
        mdl.addColumn("Usuario");
        mdl.addColumn("Costo Unitario");
        mdl.addColumn("Costo Total");
        mdl.addColumn("Comentarios");

        String query = "SELECT mi.idMovimiento, mi.Tipo_movimiento, mi.Tipo_Item, " +
                       "CASE " +
                       "    WHEN mi.Tipo_Item = 'Materia_Prima' THEN mp.Nombre " +
                       "    WHEN mi.Tipo_Item = 'Productos_Terminados' THEN pt.Nombre " +
                       "    WHEN mi.Tipo_Item = 'Material_Empaque' THEN me.Nombre " +
                       "END as Nombre_Item, " +
                       "mi.Cantidad, mi.FechaMovimiento, u.Nombre as Nombre_Usuario, mi.Costo_Unitario, " +
                       "mi.Costo_Total, mi.Comentarios_detalles " +
                       "FROM Movimientos_Inventario mi " +
                       "LEFT JOIN Materia_Prima mp ON mi.idMateriaPrima_FK = mp.idMateria_Prima " +
                       "LEFT JOIN Productos_Terminados pt ON mi.idProductoTerminado_FK = pt.idProductos " +
                       "LEFT JOIN Material_empaque me ON mi.idMaterialEmpaque_FK = me.idEmpaque " +
                       "JOIN Usuarios u ON mi.idUsuario = u.idUsuarios ";
        //Inicio cambio
        query += "WHERE mi.FechaMovimiento BETWEEN ? AND ? ";
        if (idEmpresaUsuario != -1) {
            query += "AND (mp.idEmpresa = ? OR pt.idEmpresa = ? OR me.idEmpresa = ?) " +
                     "OR (mi.FechaMovimiento BETWEEN ? AND ? AND mi.idMateriaPrima_FK IS NULL AND mi.idProductoTerminado_FK IS NULL AND mi.idMaterialEmpaque_FK IS NULL AND u.idEmpresa = ?) ";
        }
        query += "ORDER BY mi.FechaMovimiento DESC";

        try (Connection connection = conn.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setTimestamp(1, fechaInicio);
            pstmt.setTimestamp(2, fechaFin);

            if (idEmpresaUsuario != -1) {
                pstmt.setInt(3, idEmpresaUsuario);
                pstmt.setInt(4, idEmpresaUsuario);
                pstmt.setInt(5, idEmpresaUsuario);
                pstmt.setTimestamp(6, fechaInicio); 
                pstmt.setTimestamp(7, fechaFin);    
                pstmt.setInt(8, idEmpresaUsuario);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] fc = new Object[10];
                    fc[0] = rs.getInt("idMovimiento");
                    fc[1] = rs.getString("Tipo_movimiento");
                    fc[2] = rs.getString("Tipo_Item");
                    fc[3] = rs.getString("Nombre_Item");
                    fc[4] = rs.getInt("Cantidad");
                    fc[5] = rs.getTimestamp("FechaMovimiento");
                    fc[6] = rs.getString("Nombre_Usuario");
                    fc[7] = rs.getBigDecimal("Costo_Unitario");
                    fc[8] = rs.getBigDecimal("Costo_Total");
                    fc[9] = rs.getString("Comentarios_detalles");
                    mdl.addRow(fc);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos por rango de fechas: " + e.getMessage());
            e.printStackTrace();
        }
        return mdl;
    }
    
}