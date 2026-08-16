package vista;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;

    public LoginUI() {
        setTitle("Iniciar Sesión - Nexcell");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Usuario:");
        userField = new JTextField();

        JLabel passLabel = new JLabel("Contraseña:");
        passField = new JPasswordField();

        loginButton = new JButton("Ingresar");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);

    }

    public JTextField getUserField() { return userField; }
    public JPasswordField getPassField() { return passField; }
    public JButton getLoginButton() { return loginButton; }
}
