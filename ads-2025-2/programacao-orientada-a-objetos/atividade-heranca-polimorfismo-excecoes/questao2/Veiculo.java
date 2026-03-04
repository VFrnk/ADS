public abstract class Veiculo implements Tributavel {
    private String modelo;
    private double precoBase;

    Veiculo(String modelo, double precoBase) throws DadoInvalidoException {
        if (modelo == null || modelo.isBlank())
            throw new DadoInvalidoException("modelo nao deve ser nulo ou vazio.");

        if (precoBase <= 0)
            throw new DadoInvalidoException("preco base deve ser maior que 0.");

        this.modelo = modelo;
        this.precoBase = precoBase;
    }

    public abstract double calcularImposto();

    public String getModelo() {
        return this.modelo;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }
}
