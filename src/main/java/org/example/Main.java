package org.example;

import org.example.entities.Movie;
import org.example.interfaces.OnOneListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        // Crear instancia de interface
        OnOneListener onOneListener = new OnOneListener() {
            @Override
            public void onOne(String message) {
                System.out.println("Sin lambda: " + message.toUpperCase());
            }
        };

        OnOneListener onOneListener1 = (String message) -> {
            System.out.println("Con lambda: " + message);
        };

        onOneListener.onOne("Hola mundo desde mi primer FunctionalInterface");
        onOneListener1.onOne("Hola mundo desde mi segunda FunctionalInterface");

        // CUERPO DE LAMBDA
        // (parametros) -> { cuerpo-lambda }
        // Listener listener = (parametros) -> { cuerpo-lambda }

        OnOneListener onOneListener2 = message -> System.out.println("Con lambda reducida: " + message);
        onOneListener2.onOne("Hola mundo desde mi tercera FunctionalInterface");

        // Foreach - lambda
        List<Movie> movies = addMovies();

        System.out.println("\n=======Movies=======");
        AtomicInteger atomicInteger = new AtomicInteger(1); // Agrega un integer (tipo index) con valor inicial 0
        movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement() + ". " + m.getTitle() + " Visto: " + m.isViewed()));

        // Filter con Stream
        System.out.println("\n=======Movies Filters=======");

        List<String> words = Arrays.asList("hello", null, "");
        words.stream()
                .filter(w -> w != null) // ["hello", ""]
                .filter(w -> !w.isEmpty()) // ["hello"]
                .forEach(System.out::println);

        StringBuilder contentReport = new StringBuilder();
        movies.stream()
                .filter(m -> m.isViewed()) // filtra por los visto (true)
                .forEach(m -> contentReport.append(m.toString() + "\n")); // concatenar

        System.out.println("ContentReport: " + contentReport);

        System.out.println("\n=======Predicate=======");
        // Obtiene un objeto a evaluar y regresa true o false
        Predicate<Movie> moviesViewed = movie -> movie.isViewed(); // Verifica si una movie es vista (true)
        System.out.println("Predicate: " + moviesViewed.test(movies.get(0))); // false => porque movie no esta en vista
        System.out.println("Predicate: " + moviesViewed.test(movies.get(3))); // true => porque movie ya se vio

        System.out.println("\n=======Consumer=======");
        StringBuilder stringBuilder1 = new StringBuilder();
        // Obtiene un objeto como parametro <T> para en seguida añadir la logica y mandarla a otra funcion del tipo
        // como es el Consumer a un foreach, filter, map, etc.
        Consumer<Movie> movieEachs = movieEach -> stringBuilder1.append(movieEach + "\n");
        movies.stream().forEach(movieEachs);
        System.out.println("StringBuilder: " + stringBuilder1);

        System.out.println("\n=======Consumer Case-uses=======");
        Consumer<List<Movie>> moviesLogic = moviesArr -> {
            AtomicInteger atomicInteger1 = new AtomicInteger(1); // Agrega un integer (tipo index) con valor inicial 0
            List<Movie> moviesArrLogic = moviesArr;
            moviesArrLogic.stream()
                    .map(m -> m.getTitle() + " add logic: " + atomicInteger1.getAndIncrement())
                    .forEach(System.out::println);
        };
        moviesLogic.accept(movies);

    }

    private static List<Movie> addMovies() {
        return Arrays.asList(
                new Movie("Coco", false),
                new Movie("Titanic", false),
                new Movie("Avatar", false),
                new Movie("Sombra del agua", true)
        );
    }

}