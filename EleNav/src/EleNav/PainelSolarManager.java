package EleNav;

import java.util.ArrayList;
import java.util.List;

public class PainelSolarManager {
    private List<PainelSolar> listaPainelSolar = new ArrayList<>();

    public void criarPainelSolar(String nomePainel, double potencia, double area, double preco) {
        PainelSolar painelSolar = new PainelSolar(nomePainel, potencia, area, preco);
        listaPainelSolar.add(painelSolar);
        System.out.println("Painel solar '" + nomePainel + "' cadastrado com sucesso!");
    }

    public List<PainelSolar> getListaPainelSolar() {
        return listaPainelSolar;
    }

    public void listarPainelSolar() {
        if (listaPainelSolar.isEmpty()) {
            System.out.println("Nenhum painel solar cadastrado.");
            return;
        }
        System.out.println("\nLista de Painéis Solares:");
        for (int i = 0; i < listaPainelSolar.size(); i++) {
            PainelSolar painel = listaPainelSolar.get(i);
            System.out.println(i + " - Nome: " + painel.getNomePainel() +
                               ", Potência: " + painel.getPotencia() + " kW" +
                               ", Área: " + painel.getArea() + " m²" +
                               ", Preço: R$ " + painel.getPreco());
        }
    }
}