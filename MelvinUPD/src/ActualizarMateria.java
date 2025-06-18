import java.sql.*;
import java.math.BigDecimal;
// Melvin Rodriguz 
public class ActualizarMateria {

    public boolean actualizarDatosMateriaPrima(
        int idMateriaPrima,
        String nombre,
        String unidadDeMedida,
        int cantidad,
        BigDecimal precioUnitario,
        Timestamp ultimaActualizacionMp
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exitoActualizacion = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Materia_prima SET " +
                             "Nombre = ?, " +
                             "Unidad_de_medida = ?, " +
                             "Cantidad = ?, " +
                             "Precio_unitario = ?, " +
                             "Ultima_actualizacion_mp = ? " +
                             "WHERE idMateriaPrima = ?";

                ps = con.prepareStatement(sql);

                ps.setString(1, nombre);
                ps.setString(2, unidadDeMedida);
                ps.setInt(3, cantidad);
                ps.setBigDecimal(4, precioUnitario);
                ps.setTimestamp(5, ultimaActualizacionMp);
                ps.setInt(6, idMateriaPrima);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    exitoActualizacion = true;
                    System.out.println("✔ Datos actualizados para idMateriaPrima " + idMateriaPrima);
                } else {
                    System.out.println("✘ No se encontró Materia Prima con ID: " + idMateriaPrima);
                }
            } else {
                System.err.println("✘ Conexión a la base de datos no disponible.");
            }

        } catch (SQLException e) {
            System.err.println("✘ Error SQL al actualizar Materia Prima: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("✘ Error al cerrar recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return exitoActualizacion;
    }
}
