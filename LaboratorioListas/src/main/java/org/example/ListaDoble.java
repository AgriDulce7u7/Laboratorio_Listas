package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ListaDoble<T> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;

    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    // Agregar al final
    public void agregarFinal(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
    }

    // Imprimir hacia adelante
    public void imprimirLista() {
        NodoDoble<T> actual = cabeza;
        if (actual == null) {
            System.out.println("(vacía)");
            return;
        }
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // === 1) Imprimir hacia atrás ===
    public void imprimirHaciaAtras() {
        NodoDoble<T> actual = cola;
        if (actual == null) {
            System.out.println("(vacía)");
            return;
        }
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getAnterior();
        }
        System.out.println();
    }

    // === 2) Iterador ===
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.getValor();
                actual = actual.getSiguiente();
                return valor;
            }
        };
    }

    // === 3) Filtrar (ejemplo: personas con cédula de cantidad de dígitos par) ===
    public ListaDoble<T> filtrar(Predicate<T> pred) {
        ListaDoble<T> res = new ListaDoble<>();
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            if (pred.test(actual.getValor())) {
                res.agregarFinal(actual.getValor());
            }
            actual = actual.getSiguiente();
        }
        return res;
    }
}