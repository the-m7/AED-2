import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("TEXTO DEL ARCHIVO:");
        String text = FileHelper.readFile2("src\\text.txt");

        // Crear un HashMap para almacenar las frecuencias de los caracteres
        HashMap<Character, Integer> frecuencias = new HashMap<>();

        // Recorrer cada caracter del texto
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Incrementar la frecuencia del caracter actual
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Crear una lista para almacenar las frecuencias
        ArrayList<Integer> listaFrecuencias = new ArrayList<>(frecuencias.values());

        String archText = "";

        char[] chars = new char[frecuencias.size()];
        int[] freq = new int[frecuencias.size()];

        // Mostrar las frecuencias
        System.out.println("\nFrecuencias de caracteres:");
        int i = 0;
        for (char c : frecuencias.keySet()) {
            System.out.println("'" + c + "': " + frecuencias.get(c));
            archText+=c + ":" + frecuencias.get(c)+"\n";
            chars[i] = c;
            freq[i] = frecuencias.get(c);
            i++;
        }

        FileHelper.saveFile(archText.substring(0, archText.length()-1), "src\\frecuencias.txt");

        // Mostrar la lista de frecuencias
        System.out.println("Lista de frecuencias:");
        System.out.println(listaFrecuencias);

        HuffmanTree huff = new HuffmanTree(chars, freq); // Text y frecuencias son los descritos arriba

        printCode(huff.root, "");

        HashMap<Character, String> hash = new HashMap<Character, String>();
        HashCode(huff.root, "", hash);

        System.out.println("Caracteres en el HuffmanTree:");
        System.out.println(hash.toString());

        // imprime el texto del archivo codificado
        String texto_codificado = encodeText(text, hash);
        System.out.println("\nTexto codificado:");
        System.out.println(texto_codificado);

        // Ajustar el texto codificado para que sea divisible entre 8
        while (texto_codificado.length() % 8 != 0) {
            texto_codificado += "0";
        }
        String texto_codificado_o = texto_codificado;

        // Convertir el texto codificado a valores decimales y luego restar 128
        ArrayList<Byte> bytesSalida = new ArrayList<>();
        while (texto_codificado.length() > 0) {
            String ochoBites = texto_codificado.substring(0, 8);
            texto_codificado = texto_codificado.substring(8);
            bytesSalida.add((byte) (binarioADecimal(Integer.parseInt(ochoBites)) - 128));
        }

        // Guardar los bytes en un archivo binario
        FileHelper.saveBinaryFile(bytesSalida, "src\\texto_codificado.txt");
        System.out.println("\nComparasión de archivos:");
        FileHelper.compareFileSizes("src\\text.txt", "src\\texto_codificado.txt");

        System.out.println("\nArchivo decodificado:");
        // Imprime el texto decodificado
        System.out.println(decodeText(texto_codificado_o, huff.root));
    }

    public static void printCode(HuffmanNode root, String s) {

        // base case; if the left and right are null
        // then its a leaf node and we print
        // the code s generated by traversing the tree.
        if (root.left == null && root.right == null
                && root.c != '¶') {
            // c is the character in the node
            System.out.println(root.c + ":" + s);

            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and right sub-tree of the generated tree.
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void HashCode(HuffmanNode root, String s, HashMap<Character, String> hash) {

        // base case; if the left and right are null
        // then its a leaf node and we print
        // the code s generated by traversing the tree.
        if (root.left == null && root.right == null
                && root.c != '¶') {
            hash.put(root.c, s);
            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and right sub-tree of the generated tree.
        HashCode(root.left, s + "0", hash);
        HashCode(root.right, s + "1", hash);
    }

    // para codificar el texto:
    public static String encodeText(String text, Map<Character, String> codes) {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            encodedText.append(codes.get(c));
        }
        return encodedText.toString();
    }

    // para decodificar el texto:
    public static String decodeText(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decodedText.append(current.c);
                current = root;
            }
        }
        return decodedText.toString();
    }

    public static int binarioADecimal(int numeroBinario) {
        int numeroDecimal = 0;
        int base = 1; // La base inicial es 2^0

        while (numeroBinario > 0) {
            int digito = numeroBinario % 10; // Obtener el último dígito del número binario
            numeroBinario = numeroBinario / 10; // Descartar el último dígito
            numeroDecimal += digito * base; // Multiplicar el dígito por la base y sumarlo al número decimal
            base *= 2; // Incrementar la base multiplicando por 2
        }

        return numeroDecimal;
    }

}
