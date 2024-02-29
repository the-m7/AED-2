package src;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculadoraPostfix implements ICalculadoraPostfix {

	@Override
	public ArrayList<Character> validateExpression(String expression) throws Exception {
		HashMap<Character, Integer> Simbolos =  new HashMap<Character, Integer>();
		


		Simbolos.put('(', -1);
		Simbolos.put('#', -1);
		Simbolos.put('+', 0);
		Simbolos.put('-', 0);
		Simbolos.put('*', 1);
		Simbolos.put('/', 1);
		
		IStack<Character> Stack = FactoryStack.getInstance(0);
		Stack.push('#');
		
		ArrayList<Character> ListExpr = new ArrayList<Character>();
				
		for(int i=0;i<expression.length();i++) {
			Character c = expression.charAt(i);
			
//			System.out.print(c);
//			
//			System.out.println(", "+Stack.peek());
			
//			System.out.println(Simbolos.get(c));
//			
//			System.out.println(Simbolos.get(c) > Simbolos.get('#'));
			
			if(Character.isDigit(c)) {
				ListExpr.add(c);
			}else if(c.equals(' ')) {
				;
			}else if(c.equals('(')) {
				Stack.push(c);
			}else if(c.equals(')')) {
				while(!Stack.peek().equals('#') && !Stack.peek().equals('(')) {
					ListExpr.add(Stack.pop());
				}
				Stack.pop();
			}else if(Simbolos.get(c) > Simbolos.get(Stack.peek())) {
				Stack.push(c);
			}else {
				while(!Stack.peek().equals('#') && (Simbolos.get(c) <= Simbolos.get(Stack.peek()))) {
					ListExpr.add(Stack.pop());
				}
				Stack.push(c);
			}
		}
		while(!Stack.peek().equals('#')) {
			ListExpr.add(Stack.pop());
		}
		
		return ListExpr;
	}

	@Override
	public int resolve(ArrayList<Character> expression) throws Exception {
		
		IStack<Integer> Stack = FactoryStack.getInstance(0);
		
		int size = expression.size();
		
		for(int i=0;i<size;i++) {
			Character ele = expression.get(i);
			//System.out.println(ele);
			if(Character.isDigit(ele)) {
				Integer num = Character.getNumericValue(ele);
				Stack.push(num);
				//System.out.println(num);
			}

			else { // Si no es un número entonces es un símbolo
				//System.out.println("simbolo");
				int n2 = Stack.pop();
				int n1 = Stack.pop();
				switch(ele) {
				case '+':
//					System.out.println("+ "+(n1+n2));
					Stack.push(n1+n2);
					break;
				case '-':
	//				System.out.println("- "+(n1-n2));
					Stack.push(n1-n2);
					break;
				case '*':
//					System.out.println("* "+(n1*n2));
					Stack.push(n1*n2);
					break;
				case '/':
//					System.out.println("/ "+(n1/n2));
					if(n2!=0)
						Stack.push(n1/n2);
					else
						throw new Exception("Error, Division por cero");
					break;
				case '%':
//					System.out.println("% "+(n1%n2));
					Stack.push(n1%n2);
					break;
				}
			}
		}
		
		return Stack.peek();
	}

}
