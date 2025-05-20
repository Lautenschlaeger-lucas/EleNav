package EleNav;

import java.time.LocalDate;

public class Instalacao {
    private int idInstalacao;
    private PainelSolar painelInstalado;
    private Residencia residencia;
    private LocalDate dataSolicitacao;
    private String status; // Ex: "Solicitado", "Aguardando Agendamento", "Agendado", "Concluído"

    public Instalacao(int idInstalacao, PainelSolar painelInstalado, Residencia residencia) {
        this.idInstalacao = idInstalacao;
        this.painelInstalado = painelInstalado;
        this.residencia = residencia;
        this.dataSolicitacao = LocalDate.now();
        this.status = "Solicitado";
    }

    // Getters e setters
    public int getIdInstalacao() {
        return idInstalacao;
    }

    public PainelSolar getPainelInstalado() {
        return painelInstalado;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Instalação ID: " + idInstalacao +
               ", Painel: " + (painelInstalado != null ? painelInstalado.getNomePainel() : "N/A") +
               ", Residência: " + (residencia != null ? residencia.getEndereco() : "N/A") +
               ", Data Solicitação: " + dataSolicitacao +
               ", Status: " + status;
    }
}