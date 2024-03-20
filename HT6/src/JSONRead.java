import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject; 
import com.fasterxml.jackson.databind.ObjectMapper

public class JSONRead {
    // Método para leer un archivo JSON y convertirlo en un objeto Java
    public static <T> T readJSONFile(String filePath, Class<T> valueType) throws IOException {
        // Crear un objeto ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        // Leer el archivo JSON y convertirlo en un objeto Java del tipo especificado
        return objectMapper.readValue(new File(filePath), valueType);
    }
}
