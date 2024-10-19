package co.com.proteccion.tdv.core;

public class ConjuntoVector {

    private char[] vector;
    private int tamano;
    private static final char[] UNIVERSAL_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public ConjuntoVector(int tamano) {
        this.tamano = tamano;
        this.vector = new char[tamano];
    }

    public char[] getVector() {
        return vector;
    }

    public int getTamano() {
        return tamano;
    }

    public void setDato(int posicion, char dato) {
        if (posicion >= 0 && posicion < tamano) {
            if (!pertenecer(dato)) {
                System.out.println("El dato no pertenece al conjunto universal");
                return;
            }
            for (char c : vector) {
                if (c == dato) {
                    System.out.println("El dato ya está en el conjunto");
                    return;
                }
            }
            this.vector[posicion] = dato;
        } else {
            System.out.println("Posición fuera de rango");
        }
    }

    public void getDato(int posicion) {
        if (posicion >= 0 && posicion < tamano) {
            System.out.println(String.format("El dato en la posición %s es: %s", posicion, this.vector[posicion]));
        } else {
            System.out.println("Posición fuera de rango");
        }
    }

    public void eliminar(char dato) {
        for (int i = 0; i < tamano; i++) {
            if (this.vector[i] == dato) {
                this.vector[i] = '\0'; // Asumiendo que '\0' es el valor por defecto para indicar un espacio vacío
                break;
            }
        }
    }

    public int cantidadDeElementos() {
        int contadorElementos = 0;
        for (char c : this.vector) {
            if (c != '\0') {
                contadorElementos++;
            }
        }
        return contadorElementos;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (char c : vector) {
            if (c != '\0') {
                System.out.print(c + " ");
            }
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
        for (char c : this.vector) {
            if (c == dato) {
                return true;
            }
        }
        return false;
    }

    public boolean subConjunto(ConjuntoVector b) {
        for (char c : this.vector) {
            if (c != '\0' && !b.perteneceAlConjunto(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean esVacio() {
        for (char c : this.vector) {
            if (c != '\0') {
                return false;
            }
        }
        return true;
    }

    public ConjuntoVector union(ConjuntoVector b) {
        ConjuntoVector unionConjunto = new ConjuntoVector(this.tamano + b.getTamano());
        int index = 0;
        for (char c : this.vector) {
            if (c != '\0') {
                unionConjunto.setDato(index++, c);
            }
        }
        for (char c : b.getVector()) {
            if (c != '\0' && !unionConjunto.perteneceAlConjunto(c)) {
                unionConjunto.setDato(index++, c);
            }
        }
        return unionConjunto;
    }

    public ConjuntoVector interseccion(ConjuntoVector b) {
        ConjuntoVector interseccionConjunto = new ConjuntoVector(Math.min(this.tamano, b.getTamano()));
        int index = 0;
        for (char c : this.vector) {
            if (c != '\0' && b.perteneceAlConjunto(c)) {
                interseccionConjunto.setDato(index++, c);
            }
        }
        return interseccionConjunto;
    }
    public ConjuntoVector diferencia(ConjuntoVector b){
        ConjuntoVector diferenciaConjunto = new ConjuntoVector(this.tamano);
        int index = 0;
        for (char c : this.vector) {
            if (c != '\0' && !b.perteneceAlConjunto(c)) {
                diferenciaConjunto.setDato(index++, c);
            }
        }
        return diferenciaConjunto;
    }
    public ConjuntoVector diferenciaSimetrica(ConjuntoVector b){
        ConjuntoVector diferenciaSimetricaConjunto = new ConjuntoVector(this.tamano + b.getTamano());
        int index = 0;
        for (char c : this.vector) {
            if (c != '\0' && !b.perteneceAlConjunto(c)) {
                diferenciaSimetricaConjunto.setDato(index++, c);
            }
        }
        for (char c : b.getVector()) {
            if (c != '\0' && !this.perteneceAlConjunto(c)) {
                diferenciaSimetricaConjunto.setDato(index++, c);
            }
        }
        return diferenciaSimetricaConjunto;
    }
    public ConjuntoVector complemento(){
        ConjuntoVector complementoConjunto = new ConjuntoVector(UNIVERSAL_SET.length);
        int index = 0;
        for (char c : UNIVERSAL_SET) {
            if (!this.perteneceAlConjunto(c)) {
                complementoConjunto.setDato(index++, c);
            }
        }
        return complementoConjunto;
    }
}