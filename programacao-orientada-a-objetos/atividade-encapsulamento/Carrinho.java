import java.util.ArrayList;
import java.util.List;

// Exercício 7 - Carrinho
public class Carrinho {

    // - List<String> itens = new ArrayList<>();
    // - List<Double> precos = new ArrayList<>();
    // - double total;
    private List<String> itens;
    private List<Double> precos;
    private double total;

    /**
     * Construtor padrão do carrinho.
     */
    public Carrinho() {
        this.itens = new ArrayList<String>();
        this.precos = new ArrayList<Double>();
        this.total = 0.0;
    }

    /**
     * Adiciona um item ao carrinho.
     * Regras:
     * - nome válido (trim, não vazio)
     * - preco > 0
     * - atualizar total
     *
     * @param nome  nome do item
     * @param preco preço do item
     */
    public void adicionarItem(String nome, double preco) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome nao pode ser null ou vazio.");

        if (preco <= 0.0)
            throw new IllegalArgumentException("Preco deve ser maior que zero.");

        this.itens.add(nome.trim());
        this.precos.add(preco);
        this.total += preco;
    }

    /**
     * Remove um item pelo índice.
     * Deve validar se o índice está na faixa e atualizar o total.
     *
     * @param idx índice do item a ser removido
     * @return true se removeu, false se índice inválido
     */
    public boolean removerItemPorIndice(int idx) {
        if (idx < 0 || idx >= this.itens.size())
            return false;

        this.itens.remove(idx);
        double precoRemovido = this.precos.remove(idx);
        this.total -= precoRemovido;
        return true;
    }

    /**
     * Retorna uma cópia da lista de itens.
     *
     * @return lista de nomes de itens
     */
    public List<String> getItens() {
        return new ArrayList<String>(this.itens);
    }

    /**
     * Retorna uma cópia da lista de preços.
     *
     * @return lista de preços
     */
    public List<Double> getPrecos() {
        return new ArrayList<Double>(this.precos);
    }

    /**
     * Getter de total (somente leitura).
     *
     * @return valor total do carrinho
     */
    public double getTotal() {
        return this.total;
    }
}
