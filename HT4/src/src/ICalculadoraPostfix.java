package src;

import java.util.ArrayList;

/**
 * Interfaz para la calculadora
 */
public interface ICalculadoraPostfix {
	ArrayList<Character> validateExpression(String expression) throws Exception;
	
	int resolve(ArrayList<Character> listExpr) throws Exception;

}
