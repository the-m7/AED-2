package src;

import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		int size = 10;
		int max = 10;

		ArrayList<Integer> Lista = new ArrayList<Integer>();
		int[] Lista_i = new int[size];
		
		for(int i=0; i<size; i++) {
			int n=(int)((Math.random() * max) + 1);
			Lista.add(n);
			Lista_i[i]=n;
//			System.out.print(Lista.get(i)+", ");
		}
//		Lista.sort(null);
		
		
		int[] Li1c = Lista_i.clone();
		GnomeSort.gnomeSort(Li1c);
		
		int[] Li2c = Lista_i.clone();
		HeapSort.sort(Li2c);
		
		int[] Li3c = Lista_i.clone();
		MergeSort.sort(Li3c,0,5);
		
		ArrayList<Integer> L1c = new ArrayList<Integer>(Lista);
		L1c=QuickSort.Sort(L1c);

		int[] Li4c = Lista_i.clone();
		SelectionSort.sort(Li4c);
		
		int[] Li5c = Lista_i.clone();
		ShellSort.shellSort(Li5c,2);
		
			System.out.println(toString(Lista));
			//System.out.print(arr[i]+", ");
			System.out.println(toString(L1c));
			System.out.println();
	
	
	}
	
	public static String toString(ArrayList<Integer> Lista) {
		String s = "";
		int size = Lista.size();
		for(int i=0; i<size;i++) {
			s=s.concat(Lista.get(i).toString()+", ");
//			System.out.println(s);
		}
		
		return s.substring(0, s.length()-2);
	}
	
}


