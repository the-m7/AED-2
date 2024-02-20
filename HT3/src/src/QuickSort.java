package src;

import java.util.ArrayList;

public class QuickSort {
	public static ArrayList<Integer> Sort(ArrayList<Integer> Lista) {
		ArrayList<Integer> L1 = new ArrayList<Integer>();
		ArrayList<Integer> L2 = new ArrayList<Integer>();
		
		int size = Lista.size();
		
		if(size>1) {
			Integer ele = Lista.get(size-1);
			
			for(int i=0; i<size-1; i++) {
				Integer point = Lista.get(i);
				if(point<ele)
					L1.add(point);
				else
					L2.add(point);
		}	
		L1 = Sort(L1);
		L2 = Sort(L2);	
		
		L1.add(ele);
		L1.addAll(L2);
		
		return L1;
		}
		else {
			return Lista;
		}
	}
}
