import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

//Gabriel Ricardo Rodriguez de León

public class Inicio_de_sesion extends JFrame implements ActionListener {

    JRadioButton radio1, radio2, radio3;
    JLabel labelimgact; //cual label se ve
    JLabel siglabel;    // el label q se desvanece
    Timer tiempodes;
    int vis = 0;
    private static final int vel = 140;
    private static final int entretiempo = 30;
    JLayeredPane imalp;
    ButtonGroup btg;

    JLabel imgusuario;
    JLabel imgcontra;
    JLabel Bienvenido;//Bienvenidos...
    JPanel lateral;//Panel
    JLabel pR;//Logo de scentra
    JTextField Usuario;
    JPasswordField Contrasena;
    JLabel sub;
    JLabel usuario;
    JLabel contraseña;
    JLabel registro;
    Font b = new Font("Segoe UI", Font.BOLD, 44);//Fuente para el Bienvenidos
    Font s = new Font("Segoe UI", Font.BOLD, 24);
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    JButton iniciar;

    private String[] rutasImagenesFade = {"i1.png", "i2.png", "i3.png"};
    private String imagenActualFadePath = ""; 


    public Inicio_de_sesion() {
        this.setResizable(false);
        this.getContentPane().setBackground(Color.decode("#F7F8FC"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        obj();
        agr();
        posicionar();
        this.setSize(1366, 768);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void obj() {
        lbl();
        pnl();
        Txt();
        Btn();
        imagenes();
    }

    public void pnl() {
        lateral = new JPanel();
        lateral.setBackground(Color.decode("#6A7790"));
        lateral.setLayout(null);
    }

    public static void main(String[] args) {
        new Inicio_de_sesion();
    }

    public void imagenes() {
        imalp = new JLayeredPane();
        imalp.setBounds(100, 150, 400, 400);
        lateral.add(imalp);
        labelimgact = new JLabel();
        siglabel = new JLabel();
        labelimgact.setBounds(0, 0, 400, 400);
        siglabel.setBounds(0, 0, 400, 400);
        imalp.add(labelimgact, JLayeredPane.DEFAULT_LAYER);
        imalp.add(siglabel, JLayeredPane.PALETTE_LAYER);
        labelimgact.setVisible(false);
        siglabel.setVisible(false);
        //radiobuttons
        radio1 = new JRadioButton();
        radio2 = new JRadioButton();
        radio3 = new JRadioButton();
        radio1.setOpaque(false);
        radio2.setOpaque(false);
        radio3.setOpaque(false);
        btg = new ButtonGroup();
        btg.add(radio1);
        btg.add(radio2);
        btg.add(radio3);
        radio1.setBounds(240, 640, 50, 50);
        radio2.setBounds(290, 640, 50, 50);
        radio3.setBounds(340, 640, 50, 50);
        lateral.add(radio1);
        lateral.add(radio2);
        lateral.add(radio3);
        radio1.addActionListener(e -> mostrarlaimagen(0));
        radio2.addActionListener(e -> mostrarlaimagen(1));
        radio3.addActionListener(e -> mostrarlaimagen(2));
        radio1.setSelected(true);
        mostrarlaimagen(0);
        imgusuario = new JLabel();
        imgcontra = new JLabel();
        add(imgusuario);
        add(imgcontra);
        imgusuario.setBounds(700, 285, 20, 20);
        imgcontra.setBounds(700, 365, 20, 20);
        ImageIcon ius = new ImageIcon("usuario.png");
        imgusuario.setIcon(new ImageIcon(ius.getImage().getScaledInstance(imgusuario.getWidth(), imgusuario.getHeight(), Image.SCALE_SMOOTH)));
        ImageIcon ics = new ImageIcon("contra.png");
        imgcontra.setIcon(new ImageIcon(ics.getImage().getScaledInstance(imgcontra.getWidth(), imgcontra.getHeight(), Image.SCALE_SMOOTH)));

    }

    public void mostrarlaimagen(int numi){
        final String dirimg; // Declarar dirimg como final
        if (numi >= 0 && numi < rutasImagenesFade.length) {
            dirimg = rutasImagenesFade[numi];
        } else {
            return;
        }

        File imgFile = new File(dirimg);
        if (!imgFile.exists()) {
            System.err.println("Error: La imagen no se encontró en la ruta: " + dirimg);
            return;
        }

        if (dirimg.equals(imagenActualFadePath) && labelimgact.isVisible()) {
            if (tiempodes != null && tiempodes.isRunning()) {
                tiempodes.stop();
            }
            return;
        }

        final ImageIcon nuevaIcono = new ImageIcon(dirimg); // Declarar como final para capturar
        final Image imagenEscalada = nuevaIcono.getImage().getScaledInstance(imalp.getWidth(), imalp.getHeight(), Image.SCALE_SMOOTH); // Declarar como final

        if (!labelimgact.isVisible() || labelimgact.getIcon() == null || imagenActualFadePath.isEmpty()) {
            labelimgact.setIcon(new ImageIcon(imagenEscalada));
            labelimgact.setVisible(true);
            siglabel.setVisible(false);
            imagenActualFadePath = dirimg;
            if (tiempodes != null && tiempodes.isRunning()) {
                tiempodes.stop();
            }
            return;
        }

        // Establece la imagen original escalada a siglabel inicialmente
        siglabel.setIcon(new ImageIcon(imagenEscalada)); 
        siglabel.setVisible(true);
        imalp.setLayer(siglabel, JLayeredPane.PALETTE_LAYER);

        if (tiempodes != null && tiempodes.isRunning()) {
            tiempodes.stop();
        }

        vis = 0;
        tiempodes = new Timer(entretiempo, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                vis += vel;
                if (vis >= 255) {
                    vis = 255;
                    tiempodes.stop();
                    // Al finalizar el fade, establece labelimgact a la imagen original escalada,
                    // no al último frame transparente.
                    labelimgact.setIcon(new ImageIcon(imagenEscalada)); // Usar la imagen original escalada aquí
                    labelimgact.setVisible(true);
                    siglabel.setVisible(false);
                    siglabel.setIcon(null); // Limpiar el icono de siglabel
                    imagenActualFadePath = dirimg;
                    return;
                }
                
                // Siempre dibuja la imagen original escalada sobre un nuevo buffer transparente
                if (imagenEscalada != null) { // Asegurarse de que imagenEscalada no sea nula
                    BufferedImage transparentImage = new BufferedImage(
                        siglabel.getWidth(), siglabel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = transparentImage.createGraphics();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) vis / 255f));
                    
                    g2d.drawImage(imagenEscalada, 0, 0, siglabel.getWidth(), siglabel.getHeight(), null); // Usar imagenEscalada (original)
                    g2d.dispose();
                    siglabel.setIcon(new ImageIcon(transparentImage));
                }
            }
        });
        tiempodes.start();
    }
    public void agr() {
        this.add(lateral);
        this.add(sub);
        this.add(Bienvenido);
        this.add(pR);
        this.add(usuario);
        this.add(contraseña);
        this.add(Usuario);
        this.add(Contrasena);
        this.add(iniciar);
        this.add(registro);
    }

    public void Txt() {
        Usuario = new JTextField();
        Contrasena = new JPasswordField();
        Usuario.setHorizontalAlignment(SwingConstants.CENTER);
        Contrasena.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void Btn() {
        iniciar = new JButton("Iniciar sesión");
        iniciar.setBackground(Color.decode("#6A7793"));
        iniciar.setForeground(Color.white);
        iniciar.setFont(bt);
        iniciar.setFocusPainted(false);
        iniciar.setBorderPainted(false);
        iniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iniciar.setOpaque(false);
        iniciar.setContentAreaFilled(false);
        iniciar.addActionListener(this);
        iniciar.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(iniciar.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                super.paint(g, c);
                g2.dispose();
            }
        });
    }

    public void posicionar() {
        lateral.setBounds(800, 0, 566, 768);
        Bienvenido.setBounds(50, 170, 600, 50);
        Bienvenido.setHorizontalAlignment(JLabel.LEFT);
        sub.setBounds(50, 210, 600, 50);
        usuario.setBounds(50, 240, 600, 50);
        Usuario.setBounds(50, 280, 640, 30);
        contraseña.setBounds(50, 320, 600, 50);
        Contrasena.setBounds(50, 360, 640, 30);
        registro.setBounds(50, 390, 640, 30);
        iniciar.setBounds(280, 500, 200, 40);
        pR.setBounds(30, -50, 110, 160);
        ImageIcon usu = new ImageIcon("logo.png");
        pR.setIcon(new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH)));

    }

    public void lbl() {
        Bienvenido = new JLabel("Bienvenido de vuelta");
        sub = new JLabel("Inicia sesión");
        Bienvenido.setFont(b);
        sub.setFont(s);
        Bienvenido.setForeground(Color.decode("#586875"));
        sub.setForeground(Color.decode("#586875"));
        registro = new JLabel("¿No tienes cuenta? Registrate");
        registro.setForeground(Color.decode("#586875"));
        registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registro r = new Registro();
                dispose();
            }
        });
        pR = new JLabel();
        usuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        usuario.setForeground(Color.decode("#586875"));
        contraseña.setForeground(Color.decode("#586875"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciar) {
            String nombreusuario = Usuario.getText();
            String contrasena = new String(Contrasena.getPassword());
            InicioRol lm = new InicioRol();
            String[] dtus = lm.validar(nombreusuario, contrasena);
            if (dtus != null) {
                String rol = dtus[0];
                int idEmpresa = Integer.parseInt(dtus[1]);
                SesionUsuario.getInstance().setUsuario(rol, idEmpresa, nombreusuario, contrasena);
                new Dashboard();
                this.dispose();
                if (tiempodes != null && tiempodes.isRunning()) {
                    tiempodes.stop();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        }
    }
}