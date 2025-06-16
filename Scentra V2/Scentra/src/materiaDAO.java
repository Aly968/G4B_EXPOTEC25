import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
//Alison Del Rosario Vicente Coroy

public class materiaDAO {

    private final Conexion conexionService;

    public materiaDAO(Conexion conexionService) {
        this.conexionService = conexionService;
    }

    public void mostrarMateria(int idEmpresa, DefaultTableModel modelo) {

        String sql = "SELECT idMateria_Prima, Nombre, Unidad_de_medida, Cantidad_disponible, Cantidad_minima, "
                + "Precio_unitario, Ultima_actualizacion_inv FROM Materia_Prima WHERE idEmpresa = ?";
        
          try (java.sql.Connection conn = conexionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
         
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("idMateria_Prima"),
                        rs.getString("Nombre"),
                        rs.getString("Unidad_de_medida"),
                        rs.getInt("Cantidad_disponible"),
                        rs.getInt("Cantidad_minima"),
                        rs.getDouble("Precio_unitario"),
                        rs.getDate("Ultima_actualización_inv")
                    });
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ítems de empresa " + idEmpresa, e);
        }

    }
}
