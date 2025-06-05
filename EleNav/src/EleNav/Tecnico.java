package EleNav;

public class Tecnico extends Instalacao {
    private int idTecnico;
    private String nome;
    private String especialidade;

    public Tecnico(Integer idInstalacao, PainelSolar painelInstalado, Residencia residencia, int idTecnico, String nome, String especialidade) {
        super(idInstalacao != null ? idInstalacao : 0, painelInstalado, residencia); // Aceita null e define um valor padrão
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

    public void atualizarStatus(StatusInstalacao novoStatus) {
        super.atualizarStatus(novoStatus); // Atualizando o status da instalação herdada
        System.out.println("Técnico " + nome + " atualizou a instalação " + getIdInstalacao() + " para " + novoStatus);
    }
}
