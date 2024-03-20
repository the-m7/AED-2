import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 

public class JSONRead {
        public static ArrayList<Estudiante> jsonRead() {
        JSONParser parser = new JSONParser();

        ArrayList<Estudiante> Lista = new ArrayList<Estudiante>();

        try (FileReader reader = new FileReader("estudiantes.json")) {
            // Parseando el archivo JSON
            Object obj = parser.parse(reader);

            // Convirtiendo el objeto JSON en JSONArray
            JSONArray jsonArray = (JSONArray) obj;

            // Iterando sobre cada objeto JSON en el JSONArray
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;

                // Obteniendo los valores de "id" y "nombre"
                String name = (String) jsonObject.get("name");
                String phone = (String) jsonObject.get("phone");
                String email = (String) jsonObject.get("email");
                Integer postalZip = (Integer) jsonObject.get("email");
                String country = (String) jsonObject.get("country");
                

                // Imprimiendo los valores
                Lista.add(new Estudiante(name, phone, email, country, postalZip));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        System.out.println(Lista.toString());
        return Lista;
    }

}
