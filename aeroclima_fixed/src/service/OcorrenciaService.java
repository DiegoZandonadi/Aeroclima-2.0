package service;

import abstracts.OcorrenciaBase;
import interfaces.Calculavel;
import model.Alerta;
import model.OcorrenciaClimatica;
import model.OcorrenciaInfraestrutura;
import model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// gerencia as ocorrências climáticas e de infraestrutura, e gera alertas
public class OcorrenciaService {

    private List<OcorrenciaBase> ocorrencias = new ArrayList<>();
    private List<Alerta> alertas = new ArrayList<>();
    private int proximoIdOcorrencia = 600001;
    private int proximoIdAlerta = 700001;

    // limiar padrão para detecção automática de anomalia
    private static final double TEMP_ANOMALIA   = 38.0;
    private static final double PRECIP_ANOMALIA = 80.0;

    // --- registro de ocorrências ---

    public OcorrenciaClimatica registrarOcorrencia(String tipoEvento, String nivelGravidade,
                                                    int idMedicao, double temp, double precip) {
        OcorrenciaClimatica oc = new OcorrenciaClimatica(
            proximoIdOcorrencia++, tipoEvento, LocalDate.now(),
            nivelGravidade, idMedicao, temp, precip);
        ocorrencias.add(oc);
        System.out.println("  ✔ " + oc.descreverOcorrencia("resumido"));
        return oc;
    }

    public OcorrenciaInfraestrutura registrarOcorrenciaInfraestrutura(
            String sistemaAfetado, String nivelGravidade,
            double percentualFalha, int idSatelite) {

        OcorrenciaInfraestrutura oi = new OcorrenciaInfraestrutura(
            proximoIdOcorrencia++, sistemaAfetado, LocalDate.now(),
            nivelGravidade, percentualFalha, idSatelite);
        ocorrencias.add(oi);
        System.out.println("  ✔ " + oi.descreverOcorrencia());
        return oi;
    }

    // detecta anomalia automaticamente com os limites padrão do sistema
    public OcorrenciaClimatica detectarAnomalia(int idMedicao, double temp, double precip) {
        if (temp < TEMP_ANOMALIA && precip < PRECIP_ANOMALIA) return null;

        String tipo = (temp >= TEMP_ANOMALIA && precip >= PRECIP_ANOMALIA)
            ? "Tempestade com Calor Extremo"
            : temp >= TEMP_ANOMALIA ? "Onda de Calor" : "Tempestade Severa";

        String gravidade = (temp >= 45.0 || precip >= 150.0) ? "Critico"
                         : (temp >= 42.0 || precip >= 100.0) ? "Medio" : "Baixo";

        return registrarOcorrencia(tipo, gravidade, idMedicao, temp, precip);
    }

    // sobrecarga: detecta anomalia com limites customizados pelo chamador
    public OcorrenciaClimatica detectarAnomalia(int idMedicao, double temp, double precip,
                                                 double limiarTemp, double limiarPrecip) {
        if (temp < limiarTemp && precip < limiarPrecip) return null;
        String tipo = temp >= limiarTemp ? "Anomalia Térmica Customizada" : "Anomalia de Precipitação Customizada";
        String gravidade = temp >= 45.0 || precip >= 150.0 ? "Critico" : "Medio";
        return registrarOcorrencia(tipo, gravidade, idMedicao, temp, precip);
    }

    // --- geração de alertas ---

    public Alerta gerarAlerta(OcorrenciaBase ocorrencia, String mensagem) {
        Alerta alerta = new Alerta(proximoIdAlerta++, mensagem, ocorrencia.getId());
        alertas.add(alerta);
        return alerta;
    }

    // dispara alertas para uma lista de usuários quando a ocorrência é crítica
    // usa a interface Calculavel para calcular o risco antes de montar a mensagem
    public void dispararAlertas(OcorrenciaClimatica ocorrencia, List<Usuario> usuarios) {
        if (!ocorrencia.isCritica()) {
            System.out.println("  Ocorrência não é crítica — alertas não disparados.");
            return;
        }

        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("  Nenhum usuário assinante encontrado para esta região.");
            return;
        }

        // usa a interface Calculavel para obter o risco calculado
        Calculavel calc = ocorrencia;
        String msg = String.format("[ALERTA CRÍTICO] %s | Risco: %.1f%%",
            ocorrencia.getTipoEvento(), calc.calcularRisco());

        for (Usuario u : usuarios) {
            Alerta alerta = gerarAlerta(ocorrencia, msg);
            alerta.enviar();
            u.receberAlerta(msg);
        }
    }

    // --- listagens usando polimorfismo de OcorrenciaBase ---

    public void listarOcorrencias() {
        if (ocorrencias.isEmpty()) { System.out.println("  Nenhuma ocorrência registrada."); return; }
        for (OcorrenciaBase oc : ocorrencias) {
            // polimorfismo: cada subclasse imprime sua versão de descreverOcorrencia()
            System.out.println("  " + oc.descreverOcorrencia());
        }
    }

    public void listarAlertas() {
        if (alertas.isEmpty()) { System.out.println("  Nenhum alerta gerado."); return; }
        for (Alerta a : alertas) a.exibirDados();
    }

    public List<OcorrenciaBase> getOcorrencias() { return ocorrencias; }
    public List<Alerta> getAlertas() { return alertas; }
}
