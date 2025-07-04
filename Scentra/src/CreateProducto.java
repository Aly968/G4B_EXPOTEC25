import java.sql.*;
import javax.swing.JOptionPane;

public class CreateProducto {
    //atributos
    public boolean crearProducto(int idProductos, int idEmpresa, String nombre, String descripcion,
                                 int cantidadDisponible, double costoProduccion, double precioVenta,
                                 Timestamp fechaProduccion, Timestamp ultimaActualizacionProd) {
       //creación de conexión
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();
         //para ejecutar la consulta de sql
        PreparedStatement ps = null;
           // verificación si la consulta fue ejecutada correctamente
        boolean creado = false;
//        si hay alguna conexion
        if (con != null) {
            String sql = "INSERT INTO Productos_Terminados (idProducto, idEmpresa, nombre, descripcion, cantidad_disponible, costo_produccion, precio_venta, fecha_produccion, ultima_actualizacion_prod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
          //try-catch
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, idProductos);
                ps.setInt(2, idEmpresa);
                ps.setString(3, nombre);
                ps.setString(4, descripcion);
                ps.setInt(5, cantidadDisponible);
                ps.setDouble(6, costoProduccion);
                ps.setDouble(7, precioVenta);
                ps.setTimestamp(8, fechaProduccion);
                ps.setTimestamp(9, ultimaActualizacionProd);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    creado = true;
                    JOptionPane.showMessageDialog(null, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al crear Producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } 
             //cierre de conexion para evitar errores
            finally {
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