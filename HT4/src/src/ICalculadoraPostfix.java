package src;

import java.util.ArrayList;

/**
 * Interfaz para la calculadora
 */
public interface ICalculadoraPostfix {
	ArrayList<String> validateExpression(String expression) throws Exception;
	
	int resolve(ArrayList<String> expression) throws Exception;

}
