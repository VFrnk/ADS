public class Termostato {
    private int temperaturaAtual;
    private int temperaturaMin;
    private int temperaturaMax;

    public Termostato(int temperaturaMin, int temperaturaMax, int temperaturaAtual) {
        this.temperaturaMin = Math.min(temperaturaMin, temperaturaMax);
        this.temperaturaMax = temperaturaMax;

        if (!this.ajustarTemperatura(temperaturaAtual))
            this.temperaturaAtual = (this.temperaturaMax + this.temperaturaMin) / 2;
    }

    public boolean ajustarTemperatura(int novaTemperatura){
        if (novaTemperatura < this.temperaturaMin || novaTemperatura > this.temperaturaMax)
            return false;

        this.temperaturaAtual = novaTemperatura;
        return true;
    }

    public void aumentar(){
        this.temperaturaAtual = Math.min(this.temperaturaMax, this.temperaturaAtual + 1);
    }

    public void diminuir(){
        this.temperaturaAtual = Math.max(this.temperaturaMin, this.temperaturaAtual - 1);
    }


    // Getter adicional para fins de testes
    public int getTemperaturaAtual(){
        return this.temperaturaAtual;
    }

}
