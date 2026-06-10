package model;

import abstracts.OcorrenciaBase;
import interfaces.Calculavel;
import java.time.LocalDate;

// herda de OcorrenciaBase e implementa Calculavel
// representa uma ocorrência climática como seca, tempestade ou onda de calor
public class OcorrenciaClimatica extends OcorrenciaBase implements Calculavel {

    private String tipoEvento;
    private int idMedicao;
    private double temperaturaAssociada;
    private double precipitacaoAssociada;

    // valores de referência usados no cálculo de risco
    private static final double TEMP_REFERENCIA   = 45.0;
    private static final double PRECIP_REFERENCIA = 150.0;

    public OcorrenciaClimatica(int id, String tipoEvento, LocalDate dataDeteccao,
                                String nivelGravidade, int idMedicao,
                                double temperaturaAssociada, double precipitacaoAssociada) {
        super(id, dataDeteccao, nivelGravidade);
        this.tipoEvento = tipoEvento;
        this.idMedicao = idMedicao;
        this.temperaturaAssociada = temperaturaAssociada;
        this.precipitacaoAssociada = precipitacaoAssociada;
    }

    // --- sobrescrita de OcorrenciaBase ---

    @Override
    public String descreverOcorrencia() {
        return String.format(
            "Ocorrência Climática [%d] | %s | Gravidade: %s | Data: %s | %.2f°C / %.2f mm",
            getId(), tipoEvento, getNivelGravidade(), getDataDeteccao(),
            temperaturaAssociada, precipitacaoAssociada
        );
    }

    // sobrecarga do método descreverOcorrencia — versão resumida ou completa
    public String descreverOcorrencia(String formato) {
        if ("resumido".equalsIgnoreCase(formato)) {
            return String.format("[%s] %s — %s", getNivelGravidade(), tipoEvento, getDataDeteccao());
        }
        return descreverOcorrencia() +
               String.format(" | Risco calculado: %.1f%% (%s)", calcularRisco(), classificarAnomalia());
    }

    @Override
    public boolean isCritica() {
        return "Critico".equalsIgnoreCase(getNivelGravidade());
    }

    // --- implementação da interface Calculavel ---

    @Override
    public double calcularRisco() {
        double riscoTemp  = (temperaturaAssociada  / TEMP_REFERENCIA)  * 50.0;
        double riscoPrec  = (precipitacaoAssociada / PRECIP_REFERENCIA) * 50.0;
        return Math.min(riscoTemp + riscoPrec, 100.0);
    }

    @Override
    public String classificarAnomalia() {
        double risco = calcularRisco();
        if (risco >= 80.0) return "EXTREMA";
        if (risco >= 50.0) return "ALTA";
        if (risco >= 25.0) return "MODERADA";
        return "BAIXA";
    }

    public void exibirDados() {
        System.out.println("  " + descreverOcorrencia("completo"));
    }

    public String getTipoEvento() { return tipoEvento; }
    public int getIdMedicao() { return idMedicao; }
    public double getTemperaturaAssociada() { return temperaturaAssociada; }
    public double getPrecipitacaoAssociada() { return precipitacaoAssociada; }

    @Override
    public String toString() {
        return descreverOcorrencia("resumido");
    }
}
