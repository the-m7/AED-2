package src;

public class Radio implements IRadio {

	
	@Override
	/**
	 * Cambio binario
	 * @param boolean
	 */
	public boolean cambioBinario(boolean valor) {
		// TODO Auto-generated method stub
		return !valor;
	}

	@Override
	/**
	 * Guardar en la lista de AM
	 * @param Lista de botones AM
	 * @param posicion para guardar
	 * @param estación actual
	 */
	public int[] guardarAM(int[] lista_AM, int posicion, int estacion) {
		lista_AM[posicion]=estacion;
		return lista_AM;
	}

	@Override
	/**
	 * Guardar en la lista de FM
	 * @param Lista de botones FM
	 * @param posicion para guardar
	 * @param estación actual
	 */
	public double[] guardarFM(double[] lista_FM, int posicion, double estacion) {
		lista_FM[posicion]=estacion;
		return lista_FM;
	}

	@Override
	/**
	 * Avanzar emisora AM
	 * @param estación actual
	 */
	public int avanzarAM(int estacion_actual) {
		// TODO Auto-generated method stub
		if(estacion_actual+10>1610)
			return 530;
		return estacion_actual+10;
	}

	@Override
	/**
	 * Avanzar emisora FM
	 * @param estación actual
	 */
	public double avanzarFM(double estacion_actual) {
		// TODO Auto-generated method stub
		if(estacion_actual+0.2>108)
			return 87.9;
		return Math.round((estacion_actual+0.2)*10)/10.0;
	}
}
