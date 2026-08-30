package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUI extends JFrame {

    // Componentes de Productos
    private JTextField buscarProductoField;
    private JButton btnBuscarProducto;
    private JTable tablaProductos;
    private JButton btnAbrirFormularioProducto;
    private JButton btnModificarProducto;
    private JButton btnBajaProducto;
    private JButton btnAltaProducto;

    // Componentes de Usuarios
    private JTextField buscarUsuarioField;
    private JButton btnBuscarUsuario;
    private JTable tablaUsuarios;
    private JButton btnAbrirFormularioUsuario;
    private JButton btnModificarUsuario;
    private JButton btnBajaUsuario;
    private JButton btnAltaUsuario;

    // Reportes y General
    private JComboBox<String> comboReportes;
    private JButton btnGenerarReporte;
    private JButton btnLimpiarReporte;
    private JTable tablaReportes;
    private JButton btnCerrarSesion;

    public AdminUI() {
        setTitle("Panel de Administrador - Nexcell");
        setSize(850, 600); // Agrandamos el ancho para que entren los botones
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane sistemaPestanas = new JTabbedPane();
        sistemaPestanas.addTab("Gestión de Productos", crearPanelProductos());
        sistemaPestanas.addTab("Gestión de Usuarios", crearPanelUsuarios());
        sistemaPestanas.addTab("Reportes", crearPanelReportes());

        add(sistemaPestanas, BorderLayout.CENTER);

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

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buscarProductoField = new JTextField(15);
        btnBuscarProducto = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Filtrar: "));
        panelBusqueda.add(buscarProductoField);
        panelBusqueda.add(btnBuscarProducto);

        // Agregamos los botones de ABM (Alta, Baja, Modificación)
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        btnAbrirFormularioProducto = new JButton("Nuevo");
        btnModificarProducto = new JButton("Modificar");
        btnBajaProducto = new JButton("Baja Lógica");
        btnAltaProducto = new JButton("Reactivar");

        panelAcciones.add(btnAbrirFormularioProducto);
        panelAcciones.add(btnModificarProducto);
        panelAcciones.add(btnBajaProducto);
        panelAcciones.add(btnAltaProducto);

        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(panelAcciones, BorderLayout.EAST);

        // Agregamos la columna "Estado" para visualizar la baja lógica
        String[] columnas = {"ID", "Modelo", "Categoría", "Stock", "Precio", "Estado"};
        Object[][] datosEjemplo = {
            {"CEL-001", "Motorola Edge 60 Pro", "Celulares", "15", "$850.000", "Activo"},
            {"ACC-002", "Funda Silicona", "Accesorios", "30", "$15.000", "Inactivo"}
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
        panelBusqueda.add(new JLabel("Usuario: "));
        panelBusqueda.add(buscarUsuarioField);
        panelBusqueda.add(btnBuscarUsuario);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        btnAbrirFormularioUsuario = new JButton("Nuevo");
        btnModificarUsuario = new JButton("Modificar");
        btnBajaUsuario = new JButton("Baja Lógica");
        btnAltaUsuario = new JButton("Reactivar");

        panelAcciones.add(btnAbrirFormularioUsuario);
        panelAcciones.add(btnModificarUsuario);
        panelAcciones.add(btnBajaUsuario);
        panelAcciones.add(btnAltaUsuario);

        panelSuperior.add(panelBusqueda, BorderLayout.WEST);
        panelSuperior.add(panelAcciones, BorderLayout.EAST);

        String[] columnas = {"Username", "Rol del Sistema", "Estado"};
        Object[][] datosEjemplo = {
            {"vendedor1", "Vendedor", "Activo"},
            {"gerente_suc", "Gerente", "Activo"}
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

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] opcionesReporte = {"Stock de productos", "Productos registrados", "Usuarios del sistema", "Movimientos"};
        comboReportes = new JComboBox<>(opcionesReporte);
        btnGenerarReporte = new JButton("Generar");
        btnLimpiarReporte = new JButton("Limpiar");

        panelSuperior.add(new JLabel("Tipo de Reporte: "));
        panelSuperior.add(comboReportes);
        panelSuperior.add(btnGenerarReporte);
        panelSuperior.add(btnLimpiarReporte);

        tablaReportes = new JTable(new DefaultTableModel());
        JScrollPane scrollTabla = new JScrollPane(tablaReportes);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    // --- GETTERS DE PRODUCTOS ---
    public JTable getTablaProductos() { return tablaProductos; }
    public JButton getBtnAbrirFormularioProducto() { return btnAbrirFormularioProducto; }
    public JButton getBtnModificarProducto() { return btnModificarProducto; }
    public JButton getBtnBajaProducto() { return btnBajaProducto; }
    public JButton getBtnAltaProducto() { return btnAltaProducto; }

    // --- GETTERS DE USUARIOS ---
    public JTable getTablaUsuarios() { return tablaUsuarios; }
    public JButton getBtnAbrirFormularioUsuario() { return btnAbrirFormularioUsuario; }
    public JButton getBtnModificarUsuario() { return btnModificarUsuario; }
    public JButton getBtnBajaUsuario() { return btnBajaUsuario; }
    public JButton getBtnAltaUsuario() { return btnAltaUsuario; }

    // --- GETTERS REPORTES Y GENERAL ---
    public JComboBox<String> getComboReportes() { return comboReportes; }
    public JButton getBtnGenerarReporte() { return btnGenerarReporte; }
    public JButton getBtnLimpiarReporte() { return btnLimpiarReporte; }
    public JTable getTablaReportes() { return tablaReportes; }
    public JButton getBtnCerrarSesion() { return btnCerrarSesion; }
}
