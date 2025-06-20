
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//Gabriel Ricardo Rodriguez de León
public class Progreso extends JFrame implements ActionListener {

    JProgressBar Barra;
    JLabel Porcentaje;
    JLabel pR;

    public Progreso() {
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.decode("#F7F8FC"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        obj();
        agr();
        posicionar();
        //Tamaño maximo-inicio
        this.setSize(750, 500);
        //Final- copia pega en mis otras ventanas
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(100);
                Barra.setValue(i);
                Porcentaje.setText(i + "%");
                if (Barra.getValue() == 100) {
                    new Inicio_de_sesion();
                    dispose();
                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new Progreso();
    }

    public void agr() {
        this.add(Barra);
        this.add(Porcentaje);
        this.add(pR);
    }

    public void posicionar() {
        Barra.setBounds(100, 250, 530, 25);
        Porcentaje.setBounds(210, 300, 300, 25);
        Porcentaje.setHorizontalAlignment(JLabel.CENTER);
        try {
            pR.setBounds(270, 45, 200, 250);
            ImageIcon usu = new ImageIcon("logo.png");
            pR.setIcon(new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH)));
        } catch (Exception e) {

        }
        //new ImageIcon(usu.getImage().getScaledInstance(pR.getWidth(), pR.getHeight(), Image.SCALE_SMOOTH))
    }

    public void obj() {
        Barra = new JProgressBar();
        Barra.setValue(0);
        Porcentaje = new JLabel("--%");
        Porcentaje.setForeground(Color.decode("#586875"));
        pR = new JLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
