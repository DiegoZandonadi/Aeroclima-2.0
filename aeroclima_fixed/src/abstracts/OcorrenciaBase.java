package abstracts;

import java.time.LocalDate;

// classe abstrata para representar qualquer tipo de ocorrência no sistema
// pode ser climática ou de infraestrutura, mas os campos básicos são os mesmos
public abstract class OcorrenciaBase {

    private int id;
    private LocalDate dataDeteccao;
    private String nivelGravidade;

    public OcorrenciaBase(int id, LocalDate dataDeteccao, String nivelGravidade) {
        this.id = id;
        this.dataDeteccao = dataDeteccao;
        this.nivelGravidade = nivelGravidade;
    }

    // cada tipo de ocorrência descreve a si mesma de forma diferente
    public abstract String descreverOcorrencia();
    public abstract boolean isCritica();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDataDeteccao() { return dataDeteccao; }
    public void setDataDeteccao(LocalDate dataDeteccao) { this.dataDeteccao = dataDeteccao; }

    public String getNivelGravidade() { return nivelGravidade; }
    public void setNivelGravidade(String nivelGravidade) { this.nivelGravidade = nivelGravidade; }
}
