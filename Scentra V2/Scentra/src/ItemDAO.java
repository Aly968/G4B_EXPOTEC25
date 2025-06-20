import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    private static final String SQL_ITEMS_POR_EMPRESA =
        "SELECT 'Materia Prima'        AS Categoria, mp.idMateria_Prima AS id, mp.Nombre, mp.Cantidad_disponible AS cantidad, mp.Precio_unitario AS precio " +
        "  FROM Materia_Prima mp WHERE mp.idEmpresa = ? " +
        "UNION ALL " +
        "SELECT 'Producto Terminado'    AS Categoria, pt.idProductos     AS id, pt.Nombre, pt.Cantidad_disponible AS cantidad, pt.Costo_produccion AS precio " +
        "  FROM Productos_Terminados pt WHERE pt.idEmpresa = ? " +
        "UNION ALL " +
        "SELECT 'Material Empaque'      AS Categoria, me.idEmpaque        AS id, me.Nombre, me.Cantidad          AS cantidad, me.Precio_unitario AS precio " +
        "  FROM Material_empaque me WHERE me.idEmpresa = ?;";

    private final Conexion conexionService;

    public ItemDAO(Conexion conexionService) {
        this.conexionService = conexionService;
    }

    public void fillItemsByEmpresa(int idEmpresa, DefaultTableModel modelo) {
        modelo.setRowCount(0);  // limpia filas previas

        try (Connection conn = conexionService.getConnection();
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
            throw new RuntimeException("Error al obtener ítems de empresa " + idEmpresa, e);
        }
    }
}