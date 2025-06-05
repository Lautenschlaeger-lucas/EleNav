package EleNav;

import java.time.LocalDate;

public class Instalacao {
    private final int idInstalacao;
    private final PainelSolar painelInstalado;
    private final Residencia residencia;
    private final LocalDate dataSolicitacao;
    private Tecnico tecnicoResponsavel;
    private StatusInstalacao status; // Usando Enum

    public Instalacao(int idInstalacao, PainelSolar painelInstalado, Residencia residencia) {
        this.idInstalacao = idInstalacao;
        this.painelInstalado = painelInstalado;
        this.residencia = residencia;
        this.dataSolicitacao = LocalDate.now();
        this.status = StatusInstalacao.SOLICITADO;
    }

    // Getters
    public int getIdInstalacao() {
        return idInstalacao;
    }
    
    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) { 
        this.tecnicoResponsavel = tecnicoResponsavel;
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

    public StatusInstalacao getStatus() {
        return status;
    }

    // Método para atualizar status com validação
    public void atualizarStatus(StatusInstalacao novoStatus) {
        if (novoStatus != null) {
            this.status = novoStatus;
            System.out.println("Status atualizado para: " + status);
        } else {
            System.out.println("Erro: Status inválido!");
        }
    }

    @Override
    public String toString() {
        return "Instalação ID: " + idInstalacao +
               ", Painel: " + painelInstalado.getNomePainel() +
               ", Residência: " + residencia.getEndereco() +
               ", Data Solicitação: " + dataSolicitacao +
               ", Status: " + status;
    }
}
