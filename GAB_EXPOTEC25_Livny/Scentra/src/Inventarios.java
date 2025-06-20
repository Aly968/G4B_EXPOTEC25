
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
//Alison Del Rosario Vicente Coroy

public class Inventarios extends JFrame implements ActionListener {

    //Gabriel Ricardo Rodriguez de León
    //inicio de creacion de variables de menu hamburguesa
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
    JPanel lateral;
    //final
    //creacion de componentes de la clase
    JLabel lbltitulo;
    JSeparator lineaTitulo;
    JButton btnMaterial;
    JButton btnInventarios;
    JButton btnProductos;
    JButton btnMateria;
    JButton btnAñadir;
    JButton btnEliminar;
    JButton btnActualizar;
    pnlInventario msinv;
    private JTextField txtID;
    //Permite mostrar solo un panel a la vez tipo "tarjetas"
    private CardLayout cardLayout;
    //Panel principal donde se agregar los paneles que serán manejados con CardLayout
    private JPanel panelContenedor;
    //Panel que contiene los botones que permiten cambiar de inventarios y las acciones de los datos(Eliminar,Agregar)
    private JPanel panelTipos;
    private String rolUsuario;
    private int idEmpresaUsuario;
    //Fuente
    Font bt = new Font("Segoe UI", Font.BOLD, 14);

    //Contructor principal de la clase
    public Inventarios() {
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

    //Crea un objeto del tipo Inventarios
    public static void main(String[] args) {
        new Inventarios();
    }

    //Posicionar componentes
    public void posicionar() {
        //Posicion del menú lateral
        lateral.setBounds(0, 0, ANCHO_MINIMO_MENU, this.getHeight());
        //Posición del los titulos del Frame
        lbltitulo.setBounds(120, 25, 600, 70);
        lineaTitulo.setBounds(120, 95, 1190, 25);
        //Posición  y tamaño del panel de tipos de inventario
        panelTipos.setBounds(100, 120, 250, 550);
        //Posición y tamaño del panel principal manejado con CardLayour
        panelContenedor.setBounds(370, 120, 950, 550);
        //Botones que son agregados al PanelTipos
        btnProductos.setBounds(35, 20, 180, 40);
        btnMateria.setBounds(35, 70, 180, 40);
        btnMaterial.setBounds(35, 120, 180, 40);
        btnInventario.setBounds(35, 170, 180, 40);
        txtID.setBounds(50, 270, 150, 30);
        btnAñadir.setBounds(52, 370, 150, 40);
        btnEliminar.setBounds(45, 420, 160, 40);
        btnActualizar.setBounds(40, 470, 170, 40);
        //Posición y tamaño del icono 
        pR2.setBounds(10, 20, ANCHO_MINIMO_MENU - 20, LOGO_CONTRAIDO_ALTO);
        Actualizartamañoicono();
        menuItemsPanel.setBounds(0, pR2.getY() + pR2.getHeight() + 10,
                ANCHO_MAXIMO_MENU,
                this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));
    }

    //Agrega componentes
    public void agr() {
        msinv = new pnlInventario();

        //Agrega componentes al Frame
        add(lbltitulo);
        add(lineaTitulo);
        add(panelContenedor);
        // Agrega cada panel a CardLayout y le asigna un nombre 
        // IMPORTANTE: Se asume que estas clases (pnlProductos, Dashboard, etc.) existen en archivos separados
        // y que han sido compiladas. Si no existen, el código no compilará.
        panelContenedor.add(new pnlProductos(), "Productos");
        panelContenedor.add(new pnlMateria(), "Materia");
        panelContenedor.add(new pnlMaterial(), "Material");
        panelContenedor.add(msinv, "Inventarios");
        //Añade el panel de tipos al Frame
        add(panelTipos);
        //Añade componentes al panel de tipos
        panelTipos.add(btnProductos);
        panelTipos.add(btnMateria);
        panelTipos.add(btnMaterial);
        panelTipos.add(btnInventario);
        panelTipos.add(txtID);
        panelTipos.add(btnAñadir);
        panelTipos.add(btnEliminar);
        panelTipos.add(btnActualizar);
        //Manejo de eventos al presionar un botón dentro de un panel
        btnProductos.addActionListener(this);
        btnMateria.addActionListener(this);
        btnMaterial.addActionListener(this);
        btnInventario.addActionListener(this);
        btnEliminar.addActionListener(this);
        //Añade el menú lateral al Frame y sus componentes
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
        //final
    }

