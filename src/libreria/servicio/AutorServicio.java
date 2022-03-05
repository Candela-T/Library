
package libreria.servicio;

import libreria.DAO.AutorDAO;
import libreria.entidades.Autor;

public class AutorServicio {

    private AutorDAO autorDao;//atributo

    public AutorServicio() {
        this.autorDao = new AutorDAO();
    }

    public Autor crearAutor(Autor autor) {
        try {
            autorDao.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void darBajaAutor(Long id) {
        try {
            Autor autor = autorDao.buscarPorId(id);
            autor.setAlta(false);
            autorDao.editar(autor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darAltaAutor(Long id) {
        try {
            Autor autor = autorDao.buscarPorId(id);
            autor.setAlta(true);
            autorDao.editar(autor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Autor busquedaAutorPorId(Long id) {
        try {
            return autorDao.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        try {
            return autorDao.buscarPorNombre(nombre);

        } catch ( Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarNombreAutor(Long id, String nombre) throws Exception {
        try {
            Autor autor = autorDao.buscarPorId(id);
            autor.setNombre(nombre);
            autorDao.editar(autor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
