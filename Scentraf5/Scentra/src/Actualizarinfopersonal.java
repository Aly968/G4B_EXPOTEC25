
import java.sql.*;
import javax.swing.JOptionPane;

class Actualizarinfopersonal {
//Gabriel Rodriguez

    public boolean actualizarDatosUsuario(
            int idUsuario,
            String nuevoNombre,
            String nuevaContrasena
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();
            if (con != null) {
                String sql = "UPDATE Usuarios SET "
                        + "Nombre = ?, "
                        + "Contrasena = ? "
                        + "WHERE idUsuarios = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, nuevoNombre);
                ps.setString(2, nuevaContrasena);
                ps.setInt(3, idUsuario);

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    exito = true;
                    JOptionPane.showMessageDialog(null, "✔ Información personal del usuario actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "✘ No se encontró un usuario con ese ID para actualizar su información personal.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "✘ Conexión no disponible.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "✘ Error SQL al actualizar la información personal del usuario: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "✘ Error al cerrar conexión: " + e.getMessage(), "Error al Cerrar", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        return exito;
    }
}
