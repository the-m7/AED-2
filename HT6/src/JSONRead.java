import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 

public class JSONRead {
        public static ArrayList<Estudiante> jsonRead(String fileName) {
        JSONParser parser = new JSONParser();

        ArrayList<Estudiante> Lista = new ArrayList<Estudiante>();

        try (FileReader reader = new FileReader(fileName)) {
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
                String postalZip = (String) jsonObject.get("postalZip");
                String country = (String) jsonObject.get("country");
                

                // Imprimiendo los valores
                Lista.add(new Estudiante(name, phone, email, country, postalZip));
                
            }
            System.out.println(Lista.get(0).toString());
            System.out.println(Lista.get(1).toString());
            return Lista;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        //System.out.println(Lista.toString());
        return null;
    }

}
