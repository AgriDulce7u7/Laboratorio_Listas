package Ejercicio10;

// Clase Lista Simple
class ListaSimple {
    private Nodo cabeza;
    private int tamaño;

    public ListaSimple() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Método para agregar un elemento al final de la lista
    public void agregar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            // Recorremos hasta encontrar el último nodo
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Método para obtener la cabeza de la lista (getter)
    public Nodo getCabeza() {
        return cabeza;
    }

    // Método para establecer la cabeza (setter) - útil para concatenación
    public void setCabeza(Nodo nuevaCabeza) {
        this.cabeza = nuevaCabeza;
        // Recalculamos el tamaño
        this.tamaño = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            tamaño++;
            actual = actual.siguiente;
        }
    }

    // Método para obtener el último nodo de la lista
    private Nodo obtenerUltimo() {
        if (cabeza == null) return null;

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    // Método para imprimir la lista
    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = cabeza;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
