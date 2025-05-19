package EleNav;

public class PainelSolar {
    private double potencia;
    private double area;
    private double preco;

    public PainelSolar(double potencia, double area, double preco) {
        this.potencia = potencia;
        this.area = area;
        this.preco = preco;
    }

    public double getPotencia() {
        return potencia;
    }
    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }
    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        this.area = area;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String imprimirDadosPainel() {
        return "Potência: " + this.potencia + " kW\nÁrea: " + this.area + " m²\nPreço: R$ " + this.preco;
    }
}