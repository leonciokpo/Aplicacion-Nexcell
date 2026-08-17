package vista;

import javax.swing.*;
import java.awt.*;

public class RegistroUsuarioUI extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> rolBox;
    private JButton btnGuardarUsuario;

    public RegistroUsuarioUI(JFrame parent) {
        super(parent, "Registrar Nuevo Usuario", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Username
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nombre de Usuario:"), gbc);
        gbc.gridx = 1; usernameField = new JTextField(15); panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; passwordField = new JPasswordField(15); panel.add(passwordField, gbc);

        // Rol
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Rol del Sistema:"), gbc);
        gbc.gridx = 1;
        String[] roles = {"Vendedor", "Gerente"};
        rolBox = new JComboBox<>(roles);
        panel.add(rolBox, gbc);

        // Botón
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnGuardarUsuario = new JButton("Guardar Usuario");
        panel.add(btnGuardarUsuario, gbc);

        add(panel);
    }

    // Getters
    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JComboBox<String> getRolBox() { return rolBox; }
    public JButton getBtnGuardarUsuario() { return btnGuardarUsuario; }
}
