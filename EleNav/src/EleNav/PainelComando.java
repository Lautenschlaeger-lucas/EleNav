package EleNav;

import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class PainelComando {
    private UsuarioManager usuarioManager = new UsuarioManager();
    private PainelSolarManager painelSolarManager = new PainelSolarManager();
    private ResidenciaManager residenciaManager = new ResidenciaManager();
    private LoginManager loginManager = new LoginManager();
    private InstalacaoManager instalacaoManager = new InstalacaoManager();

  //  private boolean usuarioLogado = false;

    public PainelComando() {
        instalacaoManager.adicionarTecnico(new Tecnico(1, null, null, 101, "João Técnico", "Solar"));
        instalacaoManager.adicionarTecnico(new Tecnico(2, null, null, 102, "Maria Instaladora", "Solar"));
    }

    public void painelPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            imprimirMenuPrincipal();
            int option = lerInt(scanner);

            switch (option) {
                case 1 -> cadastrarUsuarioLogin(scanner);
                case 2 -> realizarLoginCliente(scanner);
                case 3 -> acessarPainelTecnico(scanner);
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    sair = true;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private void imprimirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Cadastrar Login de Cliente");
        System.out.println("2 - Login como Cliente");
        System.out.println("3 - Login como Técnico");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção: ");
    }

    private void realizarLoginCliente(Scanner scanner) {
        System.out.print("Email: ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (loginManager.verificarLogin(user, password)) {
            System.out.println("Login de cliente realizado com sucesso!");
            menuLogado(scanner);
        } else {
            System.out.println("Usuário ou senha inválidos!");
        }
    }

    private void menuLogado(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            imprimirMenuLogado();
            int opcao = lerInt(scanner);

            switch (opcao) {
                case 3 -> usuarioManager.listarUsuarios();
                case 4 -> criarPainelSolar(scanner);
                case 5 -> painelSolarManager.listarPainelSolar();
                case 6 -> criarResidencia(scanner);
                case 7 -> residenciaManager.listarResidencias();
                case 8 -> calcularPainelSolar(scanner);
                case 9 -> solicitarInstalacao(scanner);
                case 10 -> listarSolicitacoesInstalacao();
                case 11 -> acessarPainelTecnico(scanner);
                case 0 -> {
                    System.out.println("Saindo...");
                    sair = true;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void imprimirMenuLogado() {
        System.out.println("\n=== MENU USUÁRIO LOGADO ===");
        System.out.println("3 - Listar Usuários");
        System.out.println("4 - Criar Painel Solar");
        System.out.println("5 - Listar Painéis Solares");
        System.out.println("6 - Criar Residência");
        System.out.println("7 - Listar Residências");
        System.out.println("8 - Calcular Painéis e Retorno do Investimento");
        System.out.println("9 - Solicitar Instalação");
        System.out.println("10 - Listar Solicitações de Instalação");
        System.out.println("11 - Acessar Painel do Técnico");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção: ");
    }

    private int lerInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número válido: ");
            }
        }
    }

    private void cadastrarUsuarioLogin(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        long CPF = 0;
        while (true) {
            System.out.print("CPF (apenas números): ");
            String cpfInput = scanner.nextLine();
            try {
                CPF = Long.parseLong(cpfInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("CPF inválido. Digite apenas números.");
            }
        }

        System.out.print("Email (login): ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        usuarioManager.cadastrarUsuario(nome, endereco, CPF);
        loginManager.cadastrarLogin(user, password);
    }

    private void criarPainelSolar(Scanner scanner) {
        System.out.print("Nome (Marca/modelo) do painel solar: ");
        String nomePainel = scanner.nextLine();

        double potencia = lerDouble(scanner, "Potência do painel solar (kW): ");
        double area = lerDouble(scanner, "Área do painel solar (m²): ");
        double preco = lerDouble(scanner, "Preço do painel solar (R$): ");

        painelSolarManager.criarPainelSolar(nomePainel, potencia, area, preco);
        System.out.println("Painel solar criado com sucesso!");
    }

    private void criarResidencia(Scanner scanner) {
        System.out.print("Endereço da residência: ");
        String endereco = scanner.nextLine();

        double consumo = lerDouble(scanner, "Consumo mensal (kWh): ");
        double tamanho = lerDouble(scanner, "Tamanho do telhado (m²): ");

        residenciaManager.criarResidencia(endereco, consumo, tamanho);
        System.out.println("Residência criada com sucesso!");
    }

    private double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }

    private void calcularPainelSolar(Scanner scanner) {
        if (residenciaManager.getListaResidencia().isEmpty() || painelSolarManager.getListaPainelSolar().isEmpty()) {
            System.out.println("Erro: É necessário cadastrar uma residência e pelo menos um painel solar.");
            return;
        }

        System.out.println("Selecione a residência (índice): ");
        for (int i = 0; i < residenciaManager.getListaResidencia().size(); i++) {
            System.out.println(i + " - " + residenciaManager.getListaResidencia().get(i).getEndereco());
        }
        int indiceResidencia = lerInt(scanner);

        System.out.println("Selecione o painel solar (índice): ");
        for (int i = 0; i < painelSolarManager.getListaPainelSolar().size(); i++) {
            System.out.println(i + " - Potência: " + painelSolarManager.getListaPainelSolar().get(i).getPotencia() + " kW");
        }
        int indicePainel = lerInt(scanner);

        CalcPainel.calcularPainelSolar(
            residenciaManager.getListaResidencia(),
            painelSolarManager.getListaPainelSolar(),
            indiceResidencia,
            indicePainel
        );
    }

    private void solicitarInstalacao(Scanner scanner) {
        if (residenciaManager.getListaResidencia().isEmpty() || painelSolarManager.getListaPainelSolar().isEmpty()) {
            System.out.println("Erro: É necessário cadastrar uma residência e pelo menos um painel solar para solicitar uma instalação.");
            return;
        }

        int indiceResidencia, indicePainel;

        do {
            System.out.println("Selecione a residência para a instalação (índice): ");
            for (int i = 0; i < residenciaManager.getListaResidencia().size(); i++) {
                System.out.println(i + " - " + residenciaManager.getListaResidencia().get(i).getEndereco());
            }
            indiceResidencia = lerInt(scanner);
        } while (indiceResidencia < 0 || indiceResidencia >= residenciaManager.getListaResidencia().size());

        do {
            System.out.println("Selecione o painel solar a ser instalado (índice): ");
            for (int i = 0; i < painelSolarManager.getListaPainelSolar().size(); i++) {
                System.out.println(i + " - " + painelSolarManager.getListaPainelSolar().get(i).getNomePainel());
            }
            indicePainel = lerInt(scanner);
        } while (indicePainel < 0 || indicePainel >= painelSolarManager.getListaPainelSolar().size());

        Residencia residenciaSelecionada = residenciaManager.getListaResidencia().get(indiceResidencia);
        PainelSolar painelSelecionado = painelSolarManager.getListaPainelSolar().get(indicePainel);

        List<Tecnico> tecnicos = instalacaoManager.getTecnicosParceiros();
        int idTecnicoResponsavel = -1; 

        if (!tecnicos.isEmpty()) {
            Random rand = new Random();
            Tecnico tecnicoAleatorio = tecnicos.get(rand.nextInt(tecnicos.size()));
            idTecnicoResponsavel = tecnicoAleatorio.getIdTecnico();
            System.out.println("Técnico " + tecnicoAleatorio.getNome() + " (ID: " + idTecnicoResponsavel + ") atribuído automaticamente à instalação.");
        } else {
            System.out.println("Nenhum técnico cadastrado. Instalação será solicitada sem técnico atribuído.");
        }

        instalacaoManager.solicitarInstalacao(painelSelecionado, residenciaSelecionada, idTecnicoResponsavel);

        System.out.println("Solicitação de instalação feita com sucesso!");
    }


    private void listarSolicitacoesInstalacao() {
        List<Instalacao> instalacoes = instalacaoManager.listarInstalacoesSolicitadas();
        if (instalacoes.isEmpty()) {
            System.out.println("Nenhuma solicitação de instalação pendente.");
            return;
        }
        System.out.println("\nLista de Solicitações de Instalação:");
        for (Instalacao instalacao : instalacoes) {
            System.out.println(instalacao);
        }
    }

    private void acessarPainelTecnico(Scanner scanner) {
        System.out.print("Digite seu ID de técnico: ");
        int idTecnico = lerInt(scanner);

        Tecnico tecnicoLogado = instalacaoManager.getTecnicoPorId(idTecnico);

        if (tecnicoLogado != null) {
            System.out.println("Bem-vindo, " + tecnicoLogado.getNome() + "!");
            menuTecnico(scanner, tecnicoLogado);
        } else {
            System.out.println("ID de técnico inválido! Retornando ao menu principal...");
        }
    }

    private void menuTecnico(Scanner scanner, Tecnico tecnicoLogado) {
        boolean sair = false;
        while (!sair) {
            imprimirMenuTecnico();
            int opcao = lerInt(scanner);

            switch (opcao) {
                case 1 -> listarInstalacoesDoTecnico(tecnicoLogado);
                case 2 -> atualizarStatusInstalacaoPorTecnico(scanner, tecnicoLogado);
                case 0 -> {
                    System.out.println("Saindo do painel do técnico...");
                    sair = true;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void imprimirMenuTecnico() {
        System.out.println("\nPainel do Técnico:");
        System.out.println("1 - Listar Instalações Atribuídas");
        System.out.println("2 - Atualizar Status de Instalação");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção: ");
    }

    private void listarInstalacoesDoTecnico(Tecnico tecnicoLogado) {
        List<Instalacao> instalacoes = instalacaoManager.listarInstalacoesSolicitadas();

        System.out.println("\nInstalações atribuídas ao técnico " + tecnicoLogado.getNome() + ":");

        boolean encontrouInstalacoes = false;
        for (Instalacao instalacao : instalacoes) {
            if (instalacao.getTecnicoResponsavel() != null &&
                instalacao.getTecnicoResponsavel().getIdTecnico() == tecnicoLogado.getIdTecnico()) {
                System.out.println(instalacao.getIdInstalacao() + " - " + instalacao);
                encontrouInstalacoes = true;
            }
        }

        if (!encontrouInstalacoes) {
            System.out.println("Nenhuma instalação atribuída a este técnico.");
        }
    }

    private void atualizarStatusInstalacaoPorTecnico(Scanner scanner, Tecnico tecnicoLogado) {
        List<Instalacao> instalacoes = instalacaoManager.listarInstalacoesSolicitadas();

        if (instalacoes.isEmpty()) {
            System.out.println("Não há instalações registradas.");
            return;
        }

        System.out.println("Selecione a instalação para atualização de status (ID): ");
        for (Instalacao instalacao : instalacoes) {
            if (instalacao.getTecnicoResponsavel() != null && instalacao.getTecnicoResponsavel().getIdTecnico() == tecnicoLogado.getIdTecnico()) {
                System.out.println(instalacao.getIdInstalacao() + " - " + instalacao);
            }
        }

        int idInstalacao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        System.out.println("Escolha o novo status:");
        System.out.println("1 - AGUARDANDO_AGENDAMENTO");
        System.out.println("2 - EM_ANDAMENTO");
        System.out.println("3 - FINALIZADA");

        int opcaoStatus = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        StatusInstalacao novoStatus;
        switch (opcaoStatus) {
            case 1:
                novoStatus = StatusInstalacao.AGUARDANDO_AGENDAMENTO;
                break;
            case 2:
                novoStatus = StatusInstalacao.EM_ANDAMENTO;
                break;
            case 3:
                novoStatus = StatusInstalacao.FINALIZADA;
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        instalacaoManager.atualizarStatusInstalacaoPorTecnico(idInstalacao, tecnicoLogado.getIdTecnico(), novoStatus);
    }
}