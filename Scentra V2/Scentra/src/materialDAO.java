
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
//Alison Del Rosario Vicente Coroy

public class materialDAO {
    
    private final Conexion conexionService;

    public materialDAO(Conexion conexionService) {
        this.conexionService = conexionService;
    }

    public void mostrarMaterial(int idEmpresa, DefaultTableModel modelo) {

        String sql = "SELECT idEmpaque,Nombre,Unidad_de_medida,Cantidad, "
                + "Precio_unitario, Ultima_actualizacion_empq FROM Material_empaque WHERE idEmpresa = ?";
        
          try (java.sql.Connection conn = conexionService.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
         
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("idEmpaque"),
                        rs.getString("Nombre"),
                        rs.getString("Unidad_de_medida"),
                        rs.getInt("Cantidad"),
                        rs.getDouble("Precio_unitario"),
                        rs.getDate("Ultima_actualización_empq")
                    });
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener ítems de empresa " + idEmpresa, e);
        }

    }
}
