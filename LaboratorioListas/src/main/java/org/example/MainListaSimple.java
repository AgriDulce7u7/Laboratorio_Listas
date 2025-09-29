package org.example;

public class MainListaSimple {
    public static void main(String[] args) {
        // ==== Lista de números ====
        ListaSimple<Integer> listaNums = new ListaSimple<>();
        listaNums.agregarFinal(1);
        listaNums.agregarFinal(2);
        listaNums.agregarFinal(3);
        listaNums.agregarFinal(4);
        listaNums.agregarFinal(5);
        listaNums.agregarFinal(6);
        listaNums.agregarFinal(3); // repetido para probar contar

        System.out.println("Lista original de números:");
        listaNums.imprimirLista();

        // 1) Obtener números en posiciones impares
        ListaSimple<Integer> posicionesImpares = listaNums.obtenerElementosEnPosicionesImpares();
        System.out.println("\nNúmeros en posiciones impares:");
        posicionesImpares.imprimirLista();

        // 3) Eliminar números pares
        listaNums.eliminarSi(n -> n % 2 == 0);
        System.out.println("\nLista después de eliminar pares:");
        listaNums.imprimirLista();

        // 4) Obtener lista de impares
        ListaSimple<Integer> listaImpares = listaNums.obtenerImparesNumeros();
        System.out.println("\nLista solo con impares:");
        listaImpares.imprimirLista();

        // 5) Contar repeticiones de un valor
        int valorBuscado = 3;
        int repeticiones = listaNums.contarRepetidos(valorBuscado);
        System.out.println("\nEl valor " + valorBuscado + " se repite " + repeticiones + " veces.");

        // ==== Lista de personas ====
        ListaSimple<Persona> listaPersonas = new ListaSimple<>();
        listaPersonas.agregarFinal(new Persona("Ana", "123456"));
        listaPersonas.agregarFinal(new Persona("Luis", "98765"));
        listaPersonas.agregarFinal(new Persona("Marta", "4444"));
        listaPersonas.agregarFinal(new Persona("Pedro", "87654321"));

        System.out.println("\nLista original de personas:");
        listaPersonas.imprimirLista();

        // 2) Personas con cédula de cantidad de dígitos par
        ListaSimple<Persona> personasCedulaPar = listaPersonas.filtrar(p -> p.getCedula().length() % 2 == 0);
        System.out.println("\nPersonas con cédula de cantidad de dígitos par:");
        personasCedulaPar.imprimirLista();
    }
}
