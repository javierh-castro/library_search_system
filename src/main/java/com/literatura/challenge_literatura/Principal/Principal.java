package com.literatura.challenge_literatura.Principal;

import com.literatura.challenge_literatura.model.DatosLibro;
import com.literatura.challenge_literatura.model.Libro;
import com.literatura.challenge_literatura.repository.LibroRepository;
import com.literatura.challenge_literatura.service.ConsumoAPI;
import com.literatura.challenge_literatura.service.ConvierteDatos;
import com.literatura.challenge_literatura.service.RespuestaAPI;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private  LibroRepository repositorio;
    private final String URL_BASE = "https://gutendex.com";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibroRepository libroRepo;
    private AutorRepository autorRepo;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;

    public Principal(LibroRepository repository) {

        this.repositorio = repository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     1 - buscar libro por título
                     2 - Listar Libros registrados
                     3 - Listar autores registrados
                     4 - Listar autores vivos en un determinado año
                     5 - Listar Libros por idioma
                     0 - salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
//                    buscarEpisodioPorSerie();
                    break;
                case 3:
//                    mostrarSeriesBuscadas();
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
        private DatosLibro getDatosLibro() {
            System.out.println("Escribe el título o autor del libro:");
            String busqueda = teclado.nextLine();
            String url = URL_BASE + "/books?search=" + busqueda.replace(" ", "+");

            String json = consumoApi.obtenerDatos(url); // Hace petición HTTP
            Datos respuesta  = conversor.obtenerDatos(json, Datos.class); // Convierte JSON en objeto
            if (respuesta.results().isEmpty()) {
                System.out.println("No se encontraron libros.");
                return null;
            }
            DatosLibro datos = respuesta.results().get(0);
            System.out.println("Resultado de la búsqueda:");
            System.out.println(datos);
            return datos;
        }


    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos == null) return;
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println("Libro guardado!!!");
    }
}