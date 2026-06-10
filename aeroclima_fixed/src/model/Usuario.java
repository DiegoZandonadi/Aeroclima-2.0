package model;

import interfaces.Notificavel;
import java.util.ArrayList;
import java.util.List;

// representa um cliente do sistema AeroClima
// implementa a interface Notificavel para poder receber alertas
public class Usuario implements Notificavel {

    private int idUsuario;
    private String nomeUsuario;
    private String emailCorporativo;
    private String telefoneContato;
    private String tipoCliente;       // Agronegocio, Seguradoras, OrgaoPublico
    private String canalNotificacao;  // Email, SMS ou Push
    private List<String> alertasRecebidos;

    public Usuario(int idUsuario, String nomeUsuario, String emailCorporativo,
                   String telefoneContato, String tipoCliente, String canalNotificacao) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailCorporativo = emailCorporativo;
        this.telefoneContato = telefoneContato;
        this.tipoCliente = tipoCliente;
        this.canalNotificacao = canalNotificacao;
        this.alertasRecebidos = new ArrayList<>();
    }

    // --- implementação da interface Notificavel ---

    @Override
    public void receberAlerta(String mensagem) {
        alertasRecebidos.add(mensagem);
        System.out.printf("  [%s via %s] %s → '%s'%n",
            tipoCliente, canalNotificacao, nomeUsuario, mensagem);
    }

    @Override
    public String getCanalNotificacao() {
        return canalNotificacao;
    }

    public void exibirDados() {
        System.out.printf("  Usuário [%d] %s | %s | Canal: %s | Alertas recebidos: %d%n",
            idUsuario, nomeUsuario, tipoCliente, canalNotificacao, alertasRecebidos.size());
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getEmailCorporativo() { return emailCorporativo; }
    public void setEmailCorporativo(String emailCorporativo) { this.emailCorporativo = emailCorporativo; }

    public String getTelefoneContato() { return telefoneContato; }

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public void setCanalNotificacao(String canal) { this.canalNotificacao = canal; }

    public List<String> getAlertasRecebidos() { return alertasRecebidos; }

    @Override
    public String toString() {
        return "Usuário [" + idUsuario + "] " + nomeUsuario + " | " + tipoCliente + " | Canal: " + canalNotificacao;
    }
}
