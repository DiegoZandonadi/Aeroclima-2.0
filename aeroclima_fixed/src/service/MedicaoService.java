package service;

import model.Medicao;
import model.Satelite;
import util.ValidadorClimatico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// gerencia o registro e consulta de medições climáticas
// valida os dados antes de salvar usando o ValidadorClimatico
public class MedicaoService {

    private List<Medicao> medicoes = new ArrayList<>();
    private int proximoId = 500000001;

    // registra uma medição com todos os dados climáticos
    public Medicao registrarMedicao(Satelite satelite, int idRegiao,
                                     double latitude, double longitude,
                                     double temperatura, double precipitacao) {
        if (!ValidadorClimatico.coordenadasValidas(latitude, longitude)) {
            System.out.printf("  ERRO: Coordenadas inválidas (lat=%.4f, lon=%.4f). Medição rejeitada.%n",
                latitude, longitude);
            return null;
        }
        if (!ValidadorClimatico.temperaturaPlausivelParaSuperficie(temperatura)) {
            System.out.printf("  ERRO: Temperatura fora do intervalo plausível (%.2f°C). Medição rejeitada.%n",
                temperatura);
            return null;
        }
        if (!ValidadorClimatico.precipitacaoValida(precipitacao)) {
            System.out.println("  ERRO: Precipitação negativa. Medição rejeitada.");
            return null;
        }

        Medicao m = new Medicao(proximoId++, LocalDateTime.now(),
            latitude, longitude, temperatura, precipitacao,
            satelite.getId(), idRegiao);

        medicoes.add(m);
        satelite.adicionarMedicao(m);

        System.out.printf("  ✔ Medição registrada: %.2f°C / %.2f mm [%s]%n",
            temperatura, precipitacao, satelite.getNome());
        return m;
    }

    // sobrecarga: registra medição sem precipitação (quando o sensor não está disponível)
    public Medicao registrarMedicao(Satelite satelite, int idRegiao,
                                     double latitude, double longitude,
                                     double temperatura) {
        System.out.println("  Aviso: precipitação não informada — assumindo 0.0 mm.");
        return registrarMedicao(satelite, idRegiao, latitude, longitude, temperatura, 0.0);
    }

    public List<Medicao> buscarPorSatelite(int idSatelite) {
        List<Medicao> resultado = new ArrayList<>();
        for (Medicao m : medicoes) {
            if (m.getIdSatelite() == idSatelite) resultado.add(m);
        }
        return resultado;
    }

    public List<Medicao> buscarPorRegiao(int idRegiao) {
        List<Medicao> resultado = new ArrayList<>();
        for (Medicao m : medicoes) {
            if (m.getIdRegiao() == idRegiao) resultado.add(m);
        }
        return resultado;
    }

    public void listarMedicoes() {
        if (medicoes.isEmpty()) { System.out.println("  Nenhuma medição registrada."); return; }
        for (Medicao m : medicoes) m.exibirDados();
    }

    public List<Medicao> getMedicoes() { return medicoes; }
}
