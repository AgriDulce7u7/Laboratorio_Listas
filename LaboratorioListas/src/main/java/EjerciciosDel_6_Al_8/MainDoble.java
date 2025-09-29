package EjerciciosDel_6_Al_8;

public class MainDoble {
    public static void main(String[] args) {
        ListaDoble<Integer> listaNums = new ListaDoble<>();
        listaNums.agregarFinal(10);
        listaNums.agregarFinal(20);
        listaNums.agregarFinal(30);

        System.out.println("Lista hacia adelante:");
        listaNums.imprimirLista();

        System.out.println("Lista hacia atrás:");
        listaNums.imprimirHaciaAtras();

        System.out.print("Recorriendo con iterator: ");
        for (Integer n : listaNums) {
            System.out.print(n + " ");
        }


        // Lista de personas
        ListaDoble<Persona> listaPersonas = new ListaDoble<>();
        listaPersonas.agregarFinal(new Persona("Ana", "123456"));
        listaPersonas.agregarFinal(new Persona("Luis", "98765"));
        listaPersonas.agregarFinal(new Persona("Marta", "4444"));
        listaPersonas.agregarFinal(new Persona("Pedro", "87654321"));

        System.out.println("\nLista de personas:");
        listaPersonas.imprimirLista();

        System.out.println("\nLista de personas hacía atrás:");
        listaPersonas.imprimirHaciaAtras();

        System.out.println();

        // Filtrar personas con cédula de dígitos pares
        ListaDoble<Persona> cedulaPar = listaPersonas.filtrar(p -> p.getCedula().length() % 2 == 0);
        System.out.println("Personas con cédula de dígitos pares:");
        cedulaPar.imprimirLista();
    }
}