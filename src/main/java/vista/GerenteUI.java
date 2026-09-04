package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenteUI extends JFrame {

    // Componentes de Reportes de Ventas
    private JComboBox<String> comboFiltroVentas;
    private JButton btnGenerarReporte;
    private JButton btnLimpiarReporte;
    private JTable tablaReportesVentas;

    // Botón general
    private JButton btnCerrarSesion;

    public GerenteUI() {
        setTitle("Panel de Gerencia - Nexcell");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane sistemaPestanas = new JTabbedPane();
        sistemaPestanas.addTab("Reportes de Ventas", crearPanelReportesVentas());

        // Podés agregar más pestañas a futuro (ej: "Rendimiento Vendedores")
        add(sistemaPestanas, BorderLayout.CENTER);

        // Panel inferior para cerrar sesión
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setForeground(Color.RED);
        panelInferior.add(btnCerrarSesion);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelReportesVentas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Controles superiores
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] filtros = {"Ventas del Día", "Ventas del Mes", "Ventas por Vendedor", "Todas las Ventas"};
        comboFiltroVentas = new JComboBox<>(filtros);
        btnGenerarReporte = new JButton("Generar Reporte");
        btnLimpiarReporte = new JButton("Limpiar Pantalla");

        panelSuperior.add(new JLabel("Filtrar por: "));
        panelSuperior.add(comboFiltroVentas);
        panelSuperior.add(btnGenerarReporte);
        panelSuperior.add(btnLimpiarReporte);

        // Tabla central
        tablaReportesVentas = new JTable(new DefaultTableModel());
        JScrollPane scrollTabla = new JScrollPane(tablaReportesVentas);

        // Panel de totales (Opcional, para darle un toque más gerencial)
        JPanel panelTotales = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTotales.add(new JLabel("Total Reporte: $ 0.00")); // A futuro esto se calcula dinámicamente

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        panel.add(panelTotales, BorderLayout.SOUTH);

        return panel;
    }

    // --- GETTERS ---
    public JComboBox<String> getComboFiltroVentas() { return comboFiltroVentas; }
    public JButton getBtnGenerarReporte() { return btnGenerarReporte; }
    public JButton getBtnLimpiarReporte() { return btnLimpiarReporte; }
    public JTable getTablaReportesVentas() { return tablaReportesVentas; }
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
}