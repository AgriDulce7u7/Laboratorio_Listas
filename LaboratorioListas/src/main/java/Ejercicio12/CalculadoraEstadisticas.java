package Ejercicio12;// Ejercicio 12: Calcular media y desviación estándar usando listas enlazadas

import java.io.*;
import java.util.Scanner;

// Clase principal para el cálculo de estadísticas
public class CalculadoraEstadisticas {
    
    /**
     * Lee números reales desde un archivo de texto
     * El archivo debe contener un número por línea
     */
    public static ListaReales leerArchivo(String nombreArchivo) {
        ListaReales lista = new ListaReales();
        
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            System.out.println("Leyendo archivo: " + nombreArchivo);
            
            int numerosLeidos = 0;
            while (scanner.hasNextDouble()) {
                double numero = scanner.nextDouble();
                lista.agregar(numero);
                numerosLeidos++;
            }
            
            System.out.printf("Se leyeron exitosamente %d números del archivo.\n", numerosLeidos);
            
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: No se pudo encontrar el archivo: " + nombreArchivo);
            System.out.println("Asegúrese de que el archivo existe en la ruta especificada.");
        } catch (Exception e) {
            System.err.println("ERROR al leer el archivo: " + e.getMessage());
        }
        
        return lista;
    }
    
    /**
     * Crea un archivo de ejemplo con números aleatorios para prueba
     * Esto es útil si no tienes un archivo de datos disponible
     */
    public static void crearArchivoEjemplo(String nombreArchivo, int cantidadNumeros) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            System.out.println("Creando archivo de ejemplo: " + nombreArchivo);
            
            // Generamos números aleatorios en un rango razonable
            for (int i = 0; i < cantidadNumeros; i++) {
                // Números entre -100 y 100 con decimales
                double numero = (Math.random() - 0.5) * 200;
                writer.printf("%.3f\n", numero);
            }
            
            System.out.printf("Archivo creado con %d números aleatorios.\n", cantidadNumeros);
            
        } catch (IOException e) {
            System.err.println("ERROR al crear el archivo de ejemplo: " + e.getMessage());
        }
    }
    
    /**
     * Permite al usuario ingresar números manualmente si no tiene un archivo
     */
    public static ListaReales ingresarDatosManualmente() {
        Scanner scanner = new Scanner(System.in);
        ListaReales lista = new ListaReales();
        
        System.out.println("\n=== INGRESO MANUAL DE DATOS ===");
        System.out.println("Ingrese los números reales (ingrese 'fin' para terminar):");
        
        while (true) {
            System.out.print("Número: ");
            String entrada = scanner.next();
            
            if (entrada.equalsIgnoreCase("fin")) {
                break;
            }
            
            try {
                double numero = Double.parseDouble(entrada);
                lista.agregar(numero);
                System.out.println("Número agregado. Total actual: " + lista.getTamaño());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Por favor ingrese un número válido o 'fin' para terminar.");
            }
        }
        
        return lista;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaReales datos = new ListaReales();
        
        System.out.println("=== CALCULADORA DE MEDIA Y DESVIACIÓN ESTÁNDAR ===");
        System.out.println("Este programa calcula estadísticas usando listas enlazadas propias");
        System.out.println();
        
        System.out.println("¿Cómo desea ingresar los datos?");
        System.out.println("1. Leer desde un archivo existente");
        System.out.println("2. Crear archivo de ejemplo y leerlo");
        System.out.println("3. Ingresar datos manualmente");
        System.out.print("Seleccione una opción (1-3): ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre del archivo: ");
                String nombreArchivo = scanner.nextLine();
                datos = leerArchivo(nombreArchivo);
                break;
                
            case 2:
                String archivoEjemplo = "datos_ejemplo.txt";
                System.out.print("¿Cuántos números desea generar? ");
                int cantidad = scanner.nextInt();
                crearArchivoEjemplo(archivoEjemplo, cantidad);
                datos = leerArchivo(archivoEjemplo);
                break;
                
            case 3:
                datos = ingresarDatosManualmente();
                break;
                
            default:
                System.out.println("Opción no válida. Usando ingreso manual.");
                datos = ingresarDatosManualmente();
        }
        
        // Verificamos que tenemos datos para procesar
        if (datos.estaVacia()) {
            System.out.println("No hay datos para procesar. El programa terminará.");
            return;
        }
        
        // Mostramos los datos cargados
        datos.mostrarDatos();
        
        try {
            // Calculamos las estadísticas principales
            double media = datos.calcularMedia();
            double desviacionEstandar = datos.calcularDesviacionEstandar();
            
            System.out.println("\n=== RESULTADOS ESTADÍSTICOS ===");
            System.out.printf("Cantidad de números: %d\n", datos.getTamaño());
            System.out.printf("Media aritmética: %.6f\n", media);
            System.out.printf("Desviación estándar: %.6f\n", desviacionEstandar);
            
            // Interpretación de los resultados
            System.out.println("\n=== INTERPRETACIÓN ===");
            System.out.printf("La media (%.3f) representa el valor promedio de todos los datos.\n", media);
            System.out.printf("La desviación estándar (%.3f) indica qué tan dispersos están los datos:\n", desviacionEstandar);
            
            if (desviacionEstandar < media * 0.1) {
                System.out.println("- Los datos están muy concentrados cerca de la media");
            } else if (desviacionEstandar < media * 0.3) {
                System.out.println("- Los datos tienen una dispersión moderada");
            } else {
                System.out.println("- Los datos están muy dispersos respecto a la media");
            }
            
            // Mostramos estadísticas adicionales
            datos.mostrarEstadisticasComplementarias();
            
        } catch (IllegalStateException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        
        scanner.close();
    }
}