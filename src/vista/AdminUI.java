package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUI extends JFrame {

    // Componentes de Reportes
    private JComboBox<String> comboReportes;
    private JTextField buscarReporteField;
    private JButton btnGenerarReporte;
    private JButton btnLimpiarReporte;
    private JTable tablaReportes;

    // Componentes de Producto
    private JTextField buscarProductoField;
    private JButton btnBuscarProducto;
    private JTable tablaProductos;
    private JButton btnAbrirFormularioProducto;

    // Componentes de Usuarios
    private JTextField buscarUsuarioField;
    private JButton btnBuscarUsuario;
    private JTable tablaUsuarios;
    private JButton btnAbrirFormularioUsuario;

    // Botón general
    private JButton btnCerrarSesion;

    public AdminUI() {
        setTitle("Panel de Administrador - Nexcell");
        setSize(750, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane sistemaPestanas = new JTabbedPane();
        sistemaPestanas.addTab("Gestión de Productos", crearPanelProductos());
        sistemaPestanas.addTab("Gestión de Usuarios", crearPanelUsuarios());
        sistemaPestanas.addTab("Reportes", crearPanelReportes());

        add(sistemaPestanas, BorderLayout.CENTER);

        // Panel inferior para cerrar sesión
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setForeground(Color.RED);
        panelInferior.add(btnCerrarSesion);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buscarProductoField = new JTextField(15);
        btnBuscarProducto = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Buscar (Modelo/Código): "));
        panelBusqueda.add(buscarProductoField);
        panelBusqueda.add(btnBuscarProducto);

        // Botón Nuevo
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnAbrirFormularioProducto = new JButton("Nuevo Producto");
        panelAcciones.add(btnAbrirFormularioProducto);

        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(panelAcciones, BorderLayout.EAST);

        String[] columnas = {"Código", "Categoría", "Modelo", "Stock", "Precio"};
        Object[][] datosEjemplo = {
            {"CEL-001", "Celulares", "Motorola Edge 60 Pro", "15", "$850.000"}
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(datosEjemplo, columnas);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelUsuarios() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buscarUsuarioField = new JTextField(15);
        btnBuscarUsuario = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Buscar (Username): "));
        panelBusqueda.add(buscarUsuarioField);
        panelBusqueda.add(btnBuscarUsuario);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnAbrirFormularioUsuario = new JButton("Nuevo Usuario");
        panelAcciones.add(btnAbrirFormularioUsuario);

        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(panelAcciones, BorderLayout.EAST);

        String[] columnas = {"Username", "Rol del Sistema"};
        Object[][] datosEjemplo = {
            {"vendedor", "Vendedor"},
            {"gerente", "Gerente"}
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(datosEjemplo, columnas);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaUsuarios);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT));

        comboReportes = new JComboBox<>(new String[]{
                "Stock de productos",
                "Productos registrados",
                "Usuarios del sistema",
                "Movimientos de stock"
        });

        buscarReporteField = new JTextField(15);
        btnGenerarReporte = new JButton("Generar");
        btnLimpiarReporte = new JButton("Limpiar");

        filtros.add(new JLabel("Reporte:"));
        filtros.add(comboReportes);
        filtros.add(new JLabel("Buscar:"));
        filtros.add(buscarReporteField);
        filtros.add(btnGenerarReporte);
        filtros.add(btnLimpiarReporte);

        tablaReportes = new JTable();
        panel.add(filtros, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaReportes), BorderLayout.CENTER);

        return panel;
    }

    // --- GETTERS PRODUCTOS ---
    public JTextField getBuscarProductoField() { return buscarProductoField; }
    public JButton getBtnBuscarProducto() { return btnBuscarProducto; }
    public JTable getTablaProductos() { return tablaProductos; }
    public JButton getBtnAbrirFormularioProducto() { return btnAbrirFormularioProducto; }

    // --- GETTERS USUARIOS ---
    public JTextField getBuscarUsuarioField() { return buscarUsuarioField; }
    public JButton getBtnBuscarUsuario() { return btnBuscarUsuario; }
    public JTable getTablaUsuarios() { return tablaUsuarios; }
    public JButton getBtnAbrirFormularioUsuario() { return btnAbrirFormularioUsuario; }

    // --- GETTERS REPORTES ---
    public JComboBox<String> getComboReportes() {return comboReportes;}
    public JTextField getBuscarReporteField() {return buscarReporteField;}
    public JButton getBtnGenerarReporte() {return btnGenerarReporte;}
    public JButton getBtnLimpiarReporte() {return btnLimpiarReporte;}
    public JTable getTablaReportes() {return tablaReportes;}

    // --- GETTER CERRAR SESIÓN ---
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
}
