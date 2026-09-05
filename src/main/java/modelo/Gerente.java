package modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import vista.GerenteUI;
import controlador.GerenteController;

@Entity
@DiscriminatorValue("GERENTE") // Así va a aparecer en la columna "rol" de MySQL
public class Gerente extends Usuario {

    public Gerente() {} // Constructor vacío para JPA

    public Gerente(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz(jakarta.persistence.EntityManager em) {
        GerenteUI gerenteVista = new GerenteUI();
        new GerenteController(gerenteVista, em);
        gerenteVista.setVisible(true);
    }
}
