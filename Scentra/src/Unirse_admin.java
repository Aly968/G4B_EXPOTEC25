
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

//Gabriel Ricardo Rodriguez de León
//a este apartado aún me falta agregarle más pero hare el día miercoles a más tardar, estara listo para la fase 3
public class Unirse_admin extends JFrame implements ActionListener {

    JPanel menuItemsPanel;
    JButton btnUnirseEmpresa;
    private boolean menuExpandido = false;
    private final int ANCHO_MINIMO_MENU = 70;
    private final int ANCHO_MAXIMO_MENU = 250;
    private Timer animacionTimer;
    private final int VELOCIDAD_ANIMACION = 5;
    private final int PASO_ANIMACION = 15;
    private ImageIcon originalLogoIcon;
    private final int LOGO_CONTRAIDO_ALTO = 60;
    private final int LOGO_EXPANDIDO_ALTO = 100;
    JLabel pR2;
    JPanel lateral;
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    JSeparator separatorMenu;
    JLabel Titulo;
    JSeparator linea;
    Font b = new Font("Segoe UI", Font.BOLD, 50);
    //de aqui en adelante se trabaja lo de unirse a una empresa
    JPanel unirsepnl;
    JTextField cod;
    JButton btnunirse;
    JSeparator lineaaboton;
    JSeparator lineaunirse;
    JButton X;
    Font sb = new Font("Segoe UI", Font.BOLD, 30);
    JLabel subunir;
    private Connection conn;
    private int idUsuario;

    JTextField Iniciales;
    JTextField Nombreempresa;
    JTextField CodigoEmp;
    JLabel Nombreemp;
    JLabel Inicem;
    JLabel Coloremp;
    JLabel Tipoempr;
    JLabel Codigo;
    JComboBox CmbColorIndustria;
    JComboBox CmbTipoIndustria;

    //subtitulos
    Font s = new Font("Segoe UI", Font.BOLD, 24);
    Font mini = new Font("Segoe UI", Font.BOLD, 12);

