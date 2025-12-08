public abstract class Funcionario {
    private String nome;
    private String documento;

    Funcionario(String nome, String documento) throws DadoInvalidoException {
        setNome(nome);
        setDocumento(documento);
    }

    public abstract double calcularPagamento();

    //Getters
    public final String getNome() {
        return this.nome;
    }

    public final String getDocumento() {
        return this.documento;
    }

    //Setters
    private void setNome(String nome) throws DadoInvalidoException {
        if(nome == null || nome.isBlank())
            throw new DadoInvalidoException("Nome nao deve ser null ou vazio.");

        this.nome = nome;
    }

    private void setDocumento(String documento) throws DadoInvalidoException {
        if(documento == null || documento.isBlank())
            throw new DadoInvalidoException("Documento nao deve ser null ou vazio.");

        this.documento = documento;
    }
}