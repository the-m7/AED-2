package src;

public interface IRadio {
    boolean cambioBinario(boolean valor);

    int[] guardarAM(int[] lista_AM, int posicion, int estacion);

    double[] guardarFM(double[] lista_FM, int posicion, double estacion);

    int avanzarAM(int estacion_actual);

    double avanzarFM(double estacion_actual);

}
