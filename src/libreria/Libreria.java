
package libreria;

import java.util.Scanner;
import libreria.servicio.AutorServicio;
import libreria.servicio.EditorialServicio;
import libreria.servicio.LibroServicio;


public class Libreria {
  
    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in);
        
        //Corregir --> poner onteToMany a editorial y autor. ver como es la anotacion
        LibroServicio libroServicio = new LibroServicio();
        EditorialServicio editorialServicio = new EditorialServicio();
        AutorServicio autorServicio = new AutorServicio();
        
        
        Menu menu = new Menu(libroServicio, editorialServicio, autorServicio);
        menu.ejecutarMenu();


   

    }

}