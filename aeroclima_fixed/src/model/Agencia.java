package model;

import java.util.ArrayList;
import java.util.List;

// representa uma agência espacial que opera satélites
public class Agencia {

    private int idAgencia;
    private String nomeAgencia;
    private String paisOrigem;
    private List<Satelite> satelites;

    public Agencia(int idAgencia, String nomeAgencia, String paisOrigem) {
        this.idAgencia = idAgencia;
        this.nomeAgencia = nomeAgencia;
        this.paisOrigem = paisOrigem;
        this.satelites = new ArrayList<>();
    }

    public void adicionarSatelite(Satelite satelite) {
        satelites.add(satelite);
    }

    public void exibirSatelites() {
        if (satelites.isEmpty()) {
            System.out.println("  Nenhum satélite cadastrado para esta agência.");
            return;
        }
        for (Satelite s : satelites) {
            System.out.println("  - " + s.getNome() + " (Lançamento: " + s.getDataLancamento() + ")");
        }
    }

    public int getIdAgencia() { return idAgencia; }
    public void setIdAgencia(int idAgencia) { this.idAgencia = idAgencia; }

    public String getNomeAgencia() { return nomeAgencia; }
    public void setNomeAgencia(String nomeAgencia) { this.nomeAgencia = nomeAgencia; }

    public String getPaisOrigem() { return paisOrigem; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }

    public List<Satelite> getSatelites() { return satelites; }

    @Override
    public String toString() {
        return "Agência ID: " + idAgencia + " | " + nomeAgencia + " (" + paisOrigem + ") | Satélites: " + satelites.size();
    }
}
