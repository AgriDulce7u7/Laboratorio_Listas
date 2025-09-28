package Ejercicio9;

public class Aplicacion {
    // Método main para probar la funcionalidad
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();

        System.out.println("=== Prueba de Lista Circular ===");

        // Insertar elementos
        lista.insertar(10);
        lista.insertar(20);
        lista.insertar(30);
        lista.insertar(40);

        // Imprimir la lista
        lista.imprimir();

        // Buscar elementos
        System.out.println("\nBúsquedas:");
        System.out.println("Buscar 20: " + lista.buscar(20));
        System.out.println("Buscar 50: " + lista.buscar(50));
        System.out.println("Buscar 10: " + lista.buscar(10));

        System.out.println("\nTamaño de la lista: " + lista.getTamano());
    }
}
