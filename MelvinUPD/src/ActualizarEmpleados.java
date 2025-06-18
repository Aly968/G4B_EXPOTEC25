import java.sql.*;
import java.math.BigDecimal;
// Melvin Rodriguz 
public class ActualizarEmpleados {

    public boolean actualizarDatosEmpleado(
        int idEmpleado,
        String nombre,
        int edad,
        String puesto,
        String direccion,
        BigDecimal sueldo,
        Timestamp ultimaActualizacion
    ) {
        Conexion conexion = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Empleado SET " +
                             "Nombre = ?, " +
                             "Edad = ?, " +
                             "Puesto = ?, " +
                             "Direccion = ?, " +
                             "Sueldo = ?, " +
                             "Ultima_actualizacion_e = ? " +
                             "WHERE idEmpleado = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setInt(2, edad);
                ps.setString(3, puesto);
                ps.setString(4, direccion);
                ps.setBigDecimal(5, sueldo);
                ps.setTimestamp(6, ultimaActualizacion);
                ps.setInt(7, idEmpleado);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    exito = true;
                    System.out.println("✔ Empleado actualizado correctamente.");
                } else {
                    System.out.println("✘ No se encontró un empleado con ese ID.");
                }
            } else {
                System.err.println("✘ Conexión no disponible.");
            }

        } catch (SQLException e) {
            System.err.println("✘ Error SQL al actualizar empleado: " + e.getMessage());
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
