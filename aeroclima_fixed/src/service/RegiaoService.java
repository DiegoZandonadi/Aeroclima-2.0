package service;

import model.Regiao;
import java.util.ArrayList;
import java.util.List;

// gerencia o cadastro e consulta de regiões monitoradas
public class RegiaoService {

    private List<Regiao> regioes = new ArrayList<>();
    private int proximoId = 400001;

    public Regiao cadastrarRegiao(String nome, String coordenadas, String bioma) {
        Regiao r = new Regiao(proximoId++, nome, coordenadas, bioma);
        regioes.add(r);
        System.out.println("  ✔ Região cadastrada: " + r.getNomeRegiao() + " (" + bioma + ")");
        return r;
    }

    public Regiao buscarPorId(int id) {
        for (Regiao r : regioes) {
            if (r.getIdRegiao() == id) return r;
        }
        return null;
    }

    public void listarRegioes() {
        if (regioes.isEmpty()) {
            System.out.println("  Nenhuma região cadastrada.");
            return;
        }
        for (Regiao r : regioes) {
            r.exibirDados();
        }
    }

    public List<Regiao> getRegioes() { return regioes; }
}
