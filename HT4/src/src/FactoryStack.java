package src;

import java.util.Scanner;

public class FactoryStack {
	
	public static int elegirImplementacionStack() {
        int opcion;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Programa - evaluación de expresiones infix");
            System.out.println("1. ArrayList");
            System.out.println("2. Vector");
            System.out.println("3. Lista (Simplemente encadenada)");
            System.out.println("4. Lista (Doblemente encadenada)");
            System.out.println("Ingrese el número correspondinete a la implementación que desea usar:");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 4);

        return opcion;		//devuelve la opcion elegida por el usuario
    }

	public static <T> IStack<T> getInstance(int n){
		switch(n) { 		//de acuerdo a la opcion elegida por el usuario se utiliza una implementación
		case 1:
			return new ArrayStack<T>();
		case 2:
			return new StackVector<T>();
		case 3:
			return new StackUsingNodes<T>();
		case 4:
			return new StackDoubleNodes<T>();
		}
		return null;
		
	}
	
}
