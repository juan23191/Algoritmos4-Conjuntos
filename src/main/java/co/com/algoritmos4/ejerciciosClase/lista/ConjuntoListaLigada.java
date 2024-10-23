package co.com.algoritmos4.ejerciciosClase.lista;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoListaLigada {
    private Nodo cabeza;
    private static final char[] UNIVERSAL_SET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public ConjuntoListaLigada() {
        this.cabeza = null;
    }

    public void agregar(char dato) {
        if (!perteneceAlConjunto(dato)) {
            Nodo nuevoNodo = new Nodo(dato);
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        }
    }

    public void eliminar(char dato) {
        Nodo actual = cabeza;
        Nodo anterior = null;
        while (actual != null) {
            if (actual.dato == dato) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    public int cantidadDeElementos() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public void mostrar() {
        System.out.print("[ ");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.print("]");
    }

    public boolean pertenecer(char dato) {
        for (char c : UNIVERSAL_SET) {
            if (c == dato) {
                return true;
            }
        }
        return false;
    }

    public boolean perteneceAlConjunto(char dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean subConjunto(ConjuntoListaLigada b) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (!b.perteneceAlConjunto(actual.dato)) {
                return false;
            }
            actual = actual.siguiente;
        }
        return true;
    }

    public boolean esVacio() {
        return cabeza == null;
    }

    public ConjuntoListaLigada union(ConjuntoListaLigada b) {
        ConjuntoListaLigada unionConjunto = new ConjuntoListaLigada();
        Set<Character> elementos = new HashSet<>();

        Nodo actual = cabeza;
        while (actual != null) {
            unionConjunto.agregar(actual.dato);
            elementos.add(actual.dato);
            actual = actual.siguiente;
        }

        actual = b.cabeza;
        while (actual != null) {
            if (!elementos.contains(actual.dato)) {
                unionConjunto.agregar(actual.dato);
            }
            actual = actual.siguiente;
        }

        return unionConjunto;
    }

    public ConjuntoListaLigada interseccion(ConjuntoListaLigada b) {
        ConjuntoListaLigada interseccionConjunto = new ConjuntoListaLigada();
        Nodo actual = cabeza;
        while (actual != null) {
            if (b.perteneceAlConjunto(actual.dato)) {
                interseccionConjunto.agregar(actual.dato);
            }
            actual = actual.siguiente;
        }
        return interseccionConjunto;
    }

    public ConjuntoListaLigada diferencia(ConjuntoListaLigada b) {
        ConjuntoListaLigada diferenciaConjunto = new ConjuntoListaLigada();
        Nodo actual = cabeza;
        while (actual != null) {
            if (!b.perteneceAlConjunto(actual.dato)) {
                diferenciaConjunto.agregar(actual.dato);
            }
            actual = actual.siguiente;
        }
        return diferenciaConjunto;
    }

    public ConjuntoListaLigada diferenciaSimetrica(ConjuntoListaLigada b) {
        ConjuntoListaLigada diferenciaSimetricaConjunto = new ConjuntoListaLigada();
        Set<Character> elementos = new HashSet<>();

        Nodo actual = cabeza;
        while (actual != null) {
            if (!b.perteneceAlConjunto(actual.dato)) {
                diferenciaSimetricaConjunto.agregar(actual.dato);
            }
            elementos.add(actual.dato);
            actual = actual.siguiente;
        }

        actual = b.cabeza;
        while (actual != null) {
            if (!elementos.contains(actual.dato) && !this.perteneceAlConjunto(actual.dato)) {
                diferenciaSimetricaConjunto.agregar(actual.dato);
            }
            actual = actual.siguiente;
        }

        return diferenciaSimetricaConjunto;
    }

    public ConjuntoListaLigada complemento() {
        ConjuntoListaLigada complementoConjunto = new ConjuntoListaLigada();
        for (char c : UNIVERSAL_SET) {
            if (!this.perteneceAlConjunto(c)) {
                complementoConjunto.agregar(c);
            }
        }
        return complementoConjunto;
    }
}