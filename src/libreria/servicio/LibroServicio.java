
package libreria.servicio;


import java.util.List;
import java.util.Scanner;
import libreria.DAO.LibroDAO;
import libreria.entidades.Libro;

public class LibroServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final LibroDAO libroDao;//atributo

    public LibroServicio() {//constructor
        this.libroDao = new LibroDAO();

    }

    public void darBajaLibro(Long id) {
        try {
            Libro libro = libroDao.buscarPorId(id);
            libro.setAlta(false);
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darAltaLibro(Long id) {
        try {
            Libro libro = libroDao.buscarPorId(id);
            libro.setAlta(true);
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Libro busquedaPorIdLibro(Long id) throws Exception {
        try {
            return libroDao.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }

    }

    public Libro busquedaPorTituloLibro(String nombre) throws Exception {
        try {
            return libroDao.buscarPorTitulo(nombre);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            //return new ArrayList(); ES MEJOR PRACTICA************** eN LISTAS
        }
    }

    public List<Libro> busquedaLibroNombreAutor(String nombreAutor) {
        try {
            return libroDao.listarTodosPorAutor(nombreAutor);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }
    }

    public List<Libro> busquedaLibroPorEditorial(String nombreEditorial) throws Exception {
        try {
            return libroDao.listarTodosPorEditorial(nombreEditorial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Libro crearLibro(Libro libro) {
        try {
            libroDao.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void editarNombreLibro(Libro libro, String nombre) {
        try {
            libro.setTitulo(nombre);
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarAnioLibro(Libro libro, int anio) {
        try {
            libro.setAnio(anio);
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarEjemplares(Libro libro, int ejemplares) {
        try {
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void editarEjemplaresPrestados(Libro libro, int ejempPrestados) {
        try {
            libro.setEjemplaresPrestados(ejempPrestados);
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            libroDao.editar(libro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
