import java.sql.*;
import java.math.BigDecimal;
// Melvin Rodriguz 
public class ActualizarProductos {

    public boolean actualizarDatosProducto(
        int idProducto,
        String nombre,
        String descripcion,
        BigDecimal precioUnitario,
        int existencias,
        Timestamp ultimaActualizacion
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Producto SET " +
                             "Nombre = ?, " +
                             "Descripcion = ?, " +
                             "Precio_unitario = ?, " +
                             "Existencias = ?, " +
                             "Ultima_actualizacion_p = ? " +
                             "WHERE idProducto = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setBigDecimal(3, precioUnitario);
                ps.setInt(4, existencias);
                ps.setTimestamp(5, ultimaActualizacion);
                ps.setInt(6, idProducto);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    exito = true;
                    System.out.println("✔ Producto actualizado correctamente.");
                } else {
                    System.out.println("✘ No se encontró un producto con ese ID.");
                }
            } else {
                System.err.println("✘ Conexión no disponible.");
            }

        } catch (SQLException e) {
            System.err.println("✘ Error SQL al actualizar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("✘ Error al cerrar conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return exito;
    }
}
