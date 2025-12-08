public abstract class ItemDigital {
    private String titulo;
    private double tamanhoMB;

    ItemDigital(String titulo, double tamanhoMB) throws ItemInvalidoException {
        if (titulo == null || titulo.isBlank())
            throw new ItemInvalidoException("titulo nao deve ser nulo ou vazio.");

        if (tamanhoMB <= 0)
            throw new ItemInvalidoException("tamanho em MB deve ser maior que 0");

        this.titulo = titulo;
        this.tamanhoMB = tamanhoMB;
    }

    public abstract String getDescricao();

    public String getTitulo() {
        return this.titulo;
    }

    public double getTamanhoMB() {
        return this.tamanhoMB;
    }
}
