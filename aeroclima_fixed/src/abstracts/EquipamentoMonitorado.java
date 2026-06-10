package abstracts;

// classe abstrata que serve de base para qualquer equipamento do sistema
// todo equipamento tem id, nome e status
public abstract class EquipamentoMonitorado {

    private int id;
    private String nome;
    private String status;

    public EquipamentoMonitorado(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = "Ativo";
    }

    // métodos abstratos que cada subclasse precisa implementar do seu jeito
    public abstract String gerarRelatorio();
    public abstract void exibirDados();

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + nome + " | Status: " + status;
    }
}
