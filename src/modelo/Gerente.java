package modelo;

import vista.GerenteUI;

public class Gerente extends Usuario {

    public Gerente(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        GerenteUI gerentePanel = new GerenteUI();
        gerentePanel.setVisible(true);
    }
}
