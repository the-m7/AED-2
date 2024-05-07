import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
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

    public static void saveBinaryFile(ArrayList<Byte> bytes, String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
}
