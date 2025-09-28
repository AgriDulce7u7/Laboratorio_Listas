package Ejercicio10;
// Ejercicio 10: Concatenar dos listas enlazadas simples

// Clase principal para el ejercicio de concatenación
public class ConcatenarListas {
    
    /**
     * Método que concatena dos listas enlazadas simples
     * Toma dos listas como parámetros y retorna una nueva lista que contiene
     * todos los elementos de la primera lista seguidos de todos los elementos
     * de la segunda lista
     * 
     * @param lista1 Primera lista a concatenar
     * @param lista2 Segunda lista a concatenar
     * @return Nueva lista que contiene la concatenación de ambas listas
     */
    public static ListaSimple concatenar(ListaSimple lista1, ListaSimple lista2) {
        // Creamos una nueva lista resultado
        ListaSimple listaResultado = new ListaSimple();
        
        // Si ambas listas están vacías, retornamos la lista vacía
        if (lista1.estaVacia() && lista2.estaVacia()) {
            return listaResultado;
        }
        
        // Si solo la primera lista tiene elementos
        if (!lista1.estaVacia() && lista2.estaVacia()) {
            listaResultado = copiarLista(lista1);
            return listaResultado;
        }
        
        // Si solo la segunda lista tiene elementos
        if (lista1.estaVacia() && !lista2.estaVacia()) {
            listaResultado = copiarLista(lista2);
            return listaResultado;
        }
        
        // Si ambas listas tienen elementos, las concatenamos
        // Primero copiamos todos los elementos de lista1
        listaResultado = copiarLista(lista1);
        
        // Luego agregamos todos los elementos de lista2
        Nodo actual = lista2.getCabeza();
        while (actual != null) {
            listaResultado.agregar(actual.dato);
            actual = actual.siguiente;
        }
        
        return listaResultado;
    }
    
    /**
     * Método auxiliar que crea una copia independiente de una lista
     * Esto asegura que la lista original no se modifique
     * 
     * @param listaOriginal Lista que se va a copiar
     * @return Nueva lista con los mismos elementos que la original
     */
    private static ListaSimple copiarLista(ListaSimple listaOriginal) {
        ListaSimple nuevaLista = new ListaSimple();
        
        if (listaOriginal.estaVacia()) {
            return nuevaLista;
        }
        
        // Recorremos la lista original y copiamos cada elemento
        Nodo actual = listaOriginal.getCabeza();
        while (actual != null) {
            nuevaLista.agregar(actual.dato);
            actual = actual.siguiente;
        }
        
        return nuevaLista;
    }
    
    // Método main para probar la funcionalidad
    public static void main(String[] args) {
        System.out.println("=== Prueba de Concatenación de Listas ===");
        
        // Creamos la primera lista: [1, 2, 3]
        ListaSimple lista1 = new ListaSimple();
        lista1.agregar(1);
        lista1.agregar(2);
        lista1.agregar(3);
        
        // Creamos la segunda lista: [4, 5, 6]
        ListaSimple lista2 = new ListaSimple();
        lista2.agregar(4);
        lista2.agregar(5);
        lista2.agregar(6);
        
        System.out.println("Lista 1:");
        lista1.imprimir();
        
        System.out.println("Lista 2:");
        lista2.imprimir();
        
        // Concatenamos las listas
        ListaSimple listaConcatenada = concatenar(lista1, lista2);
        
        System.out.println("\nLista concatenada (lista1 + lista2):");
        listaConcatenada.imprimir();
        System.out.println("Tamaño de la lista concatenada: " + listaConcatenada.getTamaño());
        
        // Verificamos que las listas originales no se modificaron
        System.out.println("\n=== Verificación de integridad ===");
        System.out.println("Lista 1 original sigue igual:");
        lista1.imprimir();
        System.out.println("Lista 2 original sigue igual:");
        lista2.imprimir();
        
        // Probamos casos especiales
        System.out.println("\n=== Casos Especiales ===");
        
        // Lista vacía + lista con elementos
        ListaSimple listaVacia = new ListaSimple();
        ListaSimple resultado1 = concatenar(listaVacia, lista1);
        System.out.println("Lista vacía + Lista 1:");
        resultado1.imprimir();
        
        // Lista con elementos + lista vacía
        ListaSimple resultado2 = concatenar(lista2, listaVacia);
        System.out.println("Lista 2 + Lista vacía:");
        resultado2.imprimir();
    }
}