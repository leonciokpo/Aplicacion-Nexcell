import controlador.LoginController;
import vista.LoginUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LoginUI ventanaLogin = new LoginUI();

            LoginController controlador = new LoginController(ventanaLogin);

            ventanaLogin.setVisible(true);

        });
    }
}
