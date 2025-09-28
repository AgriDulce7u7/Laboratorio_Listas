package Ejercicio9;

// Clase Lista Circular
public class ListaCircular {
    private NodoCircular ultimo; // Apuntamos al último nodo para facilitar la inserción
    private int tamano;

    public ListaCircular() {
        this.ultimo = null;
        this.tamano = 0;
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return ultimo == null;
    }

    // Método insertar - Inserta al final de la lista circular
    public void insertar(int valor) {
        NodoCircular nuevoNodo = new NodoCircular(valor);

        // Si la lista está vacía
        if (estaVacia()) {
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo; // Apunta a sí mismo
        } else {
            // Insertar después del último nodo
            nuevoNodo.siguiente = ultimo.siguiente; // El nuevo nodo apunta al primero
            ultimo.siguiente = nuevoNodo; // El anterior último apunta al nuevo
            ultimo = nuevoNodo; // Actualizamos el último
        }
        tamano++;
    }

    // Método buscar - Busca un elemento en la lista circular
    public boolean buscar(int valor) {
        // Si la lista está vacía, no hay nada que buscar
        if (estaVacia()) {
            return false;
        }

        // Comenzamos desde el primer nodo (siguiente del último)
        NodoCircular actual = ultimo.siguiente;

        // Recorremos la lista circular hasta volver al inicio
        do {
            if (actual.dato == valor) {
                return true; // Elemento encontrado
            }
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);

        return false; // Elemento no encontrado
    }

    // Método auxiliar para imprimir la lista circular
    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }

        NodoCircular actual = ultimo.siguiente;
        System.out.print("Lista circular: ");

        do {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);

        System.out.println("(vuelve al inicio)");
    }

    public int getTamano() {
        return tamano;
    }
}
