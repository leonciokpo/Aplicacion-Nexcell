package modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import vista.VendedorUI;
import controlador.VendedorController;

@Entity
@DiscriminatorValue("VENDEDOR") // Valor para la columna "rol"
public class Vendedor extends Usuario {

    public Vendedor() {}

    public Vendedor(String username, String password) {
        super(username, password);
    }

    @Override
    public void mostrarInterfaz(jakarta.persistence.EntityManager em) {
        VendedorUI vendedorVista = new VendedorUI();
        new VendedorController(vendedorVista, em);
        vendedorVista.setVisible(true);
    }
}
