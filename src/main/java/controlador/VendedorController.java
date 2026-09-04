package controlador;

import vista.VendedorUI;
import vista.RegistroClienteUI;
import vista.RegistroVentaUI; // Importamos la nueva vista de ventas
import vista.LoginUI;

import javax.swing.*;

public class VendedorController {

    private VendedorUI vistaPrincipal;

    public VendedorController(VendedorUI vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;

        // --- ESCUCHADORES DE LOS BOTONES PRINCIPALES ---

        // Botón: Nuevo Cliente
        this.vistaPrincipal.getBtnAbrirFormularioCliente().addActionListener(e -> abrirFormularioRegistro());

        // Botón: Cerrar Sesión
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());

        // Botón: Nueva Venta
        this.vistaPrincipal.getBtnAbrirFormularioVenta().addActionListener(e -> abrirFormularioVenta());

        // --- ESCUCHADORES DE CLIENTES ---
        this.vistaPrincipal.getBtnAbrirFormularioCliente().addActionListener(e -> abrirFormularioRegistro());
        this.vistaPrincipal.getBtnBajaCliente().addActionListener(e -> cambiarEstadoCliente(false));
        this.vistaPrincipal.getBtnAltaCliente().addActionListener(e -> cambiarEstadoCliente(true));

        // Escuchador para detectar selección en la tabla de clientes
        this.vistaPrincipal.getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.vistaPrincipal.getTablaClientes().getSelectedRow();
                boolean haySeleccion = filaSeleccionada != -1;

                if (haySeleccion) {
                    // Leemos la columna 5, que corresponde al "Estado" del cliente
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

    // --- MÉTODOS DE LÓGICA Y NAVEGACIÓN ---

    private void abrirFormularioVenta() {
        // Instanciamos la ventana emergente de ventas
        RegistroVentaUI ventanaVenta = new RegistroVentaUI(this.vistaPrincipal);

        // Le damos comportamiento al botón de confirmar
        ventanaVenta.getBtnConfirmarVenta().addActionListener(e -> {
            // Leemos los datos de la interfaz
            String dniCliente = ventanaVenta.getDniClienteField().getText();
            int productoSeleccionado = ventanaVenta.getProductoBox().getSelectedIndex();
            String cantidad = ventanaVenta.getCantidadField().getText();

            // Validación estricta de campos vacíos
            if (dniCliente.isEmpty() || productoSeleccionado == 0 || cantidad.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaVenta,
                    "Debes ingresar el DNI del cliente, seleccionar un producto y especificar la cantidad.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // A futuro: Lógica para verificar que el DNI exista en la base de datos

            // Confirmación exitosa
            JOptionPane.showMessageDialog(ventanaVenta,
                "Venta registrada exitosamente para el DNI:\n" + dniCliente,
                "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);

            ventanaVenta.dispose(); // Cerramos la ventana de registro de venta
        });

        // Mostramos la ventana
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
            // Destruimos la ventana actual
            vistaPrincipal.dispose();

            // Volvemos a levantar el Login aplicando MVC
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin);
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

            // Actualizamos la tabla visualmente en la columna de Estado (Columna 5)
            vistaPrincipal.getTablaClientes().setValueAt(nuevoEstado, fila, 5);

            // Refrescamos la selección para que el escuchador actualice el botón correspondiente
            vistaPrincipal.getTablaClientes().clearSelection();
            vistaPrincipal.getTablaClientes().setRowSelectionInterval(fila, fila);

            JOptionPane.showMessageDialog(vistaPrincipal, "El estado del cliente se actualizó a: " + nuevoEstado, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
