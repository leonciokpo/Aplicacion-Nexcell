package controlador;

import vista.VendedorUI;
import vista.RegistroClienteUI;
import vista.RegistroVentaUI;
import vista.LoginUI;

import javax.swing.*;

public class VendedorController {

    private VendedorUI vistaPrincipal;
    private jakarta.persistence.EntityManager em;

    public VendedorController(VendedorUI vistaPrincipal, jakarta.persistence.EntityManager em) {
        this.vistaPrincipal = vistaPrincipal;
        this.em = em;

        this.vistaPrincipal.getBtnAbrirFormularioCliente().addActionListener(e -> abrirFormularioRegistro());
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
        this.vistaPrincipal.getBtnAbrirFormularioVenta().addActionListener(e -> abrirFormularioVenta());
        this.vistaPrincipal.getBtnBajaCliente().addActionListener(e -> cambiarEstadoCliente(false));
        this.vistaPrincipal.getBtnAltaCliente().addActionListener(e -> cambiarEstadoCliente(true));

        this.vistaPrincipal.getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.vistaPrincipal.getTablaClientes().getSelectedRow();
                boolean haySeleccion = filaSeleccionada != -1;

                if (haySeleccion) {
                    String estadoActual = this.vistaPrincipal.getTablaClientes().getValueAt(filaSeleccionada, 5).toString();

                    if (estadoActual.equalsIgnoreCase("Activo")) {
                        this.vistaPrincipal.getBtnBajaCliente().setVisible(true);
                        this.vistaPrincipal.getBtnAltaCliente().setVisible(false);
                    } else {
                        this.vistaPrincipal.getBtnBajaCliente().setVisible(false);
                        this.vistaPrincipal.getBtnAltaCliente().setVisible(true);
                    }
                } else {
                    this.vistaPrincipal.getBtnBajaCliente().setVisible(false);
                    this.vistaPrincipal.getBtnAltaCliente().setVisible(false);
                }
            }
        });
    }

    private void abrirFormularioVenta() {
        RegistroVentaUI ventanaVenta = new RegistroVentaUI(this.vistaPrincipal);

        ventanaVenta.getBtnConfirmarVenta().addActionListener(e -> {
            String dniCliente = ventanaVenta.getDniClienteField().getText();
            int productoSeleccionado = ventanaVenta.getProductoBox().getSelectedIndex();
            String cantidad = ventanaVenta.getCantidadField().getText();

            if (dniCliente.isEmpty() || productoSeleccionado == 0 || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaVenta,
                    "Debes ingresar el DNI del cliente, seleccionar un producto y especificar la cantidad.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(ventanaVenta,
                "Venta registrada exitosamente para el DNI:\n" + dniCliente,
                "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);

            ventanaVenta.dispose();
        });

        ventanaVenta.setVisible(true);
    }

    private void abrirFormularioRegistro() {
        RegistroClienteUI ventanaRegistro = new RegistroClienteUI(this.vistaPrincipal);

        ventanaRegistro.getBtnGuardarCliente().addActionListener(e -> {
            String dni = ventanaRegistro.getDniClienteField().getText();
            String nombre = ventanaRegistro.getNombreClienteField().getText();
            String apellido = ventanaRegistro.getApellidoClienteField().getText();

            if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Por favor, completá al menos DNI, Nombre y Apellido.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(ventanaRegistro, "Cliente " + nombre + " " + apellido + " registrado con éxito en Nexcell.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaRegistro.dispose();
        });

        ventanaRegistro.setVisible(true);
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
            "¿Estás seguro que querés salir?", "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vistaPrincipal.dispose();
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin, em);
            ventanaLogin.setVisible(true);
        }
    }

    private void cambiarEstadoCliente(boolean activar) {
        int fila = vistaPrincipal.getTablaClientes().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccioná un cliente de la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dniSeleccionado = vistaPrincipal.getTablaClientes().getValueAt(fila, 0).toString();
        String accion = activar ? "Reactivar" : "Dar de Baja (Inactivar)";

        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
            "¿Estás seguro que querés " + accion.toLowerCase() + " al cliente con DNI " + dniSeleccionado + "?",
            accion, JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String nuevoEstado = activar ? "Activo" : "Inactivo";
            vistaPrincipal.getTablaClientes().setValueAt(nuevoEstado, fila, 5);
            vistaPrincipal.getTablaClientes().clearSelection();
            vistaPrincipal.getTablaClientes().setRowSelectionInterval(fila, fila);
            JOptionPane.showMessageDialog(vistaPrincipal, "El estado del cliente se actualizó a: " + nuevoEstado, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
