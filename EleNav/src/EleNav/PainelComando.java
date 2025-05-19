package EleNav;

import java.util.Scanner;

public class PainelComando {
    private UsuarioManager usuarioManager = new UsuarioManager();
    private PainelSolarManager painelSolarManager = new PainelSolarManager();
    private ResidenciaManager residenciaManager = new ResidenciaManager();
    private LoginManager loginManager = new LoginManager(); // Gerenciador de logins

    private boolean usuarioLogado = false; // Variável para controlar o login

    public PainelComando() {
    }

    public void painelPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        boolean usuarioLogado = false;

        while (!sair) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Cadastrar usuário/senha");
            System.out.println("2 - Realizar login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcaoMenu = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcaoMenu) {
                case 1:
                    System.out.println("Bem-vindo ao cadastro de usuário!");
                    System.out.print("Qual seu usuário? ");
                    String novoUsuario = scanner.nextLine();
                    System.out.print("Qual sua senha? ");
                    String novaSenha = scanner.nextLine();
                    loginManager.cadastrarLogin(novoUsuario, novaSenha);
                    break;

                case 2:
                    System.out.print("Usuário: ");
                    String user = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();

                    if (loginManager.verificarLogin(user, password)) {
                        usuarioLogado = true;
                    } else {
                        break;
                    }

                    while (usuarioLogado) {
                        System.out.println("\n--- MENU LOGADO ---");
                        System.out.println("1 - Listar Usuários");
                        System.out.println("2 - Criar Painel Solar");
                        System.out.println("3 - Listar Painéis Solares");
                        System.out.println("4 - Criar Residência");
                        System.out.println("5 - Listar Residências");
                        System.out.println("6 - Calcular Painéis e Retorno do Investimento");
                        System.out.println("0 - Sair");
                        System.out.print("Digite a opção: ");
                        int opcaoLogado = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer

                        switch (opcaoLogado) {
                            case 1:
                                usuarioManager.listarUsuarios();
                                break;
                            case 2:
                                criarPainelSolar(scanner);
                                break;
                            case 3:
                                painelSolarManager.listarPainelSolar();
                                break;
                            case 4:
                                criarResidencia(scanner);
                                break;
                            case 5:
                                residenciaManager.listarResidencias();
                                break;
                            case 6:
                                calcularPainelSolar(scanner);
                                break;
                            case 0:
                                System.out.println("Saindo do painel logado...");
                                usuarioLogado = false;
                                break;
                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private void realizarLogin(Scanner scanner) {
        System.out.print("Usuário: ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (loginManager.verificarLogin(user, password)) {
            System.out.println("Login realizado com sucesso!");
            usuarioLogado = true;
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
    }

    private void criarPainelSolar(Scanner scanner) {
        if (!usuarioLogado) {
            System.out.println("Você precisa fazer login para acessar esta funcionalidade.");
            return;
        }

        System.out.print("Potência do painel solar (kW): ");
        double potencia = scanner.nextDouble();
        System.out.print("Área do painel solar (m²): ");
        double area = scanner.nextDouble();
        System.out.print("Preço do painel solar (R$): ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        painelSolarManager.criarPainelSolar(potencia, area, preco);
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
