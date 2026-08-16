package modelo;

import vista.AdminUI;

public class Admin extends Usuario {

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        AdminUI adminPanel = new AdminUI();
        adminPanel.setVisible(true);
    }
}
