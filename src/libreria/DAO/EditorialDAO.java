
package libreria.DAO;

import java.util.List;
import libreria.entidades.Editorial;



public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    @Override
    public void editar(Editorial editorial) {
        super.editar(editorial);
    }

    public void eliminar(Long id) throws Exception {
        Editorial editorial = buscarPorId(id);
        super.eliminar(editorial);
    }

    public Editorial buscarPorId(Long id) throws Exception {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }

    public Editorial buscarPorNombre(String nombre) throws Exception {
        conectar();//si no funciona, agregar el "set parameter"
        Editorial editorial =(Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre").getSingleResult();
        desconectar();
        return editorial;
    }

//    public List<Editorial> listarTodos() throws Exception {
//        conectar();
//        List<Editorial> editoriales = em.createQuery("SELECT a FROM Editorial a")
//                .getResultList();
//        desconectar();
//        return editoriales;
//    }
//    
}
