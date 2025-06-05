package EleNav;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
    private List<UsuarioLogin> listaLogin = new ArrayList<>();

    public void cadastrarLogin(String user, String password) {
        UsuarioLogin novoLogin = new UsuarioLogin(user, password);
        listaLogin.add(novoLogin);
        System.out.println("Login cadastrado com sucesso!");
    }

    public boolean verificarLogin(String user, String password) {
        for (UsuarioLogin login : listaLogin) {
            if (login.getUser().equals(user) && login.getPassword().equals(password)) {
                System.out.println("Login realizado com sucesso!");
                return true;
            }
        }
        System.out.println("Usuário ou senha incorretos.");
        return false;
    }
}
