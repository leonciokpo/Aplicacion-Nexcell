package repositorio;

import jakarta.persistence.EntityManager;
import modelo.Celular;
import java.util.List;

public class CelularRepository {

    private EntityManager em;

    // Inyectamos la conexión al instanciar el repositorio
    public CelularRepository(EntityManager em) {
        this.em = em;
    }

    // CREATE: Guarda un equipo nuevo
    public void guardar(Celular celular) {
        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
    }

    // READ: Busca un equipo específico por su ID
    public Celular buscarPorId(Long id) {
        return em.find(Celular.class, id);
    }

    // READ: Trae todo el inventario de la base de datos
    public List<Celular> listarTodos() {
        // Acá usamos JPQL (Java Persistence Query Language).
        // No consultamos la tabla 'equipos', consultamos la clase 'Celular'
        return em.createQuery("SELECT c FROM Celular c", Celular.class).getResultList();
    }
}
