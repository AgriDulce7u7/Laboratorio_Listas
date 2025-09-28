package Ejercicio11;

// Clase que representa un polinomio completo
class Polinomio {
    private TerminoPolinomio cabeza;
    private int numeroTerminos;

    public Polinomio() {
        this.cabeza = null;
        this.numeroTerminos = 0;
    }

    /**
     * Agrega un nuevo término al polinomio
     * Los términos se organizan en orden descendente por exponente
     * Ejemplo: 3x^4 - 2x^2 + 5
     */
    public void agregarTermino(double coeficiente, int exponente) {
        // Si el coeficiente es 0, no agregamos el término
        if (coeficiente == 0) {
            return;
        }

        TerminoPolinomio nuevoTermino = new TerminoPolinomio(coeficiente, exponente);

        // Si la lista está vacía o el nuevo término tiene exponente mayor
        if (cabeza == null || exponente > cabeza.exponente) {
            nuevoTermino.siguiente = cabeza;
            cabeza = nuevoTermino;
            numeroTerminos++;
            return;
        }

        // Si ya existe un término con el mismo exponente, sumamos coeficientes
        if (cabeza.exponente == exponente) {
            cabeza.coeficiente += coeficiente;
            // Si el resultado es 0, eliminamos el término
            if (cabeza.coeficiente == 0) {
                cabeza = cabeza.siguiente;
                numeroTerminos--;
            }
            return;
        }

        // Buscamos la posición correcta para insertar
        TerminoPolinomio actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.exponente > exponente) {
            actual = actual.siguiente;
        }

        // Si encontramos un término con el mismo exponente
        if (actual.siguiente != null && actual.siguiente.exponente == exponente) {
            actual.siguiente.coeficiente += coeficiente;
            // Si el resultado es 0, eliminamos el término
            if (actual.siguiente.coeficiente == 0) {
                actual.siguiente = actual.siguiente.siguiente;
                numeroTerminos--;
            }
        } else {
            // Insertamos el nuevo término en su posición correcta
            nuevoTermino.siguiente = actual.siguiente;
            actual.siguiente = nuevoTermino;
            numeroTerminos++;
        }
    }

    /**
     * Evalúa el polinomio completo para un valor dado de x
     * Suma todos los términos evaluados individualmente
     */
    public double evaluar(double x) {
        double resultado = 0.0;
        TerminoPolinomio actual = cabeza;

        // Recorremos todos los términos y sumamos sus valores
        while (actual != null) {
            resultado += actual.evaluar(x);
            actual = actual.siguiente;
        }

        return resultado;
    }

    /**
     * Genera una tabla de valores del polinomio para un rango de x
     */
    public void generarTablaValores(double inicio, double fin, double incremento) {
        System.out.println("\n=== TABLA DE VALORES DEL POLINOMIO ===");
        System.out.printf("%-10s | %-15s\n", "x", "P(x)");
        System.out.println("-----------|----------------");

        // Iteramos desde el valor inicial hasta el final con el incremento dado
        for (double x = inicio; x <= fin; x += incremento) {
            double valor = evaluar(x);
            System.out.printf("%-10.1f | %-15.6f\n", x, valor);
        }
    }

    /**
     * Muestra el polinomio en formato matemático tradicional
     */
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("Polinomio vacío");
            return;
        }

        System.out.print("P(x) = ");
        TerminoPolinomio actual = cabeza;
        boolean esPrimero = true;

        while (actual != null) {
            // Manejo de signos para una presentación clara
            if (!esPrimero) {
                if (actual.coeficiente > 0) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" - ");
                    // Creamos un término temporal con coeficiente positivo para mostrar
                    TerminoPolinomio temp = new TerminoPolinomio(-actual.coeficiente, actual.exponente);
                    System.out.print(temp.toString());
                    actual = actual.siguiente;
                    continue;
                }
            }

            System.out.print(actual.toString());
            actual = actual.siguiente;
            esPrimero = false;
        }
        System.out.println();
    }

    public boolean estaVacio() {
        return cabeza == null;
    }
}
