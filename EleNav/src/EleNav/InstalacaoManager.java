package EleNav;

import java.util.ArrayList;
import java.util.List;

public class InstalacaoManager {
    private List<Instalacao> instalacoesSolicitadas = new ArrayList<>();
    private List<Tecnico> tecnicosParceiros = new ArrayList<>();
    private int proximoIdInstalacao = 1;

    public void adicionarTecnico(Tecnico tecnico) {
        tecnicosParceiros.add(tecnico);
    }

    public void solicitarInstalacao(PainelSolar painel, Residencia residencia) {
        Instalacao novaInstalacao = new Instalacao(proximoIdInstalacao++, painel, residencia);
        instalacoesSolicitadas.add(novaInstalacao);
        System.out.println("Instalação " + novaInstalacao.getIdInstalacao() + " solicitada com sucesso!");
        notificarTecnicoDisponivel(novaInstalacao);
    }

    public void notificarTecnicoDisponivel(Instalacao instalacao) {
        if (tecnicosParceiros.isEmpty()) {
            System.out.println("Nenhum técnico parceiro cadastrado para notificação.");
            return;
        }

        for (Tecnico tecnico : tecnicosParceiros) {
            tecnico.notificar();
            instalacao.setStatus("Aguardando Agendamento");
            System.out.println("Status da instalação " + instalacao.getIdInstalacao() + " atualizado para: " + instalacao.getStatus());
            return; // Notifica o primeiro técnico encontrado e retorna (você pode ajustar essa lógica)
        }

        System.out.println("Nenhum técnico disponível no momento para a instalação " + instalacao.getIdInstalacao() + ".");
    }

    public List<Instalacao> listarInstalacoesSolicitadas() {
        return instalacoesSolicitadas;
    }
}