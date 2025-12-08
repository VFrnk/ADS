public class Caminhao extends Veiculo {

    Caminhao(String modelo, double precoBase) throws DadoInvalidoException {
        super(modelo, precoBase);
    }

    @Override
    public double calcularImposto() {
        if (super.getPrecoBase() > 200_000)
            return super.getPrecoBase() * 0.1;
        else
            return super.getPrecoBase() * 0.08;
    }
}

