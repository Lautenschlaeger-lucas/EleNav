package EleNav;

import java.util.ArrayList;
import java.util.List;

public class InstalacaoManager {
    private final List<Instalacao> instalacoesSolicitadas = new ArrayList<>();
    private final List<Tecnico> tecnicosParceiros = new ArrayList<>();
    private int proximoIdInstalacao = 1;

    public void adicionarTecnico(Tecnico tecnico) {
        tecnicosParceiros.add(tecnico);
    }
    
    public List<Tecnico> getTecnicosParceiros() {
        return this.tecnicosParceiros; 
    }

    
    
    public Tecnico getTecnicoPorId(int idTecnico) {
        for (Tecnico tecnico : tecnicosParceiros) {
            if (tecnico.getIdTecnico() == idTecnico) {
                return tecnico;
            }
        }
        return null; // Retorna null se não encontrar
    }


    // Solicitar uma nova instalação
    public void solicitarInstalacao(PainelSolar painel, Residencia residencia, int idTecnicoResponsavel) {
        Instalacao novaInstalacao = new Instalacao(proximoIdInstalacao++, painel, residencia);
        
        Tecnico tecnico = getTecnicoPorId(idTecnicoResponsavel);
        if (tecnico != null) {
            novaInstalacao.setTecnicoResponsavel(tecnico);
        } else {
            System.out.println("Técnico com ID " + idTecnicoResponsavel + " não encontrado. Instalação sem técnico atribuído.");
        }
        
        instalacoesSolicitadas.add(novaInstalacao);
        System.out.println("Instalação " + novaInstalacao.getIdInstalacao() + " solicitada com sucesso!");
        notificarTecnicoDisponivel(novaInstalacao);
    }


    // Notificar técnicos disponíveis
    public void notificarTecnicoDisponivel(Instalacao instalacao) {
        if (instalacao.getTecnicoResponsavel() == null) {
            System.out.println("Nenhum técnico responsável definido para a instalação " + instalacao.getIdInstalacao());
            return;
        }

        Tecnico tecnico = instalacao.getTecnicoResponsavel();
        System.out.println("Técnico " + tecnico.getNome() + " (ID: " + tecnico.getIdTecnico() + ") foi notificado sobre a instalação " + instalacao.getIdInstalacao() + ".");
    }
    
    


    // Atualizar status de uma instalação específica
	/*
	 * public void atualizarStatusInstalacao(int idInstalacao, StatusInstalacao
	 * novoStatus) { for (Instalacao instalacao : instalacoesSolicitadas) { if
	 * (instalacao.getIdInstalacao() == idInstalacao) {
	 * instalacao.atualizarStatus(novoStatus);
	 * System.out.println("Status da instalação " + idInstalacao +
	 * " atualizado para: " + novoStatus); return; } }
	 * System.out.println("Instalação não encontrada!"); }
	 */
    
    public void atualizarStatusInstalacaoPorTecnico(int idInstalacao, int idTecnico, StatusInstalacao novoStatus) {
        for (Instalacao instalacao : instalacoesSolicitadas) {
            if (instalacao.getIdInstalacao() == idInstalacao) {
                if (instalacao.getTecnicoResponsavel() != null && instalacao.getTecnicoResponsavel().getIdTecnico() == idTecnico) {
                    instalacao.atualizarStatus(novoStatus);
                    System.out.println("Técnico " + idTecnico + " atualizou o status da instalação " + idInstalacao + " para: " + novoStatus);
                    return;
                } else {
                    System.out.println("Erro: Apenas o técnico responsável pela instalação pode atualizar o status.");
                    return;
                }
            }
        }
        System.out.println("Instalação não encontrada!");
    }


    // Listar instalações solicitadas
    public List<Instalacao> listarInstalacoesSolicitadas() {
        return instalacoesSolicitadas; // Apenas retorna a lista sem imprimir diretamente no método
    }
}
