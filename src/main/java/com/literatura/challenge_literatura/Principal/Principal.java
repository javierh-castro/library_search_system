package com.literatura.challenge_literatura.Principal;

import com.literatura.challenge_literatura.model.DatosLibro;
import com.literatura.challenge_literatura.model.Libro;
import com.literatura.challenge_literatura.repository.LibroRepository;
import com.literatura.challenge_literatura.service.ConsumoAPI;
import com.literatura.challenge_literatura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private  LibroRepository repositorio;
    private final String URL_BASE = "https://gutendex.com";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

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
        String url = URL_BASE + busqueda.replace(" ", "%20");

        String json = consumoApi.obtenerDatos(url); // Hace petición HTTP
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class); // Convierte JSON en objeto
        System.out.println("Resultado de la búsqueda:");
        System.out.println(datos);
        return datos;
    }


    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }
//    public Serie(DatosSerie datosSerie){
//    @Override
//    public List<LibroDto> obtenerTodosLosLibros() {
//        List<Libro> librosGuardados = libroRepository.findAll();
//        return librosGuardados.stream().map(libro -> mapearADto(libro)).collect(Collectors.toList());
//    }
}