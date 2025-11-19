public class BibliotecaTeste {

    public static void main(String[] args) {

        Biblioteca b = new Biblioteca();

        System.out.println("=== Teste de Adição ===");
        System.out.println(b.adicionarTitulo("  O senhor   dos Aneis  ")); // true
        System.out.println(b.adicionarTitulo("o SENHOR dos anéis")); // false (duplicado)
        System.out.println(b.adicionarTitulo("  ")); // false

        System.out.println("\n=== Teste de Remoção ===");
        System.out.println(b.removerTitulo("O SENHOR DOS ANEIS")); // true
        System.out.println(b.removerTitulo("Inexistente")); // false

        b.adicionarTitulo("Harry Potter");
        b.adicionarTitulo("Hamlet");
        b.adicionarTitulo("Hobbit");

        System.out.println("\n=== Teste de Busca por Prefixo ===");
        System.out.println(b.buscarPorPrefixo("Ha")); // Harry Potter, Hamlet
        System.out.println(b.buscarPorPrefixo("ho")); // Hobbit

        System.out.println("\n=== Teste de Ordenação ===");
        b.ordenar();
        System.out.println(b.buscarPorPrefixo("")); // lista ordenada
    }
}
