import java.util.ArrayList;
import org.json.simple.JSONObject; 

public class JSONRead {
    public static ArrayList<Estudiante> read(String fileName){
        
        JSONObject jo = new JSONParser().parse(new FileReader(fileName));
        
        return null;

    }
}
