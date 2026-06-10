package service;

import model.AssinaturaMonitoramento;
import model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// gerencia usuários e suas assinaturas de monitoramento por região
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();
    private List<AssinaturaMonitoramento> assinaturas = new ArrayList<>();
    private int proximoIdUsuario = 80000001;
    private int proximoIdAssinatura = 90000001;

    public Usuario cadastrarUsuario(String nome, String email, String telefone,
                                     String tipoCliente, String canal) {
        Usuario u = new Usuario(proximoIdUsuario++, nome, email, telefone, tipoCliente, canal);
        usuarios.add(u);
        System.out.println("  ✔ Usuário cadastrado: " + u.getNomeUsuario() + " [" + tipoCliente + "]");
        return u;
    }

    public AssinaturaMonitoramento assinarRegiao(int idUsuario, int idRegiao, String canal) {
        AssinaturaMonitoramento assinatura = new AssinaturaMonitoramento(
            proximoIdAssinatura++, LocalDate.now(), canal, idUsuario, idRegiao
        );
        assinaturas.add(assinatura);
        System.out.printf("  ✔ Usuário %d assinou monitoramento da região %d via %s%n",
            idUsuario, idRegiao, canal);
        return assinatura;
    }

    // retorna todos os usuários que assinaram uma determinada região
    public List<Usuario> buscarPorRegiao(int idRegiao) {
        List<Usuario> resultado = new ArrayList<>();
        for (AssinaturaMonitoramento a : assinaturas) {
            if (a.getIdRegiao() == idRegiao) {
                Usuario u = buscarPorId(a.getIdUsuario());
                if (u != null) resultado.add(u);
            }
        }
        return resultado;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) return u;
        }
        return null;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("  Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            u.exibirDados();
        }
    }

    public void listarAssinaturas() {
        if (assinaturas.isEmpty()) {
            System.out.println("  Nenhuma assinatura registrada.");
            return;
        }
        for (AssinaturaMonitoramento a : assinaturas) {
            a.exibirDados();
        }
    }

    public List<Usuario> getUsuarios() { return usuarios; }
    public List<AssinaturaMonitoramento> getAssinaturas() { return assinaturas; }
}
