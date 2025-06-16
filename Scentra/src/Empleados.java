
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//Alison Del Rosario Vicente Coroy
public class Empleados extends JFrame implements ActionListener {

    //inicio
    JPanel menuItemsPanel;
    JButton btnDashboard;
    JButton btnInventarios;
    JButton btnHistorial;
    JButton btnConfiguracion;
    JButton btnEmpleados;
    JButton btnCerrarSesion;
    private boolean menuExpandido = false;
    private final int ANCHO_MINIMO_MENU = 70;
    private final int ANCHO_MAXIMO_MENU = 250;
    private Timer animacionTimer;
    private final int VELOCIDAD_ANIMACION = 5;
    private final int PASO_ANIMACION = 15;
    private ImageIcon originalLogoIcon;
    private final int LOGO_CONTRAIDO_ALTO = 60;
    private final int LOGO_EXPANDIDO_ALTO = 100;
    JSeparator separatorMenu;
    JLabel pR2;
    JPanel lateral;
    //final
    JSeparator linea;
    JButton btnDatos;
    JLabel lbltitulo;
    JSeparator lineaTitulo;
    private JTextField txtNombreCompleto;
    private JTextField txtID;
    private JTextField txtEmpresa;
    private JTextField txtCargo;
    private JTextField txtSalario;
    private JTextField txtFechaIngreso;
    private JTable tablaEmpleados;
    private DefaultTableModel mdTabla;
    private JScrollPane scTabla;
    private JTableHeader titulos;
    private JPanel pnlTextos;
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    private String rolUsuario;
    private int idEmpresaUsuario;