    public Unirse_admin(int idUsuario, Connection conn) {
        this.idUsuario = idUsuario;
        this.conn = conn;
        this.setResizable(false);
        this.getContentPane().setBackground(Color.decode("#F7F8FC"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1366, 768);
        obj();
        agr();
        posicionar();
        configurarMenuHamburguesa();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.idUsuario = idUsuario;
        this.conn = conn;

    }

    public void obj() {
        lbl();
        pnl();
        Btn();
        img();
        Linea();
        separador();
        txt();
        cmb();
    }

    public void cmb() {
        CmbColorIndustria = new JComboBox();
        CmbColorIndustria.setBackground(Color.decode("#FFFFFF"));
        CmbColorIndustria.setForeground(Color.BLACK);
        CmbColorIndustria.setFont(bt);
        CmbColorIndustria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CmbColorIndustria.setOpaque(false);
        CmbColorIndustria.addItem("Lavanda");
        CmbColorIndustria.addItem("Blanco");
        CmbColorIndustria.addItem("Verde");
        CmbColorIndustria.addItem("Pino");
        CmbColorIndustria.addItem("Amarillo");

        CmbTipoIndustria = new JComboBox();
        CmbTipoIndustria.setBackground(Color.decode("#FFFFFF"));
        CmbTipoIndustria.setForeground(Color.BLACK);
        CmbTipoIndustria.setFont(bt);
        CmbTipoIndustria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CmbTipoIndustria.setOpaque(false);
        CmbTipoIndustria.addItem("Inactiva");
        CmbTipoIndustria.addItem("Fabricación");
        CmbTipoIndustria.addItem("Proveedores");
        CmbTipoIndustria.addItem("Empaque");
        CmbTipoIndustria.addItem("Distribución");

    }

    public void lbl() {
        pR2 = new JLabel();
        pR2.setHorizontalAlignment(SwingConstants.CENTER);
        pR2.setVerticalAlignment(SwingConstants.CENTER);
        Titulo = new JLabel("Crear empresa");
        Titulo.setFont(b);
        Titulo.setForeground(Color.decode("#6A7793"));
        subunir = new JLabel("Información sobre empresa");
        subunir.setForeground(Color.decode("#6A7793"));
        subunir.setFont(sb);

        Nombreemp = new JLabel("Nombre de la empresa");
        Nombreemp.setFont(mini);
        Nombreemp.setForeground(Color.decode("#6A7793"));
        Inicem = new JLabel("Iniciales de la empresa");
        Inicem.setFont(mini);
        Inicem.setForeground(Color.decode("#6A7793"));
        Coloremp = new JLabel("Color de la empresa");
        Coloremp.setFont(mini);
        Coloremp.setForeground(Color.decode("#6A7793"));
        Tipoempr = new JLabel("Tipo de empresa");
        Tipoempr.setFont(mini);
        Tipoempr.setForeground(Color.decode("#6A7793"));
        Codigo = new JLabel("Codigo de empresa");
        Codigo.setFont(mini);
        Codigo.setForeground(Color.decode("#6A7793"));

    }

    public void Btn() {
        btnUnirseEmpresa = createMenuItem("Crear empresa", "capa-mas.png");
        btnunirse = new JButton("Registrar");
        btnunirse.setBackground(Color.decode("#6A7793"));
        btnunirse.setFont(bt);
        btnunirse.setForeground(Color.decode("#FFFFFF"));
        X = new JButton("x");
        X.setBackground(Color.decode("#FFFFFF"));
        X.setForeground(Color.gray);
        X.setBorder(null);
        X.addActionListener(this);
        forma();

    }

    public void img() {
        try {
            originalLogoIcon = new ImageIcon("logo.png");
        } catch (Exception e) {
            System.err.println("Error al cargar 'logo.png': " + e.getMessage());
            originalLogoIcon = new ImageIcon();
        }
    }

    public void Linea() {
        separatorMenu = new JSeparator(JSeparator.HORIZONTAL);
        separatorMenu.setForeground(Color.decode("#B4A8AA"));
        separatorMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    }

    public void separador() {
        linea = new JSeparator();
        linea.setBackground(Color.decode("#B4A8AA"));
        lineaaboton = new JSeparator();
        lineaunirse = new JSeparator();
        lineaaboton.setBackground(Color.decode("#586875"));
        lineaunirse.setBackground(Color.decode("#586875"));
    }

    public void posicionar() {
        lateral.setBounds(0, 0, ANCHO_MINIMO_MENU, this.getHeight());
        pR2.setBounds(10, 20, ANCHO_MINIMO_MENU - 20, LOGO_CONTRAIDO_ALTO);
        Actualizartamañoicono();
        menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                ANCHO_MAXIMO_MENU,
                this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));
        Titulo.setBounds(140, 25, 600, 70);
        linea.setBounds(140, 95, 1200, 25);
        unirsepnl.setBounds(400, 130, 560, 560);
        lineaaboton.setBounds(50, 470, 460, 30);
        lineaunirse.setBounds(50, 75, 460, 30);
        btnunirse.setBounds(170, 490, 200, 35);
        X.setBounds(500, 0, 50, 30);
        subunir.setBounds(90, 20, 440, 50);
//        cod.setBounds(50, 125, 440, 30);
        Nombreempresa.setBounds(50, 110, 460, 30);
        Nombreemp.setBounds(50, 140, 440, 30);

        Iniciales.setBounds(50, 190, 220, 30);
        Inicem.setBounds(50, 225, 430, 30);

        CodigoEmp.setBounds(290, 190, 220, 30);
        Codigo.setBounds(290, 225, 440, 30);

        CmbColorIndustria.setBounds(50, 285, 460, 30);
        Coloremp.setBounds(50, 315, 440, 30);

