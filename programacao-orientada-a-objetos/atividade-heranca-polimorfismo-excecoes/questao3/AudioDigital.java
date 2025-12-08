public class AudioDigital extends ItemDigital {
    private int qualidadeKbps;

    AudioDigital(String titulo, double tamanhoMB, int qualidadeKbps) throws ItemInvalidoException {
        super(titulo, tamanhoMB);

        if (qualidadeKbps <= 64)
            throw new ItemInvalidoException("qualidade de audio (Kbps) deve ser maior que 64.");

        this.qualidadeKbps = qualidadeKbps;
    }

    @Override
    public String getDescricao() {
        return String.format("Audio: %s - %dKbps (%.2fMB)",
                super.getTitulo(),
                this.qualidadeKbps,
                super.getTamanhoMB()
        );
    }
}
