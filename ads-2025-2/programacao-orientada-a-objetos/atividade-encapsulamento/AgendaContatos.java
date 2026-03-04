import java.util.ArrayList;
import java.util.List;

// Exercício 6 - AgendaContatos
public class AgendaContatos {

    // - List<Contato> contatos
    private List<Contato> contatos;

    /**
     * Construtor da agenda.
     */
    public AgendaContatos() {
        this.contatos = new ArrayList<Contato>();
    }

    /**
     * Adiciona um contato à agenda.
     * Regras:
     * - nome e telefone válidos
     * - não permitir dois contatos com o mesmo nome (case insensitive)
     *
     * @param nome     nome do contato
     * @param telefone telefone do contato
     * @return true se adicionou, false caso contrário
     */
    public boolean adicionarContato(String nome, String telefone) {
        for (Contato contato : this.contatos)
            if (nome.equalsIgnoreCase(contato.getNome()))
                return false;
        
        return this.contatos.add(new Contato(nome, telefone));
    }

    /**
     * Remove um contato pelo nome (case insensitive).
     *
     * @param nome nome do contato a ser removido
     * @return true se removeu, false se não encontrar
     */
    public boolean removerContato(String nome) {
        return this.contatos.removeIf(contato -> contato.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Busca um contato pelo nome.
     * Deve retornar uma cópia do Contato ou null se não encontrar.
     *
     * @param nome nome a ser buscado
     * @return cópia do contato encontrado ou null
     */
    public Contato buscarPorNome(String nome) {
        for (Contato contato : this.contatos)
            if (contato.getNome().equalsIgnoreCase(nome))
                return new Contato(contato.getNome(), contato.getTelefone());

        return null;
    }

    /**
     * Lista de contatos da agenda.
     * Deve retornar lista de cópias ou lista imutável.
     *
     * @return lista de contatos (cópias ou imutável)
     */
    public List<Contato> listarContatos() {
        List<Contato> copia = new ArrayList<>();

        for (Contato contato : this.contatos)
            copia.add(new Contato(contato.getNome(), contato.getTelefone()));

        return copia;
    }
}