        CmbTipoIndustria.setBounds(50, 370, 460, 30);
        Tipoempr.setBounds(50, 400, 460, 30);

//    JTextField Iniciales;
//    JTextField Nombreempresa;
//    JTextField CodigoEmp;
//    JLabel Nombreemp;
//    JLabel Inicem;
//    JLabel Coloremp;
//    JLabel Tipoempr;
//    JLabel Codigo;
//    JComboBox CmbColorIndustria;
//    JComboBox CmbTipoIndustria;
    }

    private void Actualizartamañoicono() {
        if (originalLogoIcon != null) {
            int labelWidth = pR2.getWidth();
            int labelHeight = pR2.getHeight();

            if (labelWidth > 0 && labelHeight > 0) {
                Image originalImage = originalLogoIcon.getImage();
                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);

                double scaleX = (double) labelWidth / originalWidth;
                double scaleY = (double) labelHeight / originalHeight;
                double scale = Math.min(scaleX, scaleY);

                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                pR2.setIcon(new ImageIcon(scaledImage));
                pR2.setText("");
            }
        }
    }

    public void forma() {
        btnunirse.setFocusPainted(false);
        btnunirse.setBorderPainted(false);
        btnunirse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnunirse.setOpaque(false);
        btnunirse.setContentAreaFilled(false);
        btnunirse.addActionListener(this);
        btnunirse.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(btnunirse.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });

    }

    public void pnl() {
        lateral = new JPanel();
        lateral.setBackground(Color.decode("#CFD1E0"));
        lateral.setLayout(null);
        menuItemsPanel = new JPanel();
        menuItemsPanel.setBackground(Color.decode("#CFD1E0"));
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        unirsepnl = new JPanel();
        unirsepnl.setBackground(Color.decode("#FFFFFF"));
        unirsepnl.setVisible(false);
        unirsepnl.setLayout(null);
    }

    public void agr() {
        this.add(lateral);
        this.add(Titulo);
        this.add(linea);
        lateral.add(menuItemsPanel);
        menuItemsPanel.add(Box.createVerticalStrut(20));
        menuItemsPanel.add(btnUnirseEmpresa);
        menuItemsPanel.add(Box.createVerticalGlue());
        setMenuItemsVisibility(false);
        this.add(unirsepnl);
        lateral.add(pR2);
        unirsepnl.add(lineaunirse);
        unirsepnl.add(lineaaboton);
        unirsepnl.add(X);
        unirsepnl.add(subunir);
        unirsepnl.add(cod);
        unirsepnl.add(btnunirse);
        unirsepnl.add(Iniciales);
        unirsepnl.add(Nombreempresa);
        unirsepnl.add(CodigoEmp);
        unirsepnl.add(Nombreemp);
        unirsepnl.add(Inicem);
        unirsepnl.add(Coloremp);
        unirsepnl.add(Tipoempr);
        unirsepnl.add(Codigo);
        unirsepnl.add(CmbColorIndustria);
        unirsepnl.add(CmbTipoIndustria);

//    JTextField Iniciales;
//    JTextField Nombreempresa;
//    JTextField CodigoEmp;
//    JLabel Nombreemp;
//    JLabel Inicem;
//    JLabel Coloremp;
//    JLabel Tipoempr;
//    JLabel Codigo;
//    JComboBox CmbColorIndustria;
//    JComboBox CmbTipoIndustria;
    }

    public void txt() {
        cod = new JTextField();
        Iniciales = new JTextField();
        Nombreempresa = new JTextField();
        CodigoEmp = new JTextField();
    }

    private void configurarMenuHamburguesa() {
        pR2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMenu();
            }
        });
        animacionTimer = new Timer(VELOCIDAD_ANIMACION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentWidth = lateral.getWidth();
                int targetWidth = menuExpandido ? ANCHO_MAXIMO_MENU : ANCHO_MINIMO_MENU;

                if (currentWidth != targetWidth) {
                    int step = (targetWidth > currentWidth) ? PASO_ANIMACION : -PASO_ANIMACION;
                    int newWidth = currentWidth + step;

                    if ((step > 0 && newWidth > targetWidth) || (step < 0 && newWidth < targetWidth)) {
                        newWidth = targetWidth;
                    }

                    lateral.setSize(newWidth, lateral.getHeight());

                    int targetPR2Height = menuExpandido ? LOGO_EXPANDIDO_ALTO : LOGO_CONTRAIDO_ALTO;
                    pR2.setBounds(10, 20, newWidth - 20, targetPR2Height);

                    menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                            newWidth,
                            Unirse_admin.this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

                    Actualizartamañoicono();

                    lateral.revalidate();
                    lateral.repaint();

                } else {
                    animacionTimer.stop();
                    setMenuItemsVisibility(menuExpandido);
                }
            }
        });
    }

    private JButton createMenuItem(String text, String iconPath) {
        JButton menuItem = new JButton("  " + text);
        menuItem.setFont(bt);
        menuItem.setForeground(Color.decode("#3C4043"));
        menuItem.setBackground(Color.decode("#CFD1E0"));
        menuItem.setOpaque(true);
        menuItem.setBorderPainted(false);
        menuItem.setFocusPainted(false);
        menuItem.setHorizontalAlignment(SwingConstants.LEFT);
        menuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuItem.setMaximumSize(new Dimension(ANCHO_MAXIMO_MENU, 40));
        menuItem.setAlignmentX(Component.LEFT_ALIGNMENT);

        try {
            ImageIcon originalIcon = new ImageIcon(iconPath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            menuItem.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Error al cargar el icono para " + text + ": " + iconPath + " - " + e.getMessage());
            if (originalLogoIcon != null && originalLogoIcon.getImage() != null) {
                menuItem.setIcon(new ImageIcon(originalLogoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            }
        }

        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(Color.decode("#E0E2E7"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(Color.decode("#CFD1E0"));
            }
        });

        menuItem.addActionListener(this);
        return menuItem;
    }

    private void setMenuItemsVisibility(boolean visible) {
        if (visible) {
            pR2.setBounds(10, 20, ANCHO_MAXIMO_MENU - 20, LOGO_EXPANDIDO_ALTO);
            Actualizartamañoicono();

            menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                    ANCHO_MAXIMO_MENU,
                    this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

            for (Component comp : menuItemsPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    button.setText("  " + getOriginalButtonText(button));
                }
            }
            separatorMenu.setVisible(false);
        } else {
            pR2.setBounds(10, 20, ANCHO_MINIMO_MENU - 20, LOGO_CONTRAIDO_ALTO);
            Actualizartamañoicono();

            menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                    ANCHO_MINIMO_MENU,
                    this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

            for (Component comp : menuItemsPanel.getComponents()) {
                if (comp instanceof JButton) {
                    ((JButton) comp).setText(" ");
                }
            }
            separatorMenu.setVisible(false);
        }
        lateral.revalidate();
        lateral.repaint();
    }

    private String getOriginalButtonText(JButton button) {
        if (button == btnUnirseEmpresa) {
            return "Crear empresa";
        }
        return "";
    }

    private void toggleMenu() {
        menuExpandido = !menuExpandido;
        if (!menuExpandido) {
            setMenuItemsVisibility(false);
        }
        animacionTimer.start();
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnUnirseEmpresa) {
            unirsepnl.setVisible(true);
        }
        if (e.getSource() == X) {
            unirsepnl.setVisible(false);
        }
        if (e.getSource() == btnunirse) {
            finalizarRegistro();
        }

    }

    private void finalizarRegistro() {

        String nombre = Nombreempresa.getText().trim();
        String code = Codigo.getText().trim();
        String inicial = Iniciales.getText().trim();
        String color = (String) CmbColorIndustria.getSelectedItem();//Convierte a string
        String tipo = (String) CmbTipoIndustria.getSelectedItem();//Convierte a string

        if (nombre.isEmpty() || code.isEmpty() || inicial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese todos los datos para completar el registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        PreparedStatement stmt = null;
        PreparedStatement stmtU = null;
        ResultSet rs = null;

        try {
            
            int IdEmp = 1000;
            try (Statement st = conn.createStatement()) {
                rs = st.executeQuery("SELECT MAX(idEmpresa) FROM Empresa");
                if (rs.next()) {
                    int Id = rs.getInt(1);
                    if (Id >= 1000) {
                        IdEmp = Id + 1;
                    }
                }
                rs.close();
            }

            String sqlI = "INSERT INTO Empresa (idEmpresa,Nombre,Tipo,Color,Iniciales,Logo,Codigo_Empresa) VALUES (?,?,?,?,?, 'logo.png',?)";
            stmt = conn.prepareStatement(sqlI);
            stmt.setInt(1, IdEmp);
            stmt.setString(2, nombre);
            stmt.setString(3, tipo);
            stmt.setString(4, color);
            stmt.setString(5, inicial);
            stmt.setString(6, code);
            stmt.executeUpdate();

            String sqlUp = "UPDATE Usuarios SET idEmpresa = ?, Estado_Union_Empresa = 'Activo' WHERE idUsuarios = ?";
            stmtU = conn.prepareStatement(sqlUp);
            stmtU.setInt(1, IdEmp);
            stmtU.setInt(2, idUsuario);
            int rowsUp = stmtU.executeUpdate();

            if (rowsUp > 0) {
                conn.commit();
                JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                new Inicio_de_sesion().setVisible(true);
                this.dispose();
            } else {
                throw new SQLException("No se actualizó el usuario.");
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this, "Error al finalizar registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        } finally {
            try {
                if (rs != null || stmt != null || stmtU != null || conn != null) {
                    rs.close();
                    stmt.close();
                    stmtU.close();
                    conn.setAutoCommit(true);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
