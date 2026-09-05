package controlador;

import vista.AdminUI;
import vista.RegistroProductoUI;
import vista.RegistroUsuarioUI;
import vista.LoginUI;
import modelo.Admin;
import modelo.Gerente;
import modelo.Vendedor;
import modelo.Usuario;
import repositorio.UsuarioRepository;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.DateTimeException;

public class AdminController {

    private AdminUI vistaPrincipal;
    private EntityManager em;

    public AdminController(AdminUI vistaPrincipal, EntityManager em) {
        this.vistaPrincipal = vistaPrincipal;
        this.em = em;

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

        String idSeleccionado = vistaPrincipal.getTablaProductos().getValueAt(fila, 0).toString();
        String nombreActual = vistaPrincipal.getTablaProductos().getValueAt(fila, 1).toString();

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
            vistaPrincipal.getTablaProductos().setValueAt(nuevoEstado, fila, 5);
            vistaPrincipal.getTablaProductos().clearSelection();
            vistaPrincipal.getTablaProductos().setRowSelectionInterval(fila, fila);
            JOptionPane.showMessageDialog(vistaPrincipal, "El estado del producto se actualizó a: " + nuevoEstado, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void abrirFormularioUsuario() {
        RegistroUsuarioUI ventanaRegistro = new RegistroUsuarioUI(this.vistaPrincipal);
        UsuarioRepository usuarioRepo = new UsuarioRepository(this.em);

        ventanaRegistro.getBtnGuardarUsuario().addActionListener(e -> {
            // 1. Lectura de los campos de texto
            String nombre = ventanaRegistro.getTxtNombre().getText().trim();
            String apellido = ventanaRegistro.getTxtApellido().getText().trim();
            String username = ventanaRegistro.getTxtUsername().getText().trim();
            String password = new String(ventanaRegistro.getTxtPassword().getPassword());
            String email = ventanaRegistro.getTxtEmail().getText().trim();
            String dni = ventanaRegistro.getTxtDni().getText().trim();
            String direccion = ventanaRegistro.getTxtDireccion().getText().trim();

            String rol = ventanaRegistro.getCbPerfil().getSelectedItem().toString();

            // 2. Validación estricta de campos vacíos
            if (username.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Por favor, complete al menos Nombre, Apellido, Username y Contraseña.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Capturar y parsear la fecha de los ComboBox
            int dia = (int) ventanaRegistro.getCbDia().getSelectedItem();
            int mes = Integer.parseInt(ventanaRegistro.getCbMes().getSelectedItem().toString());
            int anio = (int) ventanaRegistro.getCbAnio().getSelectedItem();

            LocalDate fechaNac = null;
            try {
                fechaNac = LocalDate.of(anio, mes, dia);
            } catch (DateTimeException ex) {
                JOptionPane.showMessageDialog(ventanaRegistro, "La fecha seleccionada no existe en el calendario.", "Fecha Inválida", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Instanciación basada en la jerarquía JPA
            Usuario nuevoUsuario;
            if (rol.equals("Administrador") || rol.equals("Admin")) {
                nuevoUsuario = new Admin(username, password);
            } else if (rol.equals("Gerente")) {
                nuevoUsuario = new Gerente(username, password);
            } else {
                nuevoUsuario = new Vendedor(username, password);
            }

            // 5. Mapeo de atributos heredados de Persona
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setDni(dni);
            nuevoUsuario.setDireccion(direccion);
            nuevoUsuario.setFechaNacimiento(fechaNac);

            // 6. Guardado Transaccional Protegido (Una única vez y limpio)
            try {
                // Si hay una transacción fantasma previa, la limpiamos
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }

                em.getTransaction().begin();
                usuarioRepo.guardar(nuevoUsuario);
                em.getTransaction().commit();

                JOptionPane.showMessageDialog(ventanaRegistro, "Usuario '" + username + "' registrado exitosamente en MySQL.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ventanaRegistro.dispose();

            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                JOptionPane.showMessageDialog(ventanaRegistro, "Error al guardar en la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
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
        // Asegúrate de usar los nuevos nombres de getters
        ventanaModificacion.getTxtUsername().setText(userSeleccionado);
        ventanaModificacion.getTxtUsername().setEditable(false);

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
            vistaPrincipal.getTablaUsuarios().setValueAt(nuevoEstado, fila, 2);
            vistaPrincipal.getTablaUsuarios().clearSelection();
            vistaPrincipal.getTablaUsuarios().setRowSelectionInterval(fila, fila);
            JOptionPane.showMessageDialog(vistaPrincipal, "Estado del usuario actualizado a " + nuevoEstado + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
            "¿Estás seguro que querés salir del panel de administración?", "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vistaPrincipal.dispose();
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin, em);
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
        Object[][] datos = { {"CEL001", "Motorola Edge 60", 15, "$850.000", "Normal"} };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteProductos() {
        String[] columnas = {"Código", "Producto", "Categoría", "Precio"};
        Object[][] datos = { {"CEL001", "Motorola Edge 60", "Celulares", "$850.000"} };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteUsuarios() {
        String[] columnas = {"Usuario", "Rol", "Estado"};
        Object[][] datos = { {"admin", "Administrador", "Activo"} };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }

    private void reporteMovimientos() {
        String[] columnas = {"Fecha", "Producto", "Movimiento", "Cantidad", "Usuario"};
        Object[][] datos = { {"28/08/2026", "Motorola Edge 60", "Entrada", "+10", "admin"} };
        vistaPrincipal.getTablaReportes().setModel(new DefaultTableModel(datos, columnas));
    }
}
