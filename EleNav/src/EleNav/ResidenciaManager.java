package EleNav;

import java.util.ArrayList;
import java.util.List;

public class ResidenciaManager {
    private List<Residencia> listaResidencia = new ArrayList<>();

    public void criarResidencia(String endereco, double consumo, double tamanho) {
        Residencia residencia = new Residencia(endereco, consumo, tamanho);
        listaResidencia.add(residencia);
        System.out.println("Residência cadastrada com sucesso!");
    }

    public void listarResidencias() {
        if (listaResidencia.isEmpty()) {
            System.out.println("Nenhuma residência cadastrada.");
            return;
        }
        for (Residencia residencia : listaResidencia) {
            System.out.println(residencia.imprimirResidencia());
        }
    }

    public List<Residencia> getListaResidencia() {
        return listaResidencia;
    }
}
