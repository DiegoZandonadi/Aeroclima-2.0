package model;

import java.time.LocalDateTime;

// guarda os dados de uma medição climática feita por um satélite
public class Medicao {

    private int idMedicao;
    private LocalDateTime dataHoraColeta;
    private double latitude;
    private double longitude;
    private double temperaturaSuperficie;
    private double indicePrecipitacao;
    private int idSatelite;
    private int idRegiao;

    public Medicao(int idMedicao, LocalDateTime dataHoraColeta, double latitude,
                   double longitude, double temperaturaSuperficie,
                   double indicePrecipitacao, int idSatelite, int idRegiao) {
        this.idMedicao = idMedicao;
        this.dataHoraColeta = dataHoraColeta;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperaturaSuperficie = temperaturaSuperficie;
        this.indicePrecipitacao = indicePrecipitacao;
        this.idSatelite = idSatelite;
        this.idRegiao = idRegiao;
    }

    public void exibirDados() {
        System.out.printf(
            "  Medição [%d] | %s | Lat: %.4f | Lon: %.4f | Temp: %.2f°C | Precip: %.2f mm%n",
            idMedicao, dataHoraColeta, latitude, longitude, temperaturaSuperficie, indicePrecipitacao
        );
    }

    public int getIdMedicao() { return idMedicao; }
    public void setIdMedicao(int idMedicao) { this.idMedicao = idMedicao; }

    public LocalDateTime getDataHoraColeta() { return dataHoraColeta; }
    public void setDataHoraColeta(LocalDateTime dataHoraColeta) { this.dataHoraColeta = dataHoraColeta; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getTemperaturaSuperficie() { return temperaturaSuperficie; }
    public void setTemperaturaSuperficie(double t) { this.temperaturaSuperficie = t; }

    public double getIndicePrecipitacao() { return indicePrecipitacao; }
    public void setIndicePrecipitacao(double i) { this.indicePrecipitacao = i; }

    public int getIdSatelite() { return idSatelite; }
    public int getIdRegiao() { return idRegiao; }

    @Override
    public String toString() {
        return String.format("Medição [%d] %s | %.2f°C | %.2f mm",
            idMedicao, dataHoraColeta, temperaturaSuperficie, indicePrecipitacao);
    }
}
