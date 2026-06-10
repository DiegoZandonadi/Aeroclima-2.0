package model;

// representa uma região geográfica que pode ser monitorada por satélites
public class Regiao {

    private int idRegiao;
    private String nomeRegiao;
    private String coordenadasLimite;
    private String tipoBioma;

    public Regiao(int idRegiao, String nomeRegiao, String coordenadasLimite, String tipoBioma) {
        this.idRegiao = idRegiao;
        this.nomeRegiao = nomeRegiao;
        this.coordenadasLimite = coordenadasLimite;
        this.tipoBioma = tipoBioma;
    }

    public void exibirDados() {
        System.out.println("  Região [" + idRegiao + "] " + nomeRegiao +
            " | Bioma: " + tipoBioma +
            " | Coordenadas: " + coordenadasLimite);
    }

    public int getIdRegiao() { return idRegiao; }
    public void setIdRegiao(int idRegiao) { this.idRegiao = idRegiao; }

    public String getNomeRegiao() { return nomeRegiao; }
    public void setNomeRegiao(String nomeRegiao) { this.nomeRegiao = nomeRegiao; }

    public String getCoordenadasLimite() { return coordenadasLimite; }
    public void setCoordenadasLimite(String coordenadasLimite) { this.coordenadasLimite = coordenadasLimite; }

    public String getTipoBioma() { return tipoBioma; }
    public void setTipoBioma(String tipoBioma) { this.tipoBioma = tipoBioma; }

    @Override
    public String toString() {
        return "Região [" + idRegiao + "] " + nomeRegiao + " (" + tipoBioma + ")";
    }
}
