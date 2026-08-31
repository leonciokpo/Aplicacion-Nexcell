package modelo;

import vista.GerenteUI;
import controlador.GerenteController; // Esta es la línea que faltaba

public class Gerente extends Usuario {

    public Gerente(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz() {
        // 1. Creamos la vista
        GerenteUI gerenteVista = new GerenteUI();

        // 2. Le enchufamos el controlador
        new GerenteController(gerenteVista);

        // 3. Hacemos visible la ventana
        gerenteVista.setVisible(true);
    }
}