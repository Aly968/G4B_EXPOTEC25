import java.sql.*;
import javax.swing.JOptionPane;

public class Materia_Create {

    public boolean crearMateriaPrima(int idMateria_Prima, int idEmpresa, String nombre, String unidad_de_medida,
                                     int cantidad_disponible, int cantidad_minima, double precio_unitario,
                                     Timestamp ultima_actualizacion_inv) {

        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
        PreparedStatement ps = null;
        boolean creado = false;

        if (con != null) {
            String sql = "INSERT INTO Materia_Prima (idMateria_Prima, idEmpresa, nombre, unidad_de_medida, cantidad_disponible, cantidad_minima, precio_unitario, ultima_actualizacion_inv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, idMateria_Prima);
                ps.setInt(2, idEmpresa);
                ps.setString(3, nombre);
                ps.setString(4, unidad_de_medida);
                ps.setInt(5, cantidad_disponible);
                ps.setInt(6, cantidad_minima);
                ps.setDouble(7, precio_unitario);
                ps.setTimestamp(8, ultima_actualizacion_inv);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    creado = true;
                    JOptionPane.showMessageDialog(null, "Materia Prima creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo insertar la Materia Prima.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar Materia Prima: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar recursos: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
        return creado;
    }
}



