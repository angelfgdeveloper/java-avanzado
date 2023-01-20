package org.example;

import org.example.entities.Movie;
import org.example.interfaces.OnOneListener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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