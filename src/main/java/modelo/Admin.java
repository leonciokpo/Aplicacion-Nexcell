package modelo;

import vista.AdminUI;
import controlador.AdminController; // Importamos el nuevo cerebro

public class Admin extends Usuario {

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        // 1. Creamos la vista
        AdminUI adminVista = new AdminUI();

        // 2. Le enchufamos el controlador
        AdminController controlador = new AdminController(adminVista);

        // 3. Hacemos visible la ventana
        adminVista.setVisible(true);
    }
}
