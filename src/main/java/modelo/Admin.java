package modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import vista.AdminUI;
import controlador.AdminController;

@Entity
@DiscriminatorValue("ADMIN") // Valor para la columna "rol"
public class Admin extends Usuario {

    public Admin() {}

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz(jakarta.persistence.EntityManager em) {
        AdminUI adminVista = new AdminUI();
        new AdminController(adminVista, em);
        adminVista.setVisible(true);
    }
}