    public Empleados() {
        this.setResizable(false);
        this.getContentPane().setBackground(Color.decode("#F7F8FC"));
        SesionUsuario sesion = SesionUsuario.getInstance();
                this.rolUsuario = sesion.getRol();
        this.idEmpresaUsuario = sesion.getIdEmpresa();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1366, 768);
        obj();
        agr();
        posicionar();
        configurarMenuHamburguesa();
        if (!"Administrador".equalsIgnoreCase(rolUsuario)) {//equalsIgnoreCase para flexibilidad
            btnEmpleados.setVisible(false);
        }
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Empleados();
    }

    public void posicionar() {
        lateral.setBounds(0, 0, ANCHO_MINIMO_MENU, this.getHeight());

        lbltitulo.setBounds(120, 25, 600, 70);
        lineaTitulo.setBounds(120, 95, 1190, 25);

        pnlTextos.setBounds(520, 150, 350, 245);
        txtNombreCompleto.setBounds(20, 20, 310, 30);
        txtID.setBounds(20, 60, 145, 30);
        txtEmpresa.setBounds(185, 60, 145, 30);
        txtCargo.setBounds(20, 100, 145, 30);
        txtSalario.setBounds(185, 100, 145, 30);
        txtFechaIngreso.setBounds(20, 140, 310, 30);
        linea.setBounds(20, 180, 310, 6);
        btnDatos.setBounds(130, 200, 80, 30);
        scTabla.setBounds(270, 450, 880, 200);

        pR2.setBounds(10, 20, ANCHO_MINIMO_MENU - 20, LOGO_CONTRAIDO_ALTO);
        Actualizartamañoicono();
        menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                ANCHO_MAXIMO_MENU,
                this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));
    }

    public void obj() {
        pnl();
        btn();
        txf();
        linea();
        Tb();
        lbl();
        img();
    }

    public void agr() {
        add(pnlTextos);
        pnlTextos.add(txtNombreCompleto);
        pnlTextos.add(txtID);
        pnlTextos.add(txtEmpresa);
        pnlTextos.add(txtCargo);
        pnlTextos.add(txtSalario);
        pnlTextos.add(txtFechaIngreso);
        pnlTextos.add(btnDatos);
        pnlTextos.add(linea);
        add(lineaTitulo);
        add(lbltitulo);
        add(scTabla);
        btnDatos.addActionListener(this);

        add(lateral);
        //inicio
        lateral.add(menuItemsPanel);
        menuItemsPanel.add(Box.createVerticalStrut(20));
        menuItemsPanel.add(btnDashboard);
        menuItemsPanel.add(Box.createVerticalStrut(5));
        menuItemsPanel.add(btnInventarios);
        menuItemsPanel.add(Box.createVerticalStrut(5));
        menuItemsPanel.add(btnHistorial);
        menuItemsPanel.add(Box.createVerticalStrut(5));
        menuItemsPanel.add(btnConfiguracion);
        menuItemsPanel.add(Box.createVerticalStrut(5));
        menuItemsPanel.add(btnEmpleados);
        menuItemsPanel.add(Box.createVerticalStrut(20));
        menuItemsPanel.add(separatorMenu);
        menuItemsPanel.add(Box.createVerticalStrut(10));
        menuItemsPanel.add(btnCerrarSesion);
        menuItemsPanel.add(Box.createVerticalGlue());
        setMenuItemsVisibility(false);
        lateral.add(pR2);
    }

    public void Tb() {
        String[] columnas = {"ID", "Nombre completo", "Empresa", "Cargo", "Salario", "Fecha de ingreso"};
        mdTabla = new DefaultTableModel(null, columnas);
        tablaEmpleados = new JTable(mdTabla);
        tablaEmpleados.setFillsViewportHeight(true);
        scTabla = new JScrollPane(tablaEmpleados);

        tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaEmpleados.setBackground(new Color(249, 247, 250));
        tablaEmpleados.setForeground(Color.BLACK);
        tablaEmpleados.setRowHeight(28);
        tablaEmpleados.setShowGrid(true);
        tablaEmpleados.setGridColor(Color.BLACK);

        titulos = tablaEmpleados.getTableHeader();
        titulos.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titulos.setBackground(new Color(116, 119, 147));
        titulos.setForeground(Color.WHITE);
        titulos.setReorderingAllowed(false);

        tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(120);
        tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaEmpleados.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaEmpleados.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaEmpleados.getColumnModel().getColumn(4).setPreferredWidth(80);
        tablaEmpleados.getColumnModel().getColumn(5).setPreferredWidth(130);

        // Registros de ejemplo
        Object[] r1 = {"EMP001", "Ana Ramírez", "Google", "Supuervisor", "3500", "2025-01-10"};
        Object[] r2 = {"EMP002", "Luis Torres", "Amazon", "Supervisor ", "3000", "2025-02-15"};

        mdTabla.addRow(r1);
        mdTabla.addRow(r2);
    }

    public void pnl() {
        pnlTextos = new JPanel();
        pnlTextos.setLayout(null);
        pnlTextos.setBackground(new Color(180, 168, 170));
        lateral = new JPanel();
        lateral.setBackground(Color.decode("#CFD1E0"));
        lateral.setLayout(null);
        menuItemsPanel = new JPanel();
        menuItemsPanel.setBackground(Color.decode("#CFD1E0"));

        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    }

    public void txf() {
        txtNombreCompleto = new JTextField(" Nombre completo");
        txtID = new JTextField(" ID");
        txtEmpresa = new JTextField(" Empresa");
        txtCargo = new JTextField(" Cargo");
        txtSalario = new JTextField(" Salario");
        txtFechaIngreso = new JTextField(" Fecha de ingreso");

        diseñotxf(txtNombreCompleto);
        diseñotxf(txtID);
        diseñotxf(txtEmpresa);
        diseñotxf(txtCargo);
        diseñotxf(txtSalario);
        diseñotxf(txtFechaIngreso);
    }

    public void lbl() {
        //Creación de label Dashboard
        lbltitulo = new JLabel("Empleados");
        lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 44));
        lbltitulo.setForeground(new Color(106, 119, 147));

        pR2 = new JLabel();
        pR2.setHorizontalAlignment(SwingConstants.CENTER);
        pR2.setVerticalAlignment(SwingConstants.CENTER);
    }

    public void linea() {
        lineaTitulo = new JSeparator();
        lineaTitulo.setBackground(Color.decode("#B4A8AA"));

        linea = new JSeparator(JSeparator.HORIZONTAL);
        linea.setForeground(Color.decode("#6A7793"));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        separatorMenu = new JSeparator(JSeparator.HORIZONTAL);
        separatorMenu.setForeground(Color.decode("#B4A8AA"));
        separatorMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

    }

    public void btn() {
        btnDatos = new JButton("Añadir");
        diseñobtn(btnDatos);
        //inicio
        btnDashboard = createMenuItem("Dashboard", "panel-de-control.png");
        btnInventarios = createMenuItem("Inventario", "alt-de-inventario.png");
        btnHistorial = createMenuItem("Historial", "calendario-reloj.png");
        btnConfiguracion = createMenuItem("Configuración", "alt-administrador.png");
        btnEmpleados = createMenuItem("Empleados", "empleados.png");
        btnCerrarSesion = createMenuItem("Cerrar Sesión", "cierre-de-sesion-de-usuario.png");
        //final

    }

    public void diseñotxf(JTextField txf) {
        txf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txf.setBackground(new Color(249, 247, 250));
        txf.setForeground(Color.GRAY);
        txf.setBorder(null);
        txf.setCaretColor(Color.BLACK);
    }

    public void diseñobtn(JButton btn) {
        btn.setBackground(new Color(106, 119, 147));
        btn.setForeground(Color.WHITE);
        forma(btn);
    }

    public void forma(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);

        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(boton.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
    }

    public void img() {

        originalLogoIcon = new ImageIcon("logo.png");
        try {
            originalLogoIcon = new ImageIcon("logo.png");
        } catch (Exception e) {
            System.err.println("Error al cargar 'logo.png': " + e.getMessage());
            originalLogoIcon = new ImageIcon();
        }
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
                            Empleados.this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

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
//nueva función

    private JButton createMenuItem(String text, String iconPath) {
        //para mostrar el nombre y icono
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
//metodo,copia y pega

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
            separatorMenu.setVisible(true);
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

    //funcion, copia y pega
    private String getOriginalButtonText(JButton button) {
        if (button == btnDashboard) {
            return "Dashboard";
        }
        if (button == btnInventarios) {
            return "Inventario";
        }
        if (button == btnHistorial) {
            return "Historial";
        }
        if (button == btnConfiguracion) {
            return "Configuración";
        }
        if (button == btnEmpleados) {
            return "Empleados";
        }
        if (button == btnCerrarSesion) {
            return "Cerrar Sesión";
        }
        return "";
    }

    //nuevo metodo
    private void toggleMenu() {
        menuExpandido = !menuExpandido;
        if (!menuExpandido) {
            setMenuItemsVisibility(false);
        }
        animacionTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDashboard) {
            new Dashboard();
            this.dispose();
            if (menuExpandido) {
                toggleMenu(); // Contraer al hacer clic
            }
        } else if (e.getSource() == btnInventarios) {
            new Inventarios();
            this.dispose();
            if (menuExpandido) {
                toggleMenu();
            }
        } else if (e.getSource() == btnHistorial) {
            new Historial();
            this.dispose();
            if (menuExpandido) {
                toggleMenu();
            }
        } else if (e.getSource() == btnConfiguracion) {
            new Configuracion();
            this.dispose();
            if (menuExpandido) {
                toggleMenu();
            }
        } else if (e.getSource() == btnEmpleados) {
            new Empleados();
            this.dispose();
            if (menuExpandido) {
                toggleMenu();
            }
        } else if (e.getSource() == btnCerrarSesion) {
            System.out.println("Cerrar Sesión");
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres cerrar la sesión?", "Confirmar Cierre de Sesión",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new Inicio_de_sesion();
                this.dispose();
            }
        }
    }
}
