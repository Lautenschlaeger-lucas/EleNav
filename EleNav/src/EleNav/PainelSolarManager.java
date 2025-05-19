package EleNav;

import java.util.ArrayList;
import java.util.List;

public class PainelSolarManager {
    private List<PainelSolar> listaPainelSolar = new ArrayList<>();

    public void criarPainelSolar(double potencia, double area, double preco) {
        PainelSolar painel = new PainelSolar(potencia, area, preco);
        listaPainelSolar.add(painel);
        System.out.println("Painel solar criado com sucesso!");
    }

    public void listarPainelSolar() {
        if (listaPainelSolar.isEmpty()) {
            System.out.println("Nenhum painel solar cadastrado.");
            return;
        }
        for (PainelSolar painel : listaPainelSolar) {
            System.out.println(painel.imprimirDadosPainel());
        }
    }

    public List<PainelSolar> getListaPainelSolar() {
        return listaPainelSolar;
    }
}
