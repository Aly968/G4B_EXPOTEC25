
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

public class Configuracion extends JFrame implements ActionListener {
//Gabriel Ricardo Rodriguez de León
    //Crea los componentes del menú
    JPanel menuItemsPanel;
    JButton btnDashboard;
    JButton btnInventario;
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
    //final

    //componentes de la clase
    JTextField Iniciales;
    JTextField Nombreempresa;
    JTextField Nombreusuario;
    JTextField Contraseña;
    JLabel pR;
    JPanel infemp;
    JPanel infpe;
    JPanel Logoa;
    JButton actLog;
    JButton Actempr;
    JButton Actper;
    JComboBox CmbRol;
    JComboBox CmbColorIndustria;
    JComboBox CmbTipoIndustria;
    JSeparator linea;
    JLabel Titulo;
    JLabel infempr;
    JLabel infper;
    JLabel actlogo;
    JLabel Nombreemp;
    JLabel Inicem;
    JLabel Coloremp;
    JLabel Tipoempr;
    JLabel Nombreusu;
    JLabel Contrasenaus;
    JLabel Rolus;
    //Fuentes de texto
    Font b = new Font("Segoe UI", Font.BOLD, 50);
    JPanel lateral;//Panel
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    //subtitulos
    Font s = new Font("Segoe UI", Font.BOLD, 24);
    Font mini = new Font("Segoe UI", Font.BOLD, 12);

//Constructor principal de la clase que crea el Frame
    public Configuracion() {
        this.setResizable(false);
        this.getContentPane().setBackground(Color.decode("#F7F8FC"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1366, 768);
        obj();
        agr();
        posicionar();
        configurarMenuHamburguesa();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    //Agrega componenetes
    public void agr() {

        this.add(lateral);
        this.add(Titulo);
        this.add(linea);
        this.add(infemp);
        this.add(infpe);
        this.add(Logoa);
        //inicio  //menu hamburguesa
        lateral.add(menuItemsPanel);
        menuItemsPanel.add(Box.createVerticalStrut(20));
        menuItemsPanel.add(btnDashboard);
        menuItemsPanel.add(Box.createVerticalStrut(5));
        menuItemsPanel.add(btnInventario);
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
        //final
        infpe.add(Actper);
        infpe.add(infper);
        infemp.add(Actempr);
        infemp.add(infempr);
        Logoa.add(actlogo);
        Logoa.add(actLog);
        Logoa.add(pR);
        infemp.add(Nombreempresa);
        infemp.add(Iniciales);
        infemp.add(CmbColorIndustria);
        infemp.add(CmbTipoIndustria);
        infpe.add(CmbRol);
        infpe.add(Nombreusuario);
        infpe.add(Contraseña);
        infemp.add(Nombreemp);
        infemp.add(Inicem);
        infemp.add(Coloremp);
        infemp.add(Tipoempr);
        infpe.add(Nombreusu);
        infpe.add(Contrasenaus);
        infpe.add(Rolus);
    }

    //posiciona componentes
    public void posicionar() {
        lateral.setBounds(0, 0, ANCHO_MINIMO_MENU, this.getHeight());
        Titulo.setBounds(120, 25, 600, 70);
        linea.setBounds(120, 95, 1190, 25);

        Logoa.setBounds(930, 110, 400, 400);
        infemp.setBounds(170, 110, 750, 340);
        infpe.setBounds(170, 460, 750, 250);
        Actempr.setBounds(260, 290, 200, 35);
        Actper.setBounds(260, 200, 200, 35);
        infempr.setBounds(230, 10, 330, 30);
        infper.setBounds(240, 10, 330, 30);
        actlogo.setBounds(130, 10, 200, 30);
        actLog.setBounds(110, 350, 200, 35);
        Nombreempresa.setBounds(70, 75, 240, 25);
        CmbTipoIndustria.setBounds(400, 75, 240, 25);
        CmbColorIndustria.setBounds(70, 200, 240, 25);
        Iniciales.setBounds(400, 200, 240, 25);
        Nombreemp.setBounds(70, 50, 240, 25);
        Tipoempr.setBounds(400, 50, 240, 25);
        Coloremp.setBounds(70, 180, 240, 25);
        Inicem.setBounds(400, 180, 240, 25);
        Nombreusuario.setBounds(70, 75, 240, 25);
        CmbRol.setBounds(400, 75, 240, 25);
        Contraseña.setBounds(70, 150, 240, 25);
        // labels
        Nombreusu.setBounds(70, 50, 240, 25);
        Contrasenaus.setBounds(400, 50, 240, 25);
        Rolus.setBounds(70, 125, 240, 25);
        pR2.setBounds(10, 20, ANCHO_MINIMO_MENU - 20, LOGO_CONTRAIDO_ALTO);
        Actualizartamañoicono();
        menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                ANCHO_MAXIMO_MENU,
                this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));
    }

//llama a los metodos que crean componentes
    public void obj() {
        lbl();
        pnl();
        Btn();
        separador();
        cmb();
        img();
        txt();
        Linea();
    }

//crea separadores de texto
    public void separador() {
        linea = new JSeparator();
        linea.setBackground(Color.decode("#B4A8AA"));
    }
//crea TextField

    public void txt() {
        Iniciales = new JTextField();
        Nombreempresa = new JTextField();
        Nombreusuario = new JTextField();
        Contraseña = new JTextField();
    }
//Separador de texto

    public void Linea() {
        separatorMenu = new JSeparator(JSeparator.HORIZONTAL);
        separatorMenu.setForeground(Color.decode("#B4A8AA"));
        separatorMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    }
//Crea comboBox

