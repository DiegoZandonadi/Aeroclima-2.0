package model;

import abstracts.EquipamentoMonitorado;
import interfaces.Rastreavel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// satélite herda de EquipamentoMonitorado (status, id, nome)
// e implementa Rastreavel para saber sua localização atual
public class Satelite extends EquipamentoMonitorado implements Rastreavel {

    private LocalDate dataLancamento;
    private int idAgencia;
    private Subsistema subsistema;
    private List<Medicao> medicoes;

    public Satelite(int id, String nome, LocalDate dataLancamento, int idAgencia) {
        super(id, nome);
        this.dataLancamento = dataLancamento;
        this.idAgencia = idAgencia;
        this.medicoes = new ArrayList<>();
    }

    public void adicionarMedicao(Medicao medicao) {
        medicoes.add(medicao);
    }

    // --- implementação da interface Rastreavel ---

    @Override
    public String obterLocalizacaoAtual() {
        if (medicoes.isEmpty()) return "Sem telemetria disponível";
        Medicao ultima = medicoes.get(medicoes.size() - 1);
        return String.format("Lat %.6f / Lon %.6f (coleta: %s)",
            ultima.getLatitude(), ultima.getLongitude(), ultima.getDataHoraColeta());
    }

    @Override
    public boolean estaEmOrbita() {
        return "Ativo".equalsIgnoreCase(getStatus()) || "Em Orbita".equalsIgnoreCase(getStatus());
    }

    // --- sobrescrita de gerarRelatorio() da classe abstrata (sem parâmetro) ---

    @Override
    public String gerarRelatorio() {
        return String.format(
            "=== SATÉLITE: %s ===\n" +
            "ID          : %d\n" +
            "Lançamento  : %s\n" +
            "Status      : %s\n" +
            "Agência ID  : %d\n" +
            "Medições    : %d registros\n" +
            "Localização : %s",
            getNome(), getId(), dataLancamento, getStatus(),
            idAgencia, medicoes.size(), obterLocalizacaoAtual()
        );
    }

    // sobrecarga de gerarRelatorio — mostra as últimas N medições
    public String gerarRelatorio(int quantidadeUltimasMedicoes) {
        StringBuilder sb = new StringBuilder(gerarRelatorio());
        if (medicoes.isEmpty() || quantidadeUltimasMedicoes <= 0) return sb.toString();

        sb.append("\n--- Últimas ").append(quantidadeUltimasMedicoes).append(" medição(ões) ---");
        int inicio = Math.max(0, medicoes.size() - quantidadeUltimasMedicoes);
        for (int i = inicio; i < medicoes.size(); i++) {
            sb.append("\n  ").append(medicoes.get(i));
        }
        return sb.toString();
    }

    @Override
    public void exibirDados() {
        System.out.println(gerarRelatorio());
        if (subsistema != null) subsistema.exibirDados();
    }

    // sobrescrita do toString da superclasse Object
    @Override
    public String toString() {
        return String.format("Satélite [%d] %s | Lançamento: %s | %s",
            getId(), getNome(), dataLancamento, getStatus());
    }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public int getIdAgencia() { return idAgencia; }
    public Subsistema getSubsistema() { return subsistema; }
    public List<Medicao> getMedicoes() { return medicoes; }

    public void setSubsistema(Subsistema subsistema) {
        if (this.subsistema != null) {
            System.out.println("  Aviso: subsistema anterior substituído em " + getNome());
        }
        this.subsistema = subsistema;
    }
}
