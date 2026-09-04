package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Celular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    private int stock;

    // Constructor vacío obligatorio para Hibernate
    public Celular() {}

    // Constructor para instanciar nosotros el objeto
    public Celular(String marca, String modelo, int stock) {
        this.marca = marca;
        this.modelo = modelo;
        this.stock = stock;
    }

    // Usá Alt + Insert (o clic derecho -> Generate) para crear los Getters y Setters
}
