import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Celular;
import repositorio.CelularRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Levantamos el túnel con MySQL (esto luego se puede aislar en una clase Singleton)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NexcellPU");
        EntityManager em = emf.createEntityManager();

        // 2. Instanciamos el repositorio
        CelularRepository inventarioRepo = new CelularRepository(em);

        try {
            // 3. Cargamos un nuevo equipo con POO puro
            Celular samsung = new Celular("Samsung", "Galaxy S24", 10);
            inventarioRepo.guardar(samsung);
            System.out.println("✅ Nuevo equipo registrado.");

            // 4. Leemos la base de datos completa y la mostramos
            System.out.println("\n--- INVENTARIO NEXCELL ---");
            List<Celular> listaEquipos = inventarioRepo.listarTodos();

            for (Celular equipo : listaEquipos) {
                System.out.println("> " + equipo.getMarca() + " " + equipo.getModelo() + " | Stock: " + equipo.getStock());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerramos la conexión
            em.close();
            emf.close();
        }
    }
}
