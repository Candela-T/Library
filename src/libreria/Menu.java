package libreria;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicio.AutorServicio;
import libreria.servicio.EditorialServicio;
import libreria.servicio.LibroServicio;

public class Menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private LibroServicio libroServicio;
    private EditorialServicio editorialservicio;
    private AutorServicio autorServicio;

    public Menu(LibroServicio libroServicio, EditorialServicio editorialServicio, AutorServicio autorservicio) {
        this.libroServicio = libroServicio;
        this.editorialservicio = editorialServicio;
        this.autorServicio = autorservicio;
    }

    public void ejecutarMenu() throws Exception {
        try{
        int opc;
        do {
            System.out.println("***LIBRERIA***");

            System.out.println("Ingrese una opción (ingresando el numero que corresponde)");
            System.out.println("CREAR LIBRO --> 1");
            System.out.println("CREAR EDITORIAL --> 2");
            System.out.println("CREAR AUTOR --> 3");
            System.out.println("Dar de ALTA LIBRO --> 4");
            System.out.println("Dar de ALTA EDITORIAL --> 5");
            System.out.println("Dar de ALTA AUTOR --> 6");
            System.out.println("Dar de BAJA LIBRO --> 7");
            System.out.println("Dar de BAJA EDITORIAL --> 8");
            System.out.println("Dar de BAJA AUTOR --> 9 ");
            System.out.println("BUSCAR un AUTOR por NOMBRE --> 10");
            System.out.println("BUSCAR LIBRO por ISBN --> 11");
            System.out.println("BUSCAR LIBRO por TITULO --> 12");
            System.out.println("BUSCAR LIBRO por AUTOR --> 13");
            System.out.println("BUSCAR LIBRO por EDITORIAL --> 14");
            System.out.println("EDITAR LIBRO --> 15");
            System.out.println("EDITAR EDITORIAL--> 16");
            System.out.println("EDITAR AUTOR --> 17");
            System.out.println("SALIR --> 18");
            opc = leer.nextInt();

            switch (opc) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    crearEditorial();
                    break;
                case 3:
                    crearAutor();
                    break;
                case 4:
                    darAltaLibro();
                    break;
                case 5:
                    darAltaEditorial();
                    break;
                case 6:
                    darAltaAutor();
                    break;
                case 7:
                    darBajaLibro();
                    break;
                case 8:
                    darBajaEditorial();
                    break;
                case 9:
                    darBajaAutor();
                    break;
                case 10:
                    Autor autor = buscarAutorPornombre();
                    if(autor != null){
                        System.out.println(autor.toString());
                    }
                    break;
                case 11:
                    Libro libro1 = buscarLibroPorISBN();
                    if(libro1 != null){
                        System.out.println(libro1.toString());
                    }
                    break;
                case 12:
                    Libro libro2 = buscarLibroPorTitulo();
                    if(libro2 != null){
                        System.out.println(libro2.toString());
                    }
                    break;
                case 13:
                    List<Libro> libros = buscarLibrosPorAutor();
                    if (libros != null) {
                        libros.forEach((libro) -> {
                            System.out.println(libro.toString());
                });
                    }
                    break;
                case 14:
                    List<Libro> libros3 = buscarLibrosPorEditorial();
                    if (libros3 != null) {
                        libros3.forEach((libro) -> {
                            System.out.println(libro.toString());
                });
                    }
                    break;
                case 15:
                    editarLibro();
                    break;
                case 16:
                    editarEditorial();
                    break;
                case 17:
                    editarAutor();
                    break;
                case 18:
                    break;
                default:
                    System.out.println("Debe indicar una opcion valida");
                    break;
            }
        } while (opc != 18);
        }catch(InputMismatchException i){
            System.out.println("Debe indicar una opcion numerica valida");
        }
    }

    public Libro crearLibro() throws Exception {
        try {

            System.out.println("Ingrese el titulo del libro:");
            String titulo = leer.next();
            if (titulo.trim().isEmpty()) {
                System.out.println("Debe indicar un titulo para el libro");
            }
            System.out.println("Ingrese el anio:");
            Integer anio = leer.nextInt();
            if (anio < 0) {
                System.out.println("Debe indicar el anio");
            }

            System.out.println("Ingrese la cantidad de ejemplares totales:");
            Integer totalEjemplares = leer.nextInt();
            if (totalEjemplares < 0) {
                System.out.println("Debe indicar el total de ejemplares");
            }

            System.out.println("Ingrese el numero de ejemplares prestados:");
            Integer ejemPrestados = leer.nextInt();
            if (ejemPrestados < 0) {
                System.out.println("Debe indicar el total de ejemplares prestados");
            }
            Integer ejemplaresRestantes = totalEjemplares - ejemPrestados;

            System.out.println("Crear autor --> 0");
            System.out.println("Usar autor ya creado --> 1");
            
            int opc = leer.nextInt();
            Autor autor = null;
            
            switch (opc) {
                case 0:
                    autor = crearAutor();
                    break;
                case 1:
                    System.out.println("Ingrese el ID del autor:");
                    Long id = leer.nextLong();
                    autor = autorServicio.busquedaAutorPorId(id);
                    break;
            }

            System.out.println("crear editorial --> 0");
            System.out.println("Usar editorial ya creada --> 1");

            int aux = leer.nextInt();
            Editorial editorial = null;
            
            switch (aux) {
                case 0:
                    editorial = crearEditorial();
                    break;
                case 1:
                    System.out.println("Ingrese el ID de la editorial:");
                    Long id = leer.nextLong();
                    editorial = editorialservicio.busquedaEditorialPorId(id);
                    
                    break;
            }

            Libro libro = new Libro(titulo, anio, totalEjemplares, ejemPrestados, ejemplaresRestantes, autor, editorial);
            libroServicio.crearLibro(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    public Editorial crearEditorial() {
        
            System.out.println("Ingrese el nombre de la editorial:");
            String nombreEditorial = leer.next();
            if (nombreEditorial.trim().isEmpty()) {
                System.out.println("Debe indicar el nombre de la editorial");
                return null;
            }
            Editorial editorial = new Editorial(nombreEditorial);
            editorialservicio.crearEditorial(editorial);
            return editorial;
    }

    
    public Autor crearAutor() {
       
            System.out.println("Ingrese el nombre del autor:");
            String nombreAutor = leer.next();
            if (nombreAutor.trim().isEmpty()) {
                System.out.println("Debe indicar el nombre del autor");
                return null;
            }
            Autor autor = new Autor(nombreAutor);
            autorServicio.crearAutor(autor);
            return autor;
    }

    
    public void darAltaLibro() {
        
        System.out.println("Ingrese el ID del LIBRO para dar de ALTA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        libroServicio.darAltaLibro(id);
    }
    

    public void darAltaEditorial() {
        
        System.out.println("Ingrese el ID de la EDITORIAL para dar de ALTA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        editorialservicio.darAltaEditorial(id);
    }
    

    public void darAltaAutor() {
        
        System.out.println("Ingrese el ID del AUTOR para dar de ALTA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        autorServicio.darAltaAutor(id);
    }

    
    public void darBajaLibro() {
        
        System.out.println("Ingrese el ID del LIBRO para dar de BAJA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        libroServicio.darBajaLibro(id);
    }
    

    public void darBajaEditorial() {
        
        System.out.println("Ingrese el ID de la EDITORIAL para dar de BAJA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        editorialservicio.darBajaEditorial(id);
    }

    
    public void darBajaAutor() {
        
        System.out.println("Ingrese el ID del AUTOR para dar de BAJA:");
        Long id = leer.nextLong();
        if (id < 0) {
            System.out.println("Debe indicar un ID mayor a 0");
        }
        autorServicio.darBajaAutor(id);
    }

    
    public Autor buscarAutorPornombre() throws Exception {
       
            System.out.println("Ingrese el NOMBRE del AUTOR para buscar:");
            String nombre = leer.next();
            if (nombre.trim().isEmpty()) {
                System.out.println("Debe indicar un nombre para buscar el autor");
                return null;
            }
            return autorServicio.buscarPorNombre(nombre);
    }
    

    public Libro buscarLibroPorISBN() throws Exception {
        
            System.out.println("Ingrese el ISBN del LIBRO para BUSCAR:");
            Long isbn = leer.nextLong();
            if (isbn < 0) {
                System.out.println("Debe indicar un ID mayor a 0");
                return null;
            }
            return libroServicio.busquedaPorIdLibro(isbn);
    }
    

    public Libro buscarLibroPorTitulo() throws Exception {
        
            System.out.println("Ingrese el TITULO del LIBRO para BUSCAR:");
            String titulo = leer.next();
            if (titulo.trim().isEmpty()) {
                System.out.println("Debe indicar un titulo para buscar el libro");
                return null;
            }
            return libroServicio.busquedaPorTituloLibro(titulo);
    }

    public List<Libro> buscarLibrosPorAutor() {

            System.out.println("Ingrese el nombre del autor");
            String nombreAutor = leer.next();
            if (nombreAutor.trim().isEmpty()) {
                System.out.println("Debe indicar un autor para buscar los libros");
                return null;
            }
            return libroServicio.busquedaLibroNombreAutor(nombreAutor);


    }

    public List<Libro> buscarLibrosPorEditorial() throws Exception {
        
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            if (nombre.trim().isEmpty()) {
                System.out.println("Debe indicar una editorial para buscar los libros");
                return null;
            }
            return libroServicio.busquedaLibroPorEditorial(nombre);
        
    }

    public void editarLibro() throws Exception {

            System.out.println("Ingrese el id del libro a modificar:");
            Long id = leer.nextLong();
            if (id < 0) {
                System.out.println("Debe indicar un ID mayor a 0");
            }
            Libro libro = libroServicio.busquedaPorIdLibro(id);
            System.out.println("Cambiar titulo --> 1");
            System.out.println("Cambiar anio --> 2");
            System.out.println("Cambiar ejemplares --> 3");
            System.out.println("Cambiar ejemplares prestados --> 4");
            System.out.println("Cambiar salir --> 5");
            int opc = leer.nextInt();
            if (opc < 1 || opc > 5) {
                System.out.println("Ingrese una opcion valida");
            }
            switch (opc) {

                case 1:
                    System.out.println("Ingrese el titulo:");
                    String nombre = leer.next();
                    if (nombre.trim().isEmpty()) {
                        System.out.println("Ingrese un nombre");
                    }
                    libroServicio.editarNombreLibro(libro, nombre);
                    break;

                case 2:
                    System.out.println("Ingrese el anio");
                    Integer anio = leer.nextInt();
                    libroServicio.editarAnioLibro(libro, anio);
                    break;

                case 3:
                    System.out.println("Ingrese los ejemplares:");
                    Integer ejem = leer.nextInt();
                    if (ejem < 0) {
                        System.out.println("Debe ingresar cantidad de ejemplares");
                    }
                    libroServicio.editarEjemplares(libro, ejem);
                    break;

                case 4:
                    System.out.println("Ingrese cantidad de ejemplares prestados:");
                    Integer aux = leer.nextInt();
                    if (aux < 0) {
                        System.out.println("Debe ingresar la cantidad de ejemplares prestados");
                    }
                    libroServicio.editarEjemplaresPrestados(libro, aux);
                    break;

                case 5:
                    System.out.println("Adiós!");
                    break;
            }
    }

    public void editarEditorial() throws Exception {
        
            System.out.println("Ingrese el ID de la EDITORIAL a EDITAR:");
            Long id = leer.nextLong();
            if (id < 0) {
                System.out.println("Debe indicar un ID mayor a 0");
            }
            System.out.println("Ingrese el NOMBRE para EDITAR el nombre de la EDITORIAL:");
            String nombre = leer.next();
            if (nombre.trim().isEmpty()) {
                System.out.println("Debe indicar un nombre");
            }
            editorialservicio.editarNombreEditorial(id, nombre);
        
    }

    public void editarAutor() throws Exception {
        
            System.out.println("Ingrese el ID del AUTOR a EDITAR:");
            Long id = leer.nextLong();
            if (id < 0) {
                System.out.println("Debe indicar un ID mayor a 0");
            }
            System.out.println("Ingrese el NOMBRE para EDITAR el nombre del AUTOR:");
            String nombre = leer.next();
            if (nombre.trim().isEmpty()) {
                System.out.println("Debe indicar un nombre");
            }
            autorServicio.editarNombreAutor(id, nombre);
    }
}
