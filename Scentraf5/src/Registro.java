
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class Registro extends JFrame implements ActionListener {
//Gabriel Ricardo Rodriguez de León

    JRadioButton radio1, radio2, radio3;
    JLabel labelimgact; //cual label se ve
    JLabel siglabel;    // el label q se desvanece
    Timer tiempodes;
    int vis = 0;
    private static final int vel = 140;
    private static final int entretiempo = 30;
    JLayeredPane imalp;
    ButtonGroup btg;
    JLabel rolim;
    JLabel imgusuario;
    JLabel imgcontra;
    JLabel Bienvenido1;//Bienvenidos...
    JLabel Bienvenido2;
    JPanel lateral;//Panel
    JLabel pR;//Logo de scentra
    JTextField Usuario;//Aun no lo uso
    JPasswordField Contrasena;//Aun no lo uso
    JLabel sub;
    JLabel usuario;
    JLabel contraseña;
    JLabel registro;
    JLabel Rols;
    JComboBox Rol;
    Font b = new Font("Segoe UI", Font.BOLD, 50);//Fuente para el Bienvenidos
    Font s = new Font("Segoe UI", Font.BOLD, 24);
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    JButton iniciar;

    private String[] rutasImagenesFade = {"r1.png", "r2.png", "r3.png"};
    private String imagenActualFadePath = "";

    public Registro() {

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
        new Registro();
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
        rolim = new JLabel();
        imgusuario = new JLabel();
        imgcontra = new JLabel();
        add(imgusuario);
        add(imgcontra);
        add(rolim);
        imgusuario.setBounds(700, 295, 20, 20);
        imgcontra.setBounds(700, 375, 20, 20);
        rolim.setBounds(700, 455, 20, 20);
        ImageIcon irol = new ImageIcon("rol.png");
        rolim.setIcon(new ImageIcon(irol.getImage().getScaledInstance(rolim.getWidth(), rolim.getHeight(), Image.SCALE_SMOOTH)));
        ImageIcon ius = new ImageIcon("usuario.png");
        imgusuario.setIcon(new ImageIcon(ius.getImage().getScaledInstance(imgusuario.getWidth(), imgusuario.getHeight(), Image.SCALE_SMOOTH)));
        ImageIcon ics = new ImageIcon("contra.png");
        imgcontra.setIcon(new ImageIcon(ics.getImage().getScaledInstance(imgcontra.getWidth(), imgcontra.getHeight(), Image.SCALE_SMOOTH)));

    }

    public void mostrarlaimagen(int numi) {
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
        tiempodes = new Timer(entretiempo, new ActionListener() {
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
        this.add(Bienvenido1);
        this.add(Bienvenido2);
        this.add(pR);
        this.add(usuario);
        this.add(contraseña);
        this.add(Usuario);
        this.add(Contrasena);
        this.add(iniciar);
        this.add(registro);
        this.add(Rols);
        this.add(Rol);
    }

    public void Txt() {
        Usuario = new JTextField();
        Contrasena = new JPasswordField();
        Usuario.setHorizontalAlignment(SwingConstants.CENTER);
        Contrasena.setHorizontalAlignment(SwingConstants.CENTER);
        Rol = new JComboBox();
        Rol.addItem("Administrador");
        Rol.addItem("Empleado");
        Rol.setBackground(Color.WHITE);
    }

    public void Btn() {
        iniciar = new JButton("Continuar");
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
        Bienvenido1.setBounds(50, 110, 600, 50);
        Bienvenido1.setHorizontalAlignment(JLabel.LEFT);
        Bienvenido2.setBounds(50, 160, 600, 50);
        Bienvenido2.setHorizontalAlignment(JLabel.LEFT);
        sub.setBounds(50, 210, 600, 50);
        usuario.setBounds(50, 250, 600, 50);
        Usuario.setBounds(50, 290, 640, 30);
        contraseña.setBounds(50, 330, 600, 50);
        Contrasena.setBounds(50, 370, 640, 30);
        registro.setBounds(50, 480, 640, 30);
        iniciar.setBounds(280, 520, 200, 40);
        pR.setBounds(30, -50, 110, 160);
        ImageIcon usu = new ImageIcon("logo.png");
        pR.setIcon(new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH)));
        Rols.setBounds(50, 410, 600, 50);
        Rol.setBounds(50, 450, 640, 30);
    }

    public void lbl() {
        this.setResizable(false);
        Bienvenido1 = new JLabel("Todo a la mano, ");
        Bienvenido2 = new JLabel("todo a tu modo");
        sub = new JLabel("Registrate");
        Bienvenido1.setFont(b);
        Bienvenido2.setFont(b);
        sub.setFont(s);
        Bienvenido1.setForeground(Color.decode("#586875"));
        Bienvenido2.setForeground(Color.decode("#586875"));
        sub.setForeground(Color.decode("#586875"));
        registro = new JLabel("¿Ya tienes cuenta?,Inicia sesión");
        registro.setForeground(Color.decode("#586875"));
        registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Inicio_de_sesion r = new Inicio_de_sesion();
                dispose();
            }
        });
        pR = new JLabel();
        usuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        usuario.setForeground(Color.decode("#B4A8AA"));
        contraseña.setForeground(Color.decode("#B4A8AA"));
        Rols = new JLabel("Rol");
        Rols.setForeground(Color.decode("#B4A8AA"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciar) {
            new Unirse_a_empresa();
            this.dispose();
        }
    }
}
