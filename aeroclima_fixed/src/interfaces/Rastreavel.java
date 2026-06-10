package interfaces;

// interface para equipamentos que podem ser rastreados no espaço
// o satélite implementa essa interface
public interface Rastreavel {
    String obterLocalizacaoAtual();
    boolean estaEmOrbita();
}
