package EleNav;

public class Usuario {
    private String nome;
    private String endereco;
    private long CPF;

    public Usuario(String nome, String endereco, long CPF) {
        this.nome = nome;
        this.endereco = endereco;
        this.CPF = CPF;
    }

    public String imprimirUsuario() {
        return "Nome: " + nome + "\n" + "Endereço: " + endereco + "\n" + "CPF: " + CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public long getCPF() {
        return CPF;
    }
}
