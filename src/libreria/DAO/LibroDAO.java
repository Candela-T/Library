
package libreria.DAO;

import java.util.List;
import libreria.entidades.Libro;



public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    @Override
    public void editar(Libro libro) {
        super.editar(libro);
    }


    public void eliminar(Long id) throws Exception {
        Libro libro = buscarPorId(id);
        super.eliminar(libro);
    }

    public Libro buscarPorId(Long id) throws Exception {
        conectar();
        Libro libro = em.find(Libro.class, id);
        desconectar();
        return libro;
    }

    public Libro buscarPorTitulo(String titulo) throws Exception {
        conectar();
        Libro libro =(Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }

    public List<Libro> listarTodosPorAutor(String nombreAutor) throws Exception {
        conectar();
        List<Libro> autores = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre = :nombreAutor")
                .setParameter("nombreAutor", nombreAutor)
                .getResultList();
        desconectar();
        return autores;
    }

    public List<Libro> listarTodosPorEditorial(String nombreEditorial) throws Exception {
        conectar();
        List<Libro> autores = em.createQuery("SELECT l FROM Libro l  WHERE l.editorial.nombre LIKE :nombreEditorial")
                .setParameter("nombreEditorial", nombreEditorial)
                .getResultList();
        desconectar();
        return autores;
    }

//    public List<Libro> listarTodos() throws Exception {
//        conectar();
//        List<Libro> libros = em.createQuery("SELECT d FROM libro d")
//                .getResultList();
//        desconectar();
//        return libros;
//    }
}
