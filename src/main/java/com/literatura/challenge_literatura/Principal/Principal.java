package com.literatura.challenge_literatura.Principal;

import com.literatura.challenge_literatura.model.Autor;
import com.literatura.challenge_literatura.model.Datos;
import com.literatura.challenge_literatura.model.DatosLibro;
import com.literatura.challenge_literatura.model.Libro;
import com.literatura.challenge_literatura.repository.AutoRepository;
import com.literatura.challenge_literatura.repository.LibroRepository;
import com.literatura.challenge_literatura.service.ConsumoAPI;
import com.literatura.challenge_literatura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

//    private  LibroRepository repositorio;

    private List<DatosLibro> datosLibros = new ArrayList<>();
    private LibroRepository libroRepo;
    private AutoRepository autorRepo;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;

    public Principal(LibroRepository libroRepo, AutoRepository autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
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
                    listarLibrosEnBD();
                    break;
                case 3:
                    listarAutoresEnBD();
                    break;
                case 4:
                    buscarAutoresVivosPorAnio();
                    break;
                case 5:
                    buscarLibrosPorIdiomaBD();
                    break;
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
            String url = URL_BASE + "/books/?search=" + busqueda.replace(" ", "%20");//Me falto el /

            String json = consumoApi.obtenerDatos(url); // Hace petición HTTP
            System.out.println("📦 Respuesta JSON cruda:\n" + json);
            Datos respuesta  = conversor.obtenerDatos(json, Datos.class); // Convierte JSON en objeto
            System.out.println("¿Respuesta nula? " + (respuesta == null));
            System.out.println("¿Resultados nulos? " + (respuesta != null && respuesta.resultados() == null));
            if (respuesta.resultados().isEmpty()) {
                System.out.println("No se encontraron libros.");
                return null;
            }
            return respuesta.resultados().get(0);
        }


    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos == null) return;
        Optional<Libro> libroExistente = libroRepo.findByTituloContainsIgnoreCase(datos.titulo());
        if (libroExistente.isPresent()) {
            System.out.println("⚠️ El libro \"" + datos.titulo() + "\" ya está almacenado en la base de datos.");
            System.out.println(libroExistente.get());
        } else {
            Libro libro = new Libro(datos);
            libroRepo.save(libro);
            if (datos.idiomas() != null) {
                List<String> idiomasNormalizados = datos.idiomas().stream()
                        .map(String::toLowerCase)
                        .distinct()
                        .collect(Collectors.toList());
                libro.setIdiomas(idiomasNormalizados);
            }
              if(datos.autor()!= null ){
                List<Autor> autores = datos.autor().stream()
                        .map(datoAutor -> autorRepo
                                .findByNombre(datoAutor.nombre())
                                .orElseGet(() -> autorRepo.save(new Autor(datoAutor)))
                        )
                        .collect(Collectors.toList());
                libro.setAutor(autores);
                autores.forEach(a -> a.setLibros(List.of(libro)));
            }
            System.out.println("✅ Libro guardado correctamente:");
            System.out.println(libro);
        }
    }

    private void listarLibrosEnBD() {
        List<Libro> libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros guardados en la base de datos.");
            return;
        }
        System.out.println("📚 Se encontraron " + libros.size() + " libros, los cuales son:");
        libros.stream()
                .map(Libro::getTitulo)
                .forEach(titulo -> System.out.println("📖 " + titulo));
    }

    private void listarAutoresEnBD() {
        List<Autor> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("📭 No hay autores guardados en la base de datos.");
            return;
        }
        System.out.println("📚 Se encontraron " + autores.size() + " autores, los cuales son:");
        autores.stream()
                .map(Autor::getNombre)
                .forEach(nombre -> System.out.println("✍️ " + nombre));

    }

    private void buscarAutoresVivosPorAnio() {
        System.out.println("📅 Escribe el año para verificar qué autores estaban vivos:");
        int fecha = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresVivos = autorRepo.buscarAutoresVivosEnAnio(fecha);
        if (autoresVivos.isEmpty()) {
            System.out.println("❌ No se encontró ningún autor vivo en ese año.");
        } else {
            System.out.println("✅ Autores vivos en el año " + fecha + ":");
            autoresVivos.forEach(a -> System.out.println("📖 " + a.getNombre()));
        }
    }

    private void buscarLibrosPorIdiomaBD() {
        // Mapa de idiomas disponibles con su código y nombre
        Map<Integer, Map.Entry<String, String>> opcionesIdiomas = Map.of(
                1, Map.entry("en", "Inglés"),
                2, Map.entry("es", "Español"),
                3, Map.entry("fr", "Francés"),
                4, Map.entry("it", "Italiano"),
                5, Map.entry("zh", "Chino"),
                6, Map.entry("tl", "Talago")
        );

        // Mostrar menú de idiomas
        System.out.println("""
        🌍 Seleccione el idioma para buscar libros:
        1 - Inglés (en)
        2 - Español (es)
        3 - Francés (fr)
        4 - Italiano (it)
        5 - Chino (zh)
        6 - Talago (tl)
        0 - Cancelar
        """);

        System.out.print("Elija una opción: ");
        int opcion = teclado.nextInt();
        teclado.nextLine();

        if (opcion == 0) {
            System.out.println("❌ Búsqueda cancelada.");
            return;
        }

        if (!opcionesIdiomas.containsKey(opcion)) {
            System.out.println("❌ Opción inválida.");
            return;
        }

        Map.Entry<String, String> idiomaSeleccionado = opcionesIdiomas.get(opcion);
        String codigoIdioma = idiomaSeleccionado.getKey();
        String nombreIdioma = idiomaSeleccionado.getValue();

        // Buscar libros en ese idioma
        List<Libro> librosEnIdioma = libroRepo.findByContainingIdioma(codigoIdioma);

        if (librosEnIdioma.isEmpty()) {
            System.out.println("📭 No se encontraron libros en " + nombreIdioma + " (" + codigoIdioma + ")");
        } else {
            System.out.println("📚 Libros disponibles en " + nombreIdioma + " (" + codigoIdioma + "):");
            librosEnIdioma.forEach(libro -> {
                System.out.println("📖 Título: " + libro.getTitulo());
            });
        }
    }
}