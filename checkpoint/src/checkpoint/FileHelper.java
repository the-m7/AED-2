package checkpoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHelper {
    
    /**
     * Lee un archivo y devuelve una lista de cadenas, una por cada línea del archivo.
     * 
     * @param filePath La ruta del archivo a leer.
     * @return Una lista de cadenas, una por cada línea del archivo.
     * @throws IOException Si hay un error al leer el archivo.
     */
    public static ArrayList<String> readFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }
}