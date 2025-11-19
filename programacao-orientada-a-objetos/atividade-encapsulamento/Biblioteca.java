import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Exercício 8 - Biblioteca
public class Biblioteca {

    // - List<String> titulos = new ArrayList<>();
    private List<String> titulos;

    /**
     * Construtor da biblioteca.
     */
    public Biblioteca() {
        this.titulos = new ArrayList<String>();
    }

    /**
     * Adiciona um título à biblioteca.
     * Regras:
     * - normalizar (trim, colapsar espaços)
     * - evitar duplicatas ignorando case
     *
     * @param novoTitulo a ser adicionado
     * @return true se adicionou, false se já existia ou inválido
     */
    public boolean adicionarTitulo(String novoTitulo) {
        if (novoTitulo == null || novoTitulo.isBlank())
            return false;

        String tituloNormalizado = novoTitulo.trim().replaceAll("\\s+", " ");

        for (String titulo : this.titulos)
            if (titulo.equalsIgnoreCase(tituloNormalizado))
                return false;

        return this.titulos.add(tituloNormalizado);
    }

    /**
     * Remove um título da biblioteca.
     * Regra: remover ignorando case.
     *
     * @param tituloRemover título a ser removido
     * @return true se removeu, false se não encontrar
     */
    public boolean removerTitulo(String tituloRemover) {
        return this.titulos.removeIf(titulo -> titulo.equalsIgnoreCase(tituloRemover));
    }

    /**
     * Busca títulos que comecem com um prefixo (case-insensitive).
     *
     * @param prefixo prefixo a ser buscado
     * @return cópia da lista de títulos que atendem ao critério
     */
    public List<String> buscarPorPrefixo(String prefixo) {
        if (prefixo == null)
            throw new IllegalArgumentException("prefixo nao deve ser null.");

        List<String> resultadoBusca = new ArrayList<>();
        String lowerCasePrefix = prefixo.toLowerCase();

        for (String titulo : this.titulos)
            if (titulo.toLowerCase().startsWith(lowerCasePrefix))
                resultadoBusca.add(titulo);

        return resultadoBusca;
    }

    /**
     * Ordena os títulos internamente (por exemplo, ordem alfabética).
     */
    public void ordenar() {
        Collections.sort(this.titulos);
    }
}
