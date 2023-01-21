package org.example;

import org.example.interfaces.OnOneListener;

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

    }
}