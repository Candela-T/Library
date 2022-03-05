
package libreria;

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

        int opc = 0;
        do {
            System.out.println("***LIBRERIA***");

            System.out.println("Ingrese una opción (ingresando el numero que corresponde)");
            System.out.println("CREAR LIBRO --> 1");//funciona. no carga los id de autor y editorial
            System.out.println("CREAR EDITORIAL --> 2");//funciona
            System.out.println("CREAR AUTOR --> 3");//funciona
            System.out.println("Dar de ALTA LIBRO --> 4");//funciona
            System.out.println("Dar de ALTA EDITORIAL --> 5");//funciona
            System.out.println("Dar de ALTA AUTOR --> 6");//funciona
            System.out.println("Dar de BAJA LIBRO --> 7");//funciona
            System.out.println("Dar de BAJA EDITORIAL --> 8");//funciona
            System.out.println("Dar de BAJA AUTOR --> 9 ");//funciona
            System.out.println("BUSCAR un AUTOR por NOMBRE --> 10");//funciona
            System.out.println("BUSCAR LIBRO por ISBN --> 11");//funciona
            System.out.println("BUSCAR LIBRO por TITULO --> 12");//funciona
            System.out.println("BUSCAR LIBRO por AUTOR --> 13");//funciona
            System.out.println("BUSCAR LIBRO por EDITORIAL --> 14");//funciona
            System.out.println("EDITAR LIBRO --> 15");//funciona
            System.out.println("EDITAR EDITORIAL--> 16");//funciona
            System.out.println("EDITAR AUTOR --> 17");//funciona
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
                    System.out.println(autor.toString());
                    break;
                case 11:
                    Libro libro1 = buscarLibroPorISBN();
                    System.out.println(libro1.toString());
                    break;
                case 12:
                    Libro libro2 = buscarLibroPorTitulo();
                    System.out.println(libro2.toString());
                    break;
                case 13:
                    List<Libro> libros = buscarLibrosPorAutor();
                    for (Libro l : libros) {
                        System.out.println(l.toString());
                    }
                    break;
                case 14:
                    List<Libro> libros3 = buscarLibrosPorEditorial();
                    for (Libro l : libros3) {
                        System.out.println(l.toString());
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
                    System.out.println("Adios");
            }
        } while (opc != 18);
    }

    public Libro crearLibro() throws Exception {
        try {

            System.out.println("Ingrese el titulo del libro:");
            String titulo = leer.next();
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar un titulo para el libro");
            }
            System.out.println("Ingrese el anio:");
            Integer anio = leer.nextInt();
            if (anio == null || anio < 0) {
                throw new Exception("Debe indicar el anio");
            }

            System.out.println("Ingrese la cantidad de ejemplares totales:");
            Integer totalEjemplares = leer.nextInt();
            if (totalEjemplares == null) {
                throw new Exception("Debe indicar el total de ejemplares");
            }

            System.out.println("Ingrese el numero de ejemplares prestados:");
            Integer ejemPrestados = leer.nextInt();
            if (ejemPrestados == null) {
                throw new Exception("Debe indicar el total de ejemplares prestados");
            }
            Integer ejemplaresRestantes = totalEjemplares - ejemPrestados;

            System.out.println("Crear autor --> 0");
            System.out.println("Usar autor ya creado --> 1");
            int opc = leer.nextInt();
            Autor autor = null;
            switch (opc) {
                case 0:
                    crearAutor();
                    break;
                case 1:
                    System.out.println("Ingrese el ID del autor:");
                    Long id = leer.nextLong();
                    autor = autorServicio.busquedaAutorPorId(id);
                    autorServicio.crearAutor(autor);//agregado. tal vez falta guardarlo en una variable
                    break;
            }

            System.out.println("crear editorial --> 0");
            System.out.println("Usar editorial ya creada --> 1");

            int aux = leer.nextInt();
            Editorial editorial1 = null;
            switch (aux) {
                case 0:
                    crearEditorial();
                    break;
                case 1:
                    System.out.println("Ingrese el ID de la editorial:");
                    Long id = leer.nextLong();
                    editorial1 = editorialservicio.busquedaEditorialPorId(id);
                    editorialservicio.crearEditorial(editorial1);//agregado
                    break;

            }

            Libro libro = new Libro(titulo, anio, totalEjemplares, ejemPrestados, ejemplaresRestantes, autor, editorial1);
            libroServicio.crearLibro(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Editorial crearEditorial() throws Exception {
        try {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombreEditorial = leer.next();
            if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre de la editorial");
            }
            Editorial editorial = new Editorial(nombreEditorial);
            editorialservicio.crearEditorial(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor crearAutor() throws Exception {
        try {
            System.out.println("Ingrese el nombre del autor:");
            String nombreAutor = leer.next();
            if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autor");
            }
            Autor autor = new Autor(nombreAutor);
            autorServicio.crearAutor(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void darAltaLibro() {
        try {
            System.out.println("Ingrese el ID del LIBRO para dar de ALTA:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            libroServicio.darAltaLibro(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darAltaEditorial() {
        try {
            System.out.println("Ingrese el ID de la EDITORIAL para dar de ALTA:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            editorialservicio.darAltaEditorial(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darAltaAutor() {
        try {
            System.out.println("Ingrese el ID del AUTOR para dar de ALTA:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            autorServicio.darAltaAutor(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darBajaLibro() {
        try {
            System.out.println("Ingrese el ID del LIBRO para dar de BAJA:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            libroServicio.darBajaLibro(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void darBajaEditorial() {
        try {
            System.out.println("Ingrese el ID de la EDITORIAL para dar de BAJA:");
            Long id = leer.nextLong();
            if (id == null) {
                throw new Exception("No existe el id");
            }
            editorialservicio.darBajaEditorial(id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void darBajaAutor() {
        try {
            System.out.println("Ingrese el ID del AUTOR para dar de BAJA:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            autorServicio.darBajaAutor(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Autor buscarAutorPornombre() {
        try {
            System.out.println("Ingrese el NOMBRE del AUTOR para buscar:");
            String nombre = leer.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre para buscar el autor");
            }
            return autorServicio.buscarPorNombre(nombre);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("No existe el nombre");
            return null;
        }
    }

    public Libro buscarLibroPorISBN() {
        try {
            System.out.println("Ingrese el ISBN del LIBRO para BUSCAR:");
            Long isbn = leer.nextLong();
            if (isbn == null || isbn < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            return libroServicio.busquedaPorIdLibro(isbn);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarLibroPorTitulo() {
        try {
            System.out.println("Ingrese el TITULO del LIBRO para BUSCAR:");
            String titulo = leer.next();
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar un titulo para buscar el libro");
            }
            return libroServicio.busquedaPorTituloLibro(titulo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> buscarLibrosPorAutor() {
        try {
            System.out.println("Ingrese el nombre del autor");
            String nombreAutor = leer.next();
            if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
                throw new Exception("Debe indicar un autor para buscar los libros");
            }
            return libroServicio.busquedaLibroNombreAutor(nombreAutor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Libro> buscarLibrosPorEditorial() {
        try {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar una editorial para buscar los libros");
            }
            return libroServicio.busquedaLibroPorEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarLibro() {
        try {
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            System.out.println("Ingrese el id del libro a modificar:");
            Long id = leer.nextLong();
            if (id == null || id < 0) {
                throw new Exception("Debe indicar un ID mayor a 0");
            }
            Libro libro = libroServicio.busquedaPorIdLibro(id);
            System.out.println("Cambiar titulo --> 1");
            System.out.println("Cambiar anio --> 2");
            System.out.println("Cambiar ejemplares --> 3");
            System.out.println("Cambiar ejemplares prestados --> 4");
            System.out.println("Cambiar salir --> 5");
            int opc = leer.nextInt();
            if (opc < 1 || opc > 5) {
                throw new Exception("Ingrese una opcion valida");
            }
            switch (opc) {

                case 1:
                    System.out.println("Ingrese el titulo:");
                    String nombre = leer.next();
                    if (nombre == null || nombre.trim().isEmpty()) {
                        throw new Exception("Ingrese un nombre");
                    }
                    libroServicio.editarNombreLibro(libro, nombre);
                    break;

                case 2:
                    System.out.println("Ingrese el anio");
                    Integer anio = leer.nextInt();
                    if (anio == null) {
                        throw new Exception("Debe ingresar un anio");
                    }
                    libroServicio.editarAnioLibro(libro, anio);
                    break;

                case 3:
                    System.out.println("Ingrese los ejemplares:");
                    Integer ejem = leer.nextInt();
                    if (ejem == null) {
                        throw new Exception("Debe ingresar cantidad de ejemplares");
                    }
                    libroServicio.editarEjemplares(libro, ejem);
                    break;

                case 4:
                    System.out.println("Ingrese cantidad de ejemplares prestados:");
                    Integer aux = leer.nextInt();
                    if (aux == null) {
                        throw new Exception("Debe ingresar la cantidad de ejemplares prestados");
                    }
                    libroServicio.editarEjemplaresPrestados(libro, aux);
                    break;

                case 5:
                    System.out.println("Adiós!");
                    break;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void editarEditorial() {
        try {
            System.out.println("Ingrese el ID de la EDITORIAL a EDITAR:");
            Long id = leer.nextLong();
            if (id == null) {
                throw new Exception("Debe indicar un ID");
            }
            System.out.println("Ingrese el NOMBRE para EDITAR el nombre de la EDITORIAL:");
            String nombre = leer.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            editorialservicio.editarNombreEditorial(id, nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editarAutor() {
        try {
            System.out.println("Ingrese el ID del AUTOR a EDITAR:");
            Long id = leer.nextLong();
            if (id == null) {
                throw new Exception("Debe indicar un ID");
            }
            System.out.println("Ingrese el NOMBRE para EDITAR el nombre del AUTOR:");
            String nombre = leer.next();
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            autorServicio.editarNombreAutor(id, nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
