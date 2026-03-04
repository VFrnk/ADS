import java.nio.channels.IllegalChannelGroupException;

// Exercício 6 - Contato
public class Contato {

    // - String nome
    // - String telefone (pelo menos 8 caracteres)
    private String nome;
    private String telefone;

    /**
     * Construtor do contato.
     *
     * @param nome     nome do contato
     * @param telefone telefone do contato
     */
    public Contato(String nome, String telefone) {
        this.setNome(nome);
        this.setTelefone(telefone);
    }

     public String getNome() {
        return this.nome;
     }
     public String getTelefone() {
        return this.telefone;
     }

     public void setNome(String nome){
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome nao pode ser null ou vazio.");

        this.nome = nome;
     }

     public void setTelefone(String telefone){
        if (telefone == null || telefone.isBlank() || telefone.length() < 8)
            throw new IllegalArgumentException("telefone nao pode ser null ou vazio e deve possuir pelo menos 8 caracteres.");

        this.telefone = telefone;
     }
}
