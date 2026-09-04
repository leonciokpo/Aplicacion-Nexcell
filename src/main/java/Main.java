import controlador.LoginController;
import vista.LoginUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        // Ejecutamos la interfaz gráfica en el hilo seguro de Swing
        SwingUtilities.invokeLater(() -> {
            // 1. Creamos la ventana de Login
            LoginUI ventanaLogin = new LoginUI();

            // 2. Le conectamos su controlador
            LoginController controlador = new LoginController(ventanaLogin);

            // 3. Hacemos visible la ventana
            ventanaLogin.setVisible(true);
        });

    }
}