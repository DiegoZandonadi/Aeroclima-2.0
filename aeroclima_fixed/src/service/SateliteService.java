package service;

import model.Satelite;
import model.Subsistema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// gerencia satélites e seus subsistemas
public class SateliteService {

    private List<Satelite> satelites = new ArrayList<>();
    private int proximoIdSat = 200001;
    private int proximoIdSub = 300001;

    public Satelite cadastrarSatelite(String nome, LocalDate lancamento, int idAgencia) {
        Satelite s = new Satelite(proximoIdSat++, nome, lancamento, idAgencia);
        satelites.add(s);
        System.out.println("  ✔ Satélite cadastrado: " + s.getNome());
        return s;
    }

    public Subsistema adicionarSubsistema(Satelite satelite, String tipoPropulsao, double bateria) {
        Subsistema sub = new Subsistema(proximoIdSub++, tipoPropulsao, bateria, satelite.getId());
        satelite.setSubsistema(sub);
        System.out.println("  ✔ Subsistema vinculado ao satélite: " + satelite.getNome());
        return sub;
    }

    public Satelite buscarPorId(int id) {
        for (Satelite s : satelites) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void listarSatelites() {
        if (satelites.isEmpty()) {
            System.out.println("  Nenhum satélite cadastrado.");
            return;
        }
        for (Satelite s : satelites) {
            System.out.println("  " + s);
        }
    }

    public List<Satelite> getSatelites() { return satelites; }
}
