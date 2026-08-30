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

        // Escuchadores de Usuarios
        this.vistaPrincipal.getBtnAbrirFormularioUsuario().addActionListener(e -> abrirFormularioUsuario());
        this.vistaPrincipal.getBtnModificarUsuario().addActionListener(e -> modificarUsuario());
        this.vistaPrincipal.getBtnBajaUsuario().addActionListener(e -> cambiarEstadoUsuario(false));
        this.vistaPrincipal.getBtnAltaUsuario().addActionListener(e -> cambiarEstadoUsuario(true));

        // Escuchadores de Reportes y Sesión
        this.vistaPrincipal.getBtnGenerarReporte().addActionListener(e -> generarReporte());
        this.vistaPrincipal.getBtnLimpiarReporte().addActionListener(e -> limpiarReporte());
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    // --- LÓGICA DE PRODUCTOS ---

    private void abrirFormularioProducto() {
        RegistroProductoUI ventanaRegistro = new RegistroProductoUI(this.vistaPrincipal);

        ventanaRegistro.getBtnGuardarProducto().addActionListener(e -> {
            String idProducto = ventanaRegistro.getIdProductoField().getText();
            String nombre = ventanaRegistro.getNombreField().getText();

            if (idProducto.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Por favor, completá al menos el ID del Producto y el Nombre.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
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

        // Reutilizamos el formulario de alta, pero lo adaptamos para edición
        RegistroProductoUI ventanaModificacion = new RegistroProductoUI(this.vistaPrincipal);
        ventanaModificacion.setTitle("Modificar Producto Existente");
        ventanaModificacion.getBtnGuardarProducto().setText("Actualizar Datos");

        // Acá, a futuro, extraerías los datos de la BD usando el ID de la fila seleccionada
        // Por ahora, simulamos la precarga con el ID de la tabla
        String idSeleccionado = vistaPrincipal.getTablaProductos().getValueAt(fila, 0).toString();
        ventanaModificacion.getIdProductoField().setText(idSeleccionado);
        ventanaModificacion.getIdProductoField().setEditable(false); // La PK no se modifica

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
            // Acá ejecutarías el UPDATE en la BD: UPDATE Productos SET estado = activar WHERE id_producto = idSeleccionado
            String nuevoEstado = activar ? "Activo" : "Inactivo";
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
        ventanaModificacion.getUsernameField().setEditable(false); // El username suele ser intocable

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
            JOptionPane.showMessageDialog(vistaPrincipal, "Estado del usuario actualizado a " + (activar ? "Activo" : "Inactivo") + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
