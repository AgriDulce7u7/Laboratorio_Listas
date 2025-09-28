package Ejercicio13;

// Clase que representa la lista enlazada con el método de máxima distancia
class ListaMaximaDistancia {
    private NodoLista inicio;
    private int tamaño;

    public ListaMaximaDistancia() {
        this.inicio = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al final de la lista
     * Este método nos permite construir la lista para las pruebas
     */
    public void agregar(int valor) {
        NodoLista nuevoNodo = new NodoLista(valor);

        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            NodoLista actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    /**
     * MÉTODO PRINCIPAL - Calcula la máxima distancia entre elementos con clave x
     * <p>
     * Este es el método público que inicia el proceso recursivo.
     * Se asume que la clave x siempre existe en la lista (según enunciado).
     *
     * @param x la clave a buscar en la lista
     * @return la máxima distancia entre dos elementos que contienen la clave x
     */
    public int calcularMaximaDistancia(int x) {
        if (inicio == null) {
            throw new IllegalStateException("La lista está vacía");
        }

        // Iniciamos la recursión con los parámetros necesarios:
        // - nodo actual: inicio de la lista
        // - clave a buscar: x
        // - distancia actual desde el último elemento encontrado: 0
        // - máxima distancia encontrada hasta ahora: 0
        // - posición de la primera ocurrencia encontrada: -1 (sin encontrar aún)
        return calcularMaximaDistanciaRecursivo(inicio, x, 0, 0, -1);
    }

    /**
     * MÉTODO RECURSIVO PRIVADO - El corazón del algoritmo
     * <p>
     * Este método implementa un recorrido recursivo que:
     * 1. Busca elementos que contengan la clave x
     * 2. Calcula la distancia entre ocurrencias consecutivas
     * 3. Mantiene registro de la máxima distancia encontrada
     * <p>
     * La estrategia es utilizar la fase de "ida" de la recursión para:
     * - Recorrer cada nodo de la lista exactamente una vez
     * - Mantener un contador de distancia desde la última ocurrencia de x
     * - Actualizar la máxima distancia cada vez que encontramos x
     *
     * @param nodoActual                el nodo que estamos examinando en esta llamada recursiva
     * @param clave                     la clave que estamos buscando (x)
     * @param distanciaActual           distancia acumulada desde la última ocurrencia de la clave
     * @param maximaDistancia           la máxima distancia encontrada hasta ahora
     * @param posicionPrimeraOcurrencia posición donde encontramos la primera ocurrencia (-1 si no hemos encontrado ninguna)
     * @return la máxima distancia final entre dos elementos con la clave
     */
    private int calcularMaximaDistanciaRecursivo(NodoLista nodoActual, int clave,
                                                 int distanciaActual, int maximaDistancia,
                                                 int posicionPrimeraOcurrencia) {

        // CASO BASE: Si llegamos al final de la lista, retornamos la máxima distancia
        if (nodoActual == null) {
            return maximaDistancia;
        }

        // CASO RECURSIVO: Analizamos el nodo actual

        // ¿El nodo actual contiene la clave que buscamos?
        if (nodoActual.dato == clave) {

            // Si es la primera vez que encontramos la clave
            if (posicionPrimeraOcurrencia == -1) {
                // Marcamos que ya encontramos la primera ocurrencia
                // La distancia sigue siendo 0 porque necesitamos al menos dos elementos
                return calcularMaximaDistanciaRecursivo(nodoActual.siguiente, clave,
                        0, maximaDistancia, 0);
            } else {
                // Ya habíamos encontrado la clave antes, así que podemos calcular distancia

                // La distancia actual nos dice cuántos nodos hay entre la ocurrencia anterior y esta
                // Comparamos con la máxima distancia que teníamos guardada
                int nuevaMaximaDistancia = Math.max(maximaDistancia, distanciaActual);

                // Continuamos la recursión reiniciando el contador de distancia a 0
                // porque ahora esta ocurrencia se convierte en nuestro nuevo punto de referencia
                return calcularMaximaDistanciaRecursivo(nodoActual.siguiente, clave,
                        0, nuevaMaximaDistancia, 0);
            }
        } else {
            // El nodo actual NO contiene la clave

            // Si ya hemos encontrado al menos una ocurrencia de la clave antes,
            // incrementamos la distancia
            if (posicionPrimeraOcurrencia != -1) {
                return calcularMaximaDistanciaRecursivo(nodoActual.siguiente, clave,
                        distanciaActual + 1, maximaDistancia,
                        posicionPrimeraOcurrencia);
            } else {
                // Aún no hemos encontrado ninguna ocurrencia, seguimos buscando
                return calcularMaximaDistanciaRecursivo(nodoActual.siguiente, clave,
                        distanciaActual, maximaDistancia, -1);
            }
        }
    }

    /**
     * Método auxiliar para mostrar la lista de forma legible
     */
    public void mostrarLista() {
        if (inicio == null) {
            System.out.println("Lista vacía");
            return;
        }

        System.out.print("Lista: ");
        NodoLista actual = inicio;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }
            actual = actual.siguiente;
        }
        System.out.println();
    }

    /**
     * Método auxiliar que muestra todas las posiciones donde aparece una clave
     * Esto nos ayuda a visualizar mejor el problema y verificar nuestros resultados
     */
    public void mostrarPosicionesClave(int clave) {
        System.out.print("Posiciones donde aparece " + clave + ": ");

        NodoLista actual = inicio;
        int posicion = 0;
        boolean encontroAlguna = false;

        while (actual != null) {
            if (actual.dato == clave) {
                System.out.print(posicion + " ");
                encontroAlguna = true;
            }
            actual = actual.siguiente;
            posicion++;
        }

        if (!encontroAlguna) {
            System.out.print("(no encontrada)");
        }
        System.out.println();
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}
