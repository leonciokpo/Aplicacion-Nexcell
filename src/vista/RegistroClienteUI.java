package vista;

import javax.swing.*;
import java.awt.*;

// Usamos JDialog para que sea una ventana emergente que dependa de la principal
public class RegistroClienteUI extends JDialog {

    private JTextField dniClienteField;
    private JTextField nombreClienteField;
    private JTextField apellidoClienteField;
    private JTextField telefonoClienteField;
    private JTextField emailClienteField;
    private JTextField fechaNacClienteField;
    private JButton btnGuardarCliente;

    // Le pasamos el JFrame padre (VendedorUI) para que se centre respecto a él
    public RegistroClienteUI(JFrame parent) {
        super(parent, "Registrar Nuevo Cliente", true); // El 'true' hace que sea modal (bloquea la ventana de atrás)
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0: DNI
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; dniClienteField = new JTextField(15); panel.add(dniClienteField, gbc);

        // Fila 1: Nombre
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; nombreClienteField = new JTextField(15); panel.add(nombreClienteField, gbc);

        // Fila 2: Apellido
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; apellidoClienteField = new JTextField(15); panel.add(apellidoClienteField, gbc);

        // Fila 3: Teléfono
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Nro de Teléfono:"), gbc);
        gbc.gridx = 1; telefonoClienteField = new JTextField(15); panel.add(telefonoClienteField, gbc);

        // Fila 4: Email
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; emailClienteField = new JTextField(15); panel.add(emailClienteField, gbc);

        // Fila 5: Fecha Nacimiento
        gbc.gridx = 0; gbc.gridy = 5; panel.add(new JLabel("Fecha de Nacimiento:"), gbc);
        gbc.gridx = 1; fechaNacClienteField = new JTextField(15); panel.add(fechaNacClienteField, gbc);

        // Fila 6: Botón
        gbc.gridx = 1; gbc.gridy = 6;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnGuardarCliente = new JButton("Guardar Cliente");
        panel.add(btnGuardarCliente, gbc);

        add(panel);
    }

    // Getters para el controlador
    public JTextField getDniClienteField() { return dniClienteField; }
    public JTextField getNombreClienteField() { return nombreClienteField; }
    public JTextField getApellidoClienteField() { return apellidoClienteField; }
    public JTextField getTelefonoClienteField() { return telefonoClienteField; }
    public JTextField getEmailClienteField() { return emailClienteField; }
    public JTextField getFechaNacClienteField() { return fechaNacClienteField; }
    public JButton getBtnGuardarCliente() { return btnGuardarCliente; }
}
