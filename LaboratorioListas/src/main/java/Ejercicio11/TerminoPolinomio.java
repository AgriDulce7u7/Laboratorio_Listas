package Ejercicio11;

// Clase que representa un término del polinomio
class TerminoPolinomio {
    double coeficiente; // El coeficiente del término (ejemplo: 3 en 3x^4)
    int exponente;      // El exponente del término (ejemplo: 4 en 3x^4)
    TerminoPolinomio siguiente;

    public TerminoPolinomio(double coeficiente, int exponente) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
        this.siguiente = null;
    }

    /**
     * Evalúa este término específico para un valor dado de x
     * Por ejemplo, si el término es 3x^4 y x=2, retorna 3 * (2^4) = 48
     */
    public double evaluar(double x) {
        // Usamos Math.pow para calcular x^exponente
        return coeficiente * Math.pow(x, exponente);
    }

    @Override
    public String toString() {
        // Formateo especial para mostrar el término de manera legible
        if (exponente == 0) {
            return String.format("%.2f", coeficiente);
        } else if (exponente == 1) {
            if (coeficiente == 1) return "x";
            if (coeficiente == -1) return "-x";
            return String.format("%.2fx", coeficiente);
        } else {
            if (coeficiente == 1) return String.format("x^%d", exponente);
            if (coeficiente == -1) return String.format("-x^%d", exponente);
            return String.format("%.2fx^%d", coeficiente, exponente);
        }
    }
}
