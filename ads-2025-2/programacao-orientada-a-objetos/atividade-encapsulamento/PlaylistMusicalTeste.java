import java.util.List;

public class PlaylistMusicalTeste {

    public static void main(String[] args) {

        System.out.println("=== Criando Playlist ===");
        PlaylistMusical p = new PlaylistMusical("Minhas Favoritas");

        System.out.println("\n=== Adicionando músicas ===");
        p.adicionarMusica("Imagine");
        p.adicionarMusica("Billie Jean");
        p.adicionarMusica("Bohemian Rhapsody");

        System.out.println("Quantidade: " + p.quantidadeMusicas());
        System.out.println("Lista: " + p.listarMusicas());

        System.out.println("\n=== Removendo música ===");
        System.out.println("Remover 'Imagine': " + p.removerMusica("Imagine"));
        System.out.println("Remover 'NãoExiste': " + p.removerMusica("NãoExiste"));

        System.out.println("\nQuantidade após remoções: " + p.quantidadeMusicas());
        System.out.println("Lista: " + p.listarMusicas());

        System.out.println("\n=== Teste de Encapsulamento ===");
        List<String> copia = p.listarMusicas();
        copia.clear(); // essa alteração não deve afetar a playlist original

        System.out.println("Lista original após tentativa de clear externo: " + p.listarMusicas());

        System.out.println("\n=== Teste de Validação ===");
        try {
            p.adicionarMusica("");
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        try {
            p.adicionarMusica(null);
        } catch (Exception e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}