    public void obj() {
        pnl();
        lbl();
        btn();
        txf();
        //        icon();
        linea();
        cardLayout();
        img();
    }

    public void btn() {

        btnProductos = new JButton("Productos Terminados");
        diseñobtn(btnProductos);
        btnMateria = new JButton("Materia Prima");
        diseñobtn(btnMateria);
        btnMaterial = new JButton("Material de Empaque");
        diseñobtn(btnMaterial);
        btnInventario = new JButton("Inventario");
        diseñobtn(btnInventario);
        btnAñadir = new JButton("Añadir objeto");
        diseñobtn(btnAñadir);
        btnEliminar = new JButton("Eliminar objeto");
        diseñobtn(btnEliminar);
        btnActualizar = new JButton("Actualizar objeto");
        diseñobtn(btnActualizar);

        //inicio
        btnDashboard = createMenuItem("Dashboard", "panel-de-control.png");
        btnInventarios = createMenuItem("Inventario", "alt-de-inventario.png");
        btnHistorial = createMenuItem("Historial", "calendario-reloj.png");
        btnConfiguracion = createMenuItem("Configuración", "alt-administrador.png");
        btnEmpleados = createMenuItem("Empleados", "empleados.png");
        btnCerrarSesion = createMenuItem("Cerrar Sesión", "cierre-de-sesion-de-usuario.png");
        //final
    }
    //Creación de labels

