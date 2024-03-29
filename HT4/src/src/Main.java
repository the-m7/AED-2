package src;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Lee el archivo y obtiene la lista de expresiones
            List<String> expressions = FileHelper.readFile("datos.txt");
           
           FactoryStack.getInstance(FactoryStack.elegirImplementacionStack());
            
            // Prueba para el validate Expression
            ICalculadoraPostfix miCalc = new CalculadoraPostfix(1);
            for (String expression : expressions) {
                try {
                	System.out.println(expression);
                	ArrayList<Character> listExpr = miCalc.validateExpression(expression);
                	System.out.println(listExpr.toString());
                	System.out.println("Resultado: "+miCalc.resolve(listExpr));                	
                }
                catch (Exception e) {
                    // Manejar otras excepciones
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Manejar la excepción en caso de error al leer el archivo
            e.printStackTrace();
        } catch (Exception e) {
            // Manejar otras excepciones
            e.printStackTrace();
        }
    }
}


