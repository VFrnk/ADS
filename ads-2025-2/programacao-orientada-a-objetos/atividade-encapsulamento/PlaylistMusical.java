import java.util.ArrayList;
import java.util.List;

// Exercício 5 - PlaylistMusical
public class PlaylistMusical {

    // - String nome
    // - List<String> musicas (ex.: ArrayList<String>)
    private String nome;
    private List<String> musicas;

    /**
     * Construtor da Playlist.
     *
     * @param nome nome da playlist
     */
    public PlaylistMusical(String nome) {
        if(nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome da playlist nao pode ser null ou vazio.");

        this.nome = nome;
        this.musicas = new ArrayList<String>();
    }

    /**
     * Adiciona uma música à playlist.
     * Regra: não permitir null nem string vazia.
     *
     * @param nomeMusica nome da música a ser adicionada
     */
    public void adicionarMusica(String nomeMusica) {
        if (nomeMusica == null || nomeMusica.isBlank())
            throw new IllegalArgumentException("Nome da musica não deve ser null ou vazia.");

        this.musicas.add(nomeMusica);
    }

    /**
     * Remove uma música pelo nome.
     *
     * @param nomeMusica nome da música a ser removida
     * @return true se conseguiu remover, false caso contrário
     */
    public boolean removerMusica(String nomeMusica) {
        return this.musicas.remove(nomeMusica);
    }

    /**
     * Lista de músicas da playlist.
     * Deve retornar uma cópia da lista interna, não a lista real.
     *
     * @return cópia da lista de músicas
     */
    public List<String> listarMusicas() {
        return new ArrayList<String>(this.musicas);
    }

    /**
     * Retorna a quantidade de músicas na playlist.
     *
     * @return número de músicas
     */
    public int quantidadeMusicas() {
        return this.musicas.size();
    }
}
