package util;

// classe utilitária com métodos estáticos de validação climática
// não precisa ser instanciada, por isso o construtor é privado
public class ValidadorClimatico {

    private ValidadorClimatico() {}

    public static boolean latitudeValida(double lat) {
        return lat >= -90.0 && lat <= 90.0;
    }

    public static boolean longitudeValida(double lon) {
        return lon >= -180.0 && lon <= 180.0;
    }

    // temperatura plausível para superfície terrestre
    public static boolean temperaturaPlausivelParaSuperficie(double temp) {
        return temp >= -89.0 && temp <= 60.0;
    }

    public static boolean precipitacaoValida(double precip) {
        return precip >= 0.0;
    }

    // valida latitude e longitude juntas
    public static boolean coordenadasValidas(double lat, double lon) {
        return latitudeValida(lat) && longitudeValida(lon);
    }

    public static String classificarNivelGravidade(double temp, double precip) {
        if (temp >= 45.0 || precip >= 150.0) return "Critico";
        if (temp >= 42.0 || precip >= 100.0) return "Medio";
        return "Baixo";
    }
}
