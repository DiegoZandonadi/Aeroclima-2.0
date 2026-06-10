package model;

import java.time.LocalDate;

// representa a assinatura de um usuário para monitorar uma região
// um usuário pode assinar várias regiões, cada uma com canal preferido
public class AssinaturaMonitoramento {

    private int idAssinatura;
    private LocalDate dataInicio;
    private String canalNotificacao;
    private int idUsuario;
    private int idRegiao;

    public AssinaturaMonitoramento(int idAssinatura, LocalDate dataInicio,
                                    String canalNotificacao, int idUsuario, int idRegiao) {
        this.idAssinatura = idAssinatura;
        this.dataInicio = dataInicio;
        this.canalNotificacao = canalNotificacao;
        this.idUsuario = idUsuario;
        this.idRegiao = idRegiao;
    }

    public void exibirDados() {
        System.out.printf("  Assinatura [%d] | Usuário: %d | Região: %d | Canal: %s | Início: %s%n",
            idAssinatura, idUsuario, idRegiao, canalNotificacao, dataInicio);
    }

    public int getIdAssinatura() { return idAssinatura; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public String getCanalNotificacao() { return canalNotificacao; }
    public void setCanalNotificacao(String canalNotificacao) { this.canalNotificacao = canalNotificacao; }

    public int getIdUsuario() { return idUsuario; }
    public int getIdRegiao() { return idRegiao; }

    @Override
    public String toString() {
        return "Assinatura [" + idAssinatura + "] Usuário " + idUsuario + " → Região " + idRegiao + " via " + canalNotificacao;
    }
}
