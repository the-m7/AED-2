package src;

import java.util.Arrays;
import java.util.Scanner;
public class ShellSort {
    public static void shellSort(int[] arr, int n){
        for(int intervalo=n/2; intervalo>0; intervalo = intervalo/2){
            for(int i=intervalo;i<n;i++){
                int temp = arr[i];
                int j;
                for (j=i;j>=intervalo && arr[j-intervalo]>temp;j = j - intervalo){
                    arr[j] = arr[j-intervalo];
                }
                arr[j]=temp;
            }
        }
    }
    
    public static void main_(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamaño del arreglo: ");
        int size = scanner.nextInt();
        int[] arreglo = new int[size];
        System.out.println("Ingrese los elementos del arreglo separados por espacios:");
        for (int i = 0; i < size; i++) {
            arreglo[i] = scanner.nextInt();
        }
        //int[] arreglo = {9,8,3,7,5,6,4,1};
        ShellSort ss = new ShellSort();
        System.out.println("Arreglo sin ordenar:");
        System.out.println(Arrays.toString(arreglo));
        ss.shellSort(arreglo, arreglo.length);
        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(arreglo));

    }
}
