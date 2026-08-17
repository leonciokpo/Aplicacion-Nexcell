package controlador;

import vista.AdminUI;
import vista.RegistroProductoUI;
import vista.RegistroUsuarioUI;
import vista.LoginUI;

import javax.swing.*;

public class AdminController {

    private AdminUI vistaPrincipal;

    public AdminController(AdminUI vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;

        // Escuchamos los botones de la vista principal
        this.vistaPrincipal.getBtnAbrirFormularioProducto().addActionListener(e -> abrirFormularioProducto());
        this.vistaPrincipal.getBtnAbrirFormularioUsuario().addActionListener(e -> abrirFormularioUsuario());

        // Escuchamos el botón de cerrar sesión
        this.vistaPrincipal.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void abrirFormularioProducto() {
        // Instanciamos la ventana emergente
        RegistroProductoUI ventanaRegistro = new RegistroProductoUI(this.vistaPrincipal);

        // Lógica del botón guardar de esa ventana
        ventanaRegistro.getBtnGuardarProducto().addActionListener(e -> {
            String codigo = ventanaRegistro.getCodigoField().getText();
            String modelo = ventanaRegistro.getModeloField().getText();

            // Validación básica
            if (codigo.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "Por favor, completá al menos el Código y el Modelo.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Simulamos guardado en sistema
            JOptionPane.showMessageDialog(ventanaRegistro, "Producto '" + modelo + "' registrado con éxito en el catálogo de Nexcell.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaRegistro.dispose(); // Cerramos la ventanita
        });

        ventanaRegistro.setVisible(true);
    }

    private void abrirFormularioUsuario() {
        RegistroUsuarioUI ventanaRegistro = new RegistroUsuarioUI(this.vistaPrincipal);

        ventanaRegistro.getBtnGuardarUsuario().addActionListener(e -> {
            String user = ventanaRegistro.getUsernameField().getText();
            String pass = new String(ventanaRegistro.getPasswordField().getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(ventanaRegistro, "El usuario y la contraseña son obligatorios.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(ventanaRegistro, "Usuario registrado correctamente en el sistema.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ventanaRegistro.dispose();
        });

        ventanaRegistro.setVisible(true);
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaPrincipal,
            "¿Estás seguro que querés salir del panel de administración?", "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Destruimos la ventana del admin
            vistaPrincipal.dispose();

            // Volvemos a instanciar el login aplicando MVC
            LoginUI ventanaLogin = new LoginUI();
            new LoginController(ventanaLogin);
            ventanaLogin.setVisible(true);
        }
    }
}
