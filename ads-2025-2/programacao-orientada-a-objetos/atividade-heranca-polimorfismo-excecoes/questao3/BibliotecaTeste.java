public class BibliotecaTeste {

    public static void main(String[] args) {
        try {
            Biblioteca b = new Biblioteca();

            System.out.println("=== Teste: Adicionando itens válidos ===");
            ItemDigital livro = new LivroDigital(" O Senhor dos Aneis ", 12.5, "Tolkien");
            ItemDigital video = new VideoDigital("Senhor dos Aneis - Trailer", 200.0, 3);
            ItemDigital audio = new AudioDigital("Music Theme   ", 5.1, 128);

            b.adicionar(livro);
            b.adicionar(video);
            b.adicionar(audio);

            System.out.println("Itens adicionados com sucesso!\n");

            System.out.println("=== Teste: Tentando adicionar duplicata ===");

            try {
                // Mesmo título (ignora caixa e espaços) → deve lançar exceção
                ItemDigital duplicado = new LivroDigital(" o  senhor    dos ANEIS", 15.0, "Outro Autor");
                b.adicionar(duplicado);
                System.out.println("ERRO: não deveria permitir duplicata!");
            } catch (ItemInvalidoException e) {
                System.out.println("OK! Exceção corretamente lançada: " + e.getMessage());
            }

            System.out.println("\n=== Teste: Buscar por prefixo \"sen\" ===");
            var achados = b.buscarPorPrefixo("sen");

            for (ItemDigital item : achados) {
                System.out.println(item.getDescricao());
            }

            System.out.println("\n=== Teste: Relatório ===");
            System.out.println(b.gerarRelatorio());

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
