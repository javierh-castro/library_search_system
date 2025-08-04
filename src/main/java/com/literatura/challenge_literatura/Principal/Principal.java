package com.literatura.challenge_literatura.Principal;

import com.literatura.challenge_literatura.model.DatosLibro;
import com.literatura.challenge_literatura.model.Libro;
import com.literatura.challenge_literatura.repository.LibroRepository;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private  LibroRepository repositorio;

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

    private void buscarLibroWeb() {
//        DatosLibro datos = getDatosLibro();
//        Libro serie = new Libro(datos);
//        repositorio.save(serie);
//        System.out.println(datos);
    }
}
