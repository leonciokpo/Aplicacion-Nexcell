package modelo;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol") // Esta columna diferenciará los tipos de usuario
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // JPA necesita una clave primaria

    private String username;
    private String password;

    // JPA exige siempre un constructor vacío
    public Usuario() {}

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public abstract void mostrarInterfaz(jakarta.persistence.EntityManager em);
}
