package interfaces;

// interface para quem pode receber alertas do sistema
// o usuário implementa essa interface
public interface Notificavel {
    void receberAlerta(String mensagem);
    String getCanalNotificacao();
}
