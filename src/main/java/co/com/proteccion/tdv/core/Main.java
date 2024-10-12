package co.com.proteccion.tdv.core;

import java.util.Scanner;

public class Main {

    private static final String CONJUNTO_A = "Conjunto A";
    private static final String CONJUNTO_B = "Conjunto B";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialización de los conjuntos A y B
        int tamanoA = obtenerTamanoConjunto(scanner, CONJUNTO_A);
        ConjuntoVector conjuntoA = new ConjuntoVector(tamanoA);
        for (int i = 0; i < tamanoA; i++) {
            System.out.println("> Ingrese el dato para el " + CONJUNTO_A);
            char dato = scanner.next().charAt(0);
            conjuntoA.setDato(i, dato);
        }

        int tamanoB = obtenerTamanoConjunto(scanner, CONJUNTO_B);
        ConjuntoVector conjuntoB = new ConjuntoVector(tamanoB);
        for (int i = 0; i < tamanoB; i++) {
            System.out.println("> Ingrese el dato para el " + CONJUNTO_B);
            char dato = scanner.next().charAt(0);
            conjuntoB.setDato(i, dato);
        }

        // Menú de interacción
        while (true) {
            mostrarMenuOpciones();
            int opcion = scanner.nextInt();
            if (opcion == 11) {
                System.out.println("Saliendo...");
                scanner.close();
                return;
            }
            int idConjunto = obtenerIdConjunto(scanner);
            if (idConjunto == 0) {
                System.out.println("Opción de conjunto no válida");
                continue;
            }
            ejecutarOpcion(opcion, idConjunto, conjuntoA, conjuntoB, scanner);
        }
    }

    private static int obtenerTamanoConjunto(Scanner scanner, String nombreConjunto) {
        int tamano;
        while (true) {
            System.out.println("> Ingrese el tamaño del Conjunto " + nombreConjunto);
            if (scanner.hasNextInt()) {
                tamano = scanner.nextInt();
                break;
            } else {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }
        return tamano;
    }

    private static void mostrarMenuOpciones() {
        System.out.println("\nMenú:");
        System.out.println("1. Mostrar Conjunto");
        System.out.println("2. Verificar si un dato pertenece al Conjunto");
        System.out.println("3. Verificar si un conjunto es subconjunto de otro");
        System.out.println("4. Verificar si el Conjunto es vacío");
        System.out.println("5. Unión de dos conjuntos");
        System.out.println("6. Intersección de dos conjuntos");
        System.out.println("11. Salir");
        System.out.print("> Seleccione una opción: ");
    }

    private static int obtenerIdConjunto(Scanner scanner) {
        System.out.print("> Escoja un Conjunto (1 para A, 2 para B): ");
        return scanner.nextInt();
    }

    private static void ejecutarOpcion(int opcion, int idConjunto, ConjuntoVector conjuntoA, ConjuntoVector conjuntoB, Scanner scanner) {
        switch (opcion) {

            case 1:
                if (idConjunto == 1) {
                    conjuntoA.mostrar();
                } else {
                    conjuntoB.mostrar();
                }
                break;
            case 2:
                System.out.print("> Ingrese el dato a verificar: ");
                char dato = scanner.next().charAt(0);
                if (idConjunto == 1) {
                    System.out.println(conjuntoA.pertenecer(dato) ? String.format("El dato pertenece al %s", CONJUNTO_A) : String.format("El dato no pertenece al %s", CONJUNTO_A));
                } else {
                    System.out.println(conjuntoB.pertenecer(dato) ? String.format("El dato pertenece al %s", CONJUNTO_B) : String.format("El dato no pertenece al %s", CONJUNTO_B));
                }

                break;
            case 3:
                if (idConjunto == 1) {
                    System.out.println(conjuntoA.subConjunto(conjuntoB) ? String.format("%s es un subconjunto", CONJUNTO_A) : String.format("%s no es un subconjunto", CONJUNTO_A));
                } else {
                    System.out.println(conjuntoB.subConjunto(conjuntoA) ? String.format("%s es un subconjunto", CONJUNTO_B) : String.format("%s no es un subconjunto", CONJUNTO_B));
                }
                break;
            case 4:
                if (idConjunto == 1) {
                    System.out.println(conjuntoA.esVacio() ? String.format("El %s es vacío", CONJUNTO_A) : String.format("El %s no es vacío", CONJUNTO_A));
                } else {
                    System.out.println(conjuntoB.esVacio() ? String.format("El %s es vacío", CONJUNTO_B) : String.format("El %s no es vacío", CONJUNTO_B));
                }
                break;
            case 5:
                ConjuntoVector union;
                if (idConjunto == 1) {
                    union = conjuntoA.union(conjuntoB);
                } else {
                    union = conjuntoB.union(conjuntoA);
                }
                System.out.print("Unión de los conjuntos: ");
                union.mostrar();
                break;
            case 6:
                ConjuntoVector interseccion;
                if (idConjunto == 1) {
                    interseccion = conjuntoA.interseccion(conjuntoB);
                } else {
                    interseccion = conjuntoB.interseccion(conjuntoA);
                }
                System.out.print("Intersección de los conjuntos: ");
                interseccion.mostrar();
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}