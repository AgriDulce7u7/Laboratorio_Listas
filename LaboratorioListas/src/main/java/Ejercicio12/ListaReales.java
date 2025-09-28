package Ejercicio12;

// Clase Lista Enlazada para números reales
class ListaReales {
    private NodoReal cabeza;
    private int tamaño;

    public ListaReales() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un número real al final de la lista
     * Este método mantiene el orden de inserción de los datos
     */
    public void agregar(double valor) {
        NodoReal nuevoNodo = new NodoReal(valor);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            // Buscamos el último nodo para agregar al final
            NodoReal actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    /**
     * Calcula la media aritmética de todos los números en la lista
     * Fórmula: media = (suma de todos los valores) / (cantidad de valores)
     */
    public double calcularMedia() {
        if (tamaño == 0) {
            throw new IllegalStateException("No se puede calcular la media de una lista vacía");
        }

        double suma = 0.0;
        NodoReal actual = cabeza;

        // Recorremos toda la lista sumando los valores
        while (actual != null) {
            suma += actual.valor;
            actual = actual.siguiente;
        }

        // La media es la suma total dividida entre el número de elementos
        return suma / tamaño;
    }

    /**
     * Calcula la desviación estándar de los números en la lista
     * La desviación estándar mide qué tan dispersos están los datos respecto a la media
     * <p>
     * Fórmula: σ = √[(Σ(xi - μ)²) / N]
     * Donde: xi = cada valor, μ = media, N = número total de valores
     */
    public double calcularDesviacionEstandar() {
        if (tamaño == 0) {
            throw new IllegalStateException("No se puede calcular la desviación estándar de una lista vacía");
        }

        if (tamaño == 1) {
            return 0.0; // Si solo hay un elemento, la desviación es 0
        }

        // Primero calculamos la media
        double media = calcularMedia();
        double sumaCuadradosDiferencias = 0.0;

        NodoReal actual = cabeza;

        // Para cada valor, calculamos (valor - media)² y lo sumamos
        while (actual != null) {
            double diferencia = actual.valor - media;
            sumaCuadradosDiferencias += diferencia * diferencia;
            actual = actual.siguiente;
        }

        // La varianza es el promedio de los cuadrados de las diferencias
        double varianza = sumaCuadradosDiferencias / tamaño;

        // La desviación estándar es la raíz cuadrada de la varianza
        return Math.sqrt(varianza);
    }

    /**
     * Muestra todos los números en la lista de forma organizada
     */
    public void mostrarDatos() {
        if (tamaño == 0) {
            System.out.println("La lista está vacía");
            return;
        }

        System.out.println("\n=== DATOS ALMACENADOS ===");
        System.out.printf("Total de números: %d\n", tamaño);
        System.out.println("Valores:");

        NodoReal actual = cabeza;
        int contador = 1;

        // Mostramos los datos en filas de 5 para mejor legibilidad
        while (actual != null) {
            System.out.printf("%8.3f ", actual.valor);

            if (contador % 5 == 0) {
                System.out.println(); // Nueva línea cada 5 números
            }

            actual = actual.siguiente;
            contador++;
        }

        // Si la última fila no está completa, agregamos un salto de línea
        if ((contador - 1) % 5 != 0) {
            System.out.println();
        }
    }

    /**
     * Obtiene estadísticas básicas adicionales para complementar el análisis
     */
    public void mostrarEstadisticasComplementarias() {
        if (tamaño == 0) return;

        // Encontrar el valor mínimo y máximo
        double min = cabeza.valor;
        double max = cabeza.valor;

        NodoReal actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.valor < min) min = actual.valor;
            if (actual.valor > max) max = actual.valor;
            actual = actual.siguiente;
        }

        System.out.println("\n=== ESTADÍSTICAS ADICIONALES ===");
        System.out.printf("Valor mínimo: %.6f\n", min);
        System.out.printf("Valor máximo: %.6f\n", max);
        System.out.printf("Rango: %.6f\n", max - min);
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}
