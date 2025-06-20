import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Create_Material {
    
    public boolean crearMaterialEmpaque(int idEmpaque, int idEmpresa, String Nombre, String unidad_de_medida, int Cantidad, double Precio_unitario,  java.sql.Timestamp ultima_actualizacion_empq) {

        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
        PreparedStatement ps = null;
        boolean creado = false;

        if (con != null) {
            String sql = "INSERT INTO Material_empaque (idEmpaque, idEmpresa, Nombre, unidad_de_medida, Cantidad, Precio_unitario, ultima_actualizacion_empq) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, idEmpaque);
                ps.setInt(2, idEmpresa);
                ps.setString(3, Nombre);
                ps.setString(4, unidad_de_medida);
                ps.setInt(5, Cantidad);
                ps.setDouble(6, Precio_unitario);
                ps.setTimestamp(7, ultima_actualizacion_empq);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    creado = true;
                    JOptionPane.showMessageDialog(null, "Material creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo insertar el Material.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar Material: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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