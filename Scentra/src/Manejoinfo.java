
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Manejoinfo {

    public static class Infoempresa {

        public int idEmpresa;
        public String nombreEmpresa;
        public String tipoIndustria;
        public String inicialesEmpresa;
        public String logodirr;
        public String colorIndustria;

        public Infoempresa(int idEmpresa, String nombreEmpresa, String tipoIndustria, String inicialesEmpresa, String logodirr, String colorIndustria) {
            this.idEmpresa = idEmpresa;
            this.nombreEmpresa = nombreEmpresa;
            this.tipoIndustria = tipoIndustria;
            this.inicialesEmpresa = inicialesEmpresa;
            this.logodirr = logodirr;
            this.colorIndustria = colorIndustria;
        }

    }

    public static class perfilus {

        public int idUsuario;
        public String nombreUsuario;
        public String rol;
        public String contrasena;
        public int idEmpresa;
        public String estadoUnionEmpresa;

        public perfilus(int idUsuario, String nombreUsuario, String rol, String contrasena, int idEmpresa, String estadoUnionEmpresa) {
            this.idUsuario = idUsuario;
            this.nombreUsuario = nombreUsuario;
            this.rol = rol;
            this.contrasena = contrasena;
            this.idEmpresa = idEmpresa;
            this.estadoUnionEmpresa = estadoUnionEmpresa;
        }
    }

    public static Infoempresa getInfoempr(int idEmpresa) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Infoempresa empr = null;
        try {
            Conexion obcon = new Conexion();
            conn = obcon.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener conexión");
                return null;
            }
            String consul = "SELECT Nombre, Tipo, Color, Iniciales, Logo FROM Empresa WHERE idEmpresa = ?";
            stmt = conn.prepareStatement(consul);
            stmt.setInt(1, idEmpresa);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("Nombre");
                String tipo = rs.getString("Tipo");
                String color = rs.getString("Color");
                String iniciales = rs.getString("Iniciales");
                String logodirr = rs.getString("Logo");
                empr = new Infoempresa(idEmpresa, nombre, tipo, iniciales, logodirr, color);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener información de la empresa");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
        }
        return empr;
    }

    public static perfilus obtenerperfil(String usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        perfilus user = null;
        try {
            Conexion obcon = new Conexion();
            conn = obcon.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener conexión");
                return null;
            }
            String sql = "SELECT idUsuarios, Nombre, Rol, Contrasena, idEmpresa, Estado_Union_Empresa FROM Usuarios WHERE Nombre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idUsuario = rs.getInt("idUsuarios");
                String nombre = rs.getString("Nombre");
                String rol = rs.getString("Rol");
                String contrasena = rs.getString("Contrasena");
                int idEmpresa = rs.getInt("idEmpresa");
                String estadoUnionEmpresa = rs.getString("Estado_Union_Empresa");
                user = new perfilus(idUsuario, nombre, rol, contrasena, idEmpresa, estadoUnionEmpresa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener perfil del usuario");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
        }
        return user;
    }

    public static List<String> obttipos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> tipos = new ArrayList<>();
        try {
            Conexion obcon = new Conexion();
            conn = obcon.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener conexión");
                return tipos;
            }
            String sql = "SELECT DISTINCT Tipo FROM Empresa";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tipos.add(rs.getString("Tipo"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener tipos de industrias");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
        }
        return tipos;
    }
    public static List<String> obtcolores() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> colores = new ArrayList<>();
        try {
            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener conexión");
                return colores;
            }
            String sql = "SELECT DISTINCT Color FROM Empresa";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                colores.add(rs.getString("Color"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener colores");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
        }
        return colores;
    }
    public static List<String> obtroles() {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> roles = new ArrayList<>();
        try {
            Conexion objConexion = new Conexion();
            conn = objConexion.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo obtener conexión");
                return roles;
            }
            String sql = "SELECT DISTINCT Rol FROM Usuarios";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                roles.add(rs.getString("Rol"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener roles");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar");
                e.printStackTrace();
            }
        }
        return roles;
    }
}
