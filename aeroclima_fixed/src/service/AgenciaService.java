package service;

import model.Agencia;
import model.Satelite;
import java.util.ArrayList;
import java.util.List;

// gerencia o cadastro e consulta de agências espaciais
public class AgenciaService {

    private List<Agencia> agencias = new ArrayList<>();
    private int proximoId = 10001;

    public Agencia cadastrarAgencia(String nome, String pais) {
        Agencia a = new Agencia(proximoId++, nome, pais);
        agencias.add(a);
        System.out.println("  ✔ Agência cadastrada: " + a.getNomeAgencia());
        return a;
    }

    public Agencia buscarPorId(int id) {
        for (Agencia a : agencias) {
            if (a.getIdAgencia() == id) return a;
        }
        return null;
    }

    public void listarAgencias() {
        if (agencias.isEmpty()) {
            System.out.println("  Nenhuma agência cadastrada.");
            return;
        }
        for (Agencia a : agencias) {
            System.out.println("  " + a);
        }
    }

    public void vincularSatelite(int idAgencia, Satelite satelite) {
        Agencia a = buscarPorId(idAgencia);
        if (a != null) {
            a.adicionarSatelite(satelite);
        }
    }

    public List<Agencia> getAgencias() { return agencias; }
}
