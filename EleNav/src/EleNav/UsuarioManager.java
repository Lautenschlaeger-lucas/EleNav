package EleNav;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public void cadastrarUsuario(String nome, String endereco, long CPF) {
        Usuario usuario = new Usuario(nome, endereco, CPF);
        listaUsuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void listarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario usuario : listaUsuarios) {
            System.out.println(usuario.imprimirUsuario());
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
