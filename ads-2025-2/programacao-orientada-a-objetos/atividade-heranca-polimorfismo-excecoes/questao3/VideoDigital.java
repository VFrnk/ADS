public class VideoDigital extends ItemDigital {
    private int duracaoMin;

    VideoDigital(String titulo, double tamanhoMB, int duracaoMin) throws ItemInvalidoException {
        super(titulo, tamanhoMB);

        if (duracaoMin <= 1)
            throw new ItemInvalidoException("duracao deve ser maior que 1.");

        this.duracaoMin = duracaoMin;
    }

    @Override
    public String getDescricao() {
        return String.format("Video: %s - %d min (%.2fMB)",
                super.getTitulo(),
                this.duracaoMin,
                super.getTamanhoMB()
        );
    }
}