    public void cmb() {
        CmbColorIndustria = new JComboBox();
        CmbColorIndustria.setBackground(Color.decode("#FFFFFF"));
        CmbColorIndustria.setForeground(Color.BLACK);
        CmbColorIndustria.setFont(bt);
        CmbColorIndustria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CmbColorIndustria.setOpaque(false);
        CmbTipoIndustria = new JComboBox();
        CmbTipoIndustria.setBackground(Color.decode("#FFFFFF"));
        CmbTipoIndustria.setForeground(Color.BLACK);
        CmbTipoIndustria.setFont(bt);
        CmbTipoIndustria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CmbTipoIndustria.setOpaque(false);
        CmbRol = new JComboBox();
        CmbRol.setBackground(Color.decode("#FFFFFF"));
        CmbRol.setForeground(Color.BLACK);
        CmbRol.setFont(bt);
        CmbRol.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CmbRol.setOpaque(false);
        CmbRol.setEnabled(false);
    }
//Crea labels

    public void lbl() {
        pR2 = new JLabel();
        pR2.setHorizontalAlignment(SwingConstants.CENTER);
        pR2.setVerticalAlignment(SwingConstants.CENTER);
        Titulo = new JLabel("Configuración");
        Titulo.setFont(b);
        Titulo.setForeground(Color.decode("#6A7793"));
        infempr = new JLabel("Información de la empresa");
        infempr.setFont(s);
        infempr.setForeground(Color.decode("#6A7793"));
        infper = new JLabel("Información personal");
        infper.setFont(s);
        infper.setForeground(Color.decode("#6A7793"));
        actlogo = new JLabel("Actualizar logo");
        actlogo.setFont(s);
        actlogo.setForeground(Color.decode("#6A7793"));
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
        Nombreusu = new JLabel("Usuario");
        Nombreusu.setFont(mini);
        Nombreusu.setForeground(Color.decode("#6A7793"));
        Contrasenaus = new JLabel("Contraseña");
        Contrasenaus.setFont(mini);
        Contrasenaus.setForeground(Color.decode("#6A7793"));
        Rolus = new JLabel("Rol del usuario");
        Rolus.setFont(mini);
        Rolus.setForeground(Color.decode("#6A7793"));
    }
//Crea botones

    public void Btn() {
        Actper = new JButton("Guardar cambios");
        Actper.setBackground(Color.decode("#6A7793"));
        Actper.setForeground(Color.white);
        Actper.setFont(bt);
        Actempr = new JButton("Guardar cambios");
        Actempr.setBackground(Color.decode("#6A7793"));
        Actempr.setForeground(Color.white);
        Actempr.setFont(bt);
        actLog = new JButton("Actualizar");
        actLog.setBackground(Color.decode("#6A7793"));
        actLog.setForeground(Color.white);
        actLog.setFont(bt);
        forma();
        //inicio de asignacion de botones de menú
       btnDashboard = createMenuItem("Dashboard", "panel-de-control.png");
        btnInventario = createMenuItem("Inventario", "alt-de-inventario.png");
        btnHistorial = createMenuItem("Historial", "calendario-reloj.png");
        btnConfiguracion = createMenuItem("Configuración", "alt-administrador.png");
        btnEmpleados = createMenuItem("Empleados", "empleados.png");
        btnCerrarSesion = createMenuItem("Cerrar Sesión", "cierre-de-sesion-de-usuario.png");
        //final
    }

    //creacion de panel lateral o menu y otros
    public void pnl() {
        lateral = new JPanel();
        lateral.setBackground(Color.decode("#CFD1E0"));
        lateral.setLayout(null);
        infemp = new JPanel();
        infpe = new JPanel();
        Logoa = new JPanel();
        menuItemsPanel = new JPanel();
        infpe.setBackground(Color.white);
        Logoa.setBackground(Color.white);
        infemp.setBackground(Color.white);
        menuItemsPanel.setBackground(Color.decode("#CFD1E0"));
        infpe.setLayout(null);
        infemp.setLayout(null);
        Logoa.setLayout(null);
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    }
    //Maneja la forma redondeada de los componentes 

    public void forma() {
        Actempr.setFocusPainted(false);
        Actempr.setBorderPainted(false);
        Actempr.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Actempr.setOpaque(false);
        Actempr.setContentAreaFilled(false);
        Actempr.addActionListener(this);
        Actempr.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Actempr.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
        Actper.setFocusPainted(false);
        Actper.setBorderPainted(false);
        Actper.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Actper.setOpaque(false);
        Actper.setContentAreaFilled(false);
        Actper.addActionListener(this);
        Actper.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Actempr.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
        actLog.setFocusPainted(false);
        actLog.setBorderPainted(false);
        actLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actLog.setOpaque(false);
        actLog.setContentAreaFilled(false);
        actLog.addActionListener(this);
        actLog.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(actLog.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
    }
//maneja agregar logo a label y creacion del mismo

    public void img() {
        pR = new JLabel();
        pR.setBounds(75, -20, 280, 360);
        ImageIcon usu = new ImageIcon("logo.png");
        pR.setIcon(new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH)));
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
                            Configuracion.this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

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
        if (button == btnInventario) {
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

    //crea un objeto del tipo configuracion
    public static void main(String[] args) {
        new Configuracion();
    }

    //metodo que se ejecuta al presionar botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDashboard) {
            new Dashboard();
            this.dispose();
            if (menuExpandido) {
                toggleMenu(); // Contraer al hacer clic
            }
        } else if (e.getSource() == btnInventario) {
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
