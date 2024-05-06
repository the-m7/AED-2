import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Text;

public class Main {
    public static void main(String[] args) {
        String Text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        // Crear un HashMap para almacenar las frecuencias de los caracteres
        HashMap<Character, Integer> frecuencias = new HashMap<>();

        // Recorrer cada caracter del texto
        for (int i = 0; i < Text.length(); i++) {
            char c = Text.charAt(i);

            // Incrementar la frecuencia del caracter actual
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Crear una lista para almacenar las frecuencias
        ArrayList<Integer> listaFrecuencias = new ArrayList<>(frecuencias.values());

        char[] chars = new char[frecuencias.size()];
        int[] freq = new int[frecuencias.size()];
        
        // Mostrar las frecuencias
        System.out.println("Frecuencias de caracteres:");
        int i=0;
        for (char c : frecuencias.keySet()) {
            System.out.println("'" + c + "': " + frecuencias.get(c));
            chars[i]=c;
            freq[i]=frecuencias.get(c);
            i++ ;
        }
        

        

        // Mostrar la lista de frecuencias
        System.out.println("Lista de frecuencias:");
        System.out.println(listaFrecuencias);
    

       //char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        //int[] charfreq = { 5, 9, 12, 13, 16, 45 };

        HuffmanTree huff = new HuffmanTree(chars, freq); //Text y frecuencias son los descritos arriba

        // System.out.println(huff.root.toString());
        printCode(huff.root, "");

        HashMap<Character, String> hash = new HashMap<Character, String>();
        HashCode(huff.root, "", hash);

        System.out.println(hash.toString());

        //System.out.println("termino");
    }
    
    public static void printCode(HuffmanNode root, String s) 
    { 
  
        // base case; if the left and right are null 
        // then its a leaf node and we print 
        // the code s generated by traversing the tree. 
        if (root.left == null && root.right == null
            && root.c!='¶') { 
  
            // c is the character in the node 
            System.out.println(root.c + ":" + s); 
  
            return; 
        } 
  
        // if we go to left then add "0" to the code. 
        // if we go to the right add"1" to the code. 
  
        // recursive calls for left and 
        // right sub-tree of the generated tree. 
        printCode(root.left, s + "0"); 
        printCode(root.right, s + "1"); 
    }

    public static void HashCode(HuffmanNode root, String s, HashMap<Character,String> hash) 
    { 
  
        // base case; if the left and right are null 
        // then its a leaf node and we print 
        // the code s generated by traversing the tree. 
        if (root.left == null && root.right == null
            && root.c!='¶') { 
  
            // c is the character in the node 
            hash.put(root.c,s);
  
            return; 
        } 
  
        // if we go to left then add "0" to the code. 
        // if we go to the right add"1" to the code. 
  
        // recursive calls for left and 
        // right sub-tree of the generated tree. 
        HashCode(root.left, s + "0", hash); 
        HashCode(root.right, s + "1", hash);
    }

}
