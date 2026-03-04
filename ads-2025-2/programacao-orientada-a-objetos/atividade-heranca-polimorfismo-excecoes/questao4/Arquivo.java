public class Arquivo extends NodeSistema {
    private long tamanhoBytes;

    Arquivo(String nome, long tamanhoBytes) throws ArquivoInvalidoException {
        super(nome);

        if (tamanhoBytes <= 0)
            throw new ArquivoInvalidoException("tamanho deve ser maior que 0.");

        this.tamanhoBytes = tamanhoBytes;
    }

    @Override
    public long getTamanho() {
        return this.tamanhoBytes;
    }
}
