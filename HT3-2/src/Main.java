import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        int size = 100000;
        int max = 1000;

        ArrayList<Integer> Lista = new ArrayList<Integer>();
        int[] Lista_i = new int[size];

        for(int i=0; i<size; i++) {
            int n=(int)((Math.random() * max) + 1);
            Lista.add(n);
            Lista_i[i]=n;
//			System.out.print(Lista.get(i)+", ");
        }
		Lista.sort(null);
        Lista = reverse(Lista);
        HeapSort(Lista_i);
        Lista_i=reverse(Lista_i);

        int[] Li1c = Lista_i.clone();
        GnomeSort(Li1c);

        int[] Li2c = Lista_i.clone();
        HeapSort(Li2c);

        int[] Li3c = Lista_i.clone();
        MergeSort(Li3c,0,size-1);

        int[] Li4c = Lista_i.clone();
        SelectionSort_(Li4c);

        int[] Li5c = Lista_i.clone();
        ShellSort_(Li5c,size-1);

        ArrayList<Integer> L1c = new ArrayList<Integer>(Lista);
        L1c=QuickSort.Sort(L1c);

//        System.out.println(toString(Lista));
//        //System.out.print(arr[i]+", ");
//        System.out.println(toString(L1c));
        System.out.println("hola");


    }

    public static void GnomeSort(int[] Li1c){
        GnomeSort.gnomeSort(Li1c);
    }

    public static void HeapSort(int[] Li2c){
        HeapSort.sort_(Li2c);
    }

    public static void MergeSort(int [] Li3c, int l, int r){
        MergeSort.sort(Li3c,l,r);
    }

    public static void SelectionSort_(int[] Li3c){
        SelectionSort.sort(Li3c);
    }

    public static void ShellSort_(int[] Li5c,int n){
        ShellSort.shellSort(Li5c,n);
    }

    public static int[] reverse(int[] Lista){
        int size = Lista.length;
        int[] Lista2 = new int[size];
        for(int i=0;i<size;i++){
            Lista2[i]  = Lista[size-1-i];
        }
        return Lista2;
    }

    public static ArrayList<Integer> reverse(ArrayList<Integer> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }
        return list;
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


