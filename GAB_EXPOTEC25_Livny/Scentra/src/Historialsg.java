import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Historialsg {
    Conexion conn;
    public Historialsg() {
       conn = new Conexion();
    }
    public DefaultTableModel obtenerlosmovimientos(){
    DefaultTableModel mdl = new DefaultTableModel();
    mdl.addColumn("IDMovimiento");
    mdl.addColumn("Tipo Movimiento");
    mdl.addColumn("Tipo Item");
    mdl.addColumn("Nombre Item");
    mdl.addColumn("Cantidad");
    mdl.addColumn("Fecha Movimiento");
    mdl.addColumn("Usuario");
    mdl.addColumn("Costo Unitario");
    mdl.addColumn("Costo Total");
    mdl.addColumn("Comentarios");
    String muchotexto = "Select idMovimiento, Tipo_movimiento, Tipo_Item, Case When mi.Tipo_Item = 'Materia_Prima' THEN mp.Nombre When mi.Tipo_Item = 'Productos_Terminados' Then pt.Nombre When mi.Tipo_Item = 'Material_Empaque' then me.Nombre end as Nombre_Item, mi.Cantidad, mi.FechaMovimiento, u.Nombre as Nombre_Usuario, mi.Costo_Unitario, mi.Costo_Total, mi.Comentarios_detalles from Movimientos_Inventario mi Left Join Materia_Prima mp On mi.idMateriaPrima_FK = mp.idMateria_Prima Left Join Productos_Terminados pt on mi.idProductoTerminado_FK = pt.idProductos Left Join Material_empaque me On mi.idMaterialEmpaque_FK = me.idEmpaque Join Usuarios u On mi.idUsuario = u.idUsuarios Order by mi.FechaMovimiento DESC";
    try(Connection connection = conn.getConnection(); Statement esta = connection.createStatement(); ResultSet rs = esta.executeQuery(muchotexto)){
    while(rs.next()){
    Object[] fc = new Object[10];
    fc[0] = rs.getInt("idMovimiento");
    fc[1] = rs.getString("Tipo_movimiento");
    fc[2] = rs.getString("Tipo_Item");
    fc[3] = rs.getString("Nombre_Item");
    fc[4] = rs.getInt("Cantidad");
    fc[5] = rs.getTimestamp("FechaMovimiento");
    fc[6] = rs.getString("Nombre_Usuario");
    //se podria usar float o double pero según tengo entiendido bigdecimal es mejor para cosas monetarias
    fc[7] = rs.getBigDecimal("Costo_Unitario");
    fc[8] = rs.getBigDecimal("Costo_Total");
    fc[9] = rs.getString("Comentarios_detalles");
    mdl.addRow(fc);
    }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error al cargar datos");
        e.printStackTrace();
    }
    return mdl;
    }
}
