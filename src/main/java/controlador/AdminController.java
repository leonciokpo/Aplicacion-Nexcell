package controlador;

import vista.AdminUI;
import vista.RegistroProductoUI;
import vista.RegistroUsuarioUI;
import vista.LoginUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminController {

    private AdminUI vistaPrincipal;

    public AdminController(AdminUI vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;

        // Escuchadores de Productos
        this.vistaPrincipal.getBtnAbrirFormularioProducto().addActionListener(e -> abrirFormularioProducto());
        this.vistaPrincipal.getBtnModificarProducto().addActionListener(e -> modificarProducto());
        this.vistaPrincipal.getBtnBajaProducto().addActionListener(e -> cambiarEstadoProducto(false));
        this.vistaPrincipal.getBtnAltaProducto().addActionListener(e -> cambiarEstadoProducto(true));

        // Escuchadores para la tabla de productos
        this.vistaPrincipal.getTablaProductos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.vistaPrincipal.getTablaProductos().getSelectedRow();
                boolean haySeleccion = filaSeleccionada != -1;

                this.vistaPrincipal.getBtnModificarProducto().setVisible(haySeleccion);

                if (haySeleccion) {
                    // Leemos la columna 5, que corresponde al "Estado"
                    String estadoActual = this.vistaPrincipal.getTablaProductos().getValueAt(filaSeleccionada, 5).toString();

                    if (estadoActual.equalsIgnoreCase("Activo")) {
                        this.vistaPrincipal.getBtnBajaProducto().setVisible(true);
                        this.vistaPrincipal.getBtnAltaProducto().setVisible(false);
                    } else {
                        this.vistaPrincipal.getBtnBajaProducto().setVisible(false);
                        this.vistaPrincipal.getBtnAltaProducto().setVisible(true);
                    }
                } else {
                    this.vistaPrincipal.getBtnBajaProducto().setVisible(false);
                    this.vistaPrincipal.getBtnAltaProducto().setVisible(false);
                }
            }
        });

        // Escuchadores de Usuarios
        this.vistaPrincipal.getBtnAbrirFormularioUsuario().addActionListener(e -> abrirFormularioUsuario());
        this.vistaPrincipal.getBtnModificarUsuario().addActionListener(e -> modificarUsuario());
        this.vistaPrincipal.getBtnBajaUsuario().addActionListener(e -> cambiarEstadoUsuario(false));
        this.vistaPrincipal.getBtnAltaUsuario().addActionListener(e -> cambiarEstadoUsuario(true));

        // Escuchadores para la tabla de usuarios
        this.vistaPrincipal.getTablaUsuarios().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.vistaPrincipal.getTablaUsuarios().getSelectedRow();
                boolean haySeleccion = filaSeleccionada != -1;

                this.vistaPrincipal.getBtnModificarUsuario().setVisible(haySeleccion);

                if (haySeleccion) {
                    // Leemos la columna 2, que corresponde al "Estado" en Usuarios
                    String estadoActual = this.vistaPrincipal.getTablaUsuarios().getValueAt(filaSeleccionada, 2).toString();

                    if (estadoActual.equalsIgnoreCase("Activo")) {
                        this.vistaPrincipal.getBtnBajaUsuario().setVisible(true);
                        this.vistaPrincipal.getBtnAltaUsuario().setVisible(false);
                    } else {
                        this.vistaPrincipal.getBtnBajaUsuario().setVisible(false);
                        this.vistaPrincipal.getBtnAltaUsuario().setVisible(true);
                    }
                } else {
                    this.vistaPrincipal.getBtnBajaUsuario().setVisible(false);
                    this.vistaPrincipal.getBtnAltaUsuario().setVisible(false);
                }
            }
        });

        // Escuchadores de Reportes y Sesión
        this.vistaPrincipal.getBtnGenerarReporte().addActionListener(e -> generarReporte());
        this.vistaPrincipal.getBtnLimpiarReporte().addActionListener(e -> limpiarReporte());
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    // --- LÓGICA DE PRODUCTOS ---

    private void abrirFormularioProducto() {
        RegistroProductoUI ventanaRegistro = new RegistroProductoUI(this.vistaPrincipal);

        ventanaRegistro.getBtnGuardarProducto().addActionListener(e -> {
            String nombre = ventanaRegistro.getNombreField().getText();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Por favor, completá al menos el Nombre del Producto.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(ventanaRegistro, "Producto '" + nombre + "' registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaRegistro.dispose();
        });

        ventanaRegistro.setVisible(true);
    }

    private void modificarProducto() {
        int fila = vistaPrincipal.getTablaProductos().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccioná un producto de la tabla para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RegistroProductoUI ventanaModificacion = new RegistroProductoUI(this.vistaPrincipal);
        ventanaModificacion.setTitle("Modificar Producto Existente");
        ventanaModificacion.getBtnGuardarProducto().setText("Actualizar Datos");

        // Extraemos el ID y el Nombre de la tabla
        String idSeleccionado = vistaPrincipal.getTablaProductos().getValueAt(fila, 0).toString();
        String nombreActual = vistaPrincipal.getTablaProductos().getValueAt(fila, 1).toString();

        // Precargamos el nombre visualmente
        ventanaModificacion.getNombreField().setText(nombreActual);

        ventanaModificacion.getBtnGuardarProducto().addActionListener(e -> {
            JOptionPane.showMessageDialog(ventanaModificacion, "El producto " + idSeleccionado + " fue actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            ventanaModificacion.dispose();
        });

        ventanaModificacion.setVisible(true);
    }

    private void cambiarEstadoProducto(boolean activar) {
        int fila = vistaPrincipal.getTablaProductos().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccioná un producto de la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idSeleccionado = vistaPrincipal.getTablaProductos().getValueAt(fila, 0).toString();
        String accion = activar ? "Reactivar (Dar de Alta)" : "Dar de Baja (Inactivar)";

        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
                "¿Estás seguro que querés " + accion.toLowerCase() + " el producto " + idSeleccionado + "?",
                accion, JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String nuevoEstado = activar ? "Activo" : "Inactivo";

            // Actualizamos la tabla visualmente para reflejar el cambio (Fila seleccionada, Columna 5)
            vistaPrincipal.getTablaProductos().setValueAt(nuevoEstado, fila, 5);

            // Refrescamos la selección para que el escuchador detecte el nuevo estado y cambie el botón
            vistaPrincipal.getTablaProductos().clearSelection();
            vistaPrincipal.getTablaProductos().setRowSelectionInterval(fila, fila);

            JOptionPane.showMessageDialog(vistaPrincipal, "El estado del producto se actualizó a: " + nuevoEstado, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // --- LÓGICA DE USUARIOS ---

    private void abrirFormularioUsuario() {
        RegistroUsuarioUI ventanaRegistro = new RegistroUsuarioUI(this.vistaPrincipal);
        ventanaRegistro.getBtnGuardarUsuario().addActionListener(e -> {
            JOptionPane.showMessageDialog(ventanaRegistro, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaRegistro.dispose();
        });
        ventanaRegistro.setVisible(true);
    }

    private void modificarUsuario() {
        int fila = vistaPrincipal.getTablaUsuarios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccioná un usuario de la tabla para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RegistroUsuarioUI ventanaModificacion = new RegistroUsuarioUI(this.vistaPrincipal);
        ventanaModificacion.setTitle("Modificar Usuario");
        ventanaModificacion.getBtnGuardarUsuario().setText("Actualizar Usuario");

        String userSeleccionado = vistaPrincipal.getTablaUsuarios().getValueAt(fila, 0).toString();
        ventanaModificacion.getUsernameField().setText(userSeleccionado);
        ventanaModificacion.getUsernameField().setEditable(false);

        ventanaModificacion.getBtnGuardarUsuario().addActionListener(e -> {
            JOptionPane.showMessageDialog(ventanaModificacion, "Usuario actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            ventanaModificacion.dispose();
        });

        ventanaModificacion.setVisible(true);
    }

    private void cambiarEstadoUsuario(boolean activar) {
        int fila = vistaPrincipal.getTablaUsuarios().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaPrincipal, "Seleccioná un usuario de la tabla.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String userSeleccionado = vistaPrincipal.getTablaUsuarios().getValueAt(fila, 0).toString();
        String accion = activar ? "Reactivar" : "Inactivar";

        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
                "¿Estás seguro que querés " + accion.toLowerCase() + " al usuario " + userSeleccionado + "?",
                "Confirmar Cambio de Estado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String nuevoEstado = activar ? "Activo" : "Inactivo";

            // Actualizamos la tabla visualmente (Fila seleccionada, Columna 2)
            vistaPrincipal.getTablaUsuarios().setValueAt(nuevoEstado, fila, 2);

            // Refrescamos la selección para que el escuchador detecte el nuevo estado
            vistaPrincipal.getTablaUsuarios().clearSelection();
            vistaPrincipal.getTablaUsuarios().setRowSelectionInterval(fila, fila);

            JOptionPane.showMessageDialog(vistaPrincipal, "Estado del usuario actualizado a " + nuevoEstado + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // --- GENERAL Y REPORTES ---

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
                "¿Estás seguro que querés salir del panel de administración?", "Cerrar Sesión",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vistaPrincipal.dispose();
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin);
            ventanaLogin.setVisible(true);
        }
    }

    private void generarReporte() {
        String reporte = vistaPrincipal.getComboReportes().getSelectedItem().toString();
        if (reporte.equals("Stock de productos")) {
            reporteStock();
        } else if (reporte.equals("Productos registrados")) {
            reporteProductos();
        } else if (reporte.equals("Usuarios del sistema")) {
            reporteUsuarios();
        } else {
            reporteMovimientos();
        }
    }

    private void limpiarReporte() {
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel());
    }

    private void reporteStock() {
        String[] columnas = {"Código", "Producto", "Stock", "Precio", "Estado"};
        Object[][] datos = {
                {"CEL001", "Motorola Edge 60", 15, "$850.000", "Normal"}
        };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteProductos() {
        String[] columnas = {"Código", "Producto", "Categoría", "Precio"};
        Object[][] datos = {
                {"CEL001", "Motorola Edge 60", "Celulares", "$850.000"}
        };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteUsuarios() {
        String[] columnas = {"Usuario", "Rol", "Estado"};
        Object[][] datos = {
                {"admin", "Administrador", "Activo"}
        };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteMovimientos() {
        String[] columnas = {"Fecha", "Producto", "Movimiento", "Cantidad", "Usuario"};
        Object[][] datos = {
                {"28/08/2026", "Motorola Edge 60", "Entrada", "+10", "admin"}
        };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }
}