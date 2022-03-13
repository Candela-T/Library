package libreria;

import libreria.servicio.AutorServicio;
import libreria.servicio.EditorialServicio;
import libreria.servicio.LibroServicio;


public class Libreria {
  
    public static void main(String[] args) throws Exception {
        
        LibroServicio libroServicio = new LibroServicio();
        EditorialServicio editorialServicio = new EditorialServicio();
        AutorServicio autorServicio = new AutorServicio();
        
        Menu menu = new Menu(libroServicio, editorialServicio, autorServicio);
        menu.ejecutarMenu();
    }
}