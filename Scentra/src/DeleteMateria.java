import java.sql.*;
import javax.swing.JOptionPane;

public class DeleteMateria {

    public boolean eliminarMateriaPrima(int idMateriaPrima) {
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
        PreparedStatement ps = null;
        boolean eliminado = false;

        if (con != null) {
            String sql = "DELETE FROM Materia_Prima WHERE idMateria_Prima = ?";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, idMateriaPrima);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    eliminado = true;
                    JOptionPane.showMessageDialog(null, "Materia Prima eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró Materia Prima con el ID: " + idMateriaPrima, "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar Materia Prima: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (con != null) con.close(); // Cerrar la conexión aquí
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return eliminado;
    }
}