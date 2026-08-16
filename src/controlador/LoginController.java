package controlador;

import modelo.Usuario;
import modelo.Admin;
import modelo.Gerente;
import modelo.Vendedor;
import vista.LoginUI;

import javax.swing.*;

public class LoginController {

    private LoginUI vista;

    public LoginController(LoginUI vista) {
        this.vista = vista;

        this.vista.getLoginButton().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String user = vista.getUserField().getText();
        String pass = new String(vista.getPassField().getPassword());

        Usuario usuarioAutenticado = simularBaseDeDatos(user, pass);

        if (usuarioAutenticado != null) {
            usuarioAutenticado.mostrarInterfaz();
            vista.dispose(); // Cerramos el login
        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Usuario simularBaseDeDatos(String user, String pass) {
        if (user.equals("admin") && pass.equals("123")) {
            return new Admin(user, pass);
        } else if (user.equals("gerente") && pass.equals("123")) {
            return new Gerente(user, pass);
        } else if (user.equals("vendedor") && pass.equals("123")) {
            return new Vendedor(user, pass);
        }
        return null;
    }
}
