import java.sql.*;
import java.math.BigDecimal;// para majer volumenes grandes (Numeros)
// Melvin Rodriguz 

public class ActualizarMaterial {

    public boolean actualizarDatosMaterialEmpaque (
        int idEmpaque,
        String nombre,
        String unidadDeMedida,
        int cantidad,
        BigDecimal precioUnitario,
        Timestamp ultimaActualizacionEmpq
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exitoActualizacion = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Material_empaque SET " +
                             "Nombre = ?, " +
                             "Unidad_de_medida = ?, " +
                             "Cantidad = ?, " +
                             "Precio_unitario = ?, " +
                             "Ultima_actualizacion_empq = ? " +
                             "WHERE idEmpaque = ?";

                ps = con.prepareStatement(sql);

                // Asignar valores a la sentencia SQL
                ps.setString(1, nombre);
                ps.setString(2, unidadDeMedida);
                
                
                
                
                ps.setInt(3, cantidad);
                ps.setBigDecimal(4, precioUnitario);
                ps.setTimestamp(5, ultimaActualizacionEmpq);
                ps.setInt(6, idEmpaque);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    exitoActualizacion = true;
                    System.out.println("Actualización exitosa para idEmpaque " + idEmpaque + ". Filas afectadas: " + filasAfectadas);
                } else {
                    System.out.println("No se encontró Material de Empaque con ID: " + idEmpaque);
                }
            } else {
                System.err.println("Conexión a la base de datos no disponible.");
            }

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar Material de Empaque con ID " + idEmpaque + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return exitoActualizacion;
    }
}
