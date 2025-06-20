
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Registro extends JFrame implements ActionListener {
//Gabriel Ricardo Rodriguez de León
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
    Font b = new Font("Segoe UI", Font.BOLD, 50);//Fuente para el Bienvenidos
    Font s = new Font("Segoe UI", Font.BOLD, 24);
    Font bt = new Font("Segoe UI", Font.BOLD, 14);
    JButton iniciar;

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
    }

    public void pnl() {
        lateral = new JPanel();
        lateral.setBackground(Color.decode("#6A7790"));
    }

    public static void main(String[] args) {
        new Registro();
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
    }

    public void Txt() {
        Usuario = new JTextField();
        Contrasena = new JPasswordField();
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
        registro.setBounds(50, 400, 640, 30);
        iniciar.setBounds(280, 510, 200, 40);
        pR.setBounds(30, -50, 110, 160);
        ImageIcon usu = new ImageIcon("logo.png");
        pR.setIcon(new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH)));

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
        registro.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
            Inicio_de_sesion r = new Inicio_de_sesion();
            dispose();
        }
        });
        pR = new JLabel();
        usuario = new JLabel("Usuario");
        contraseña = new JLabel("Contraseña");
        usuario.setForeground(Color.decode("#B4A8AA"));
        contraseña.setForeground(Color.decode("#B4A8AA"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iniciar){
        new Unirse_a_empresa();
        this.dispose();
        }
    }
}
