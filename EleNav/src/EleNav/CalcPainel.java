package EleNav;

import java.util.List;

public class CalcPainel {
    private static final double TARIFA_ENERGIA = 0.80;

    public static void calcularPainelSolar(List<Residencia> listaResidencia, List<PainelSolar> listaPainelSolar, int indiceResidencia, int indicePainel) {
        if (listaResidencia.isEmpty() || listaPainelSolar.isEmpty()) {
            System.out.println("Erro: É necessário cadastrar uma residência e pelo menos um painel solar.");
            return;
        }

        Residencia residencia = listaResidencia.get(indiceResidencia);
        PainelSolar painelSolar = listaPainelSolar.get(indicePainel);

        int quantidadePaineis = (int) Math.ceil(residencia.getConsumo() / painelSolar.getPotencia());
        double custoTotal = quantidadePaineis * painelSolar.getPreco();
        double economiaMensal = residencia.getConsumo() * TARIFA_ENERGIA;
        double tempoRetorno = custoTotal / economiaMensal;

        int meses = (int) tempoRetorno;
        double diasFracao = tempoRetorno % 1;
        int dias = (int) Math.round(diasFracao * 30);

        double areaTotalNecessaria = quantidadePaineis * painelSolar.getArea(); // Usando a área do painel
        boolean instalacaoPossivel = areaTotalNecessaria <= residencia.getTamanhoTelhado();

        System.out.println("\nResultados do Cálculo:");
        System.out.println("Quantidade necessária de painéis solares: " + quantidadePaineis);
        System.out.printf("Custo total estimado do investimento: R$ %.2f%n", custoTotal);

        if (instalacaoPossivel) {
            System.out.println("Instalação POSSÍVEL no tamanho do telhado.");
        } else {
            System.out.printf("Instalação NÃO POSSÍVEL. Área necessária: %.2f m², Área do telhado: %.2f m²%n", areaTotalNecessaria, residencia.getTamanhoTelhado());
        }

        System.out.printf("Tempo estimado para retorno do investimento: %d Mes(es) e %d dia(s)%n", meses, dias);
    }
}