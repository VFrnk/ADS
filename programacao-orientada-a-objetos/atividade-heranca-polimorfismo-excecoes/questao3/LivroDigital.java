public class LivroDigital extends ItemDigital {
    private String autor;

    LivroDigital(String titulo, double tamanhoMB, String autor) throws ItemInvalidoException {
        super(titulo, tamanhoMB);

        if (autor == null || autor.isBlank())
            throw new ItemInvalidoException("Nome do autor nao deve ser nulo ou vazio.");

        this.autor = autor;
    }

    @Override
    public String getDescricao() {
        return String.format("Livro: %s - %s (%.2fMB)",
                super.getTitulo(),
                this.autor,
                super.getTamanhoMB()
        );
    }
}
