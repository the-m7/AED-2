package quickSort;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> Lista = new ArrayList<Integer>();
		
		int size = 1000;
		int max = 10;
		
		for(int i=0; i<size; i++) {
			Lista.add((int)(Math.random() * max) + 1);
			System.out.print(Lista.get(i)+", ");
		}
		
		System.out.println("\nInicio");  
		
		Lista=QuickSort.Sort(Lista);
	
		System.out.println("Fin");

		for(int i=0; i<size; i++) {
			System.out.print(Lista.get(i)+", ");
		}
	
	
	}
	

}
