package vista;

import javax.swing.*;
import java.awt.*;

public class RegistroUsuarioUI extends JDialog {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JTextField txtDni;
    private JTextField txtDireccion;
    private JComboBox<String> cbPerfil;
    private JButton btnGuardarUsuario;
    private JComboBox<Integer> cbDia;
    private JComboBox<String> cbMes;
    private JComboBox<Integer> cbAnio;

    public RegistroUsuarioUI(JFrame parent) {
        super(parent, "Registrar Nuevo Usuario", true);
        setSize(400, 450); // Agrandamos la ventana para que entren todos los campos
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0; // Usamos un contador de filas para ordenarlo fácilmente

        // Nombre
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; txtNombre = new JTextField(15); panel.add(txtNombre, gbc);
        fila++;

        // Apellido
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; txtApellido = new JTextField(15); panel.add(txtApellido, gbc);
        fila++;

        // Username
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Nombre de Usuario:"), gbc);
        gbc.gridx = 1; txtUsername = new JTextField(15); panel.add(txtUsername, gbc);
        fila++;

        // Password
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; txtPassword = new JPasswordField(15); panel.add(txtPassword, gbc);
        fila++;

        // Email
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; txtEmail = new JTextField(15); panel.add(txtEmail, gbc);
        fila++;

        // DNI
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; txtDni = new JTextField(15); panel.add(txtDni, gbc);
        fila++;

        // Direccion
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1; txtDireccion = new JTextField(15); panel.add(txtDireccion, gbc);
        fila++;

        // Fecha de Nacimiento con ComboBox
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("F. Nacimiento:"), gbc);
        gbc.gridx = 1;

        // Creamos un sub-panel para poner los 3 combos juntos horizontalmente
        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // Combo de Días (1 al 31)
        Integer[] dias = new Integer[31];
        for (int i = 0; i < 31; i++) dias[i] = i + 1;
        cbDia = new JComboBox<>(dias);

        // Combo de Meses (01 al 12)
        String[] meses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        cbMes = new JComboBox<>(meses);

        // Combo de Años (100 años hacia atrás desde el actual)
        Integer[] anios = new Integer[100];
        int anioActual = java.time.LocalDate.now().getYear();
        for (int i = 0; i < 100; i++) anios[i] = anioActual - i;
        cbAnio = new JComboBox<>(anios);

        // Agregamos todo al sub-panel con separadores
        panelFecha.add(cbDia);
        panelFecha.add(new JLabel(" / "));
        panelFecha.add(cbMes);
        panelFecha.add(new JLabel(" / "));
        panelFecha.add(cbAnio);

        panel.add(panelFecha, gbc);
        fila++;

        // Rol
        gbc.gridx = 0; gbc.gridy = fila; panel.add(new JLabel("Rol del Sistema:"), gbc);
        gbc.gridx = 1;
        String[] roles = {"Administrador", "Gerente", "Vendedor"}; // Agregamos Administrador
        cbPerfil = new JComboBox<>(roles);
        panel.add(cbPerfil, gbc);
        fila++;

        // Botón
        gbc.gridx = 1; gbc.gridy = fila;
        gbc.insets = new Insets(20, 10, 10, 10); // Más espacio antes del botón
        btnGuardarUsuario = new JButton("Guardar Usuario");
        panel.add(btnGuardarUsuario, gbc);

        add(panel);
    }

    // --- GETTERS ---
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtDireccion() { return txtDireccion; }
    public JComboBox<Integer> getCbDia() { return cbDia; }
    public JComboBox<String> getCbMes() { return cbMes; }
    public JComboBox<Integer> getCbAnio() { return cbAnio; }    public JComboBox<String> getCbPerfil() { return cbPerfil; }
    public JButton getBtnGuardarUsuario() { return btnGuardarUsuario; }
}
