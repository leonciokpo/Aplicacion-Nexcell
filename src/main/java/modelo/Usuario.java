package modelo;

public abstract class Usuario {
    protected String username;
    protected String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public abstract void mostrarInterfaz();

    // Getters
    public String getUsername() { return username; }
}
