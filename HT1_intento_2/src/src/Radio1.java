package src;

public class Radio1 implements IRadio {

    /** 
     * Cambia el valor booleano dado a su opuesto.
     * 
     * @param valor El valor booleano actual.
     * @return El valor booleano opuesto al valor actual.
     */
    @Override
    public boolean cambioBinario(boolean valor) {
        valor = !valor;
        return valor;
    }



    

    /** 
     * Guarda una estación AM en la posición especificada de la lista de estaciones AM.
     * 
     * @param lista_AM Arreglo que contiene las estaciones AM guardadas.
     * @param posicion La posición en la lista donde se guardará la estación.
     * @param estacion La estación AM a guardar.
     * @return El arreglo actualizado con la nueva estación AM guardada.
     */
    @Override
    public int[] guardarAM(int[] lista_AM, int posicion, int estacion) {
        lista_AM[posicion] = estacion;
        return lista_AM;
    }




    /** 
     * Guarda una estación FM en la posición especificada de la lista de estaciones FM.
     * 
     * @param lista_FM Arreglo que contiene las estaciones FM guardadas.
     * @param posicion La posición en la lista donde se guardará la estación.
     * @param estacion La estación FM a guardar.
     * @return El arreglo actualizado con la nueva estación FM guardada.
     */
    @Override
    public double[] guardarFM(double[] lista_FM, int posicion, double estacion) {
        lista_FM[posicion] = estacion;
        return lista_FM;
    }



    /** javadoc -d documentation -sourcepath ./src -subpackages .
     * Avanza a la siguiente estación AM. Si se encuentra en la última estación, vuelve a la primera.
     * 
     * @param estacion_actual La estación AM actual.
     * @return La siguiente estación AM.
     */
    @Override
    public int avanzarAM(int estacion_actual) {
        if (estacion_actual == 1610) {
            estacion_actual = 530;
        }
        else {
            estacion_actual = estacion_actual + 10;
        }
        return estacion_actual;
    }



    /** 
     * Avanza a la siguiente estación FM. Si se encuentra en la última estación, vuelve a la primera.
     * 
     * @param estacion_actual La estación FM actual.
     * @return La siguiente estación FM.
     */ 
    @Override
    public double avanzarFM(double estacion_actual) {
        if (estacion_actual == 107.9) {
            estacion_actual = 87.9;
        }
        else {
            estacion_actual = estacion_actual + 0.2;
        }
        return estacion_actual;
    }

}
