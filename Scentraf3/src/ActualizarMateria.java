
import java.sql.*;
import javax.swing.JOptionPane; // Importa la clase JOptionPane

public class ActualizarMateria {

    public boolean actualizarCantidadMateriaPrima(
            int idMateriaPrima,
            int cantidad
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exitoActualizacion = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Materia_Prima SET "
                        + "Cantidad_disponible = ?, "
                        + "Ultima_actualizacion_inv = ? "
                        + "WHERE idMateria_Prima = ?";

                ps = con.prepareStatement(sql);

                ps.setInt(1, cantidad);
                ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                ps.setInt(3, idMateriaPrima);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    exitoActualizacion = true;

                    JOptionPane.showMessageDialog(null, "Cantidad actualizada para idMateriaPrima " + idMateriaPrima, "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null, "No se encontró Materia Prima con ID: " + idMateriaPrima, "Error de Actualización", JOptionPane.WARNING_MESSAGE);
                }
            } else {

                JOptionPane.showMessageDialog(null, "Conexión a la base de datos no disponible.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error SQL al actualizar cantidad de Materia Prima: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
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
