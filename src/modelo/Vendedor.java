package modelo;

import vista.VendedorUI;

public class Vendedor extends Usuario {

    public Vendedor(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        VendedorUI vendedorPanel = new VendedorUI();
        vendedorPanel.setVisible(true);
    }
}
