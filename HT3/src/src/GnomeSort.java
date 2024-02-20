package src;

public class GnomeSort {
    // Método para ordenar el array usando Gnome Sort
    public static void gnomeSort(int[] arr) {
        int index = 0;
        
        while (index < arr.length) {
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                // Intercambiar elementos
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                
                index--;
            }
        }
    }

    // Método principal para probar el algoritmo de ordenamiento
    public static void main_() {
        int[] arr = {34, 2, 78, 56, 45, 32, 12};
        
        System.out.println("Array original: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Llamar al método gnomeSort para ordenar el array
        gnomeSort(arr);
        
        System.out.println("Array ordenado: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}
