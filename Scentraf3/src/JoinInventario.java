import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
public class JoinInventario {
  private static final String SQL_ITEMS_POR_EMPRESA =
        "SELECT 'Materia Prima'        AS Categoria, mp.idMateria_Prima AS id, mp.Nombre, mp.Cantidad_disponible AS cantidad, mp.Precio_unitario AS precio " +
        "  FROM Materia_Prima mp WHERE mp.idEmpresa = ? " +
        "UNION ALL " +
        "SELECT 'Producto Terminado'    AS Categoria, pt.idProductos     AS id, pt.Nombre, pt.Cantidad_disponible AS cantidad, pt.Costo_produccion AS precio " +
        "  FROM Productos_Terminados pt WHERE pt.idEmpresa = ? " +
        "UNION ALL " +
        "SELECT 'Material Empaque'      AS Categoria, me.idEmpaque        AS id, me.Nombre, me.Cantidad          AS cantidad, me.Precio_unitario AS precio " +
        "  FROM Material_empaque me WHERE me.idEmpresa = ?;";

    Conexion cons;

    public JoinInventario(Conexion cons) {
        this.cons = cons;
    }
    

    public void llenarporempresa(int idEmpresa, DefaultTableModel modelo) {
        modelo.setRowCount(0);  // limpia filas previas

        try (Connection conn = cons.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_ITEMS_POR_EMPRESA)) {
            ps.setInt(1, idEmpresa);
            ps.setInt(2, idEmpresa);
            ps.setInt(3, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getString("Categoria"),
                        rs.getInt("id"),
                        rs.getString("Nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                    });
                }
            }

        } catch (SQLException e) {
            // Aquí puedes relanzar o manejarla según necesites
            throw new RuntimeException("Error al cargar " + idEmpresa, e);
        }
    }
}