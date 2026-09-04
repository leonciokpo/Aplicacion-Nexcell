package vista;

import javax.swing.*;
import java.awt.*;

public class RegistroVentaUI extends JDialog {

    private JTextField dniClienteField; // Cambiamos el ComboBox por un Campo de Texto
    private JComboBox<String> productoBox;
    private JTextField cantidadField;
    private JButton btnConfirmarVenta;

    public RegistroVentaUI(JFrame parent) {
        super(parent, "Registrar Nueva Venta", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Cliente (Ingreso por DNI)
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("DNI del Cliente:"), gbc);
        gbc.gridx = 1; dniClienteField = new JTextField(15); panel.add(dniClienteField, gbc);

        // Producto
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Producto:"), gbc);
        gbc.gridx = 1;
        String[] productosDisponibles = {"Seleccionar...", "CEL-001 - Motorola Edge 60 Pro", "ACC-002 - Funda Silicona"};
        productoBox = new JComboBox<>(productosDisponibles);
        panel.add(productoBox, gbc);

        // Cantidad
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1; cantidadField = new JTextField(10); panel.add(cantidadField, gbc);

        // Botón
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnConfirmarVenta = new JButton("Confirmar Venta");
        panel.add(btnConfirmarVenta, gbc);

        add(panel);
    }

    // Getters para el controlador
    public JTextField getDniClienteField() { return dniClienteField; }
    public JComboBox<String> getProductoBox() { return productoBox; }
    public JTextField getCantidadField() { return cantidadField; }
    public JButton getBtnConfirmarVenta() { return btnConfirmarVenta; }
}
