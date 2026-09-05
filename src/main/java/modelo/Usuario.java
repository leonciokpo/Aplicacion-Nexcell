package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public abstract class Usuario extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean estado; // true = activo, false = inactivo

    public Usuario() {
        this.estado = true; // Estado activo por defecto al instanciar
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.estado = true; // Estado activo por defecto
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public abstract void mostrarInterfaz(jakarta.persistence.EntityManager em);
}
