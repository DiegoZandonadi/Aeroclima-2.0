package app;

import interfaces.Calculavel;
import model.*;
import service.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// classe principal do sistema AeroClima Insight
// aqui fica o menu e toda a interação com o usuário via Scanner
public class Main {

    // serviços do sistema
    private static AgenciaService agenciaService = new AgenciaService();
    private static SateliteService sateliteService = new SateliteService();
    private static RegiaoService regiaoService = new RegiaoService();
    private static MedicaoService medicaoService = new MedicaoService();
    private static OcorrenciaService ocorrenciaService = new OcorrenciaService();
    private static UsuarioService usuarioService = new UsuarioService();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║       AeroClima Insight — Sistema de Satélites   ║");
        System.out.println("║     Monitoramento Climático Inteligente | FIAP   ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        carregarDadosIniciais();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInt("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> menuAgencias();
                case 2 -> menuSatelites();
                case 3 -> menuRegioes();
                case 4 -> menuMedicoes();
                case 5 -> menuOcorrencias();
                case 6 -> menuUsuarios();
                case 7 -> gerarRelatorioGeral();
                case 0 -> System.out.println("\n  Encerrando AeroClima Insight. Até logo!");
                default -> System.out.println("  Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    // ==================== MENU PRINCIPAL ====================

    private static void exibirMenuPrincipal() {
        System.out.println("\n══════════════ MENU PRINCIPAL ══════════════");
        System.out.println("  1. Gerenciar Agências Espaciais");
        System.out.println("  2. Gerenciar Satélites");
        System.out.println("  3. Gerenciar Regiões Geográficas");
        System.out.println("  4. Registrar Medição Climática");
        System.out.println("  5. Ocorrências e Alertas");
        System.out.println("  6. Usuários e Assinaturas");
        System.out.println("  7. Relatório Geral do Sistema");
        System.out.println("  0. Sair");
        System.out.println("════════════════════════════════════════════");
    }

    // ==================== MENU AGÊNCIAS ====================

    private static void menuAgencias() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- AGÊNCIAS ESPACIAIS ---");
            System.out.println("  1. Cadastrar agência");
            System.out.println("  2. Listar agências");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    String nome = lerString("Nome da agência: ");
                    String pais = lerString("País de origem: ");
                    agenciaService.cadastrarAgencia(nome, pais);
                }
                case 2 -> {
                    System.out.println("\n  Agências cadastradas:");
                    agenciaService.listarAgencias();
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== MENU SATÉLITES ====================

    private static void menuSatelites() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- SATÉLITES ---");
            System.out.println("  1. Cadastrar satélite");
            System.out.println("  2. Adicionar subsistema a satélite");
            System.out.println("  3. Listar satélites");
            System.out.println("  4. Ver relatório de satélite");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    String nome = lerString("Nome do satélite: ");
                    String dataStr = lerString("Data de lançamento (AAAA-MM-DD): ");
                    LocalDate data = LocalDate.parse(dataStr);
                    int idAgencia = lerInt("ID da agência operadora: ");
                    Satelite s = sateliteService.cadastrarSatelite(nome, data, idAgencia);
                    agenciaService.vincularSatelite(idAgencia, s);
                }
                case 2 -> {
                    int idSat = lerInt("ID do satélite: ");
                    Satelite s = sateliteService.buscarPorId(idSat);
                    if (s == null) { System.out.println("  Satélite não encontrado."); break; }
                    String prop = lerString("Tipo de propulsão (ex: Iônica): ");
                    double bat = lerDouble("Capacidade da bateria (Wh): ");
                    sateliteService.adicionarSubsistema(s, prop, bat);
                }
                case 3 -> {
                    System.out.println("\n  Satélites cadastrados:");
                    sateliteService.listarSatelites();
                }
                case 4 -> {
                    int idSat = lerInt("ID do satélite: ");
                    Satelite s = sateliteService.buscarPorId(idSat);
                    if (s == null) { System.out.println("  Satélite não encontrado."); break; }
                    System.out.println();
                    // chama a sobrecarga com parâmetro int — mostra as últimas 3 medições
                    System.out.println(s.gerarRelatorio(3));
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== MENU REGIÕES ====================

    private static void menuRegioes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- REGIÕES GEOGRÁFICAS ---");
            System.out.println("  1. Cadastrar região");
            System.out.println("  2. Listar regiões");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    String nome = lerString("Nome da região: ");
                    String coord = lerString("Coordenadas limite (ex: -12.47/-45.43): ");
                    String bioma = lerString("Tipo de bioma (ex: Cerrado, Amazônia): ");
                    regiaoService.cadastrarRegiao(nome, coord, bioma);
                }
                case 2 -> {
                    System.out.println("\n  Regiões cadastradas:");
                    regiaoService.listarRegioes();
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== MENU MEDIÇÕES ====================

    private static void menuMedicoes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- MEDIÇÕES CLIMÁTICAS ---");
            System.out.println("  1. Registrar medição manual");
            System.out.println("  2. Listar todas as medições");
            System.out.println("  3. Buscar medições por satélite");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    sateliteService.listarSatelites();
                    int idSat = lerInt("ID do satélite: ");
                    Satelite s = sateliteService.buscarPorId(idSat);
                    if (s == null) { System.out.println("  Satélite não encontrado."); break; }

                    regiaoService.listarRegioes();
                    int idReg = lerInt("ID da região: ");
                    Regiao r = regiaoService.buscarPorId(idReg);
                    if (r == null) { System.out.println("  Região não encontrada."); break; }

                    double lat = lerDouble("Latitude: ");
                    double lon = lerDouble("Longitude: ");
                    double temp = lerDouble("Temperatura de superfície (°C): ");
                    double precip = lerDouble("Índice de precipitação (mm): ");

                    Medicao m = medicaoService.registrarMedicao(s, idReg, lat, lon, temp, precip);
                    if (m == null) { System.out.println("  Medição rejeitada pela validação."); break; }

                    // verifica automaticamente se a medição gerou uma anomalia
                    OcorrenciaClimatica oc = ocorrenciaService.detectarAnomalia(
                        m.getIdMedicao(), temp, precip);
                    if (oc != null && oc.isCritica()) {
                        System.out.println("  ⚠ Ocorrência crítica detectada! Disparando alertas...");
                        List<Usuario> assinantes = usuarioService.buscarPorRegiao(r.getIdRegiao());
                        ocorrenciaService.dispararAlertas(oc, assinantes);
                    }
                }
                case 2 -> {
                    System.out.println("\n  Medições registradas:");
                    medicaoService.listarMedicoes();
                }
                case 3 -> {
                    int idSat = lerInt("ID do satélite: ");
                    List<Medicao> lista = medicaoService.buscarPorSatelite(idSat);
                    if (lista.isEmpty()) {
                        System.out.println("  Nenhuma medição para este satélite.");
                    } else {
                        for (Medicao m : lista) m.exibirDados();
                    }
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== MENU OCORRÊNCIAS ====================

    private static void menuOcorrencias() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- OCORRÊNCIAS E ALERTAS ---");
            System.out.println("  1. Registrar ocorrência manualmente");
            System.out.println("  2. Listar ocorrências");
            System.out.println("  3. Listar alertas gerados");
            System.out.println("  4. Calcular risco de uma ocorrência (interface Calculavel)");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    String tipo = lerString("Tipo de evento (ex: Seca, Tempestade, Onda de Calor): ");
                    String grav = lerString("Nível de gravidade (Baixo, Medio, Critico): ");
                    double temp = lerDouble("Temperatura associada (°C): ");
                    double precip = lerDouble("Precipitação associada (mm): ");
                    OcorrenciaClimatica oc = ocorrenciaService.registrarOcorrencia(
                        tipo, grav, 0, temp, precip);
                    if (oc.isCritica()) {
                        System.out.println("  ⚠ Ocorrência crítica! Disparando alertas para todos os usuários...");
                        ocorrenciaService.dispararAlertas(oc, usuarioService.getUsuarios());
                    }
                }
                case 2 -> {
                    System.out.println("\n  Ocorrências registradas:");
                    ocorrenciaService.listarOcorrencias();
                }
                case 3 -> {
                    System.out.println("\n  Alertas gerados:");
                    ocorrenciaService.listarAlertas();
                }
                case 4 -> {
                    // demonstra o uso da interface Calculavel de forma explícita
                    System.out.println("\n  Informe os dados para calcular o risco:");
                    double temp = lerDouble("Temperatura (°C): ");
                    double precip = lerDouble("Precipitação (mm): ");
                    OcorrenciaClimatica oc = ocorrenciaService.registrarOcorrencia(
                        "Cálculo Manual", "Baixo", 0, temp, precip);

                    // usando a interface Calculavel — não o tipo concreto
                    Calculavel calc = oc;
                    System.out.printf("  Risco calculado: %.1f%%%n", calc.calcularRisco());
                    System.out.println("  Classificação: " + calc.classificarAnomalia());
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== MENU USUÁRIOS ====================

    private static void menuUsuarios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- USUÁRIOS E ASSINATURAS ---");
            System.out.println("  1. Cadastrar usuário");
            System.out.println("  2. Assinar monitoramento de região");
            System.out.println("  3. Listar usuários");
            System.out.println("  4. Listar assinaturas");
            System.out.println("  0. Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> {
                    String nome = lerString("Nome do usuário: ");
                    String email = lerString("E-mail corporativo: ");
                    String tel = lerString("Telefone: ");
                    String tipo = lerString("Tipo de cliente (Agronegocio, Seguradoras, OrgaoPublico): ");
                    String canal = lerString("Canal de notificação (Email, SMS, Push): ");
                    usuarioService.cadastrarUsuario(nome, email, tel, tipo, canal);
                }
                case 2 -> {
                    usuarioService.listarUsuarios();
                    int idUsr = lerInt("ID do usuário: ");
                    regiaoService.listarRegioes();
                    int idReg = lerInt("ID da região: ");
                    String canal = lerString("Canal (Email, SMS, Push): ");
                    usuarioService.assinarRegiao(idUsr, idReg, canal);
                }
                case 3 -> {
                    System.out.println("\n  Usuários cadastrados:");
                    usuarioService.listarUsuarios();
                }
                case 4 -> {
                    System.out.println("\n  Assinaturas ativas:");
                    usuarioService.listarAssinaturas();
                }
                case 0 -> {}
                default -> System.out.println("  Opção inválida.");
            }
        }
    }

    // ==================== RELATÓRIO GERAL ====================

    private static void gerarRelatorioGeral() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         RELATÓRIO GERAL DO SISTEMA       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n[ AGÊNCIAS ]");
        agenciaService.listarAgencias();

        System.out.println("\n[ SATÉLITES ]");
        for (Satelite s : sateliteService.getSatelites()) {
            System.out.println("  " + s.gerarRelatorio(2));
            if (s.getSubsistema() != null) s.getSubsistema().exibirDados();
        }

        System.out.println("\n[ REGIÕES MONITORADAS ]");
        regiaoService.listarRegioes();

        System.out.println("\n[ MEDIÇÕES — TOTAL: " + medicaoService.getMedicoes().size() + " ]");
        medicaoService.listarMedicoes();

        System.out.println("\n[ OCORRÊNCIAS CLIMÁTICAS ]");
        ocorrenciaService.listarOcorrencias();

        System.out.println("\n[ ALERTAS EMITIDOS ]");
        ocorrenciaService.listarAlertas();

        System.out.println("\n[ USUÁRIOS ]");
        usuarioService.listarUsuarios();

        System.out.println("\n[ ASSINATURAS DE MONITORAMENTO ]");
        usuarioService.listarAssinaturas();

        System.out.println("\n══════════════════════════════════════════════");
    }

    // ==================== DADOS INICIAIS ====================

    // carrega dados de exemplo para facilitar a apresentação do sistema
    private static void carregarDadosIniciais() {
        System.out.println("\n  Carregando dados de demonstração...");

        // agências
        Agencia nasa = agenciaService.cadastrarAgencia("NASA", "Estados Unidos");
        Agencia inpe = agenciaService.cadastrarAgencia("INPE", "Brasil");

        // satélites
        Satelite goes16 = sateliteService.cadastrarSatelite("GOES-16",
            LocalDate.of(2016, 11, 19), nasa.getIdAgencia());
        Satelite cbers4 = sateliteService.cadastrarSatelite("CBERS-4A",
            LocalDate.of(2019, 12, 20), inpe.getIdAgencia());

        // subsistemas de propulsão/energia
        sateliteService.adicionarSubsistema(goes16, "Iônica", 5000.0);
        sateliteService.adicionarSubsistema(cbers4, "Química", 3200.0);

        // vincula satélites às agências
        agenciaService.vincularSatelite(nasa.getIdAgencia(), goes16);
        agenciaService.vincularSatelite(inpe.getIdAgencia(), cbers4);

        // regiões monitoradas
        Regiao matopiba = regiaoService.cadastrarRegiao("MATOPIBA",
            "-12.47/-45.43", "Cerrado");
        Regiao amazonia = regiaoService.cadastrarRegiao("Amazônia Central",
            "-3.10/-60.02", "Floresta Tropical");

        // usuários do sistema
        Usuario fazendeiro = usuarioService.cadastrarUsuario(
            "Grupo AgroNorte", "contato@agronorte.com.br",
            "11999990001", "Agronegocio", "Push");
        Usuario seguradora = usuarioService.cadastrarUsuario(
            "BrasilSeg S.A.", "risco@brasileg.com.br",
            "11999990002", "Seguradoras", "Email");

        // assinaturas de regiões
        usuarioService.assinarRegiao(fazendeiro.getIdUsuario(), matopiba.getIdRegiao(), "Push");
        usuarioService.assinarRegiao(seguradora.getIdUsuario(), matopiba.getIdRegiao(), "Email");
        usuarioService.assinarRegiao(seguradora.getIdUsuario(), amazonia.getIdRegiao(), "Email");

        // medições normais
        medicaoService.registrarMedicao(goes16, matopiba.getIdRegiao(),
            -12.473896, -45.429076, 34.52, 0.01);
        medicaoService.registrarMedicao(cbers4, amazonia.getIdRegiao(),
            -3.10, -60.02, 29.80, 12.50);

        // medição com anomalia crítica — deve disparar alertas para os assinantes de MATOPIBA
        System.out.println("\n  !! Simulando medição com anomalia crítica...");
        Medicao medicaoCritica = medicaoService.registrarMedicao(
            goes16, matopiba.getIdRegiao(), -12.50, -45.50, 47.5, 160.0);
        OcorrenciaClimatica oc = ocorrenciaService.detectarAnomalia(
            medicaoCritica.getIdMedicao(), 47.5, 160.0);
        if (oc != null && oc.isCritica()) {
            // busca apenas os usuários que assinaram essa região
            List<Usuario> assinantes = usuarioService.buscarPorRegiao(matopiba.getIdRegiao());
            ocorrenciaService.dispararAlertas(oc, assinantes);
        }

        // ocorrência de infraestrutura no satélite CBERS-4A
        System.out.println("\n  !! Simulando falha de infraestrutura no CBERS-4A...");
        ocorrenciaService.registrarOcorrenciaInfraestrutura(
            "Bateria", "Critico", 85.0, cbers4.getId());

        // medição sem precipitação — demonstra a sobrecarga do método registrarMedicao
        System.out.println("\n  !! Registrando medição sem dado de precipitação (sobrecarga)...");
        medicaoService.registrarMedicao(goes16, amazonia.getIdRegiao(),
            -3.20, -60.10, 31.5);

        System.out.println("\n  Dados carregados com sucesso! Sistema pronto.\n");
    }

    // ==================== UTILITÁRIOS DE LEITURA ====================

    private static int lerInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -99;
        }
    }

    private static double lerDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private static String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
