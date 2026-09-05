package controlador;

import vista.GerenteUI;
import vista.LoginUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GerenteController {

    private GerenteUI vistaPrincipal;
    private jakarta.persistence.EntityManager em;

    public GerenteController(GerenteUI vistaPrincipal, jakarta.persistence.EntityManager em) {
        this.vistaPrincipal = vistaPrincipal;
        this.em = em;

        this.vistaPrincipal.getBtnGenerarReporte().addActionListener(e -> generarReporteVentas());
        this.vistaPrincipal.getBtnLimpiarReporte().addActionListener(e -> limpiarReporte());
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void generarReporteVentas() {
        String filtroSeleccionado = vistaPrincipal.getComboFiltroVentas().getSelectedItem().toString();

        String[] columnas = {"ID Venta", "Fecha", "DNI Cliente", "Vendedor", "Producto", "Total"};
        Object[][] datos;

        if (filtroSeleccionado.equals("Ventas del Día")) {
            datos = new Object[][]{
                {"V-1023", "31/08/2026", "35123456", "vendedor1", "Motorola Edge 60", "$850.000"}
            };
        } else if (filtroSeleccionado.equals("Ventas del Mes")) {
            datos = new Object[][]{
                {"V-1022", "15/08/2026", "40987654", "vendedor1", "Funda Silicona", "$15.000"},
                {"V-1023", "31/08/2026", "35123456", "vendedor1", "Motorola Edge 60", "$850.000"}
            };
        } else {
            datos = new Object[][]{
                {"V-1001", "10/01/2026", "22333444", "vendedor_hist", "Samsung A56", "$700.000"},
                {"V-1022", "15/08/2026", "40987654", "vendedor1", "Funda Silicona", "$15.000"},
                {"V-1023", "31/08/2026", "35123456", "vendedor1", "Motorola Edge 60", "$850.000"}
            };
        }

        vistaPrincipal.getTablaReportesVentas().setModel(new DefaultTableModel(datos, columnas));
    }

    private void limpiarReporte() {
        vistaPrincipal.getTablaReportesVentas().setModel(new DefaultTableModel());
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
            "¿Estás seguro que querés salir del panel de gerencia?", "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vistaPrincipal.dispose();
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin, em);
            ventanaLogin.setVisible(true);
        }
    }
}
