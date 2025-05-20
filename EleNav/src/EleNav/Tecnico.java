package EleNav;

public class Tecnico extends Instalacao {
    private int idTecnico;
    private String nome;
    private String especialidade;

    public Tecnico(int idInstalacao, PainelSolar painelInstalado, Residencia residencia, int idTecnico, String nome, String especialidade) {
        super(idInstalacao, painelInstalado, residencia);
        this.idTecnico = idTecnico;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void notificar() {
        System.out.println("Técnico " + nome + " (ID: " + idTecnico + ") foi notificado sobre a instalação " + getIdInstalacao() + ".");
    }
}