package modelo;

import vista.VendedorUI;
import controlador.VendedorController; // Importamos el nuevo controlador

public class Vendedor extends Usuario {

    public Vendedor(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        // 1. Creamos la vista
        VendedorUI vendedorVista = new VendedorUI();

        // 2. Creamos el cerebro y le pasamos la vista
        VendedorController controlador = new VendedorController(vendedorVista);

        // 3. Recién ahora la hacemos visible
        vendedorVista.setVisible(true);
    }
}
