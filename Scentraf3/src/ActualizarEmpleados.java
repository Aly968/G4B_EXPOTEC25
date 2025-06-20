
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ActualizarEmpleados {
//Melvin Rodriguez

    public boolean actualizarDatosUsuario(
            int idUsuario,
            String nuevoRol,
            String nuevoEstadoUnionEmpresa
    ) {
        //Crear una nueva conexión
        Conexion conexion = new Conexion();
        //se deja la conexión vacia
        Connection con = null;
        //no se tiene ninguna consulta a ejecutar
        PreparedStatement ps = null;
        boolean exito = false;

        try {
            con = conexion.getConnection();

            if (con != null) {
                String sql = "UPDATE Usuarios SET "
                        + "Rol = ?, "
                        + "Estado_Union_Empresa = ? "
                        + "WHERE idUsuarios = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, nuevoRol);
                ps.setString(2, nuevoEstadoUnionEmpresa);
                ps.setInt(3, idUsuario);

                int filas = ps.executeUpdate();

                if (filas > 0) {
                    exito = true;
                    System.out.println("✔ Usuario (Empleado) actualizado correctamente.");
                } else {
                    System.out.println("✘ No se encontró un usuario (empleado) con ese ID.");
                }
            } else {
                System.err.println("✘ Conexión no disponible.");
            }

        } catch (SQLException e) {
            System.err.println("✘ Error SQL al actualizar usuario (empleado): " + e.getMessage());
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
                System.err.println("✘ Error al cerrar conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return exito;
    }
}
