package EleNav;

import java.util.Scanner;

public class PainelComando {
    private UsuarioManager usuarioManager = new UsuarioManager();
    private PainelSolarManager painelSolarManager = new PainelSolarManager();
    private ResidenciaManager residenciaManager = new ResidenciaManager();
    private LoginManager loginManager = new LoginManager();
    private InstalacaoManager instalacaoManager = new InstalacaoManager(); // Novo gerenciador de instalação

    private boolean usuarioLogado = false;

    public PainelComando() {
        // Adicionando alguns técnicos parceiros para teste
        instalacaoManager.adicionarTecnico(new Tecnico(1, null, null, 101, "João Técnico", "Fotovoltaico"));
        instalacaoManager.adicionarTecnico(new Tecnico(2, null, null, 102, "Maria Instaladora", "Eólica"));
    }

    public void painelPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1-Cadastrar Login");
            System.out.println("2-Realizar login");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (option) {
                case 1:
                    cadastrarUsuarioLogin(scanner);
                    break;
                case 2:
                    realizarLogin(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (usuarioLogado) {
                menuLogado(scanner);
            }
        }
    }

    private void menuLogado(Scanner scanner) {
        while (true) {
            System.out.println("\nEscolha uma das opções abaixo:");
            System.out.println("1 - Cadastrar Usuário/Senha");
            System.out.println("2 - Fazer Login");
            System.out.println("3 - Listar Usuários");
            System.out.println("4 - Criar Painel Solar");
            System.out.println("5 - Listar Painéis Solares");
            System.out.println("6 - Criar Residência");
            System.out.println("7 - Listar Residências");
            System.out.println("8 - Calcular Painéis e Retorno do Investimento");
            System.out.println("9 - Solicitar Instalação"); // Nova opção
            System.out.println("10 - Listar Solicitações de Instalação"); // Nova opção
            System.out.println("0 - Sair");

            System.out.print("Digite a opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarUsuarioLogin(scanner);
                    break;
                case 2:
                    realizarLogin(scanner);
                    break;
                case 3:
                    usuarioManager.listarUsuarios();
                    break;
                case 4:
                    criarPainelSolar(scanner);
                    break;
                case 5:
                    painelSolarManager.listarPainelSolar();
                    break;
                case 6:
                    criarResidencia(scanner);
                    break;
                case 7:
                    residenciaManager.listarResidencias();
                    break;
                case 8:
                    calcularPainelSolar(scanner);
                    break;
                case 9:
                    solicitarInstalacao(scanner);
                    break;
                case 10:
                    listarSolicitacoesInstalacao();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    private void solicitarInstalacao(Scanner scanner) {
        if (residenciaManager.getListaResidencia().isEmpty() || painelSolarManager.getListaPainelSolar().isEmpty()) {
            System.out.println("Erro: É necessário cadastrar uma residência e pelo menos um painel solar para solicitar uma instalação.");
            return;
        }

        System.out.println("Selecione a residência para a instalação (índice): ");
        for (int i = 0; i < residenciaManager.getListaResidencia().size(); i++) {
            System.out.println(i + " - " + residenciaManager.getListaResidencia().get(i).getEndereco());
        }
        int indiceResidencia = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o painel solar a ser instalado (índice): ");
        for (int i = 0; i < painelSolarManager.getListaPainelSolar().size(); i++) {
            System.out.println(i + " - " + painelSolarManager.getListaPainelSolar().get(i).getNomePainel());
        }
        int indicePainel = scanner.nextInt();
        scanner.nextLine();

        if (indiceResidencia >= 0 && indiceResidencia < residenciaManager.getListaResidencia().size() &&
            indicePainel >= 0 && indicePainel < painelSolarManager.getListaPainelSolar().size()) {
            Residencia residenciaSelecionada = residenciaManager.getListaResidencia().get(indiceResidencia);
            PainelSolar painelSelecionado = painelSolarManager.getListaPainelSolar().get(indicePainel);
            instalacaoManager.solicitarInstalacao(painelSelecionado, residenciaSelecionada);
        } else {
            System.out.println("Índice de residência ou painel solar inválido.");
        }
    }

    private void listarSolicitacoesInstalacao() {
        if (instalacaoManager.listarInstalacoesSolicitadas().isEmpty()) {
            System.out.println("Nenhuma solicitação de instalação pendente.");
            return;
        }
        System.out.println("\nLista de Solicitações de Instalação:");
        for (Instalacao instalacao : instalacaoManager.listarInstalacoesSolicitadas()) {
            System.out.println(instalacao);
        }
    }
    private void cadastrarUsuarioLogin(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        long CPF = scanner.nextLong();
        scanner.nextLine(); // Limpar buffer
        System.out.print("Usuário (login): ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        usuarioManager.cadastrarUsuario(nome, endereco, CPF);
        loginManager.cadastrarLogin(user, password);
    }

    private void realizarLogin(Scanner scanner) {
        System.out.print("Usuário: ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (loginManager.verificarLogin(user, password)) {
            usuarioLogado = true;
        } else {
            usuarioLogado = false;
        }
    }

    private void criarPainelSolar(Scanner scanner) {
        if (!usuarioLogado) {
            System.out.println("Você precisa fazer login para acessar esta funcionalidade.");
            return;
        }

        
        System.out.print("Nome(Marca/modelo) do painel solar: ");
        String nomePainel = scanner.nextLine();
        System.out.print("Potência do painel solar (kW): ");
        double potencia = scanner.nextDouble();
        System.out.print("Área do painel solar (m²): ");
        double area = scanner.nextDouble();
        System.out.print("Preço do painel solar (R$): ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        painelSolarManager.criarPainelSolar(nomePainel, potencia, area, preco);
    }

    private void criarResidencia(Scanner scanner) {
        if (!usuarioLogado) {
            System.out.println("Você precisa fazer login para acessar esta funcionalidade.");
            return;
        }

        System.out.print("Endereço da residência: ");
        String endereco = scanner.nextLine();
        System.out.print("Consumo mensal (kWh): ");
        double consumo = scanner.nextDouble();
        System.out.print("Tamanho do telhado (m²): ");
        double tamanho = scanner.nextDouble();
        scanner.nextLine();
        residenciaManager.criarResidencia(endereco, consumo, tamanho);
    }

    private void calcularPainelSolar(Scanner scanner) {
        if (!usuarioLogado) {
            System.out.println("Você precisa fazer login para acessar esta funcionalidade.");
            return;
        }

        if (residenciaManager.getListaResidencia().isEmpty() || painelSolarManager.getListaPainelSolar().isEmpty()) {
            System.out.println("Erro: É necessário cadastrar uma residência e pelo menos um painel solar.");
            return;
        }

        System.out.println("Selecione a residência (índice): ");
        for (int i = 0; i < residenciaManager.getListaResidencia().size(); i++) {
            System.out.println(i + " - " + residenciaManager.getListaResidencia().get(i).getEndereco());
        }
        int indiceResidencia = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Selecione o painel solar (índice): ");
        for (int i = 0; i < painelSolarManager.getListaPainelSolar().size(); i++) {
            System.out.println(i + " - Potência: " + painelSolarManager.getListaPainelSolar().get(i).getPotencia() + " kW");
        }
        int indicePainel = scanner.nextInt();
        scanner.nextLine();

        CalcPainel.calcularPainelSolar(residenciaManager.getListaResidencia(), painelSolarManager.getListaPainelSolar(), indiceResidencia, indicePainel);
    }
}
