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

    // UPDATE: Actualiza un registro existente en la base de datos
    public void actualizar(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario); // merge() es el equivalente a UPDATE en JPA
        em.getTransaction().commit();
    }

    // READ: Busca usuarios que coincidan con un texto (Username, DNI, Nombre o Apellido)
    public List<Usuario> buscarPorTexto(String texto) {
        return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username LIKE :texto OR u.dni LIKE :texto OR u.nombre LIKE :texto OR u.apellido LIKE :texto",
                Usuario.class)
            .setParameter("texto", "%" + texto + "%")
            .getResultList();
    }

    // REPORTES: Busca usuarios registrados en un rango de fechas
    public List<Usuario> reporteUsuariosPorFecha(java.time.LocalDate desde, java.time.LocalDate hasta) {
        return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.fechaRegistro BETWEEN :inicio AND :fin", Usuario.class)
            .setParameter("inicio", desde)
            .setParameter("fin", hasta)
            .getResultList();
    }

    public boolean existeDni(String dni) {
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.dni = :dni", Long.class)
                .setParameter("dni", dni)
                .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // READ: Busca un usuario específico por su username
    public Usuario buscarPorUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :user", Usuario.class)
                .setParameter("user", username)
                .getSingleResult();
        } catch (Exception e) {
            return null; // Si no lo encuentra, devuelve null
        }
    }
}
