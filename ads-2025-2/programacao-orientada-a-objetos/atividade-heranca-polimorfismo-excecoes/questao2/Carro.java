public class Carro extends Veiculo {

    Carro(String modelo, double precoBase) throws DadoInvalidoException {
        super(modelo, precoBase);
    }

    @Override
    public double calcularImposto() {
        return super.getPrecoBase() * 0.05;
    }
}
