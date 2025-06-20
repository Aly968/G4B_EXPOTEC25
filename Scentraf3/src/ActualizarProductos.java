
import java.sql.*;
import javax.swing.JOptionPane; 

public class ActualizarProductos {

    public boolean actualizarCantidadProducto(
            int idProducto,
            int cantidadDisponible
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exitoActualizacion = false;

        try {
            con = conexion.getConnection();

            if (con != null) {

                String sql = "UPDATE Productos_Terminados SET "
                        + "Cantidad_disponible = ?, "
                        + "Ultima_actualizacion_prod = ? "
                        + "WHERE idProductos = ?";

                ps = con.prepareStatement(sql);

                // Establecemos los parámetros
                ps.setInt(1, cantidadDisponible);
                ps.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Establece la fecha y hora actual
                ps.setInt(3, idProducto);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    exitoActualizacion = true;
                    JOptionPane.showMessageDialog(null, "Cantidad actualizada para el producto ID: " + idProducto, "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró Producto con ID: " + idProducto, "Error de Actualización", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conexión a la base de datos no disponible.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al actualizar cantidad del Producto: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage(), "Error de Recursos", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        return exitoActualizacion;
    }
}
