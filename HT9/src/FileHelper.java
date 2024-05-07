import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    /**
     * Lee un archivo y devuelve una lista de cadenas, una por cada línea del
     * archivo.
     *
     * @param filePath La ruta del archivo a leer.
     * @return Una lista de cadenas, una por cada línea del archivo.
     * @throws IOException Si hay un error al leer el archivo.
     */
    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                lines.add(line);
            }
        }

        return lines;
    }

    /**
     * Lee un archivo y devuelve una lista de cadenas, una por cada línea del
     * archivo.
     *
     * @param filePath La ruta del archivo a leer.
     * @return Una lista de cadenas, una por cada línea del archivo.
     * @throws IOException Si hay un error al leer el archivo.
     */
    public static String readFile2(String filePath) throws IOException {
        String lines = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                lines = lines.concat(" ".concat(line));
            }
        }
        lines = lines.strip();
        System.out.println(lines);

        return lines;
    }

    public static void saveFile(String text, String filePath) {

        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter(filePath);

            // Creates a BufferedWriter
            BufferedWriter output = new BufferedWriter(file);

            // Writes the string to the file
            output.write(text);

            // Closes the writer
            output.close();
        }catch (Exception e) {
            e.getStackTrace();
        }
    }


    // Guarda archivo bianrio
    public static void saveBinaryFile(ArrayList<Byte> bytes, String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            new FileOutputStream(filePath).close();
            FileOutputStream fos = new FileOutputStream(filePath);

            byte[] datosOutput = new byte[bytes.size()];
            for (int i = 0; i < bytes.size(); i++) {
                datosOutput[i] = bytes.get(i);
            }

            // Escribir los datos en el archivo
            fos.write(datosOutput);

            // Cerrar el flujo de salida
            fos.close();

            System.out.println("Archivo binario guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo binario: " + e.getMessage());
        }
    }

    // Compara el tamaño de dos archivos: texto original, texto codificado
    public static void compareFileSizes(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        long fileSize1 = file1.length();
        long fileSize2 = file2.length();

        System.out.println("Tamaño del archivo text.txt: " + fileSize1 + " bytes");
        System.out.println("Tamaño del archivo texto_codificado.txt: " + fileSize2 + " bytes");

        if (fileSize1 == fileSize2) {
            System.out.println("Los archivos tienen el mismo tamaño.");
        } else if (fileSize1 > fileSize2) {
            System.out
                    .println("El archivo con el texto original es más grande que el archivo con el texto codificado.");
        } else {
            System.out
                    .println("El archivo con el texto codificado es más grande que el archivo con el texto original.");
        }
    }
}
