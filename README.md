# AeroClima Insight 2.0

## Sobre o projeto

O **AeroClima Insight** é um sistema em Java para monitoramento climático por satélites. A aplicação simula o controle de agências espaciais, satélites, regiões geográficas e medições ambientais, permitindo registrar ocorrências climáticas, gerar alertas e acompanhar usuários com assinaturas de monitoramento.

O sistema é operado por um menu interativo no terminal e foi estruturado com **POO**, utilizando classes abstratas, interfaces, modelos de domínio e camada de serviços para organizar as regras de negócio.

**Principais funcionalidades:**
- Cadastro e listagem de agências espaciais e satélites
- Gerenciamento de regiões geográficas
- Registro de medições climáticas (temperatura, umidade, pressão, etc.)
- Controle de ocorrências climáticas e de infraestrutura, com alertas
- Gestão de usuários e assinaturas de monitoramento
- Geração de relatório geral do sistema

## Integrantes

1. Davi Alves dos Santos – RM: 566279
2. Diego Zandonadi – RM: 561488
3. Henrique de Lima Martins – RM: 561876
4. Luis Miguel Gonçalves Pinto – RM: 561232
5. Rafael Joda da Costa e Silva – RM: 561939

## Classes principais

### `app.Main`
Ponto de entrada do sistema. Exibe o menu principal, recebe as opções do usuário e delega as operações para as classes de serviço.

### Classes abstratas (`abstracts/`)

| Classe | Função |
|---|---|
| `EquipamentoMonitorado` | Base para equipamentos monitorados (id, nome, status). Define `gerarRelatorio()` e `exibirDados()`. |
| `OcorrenciaBase` | Base para ocorrências do sistema. Define `descreverOcorrencia()` e `isCritica()`. |

### Interfaces (`interfaces/`)

| Interface | Função |
|---|---|
| `Rastreavel` | Permite rastrear localização e verificar se o equipamento está em órbita. |
| `Calculavel` | Define cálculo de risco e classificação de anomalias. |
| `Notificavel` | Permite que o objeto receba alertas do sistema. |

### Modelos (`model/`)

| Classe | Função |
|---|---|
| `Agencia` | Representa uma agência espacial e seus satélites vinculados. |
| `Satelite` | Herda `EquipamentoMonitorado` e implementa `Rastreavel`. Armazena medições e subsistemas. |
| `Regiao` | Define uma região geográfica monitorada. |
| `Medicao` | Registra dados climáticos coletados (coordenadas, temperatura, precipitação). |
| `OcorrenciaClimatica` | Ocorrência climática (seca, tempestade, etc.). Herda `OcorrenciaBase` e implementa `Calculavel`. |
| `OcorrenciaInfraestrutura` | Ocorrência relacionada a falhas em equipamentos. Também herda `OcorrenciaBase`. |
| `Usuario` | Cliente do sistema. Implementa `Notificavel` para receber alertas. |
| `AssinaturaMonitoramento` | Vincula um usuário a uma região monitorada. |
| `Alerta` | Representa um alerta gerado a partir de uma ocorrência. |
| `Subsistema` | Componente técnico de um satélite (propulsão, bateria, etc.). |

### Serviços (`service/`)

| Classe | Função |
|---|---|
| `AgenciaService` | Cadastro e listagem de agências. |
| `SateliteService` | Gerencia satélites e subsistemas. |
| `RegiaoService` | Gerencia regiões geográficas. |
| `MedicaoService` | Registra e consulta medições, com validação dos dados. |
| `OcorrenciaService` | Controla ocorrências e geração de alertas. |
| `UsuarioService` | Gerencia usuários e assinaturas. |

### Utilitários (`util/`)

| Classe | Função |
|---|---|
| `ValidadorClimatico` | Valida coordenadas, temperatura e precipitação antes de registrar uma medição. |

## Como executar

1. Abra o projeto no IntelliJ.
2. Marque `aeroclima_fixed/src` como **Sources Root**.
3. Execute a classe `app.Main`.

O sistema abre um menu no terminal — basta digitar o número da opção desejada.

## Estrutura

```
aeroclima_fixed/src/
├── app/          → Main.java
├── abstracts/
├── interfaces/
├── model/
├── service/
└── util/
```
