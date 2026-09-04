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

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
