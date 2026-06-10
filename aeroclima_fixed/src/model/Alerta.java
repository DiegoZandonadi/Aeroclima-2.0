package model;

import java.time.LocalDateTime;

// representa um alerta gerado quando uma ocorrência crítica é detectada
// cada alerta está ligado a uma ocorrência e pode ter status: Pendente, Enviado ou Falhou
public class Alerta {

    private int idAlerta;
    private LocalDateTime dataEmissao;
    private String mensagemAlerta;
    private String statusEnvio;
    private int idOcorrencia;

    public Alerta(int idAlerta, String mensagemAlerta, int idOcorrencia) {
        this.idAlerta = idAlerta;
        this.mensagemAlerta = mensagemAlerta;
        this.idOcorrencia = idOcorrencia;
        this.dataEmissao = LocalDateTime.now();
        this.statusEnvio = "Pendente";
    }

    public void enviar() {
        this.statusEnvio = "Enviado";
        System.out.println("  [ALERTA ENVIADO] " + mensagemAlerta);
    }

    public void marcarFalha() {
        this.statusEnvio = "Falhou";
    }

    public void exibirDados() {
        System.out.printf("  Alerta [%d] | Status: %s | Ocorrência: %d | Msg: %s%n",
            idAlerta, statusEnvio, idOcorrencia, mensagemAlerta);
    }

    public int getIdAlerta() { return idAlerta; }
    public void setIdAlerta(int idAlerta) { this.idAlerta = idAlerta; }

    public LocalDateTime getDataEmissao() { return dataEmissao; }

    public String getMensagemAlerta() { return mensagemAlerta; }
    public void setMensagemAlerta(String mensagemAlerta) { this.mensagemAlerta = mensagemAlerta; }

    public String getStatusEnvio() { return statusEnvio; }
    public void setStatusEnvio(String statusEnvio) { this.statusEnvio = statusEnvio; }

    public int getIdOcorrencia() { return idOcorrencia; }

    @Override
    public String toString() {
        return "Alerta [" + idAlerta + "] " + statusEnvio + " | Ocorrência: " + idOcorrencia + " | " + mensagemAlerta;
    }
}
