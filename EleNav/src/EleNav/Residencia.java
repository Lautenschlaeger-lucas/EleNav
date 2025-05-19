package EleNav;

public class Residencia {
    private String endereco;
    private double tamanho;
    private double consumo;
    
    public Residencia(String endereco, double consumo, double tamanho) {
        this.endereco = endereco;
        this.consumo = consumo;
        this.tamanho = tamanho;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setConsumo(double consumo) {
    	this.consumo = consumo;
    }
    
    public double getConsumo() {
    	return consumo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public String imprimirResidencia() {
        return "Endereço: " + this.endereco + "\nTamanho: " + this.tamanho;
    }

}
