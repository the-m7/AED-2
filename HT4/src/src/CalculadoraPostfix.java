package src;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculadoraPostfix implements ICalculadoraPostfix {

	@Override
	public ArrayList<String> validateExpression(String expression) throws Exception {
		HashMap<String, Integer> Simbolos =  new HashMap<String, Integer>();
		
		Simbolos.put("+", 0);
		Simbolos.put("-", 0);
		Simbolos.put("*", 1);
		Simbolos.put("/", 1);
		
		ArrayList<String> ListExpr = new ArrayList<String>();
		
		return ListExpr;
	}

	@Override
	public int resolve(ArrayList<String> expression) throws Exception {
		
		IStack<Integer> Stack = FactoryStack.ge;
		
		int size = expression.size();
		
		for(int i=0;i<size;i++) {
			String ele = expression.get(i);
			//System.out.println(ele);
			try { // Revisa si es un número
				Integer num = Integer.parseInt(ele);
				Stack.push(num);
				//System.out.println(num);
				
			}

			catch (NumberFormatException e) { // Si no es un número entonces es un símbolo
				//System.out.println("simbolo");
				int n2 = Stack.pop();
				int n1 = Stack.pop();
				switch(ele) {
				case "+":
//					System.out.println("+ "+(n1+n2));
					Stack.push(n1+n2);
					break;
				case "-":
	//				System.out.println("- "+(n1-n2));
					Stack.push(n1-n2);
					break;
				case "*":
//					System.out.println("* "+(n1*n2));
					Stack.push(n1*n2);
					break;
				case "/":
//					System.out.println("/ "+(n1/n2));
					if(n2!=0)
						Stack.push(n1/n2);
					else
						throw new Exception("Error, Division por cero");
					break;
				case "%":
//					System.out.println("% "+(n1%n2));
					Stack.push(n1%n2);
					break;
				}
			}
		}
		
		return Stack.peek();
	}

}
