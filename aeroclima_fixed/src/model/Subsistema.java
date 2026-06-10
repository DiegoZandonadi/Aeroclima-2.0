package model;

// representa o subsistema de propulsão e energia de um satélite
// cada satélite tem exatamente um subsistema
public class Subsistema {

    private int idSubsistema;
    private String tipoPropulsao;
    private double capacidadeBateria;
    private int idSatelite;

    public Subsistema(int idSubsistema, String tipoPropulsao, double capacidadeBateria, int idSatelite) {
        this.idSubsistema = idSubsistema;
        this.tipoPropulsao = tipoPropulsao;
        this.capacidadeBateria = capacidadeBateria;
        this.idSatelite = idSatelite;
    }

    public void exibirDados() {
        System.out.printf("  Subsistema ID: %d | Propulsão: %s | Bateria: %.2f Wh%n",
            idSubsistema, tipoPropulsao, capacidadeBateria);
    }

    public int getIdSubsistema() { return idSubsistema; }
    public void setIdSubsistema(int idSubsistema) { this.idSubsistema = idSubsistema; }

    public String getTipoPropulsao() { return tipoPropulsao; }
    public void setTipoPropulsao(String tipoPropulsao) { this.tipoPropulsao = tipoPropulsao; }

    public double getCapacidadeBateria() { return capacidadeBateria; }
    public void setCapacidadeBateria(double capacidadeBateria) { this.capacidadeBateria = capacidadeBateria; }

    public int getIdSatelite() { return idSatelite; }
    public void setIdSatelite(int idSatelite) { this.idSatelite = idSatelite; }

    @Override
    public String toString() {
        return "Subsistema [" + idSubsistema + "] Propulsão: " + tipoPropulsao + " | Bateria: " + capacidadeBateria + " Wh";
    }
}
