package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo; // Ej: "CEL-001"

    @Column(nullable = false)
    private String nombre;

    private String categoria;

    private int stock;

    private double precio;

    private boolean activo;

    // Hibernate exige un constructor vacío
    public Producto() {}

    public Producto(String codigo, String nombre, String categoria, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.activo = true; // Por defecto nace activo
    }

    // Getters
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public double getPrecio() { return precio; }
    public boolean isActivo() { return activo; }

    // Setters
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setStock(int stock) { this.stock = stock; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
