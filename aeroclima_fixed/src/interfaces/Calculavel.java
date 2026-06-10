package interfaces;

// interface para classes que calculam risco e classificam anomalias
// tanto ocorrência climática quanto de infraestrutura implementam isso
public interface Calculavel {
    double calcularRisco();
    String classificarAnomalia();
}
