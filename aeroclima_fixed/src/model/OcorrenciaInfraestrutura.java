package model;

import abstracts.OcorrenciaBase;
import interfaces.Calculavel;
import java.time.LocalDate;

// segunda especialização de OcorrenciaBase
// representa falhas no satélite como problema de bateria, propulsão ou comunicação
public class OcorrenciaInfraestrutura extends OcorrenciaBase implements Calculavel {

    private String sistemaAfetado;   // ex: "Bateria", "Propulsão", "Comunicação"
    private double percentualFalha;  // de 0 a 100
    private int idSatelite;

    public OcorrenciaInfraestrutura(int id, String sistemaAfetado, LocalDate dataDeteccao,
                                     String nivelGravidade, double percentualFalha, int idSatelite) {
        super(id, dataDeteccao, nivelGravidade);
        this.sistemaAfetado = sistemaAfetado;
        this.percentualFalha = percentualFalha;
        this.idSatelite = idSatelite;
    }

    // --- sobrescrita de OcorrenciaBase ---

    @Override
    public String descreverOcorrencia() {
        return String.format(
            "Falha de Infraestrutura [%d] | Sistema: %s | Falha: %.1f%% | Gravidade: %s | Data: %s | Satélite ID: %d",
            getId(), sistemaAfetado, percentualFalha, getNivelGravidade(), getDataDeteccao(), idSatelite
        );
    }

    @Override
    public boolean isCritica() {
        return "Critico".equalsIgnoreCase(getNivelGravidade()) || percentualFalha >= 80.0;
    }

    // --- implementação da interface Calculavel ---

    @Override
    public double calcularRisco() {
        return percentualFalha;
    }

    @Override
    public String classificarAnomalia() {
        if (percentualFalha >= 80.0) return "CRÍTICA — RISCO DE PERDA DO SATÉLITE";
        if (percentualFalha >= 50.0) return "ALTA — OPERAÇÃO COMPROMETIDA";
        if (percentualFalha >= 20.0) return "MODERADA — MONITORAMENTO NECESSÁRIO";
        return "BAIXA — OPERAÇÃO NORMAL";
    }

    public void exibirDados() {
        System.out.println("  " + descreverOcorrencia());
        System.out.println("  Análise: " + classificarAnomalia());
    }

    public String getSistemaAfetado() { return sistemaAfetado; }
    public double getPercentualFalha() { return percentualFalha; }
    public int getIdSatelite() { return idSatelite; }
}
