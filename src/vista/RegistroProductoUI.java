package vista;

import javax.swing.*;
import java.awt.*;

public class RegistroProductoUI extends JDialog {

    private JTextField codigoField;
    private JComboBox<String> categoriaBox;
    private JTextField modeloField;
    private JTextField stockField;
    private JTextField precioField;
    private JButton btnGuardarProducto;

    public RegistroProductoUI(JFrame parent) {
        super(parent, "Registrar Nuevo Producto", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Código
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Código / ID:"), gbc);
        gbc.gridx = 1; codigoField = new JTextField(15); panel.add(codigoField, gbc);

        // Categoría (Desplegable)
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1;
        String[] categorias = {"Celulares", "Accesorios", "Repuestos", "Otros"};
        categoriaBox = new JComboBox<>(categorias);
        panel.add(categoriaBox, gbc);

        // Modelo
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1; modeloField = new JTextField(15); panel.add(modeloField, gbc);

        // Stock
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Stock Inicial:"), gbc);
        gbc.gridx = 1; stockField = new JTextField(15); panel.add(stockField, gbc);

        // Precio
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; precioField = new JTextField(15); panel.add(precioField, gbc);

        // Botón
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.insets = new Insets(20, 10, 10, 10);
        btnGuardarProducto = new JButton("Guardar Producto");
        panel.add(btnGuardarProducto, gbc);

        add(panel);
    }

    // Getters para el controlador
    public JTextField getCodigoField() { return codigoField; }
    public JComboBox<String> getCategoriaBox() { return categoriaBox; }
    public JTextField getModeloField() { return modeloField; }
    public JTextField getStockField() { return stockField; }
    public JTextField getPrecioField() { return precioField; }
    public JButton getBtnGuardarProducto() { return btnGuardarProducto; }
}
