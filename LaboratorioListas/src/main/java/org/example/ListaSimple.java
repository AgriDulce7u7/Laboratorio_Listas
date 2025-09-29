package org.example;

import java.util.function.Predicate;

public class ListaSimple<T> {
    private Nodo<T> cabeza;

    public ListaSimple() {
        this.cabeza = null;
    }

    // Agregar al final (helper)
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    // Imprimir lista
    public void imprimirLista() {
        Nodo<T> actual = cabeza;
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

    // 1) Obtener los elementos que están en posiciones impares (1,3,5... -> index base 0)
    public ListaSimple<T> obtenerElementosEnPosicionesImpares() {
        ListaSimple<T> res = new ListaSimple<>();
        Nodo<T> actual = cabeza;
        int idx = 0;
        while (actual != null) {
            if (idx % 2 != 0) {
                res.agregarFinal(actual.getValor());
            }
            actual = actual.getSiguiente();
            idx++;
        }
        return res;
    }

    // 2) Filtrar por predicado (genérico). Usaremos esto para personas con cédula de longitud par.
    public ListaSimple<T> filtrar(Predicate<T> pred) {
        ListaSimple<T> res = new ListaSimple<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (pred.test(actual.getValor())) {
                res.agregarFinal(actual.getValor());
            }
            actual = actual.getSiguiente();
        }
        return res;
    }

    // 3) Eliminar elementos que cumplan el predicado (modifica la lista original)
    public void eliminarSi(Predicate<T> pred) {
        // Eliminar nodos iniciales que cumplan
        while (cabeza != null && pred.test(cabeza.getValor())) {
            cabeza = cabeza.getSiguiente();
        }
        Nodo<T> actual = cabeza;
        while (actual != null && actual.getSiguiente() != null) {
            if (pred.test(actual.getSiguiente().getValor())) {
                // saltar el siguiente
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
            } else {
                actual = actual.getSiguiente();
            }
        }
    }

    // 4) Obtener lista con los valores impares (versión para Integer usando filtrar)
    public ListaSimple<Integer> obtenerImparesNumeros() {
        ListaSimple<Integer> res = new ListaSimple<>();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            Integer val = (Integer) actual.getValor(); // el caller debe asegurar que T es Integer
            if (val % 2 != 0) res.agregarFinal(val);
            actual = actual.getSiguiente();
        }
        return res;
    }

    // 5) Contar cuántas veces aparece un valor (usa equals, maneja null)
    public int contarRepetidos(T valor) {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            T v = actual.getValor();
            if (valor == null) {
                if (v == null) contador++;
            } else {
                if (valor.equals(v)) contador++;
            }
            actual = actual.getSiguiente();
        }
        return contador;
    }

}