    public void lbl() {
        //Creación de label Dashboard
        lbltitulo = new JLabel("Inventario");
        lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 44));
        lbltitulo.setForeground(new Color(106, 119, 147));

        pR2 = new JLabel();
        pR2.setHorizontalAlignment(SwingConstants.CENTER);
        pR2.setVerticalAlignment(SwingConstants.CENTER);
    }

    //Creación de linea Separadora
    public void linea() {
        lineaTitulo = new JSeparator();
        lineaTitulo.setBackground(Color.decode("#B4A8AA"));

        separatorMenu = new JSeparator(JSeparator.HORIZONTAL);
        separatorMenu.setForeground(Color.decode("#B4A8AA"));
        separatorMenu.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    }
    //Creación del paneles

    public void pnl() {
        panelTipos = new JPanel();
        panelTipos.setLayout(null);
        panelTipos.setBackground(new Color(208, 201, 208));

        lateral = new JPanel();
        lateral.setBackground(Color.decode("#CFD1E0"));
        lateral.setLayout(null);
        menuItemsPanel = new JPanel();
        menuItemsPanel.setBackground(Color.decode("#CFD1E0"));

        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    }
    //Creación de CardJayout y asignar la función a Panel Contenedor

    public void cardLayout() {
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
    }

    //Creación de TextField
    public void txf() {

        txtID = new JTextField(" ID");
        txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtID.setBackground(new Color(249, 247, 250));
        txtID.setForeground(Color.GRAY);
        txtID.setBorder(null);
        txtID.setCaretColor(Color.BLACK);

    }

    //metodo que maneja la forma redondeada de los botones
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

    public void diseñobtn(JButton btn) {
        btn.setBackground(new Color(106, 119, 147));
        btn.setForeground(Color.WHITE);
        forma(btn);
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
                            Inventarios.this.getHeight() - (pR2.getY() + pR2.getHeight() + 10));

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
        JButton menuItem = new JButton("  " + text);
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
                    button.setText("  " + getOriginalButtonText(button));
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

        if (e.getSource() == btnProductos) {
            cardLayout.show(panelContenedor, "Productos");//Manda a llamar dentro del PanelContenedor al panel asignado como "Productos"
            lbltitulo.setText("Productos Terminados");
            txtID.setVisible(true);
            txtID.setText("ID");
        } else if (e.getSource() == btnMateria) {
            cardLayout.show(panelContenedor, "Materia");
            lbltitulo.setText("Materia Prima");
            txtID.setVisible(true);
            txtID.setText("ID");
        } else if (e.getSource() == btnMaterial) {
            cardLayout.show(panelContenedor, "Material");
            lbltitulo.setText("Material de Empaque");
            txtID.setVisible(true);
            txtID.setText("ID");
        } else if (e.getSource() == btnInventario) {
            cardLayout.show(panelContenedor, "Inventarios");
            lbltitulo.setText("Inventario");
            txtID.setVisible(false);
            txtID.setText("");
            if (msinv != null) {
                msinv.paraeljoin();
            }
        } else if (e.getSource() == btnEliminar) {
            String idText = txtID.getText().trim();

            if (idText.isEmpty() || idText.equals("ID")) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce un ID para eliminar.", "Error de Eliminación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idToDelete;
            try {
                idToDelete = Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String currentPanelName = "";
            for (Component comp : panelContenedor.getComponents()) {
                if (comp.isVisible()) {
                    if (comp.getClass().getSimpleName().equals("pnlProductos")) {
                        currentPanelName = "Productos Terminados";
                    } else if (comp.getClass().getSimpleName().equals("pnlMateria")) {
                        currentPanelName = "Materia Prima";
                    } else if (comp.getClass().getSimpleName().equals("pnlMaterial")) {
                        currentPanelName = "Material de Empaque";
                    }
                    // No hay "Inventario General" porque en ese caso txtID está oculto.
                    break;
                }
            }

            boolean eliminado = false;
            if (currentPanelName.equals("Productos Terminados")) {
                // Lógica para eliminar Productos Terminados (pendiente de la clase DeleteProducto), Angel debe de terminalo
                // Ejemplo:
                // DeleteProducto deleteProducto = new DeleteProducto();
                // eliminado = deleteProducto.eliminarProducto(idToDelete);
                // if (eliminado) {
                //     Component[] components = panelContenedor.getComponents();
                //     for (Component comp : components) {
                //         if (comp instanceof pnlProductos) {
                //             ((pnlProductos) comp).metodoparaactualizar/leer();
                //             break;
                //         }
                //     }
                // }
                JOptionPane.showMessageDialog(this, "La eliminación de Productos Terminados aún no está implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);

            } else if (currentPanelName.equals("Materia Prima")) {
                DeleteMateria deleteMateria = new DeleteMateria();
                eliminado = deleteMateria.eliminarMateriaPrima(idToDelete);
                if (eliminado) {
                    Component[] components = panelContenedor.getComponents();
                    for (Component comp : components) {
//                        if (comp instanceof pnlMateria) {
//                            ((pnlMateria) comp).metodoparaactualizar/leer(); 
//                            break;
//                        }
                    }
                }
            } else if (currentPanelName.equals("Material de Empaque")) {
                DeleteMaterial deleteMaterial = new DeleteMaterial();
                eliminado = deleteMaterial.eliminarMaterialEmpaque(idToDelete);
                if (eliminado) {
                    Component[] components = panelContenedor.getComponents();
                    for (Component comp : components) {
//                        if (comp instanceof pnlMaterial) {
//                            ((pnlMaterial) comp).metodoparaactualizar/leer(); 
//                            break;
//                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un tipo de inventario (Productos, Materia Prima, Material de Empaque) para eliminar un elemento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            txtID.setText("ID"); // Limpiar el campo después de intentar eliminar
        }

        if (e.getSource() == btnDashboard) {
            new Dashboard();//Crea un objeto del tipo 
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
