package Ejercicio11;// Ejercicio 11: Representación de polinomios con listas enlazadas

import java.util.Scanner;

// Clase principal para el manejo de polinomios
public class ManejadorPolinomios {
    
    /**
     * Método que permite al usuario introducir un polinomio interactivamente
     */
    public static Polinomio crearPolinomioInteractivo() {
        Scanner scanner = new Scanner(System.in);
        Polinomio polinomio = new Polinomio();
        
        System.out.println("\n=== CREACIÓN DE POLINOMIO ===");
        System.out.println("Ingrese los términos del polinomio.");
        System.out.println("Para cada término ingrese: coeficiente exponente");
        System.out.println("Ejemplo: para 3x^4, ingrese: 3 4");
        System.out.println("Para terminar, ingrese: 0 0");
        System.out.println();
        
        while (true) {
            System.out.print("Ingrese coeficiente y exponente (0 0 para terminar): ");
            double coeficiente = scanner.nextDouble();
            int exponente = scanner.nextInt();
            
            // Condición de parada
            if (coeficiente == 0 && exponente == 0) {
                break;
            }
            
            polinomio.agregarTermino(coeficiente, exponente);
            System.out.println("Término agregado. Polinomio actual:");
            polinomio.mostrar();
        }
        
        return polinomio;
    }
    
    /**
     * Crea un polinomio de ejemplo para demostración
     * Representa: 3x^4 - 4x^2 + 11
     */
    public static Polinomio crearPolinomioEjemplo() {
        Polinomio polinomio = new Polinomio();
        
        // Agregamos los términos del ejemplo: 3x^4 - 4x^2 + 11
        polinomio.agregarTermino(3, 4);   // 3x^4
        polinomio.agregarTermino(-4, 2);  // -4x^2
        polinomio.agregarTermino(11, 0);  // 11 (término constante)
        
        return polinomio;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== PROGRAMA DE POLINOMIOS ===");
        System.out.println("Este programa permite trabajar con polinomios usando listas enlazadas");
        System.out.println();
        
        System.out.println("¿Desea crear un polinomio manualmente o usar el ejemplo?");
        System.out.println("1. Crear manualmente");
        System.out.println("2. Usar ejemplo (3x^4 - 4x^2 + 11)");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        Polinomio polinomio;
        
        if (opcion == 1) {
            polinomio = crearPolinomioInteractivo();
        } else {
            polinomio = crearPolinomioEjemplo();
            System.out.println("\nUsando polinomio de ejemplo:");
        }
        
        // Mostramos el polinomio creado
        polinomio.mostrar();
        
        if (!polinomio.estaVacio()) {
            // Generamos la tabla de valores como se solicita en el ejercicio
            // Para x = 0.0, 0.5, 1.0, 1.5, ..., 5.0
            polinomio.generarTablaValores(0.0, 5.0, 0.5);
            
            // Ejemplo específico mencionado en el documento
            System.out.println("\n=== VERIFICACIÓN CON EJEMPLO DEL DOCUMENTO ===");
            System.out.println("Para x = 0.5 en el polinomio 3x^4 - 4x^2 + 11:");
            
            if (opcion == 2) { // Solo si usamos el ejemplo
                double valorEjemplo = polinomio.evaluar(0.5);
                System.out.printf("P(0.5) = 3(0.5)^4 - 4(0.5)^2 + 11 = %.6f\n", valorEjemplo);
                
                // Cálculo paso a paso como en el documento
                double termino1 = 3 * Math.pow(0.5, 4);    // 3(0.5)^4 = 0.1875
                double termino2 = -4 * Math.pow(0.5, 2);   // -4(0.5)^2 = -1
                double termino3 = 11;                      // 11
                System.out.printf("= %.4f + (%.1f) + %.0f = %.6f\n", 
                    termino1, termino2, termino3, termino1 + termino2 + termino3);
            }
        } else {
            System.out.println("No se puede generar tabla para un polinomio vacío.");
        }
        
        scanner.close();
    }
}