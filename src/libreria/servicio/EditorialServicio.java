package libreria.servicio;

import libreria.DAO.EditorialDAO;
import libreria.entidades.Editorial;

public class EditorialServicio {

    private EditorialDAO editorialDao;

    public EditorialServicio() {
        this.editorialDao = new EditorialDAO();
    }

    public void darBajaEditorial(Long id) {
        try {
            Editorial editorial = editorialDao.buscarPorId(id);
            editorial.setAlta(false);
            editorialDao.editar(editorial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darAltaEditorial(Long id) {
        try {
            Editorial editorial = editorialDao.buscarPorId(id);
            editorial.setAlta(true);
            editorialDao.editar(editorial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void crearEditorial(Editorial editorial) {
            editorialDao.guardar(editorial);
          
    }

    public void editarNombreEditorial(Long id, String nombre) throws Exception {
        try {
            Editorial editorial = editorialDao.buscarPorId(id);
            editorial.setNombre(nombre);
            editorialDao.editar(editorial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public Editorial busquedaEditorialPorId(Long id) {
        try {
            return editorialDao.buscarPorId(id);
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
     
}
