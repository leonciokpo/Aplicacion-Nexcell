package repositorio;

import jakarta.persistence.EntityManager;
import modelo.Usuario;
import java.util.List;

public class UsuarioRepository {

    private EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public void guardar(Usuario usuario) {
        em.persist(usuario);
    }

    public List<Usuario> listarTodos() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
