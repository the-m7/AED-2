package src;

import java.util.ArrayList;

public class CalculadoraPostfix implements ICalculadoraPostfix {

	@Override
	public ArrayList<String> validateExpression(String expression) throws Exception {
		String simbolos = "+-*/%";
		
		ArrayList<String> ListExpr = new ArrayList<String>();
		int size = expression.length();
		String snum = "";
		
		for(int i=0; i<size; i++) {
			Character e = expression.charAt(i);
			String e2 = e.toString();
			
			if(Character.isDigit(e)) {
				snum=snum.concat(e2);
			}
			else if(simbolos.contains(e2)) {
				if(!snum.equals("")) {
					ListExpr.add(snum);
					snum="";
				}
				ListExpr.add(e2);
			}
			else if(e.equals(' ')) {
				if(!snum.equals("")) {
					ListExpr.add(snum);
					snum="";
				}
			}
			else
				throw new Exception("Caracter no reconocido");
		//System.out.println(snum);
		}
		return ListExpr;
	}

	@Override
	public int resolve(ArrayList<String> expression) throws Exception {
		
		StackVector<Integer> Stack = new StackVector<Integer>();
		
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
