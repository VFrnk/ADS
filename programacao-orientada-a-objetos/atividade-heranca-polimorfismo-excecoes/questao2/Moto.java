public class Moto extends Veiculo {

    Moto(String modelo, double precoBase) throws DadoInvalidoException {
        super(modelo, precoBase);
    }

    @Override
    public double calcularImposto() {
        return super.getPrecoBase() * 0.03;
    }
}
