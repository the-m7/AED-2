import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        ArrayList<Estudiante> Lista = JSONRead.jsonRead("src\\estudiantes.json");

        Scanner scanner = new Scanner(System.in);
        
        if(Lista!=null){
            
            System.out.println("Seleccione el tipo de mapa que desea crear:");
            System.out.println("1. HashMap");
            System.out.println("2. TreeMap");
            System.out.println("3. LinkedHashMap");
            
            int opcion_mapa = scanner.nextInt();
            
            System.out.println("Selecciona el tipo de algoritmo para la función hash:");
            System.out.println("1. MD5");
            System.out.println("2. SHA-1");
            System.out.println("3. Orgánica");
            
            int opcion_hash = scanner.nextInt();
            
            //MapFactory<String, Estudiante> fact = new MapFactory<String, Estudiante>();

            AbstractMap<String, Estudiante> mapa = new MapFactory<String, Estudiante>().getMapInstance(opcion_mapa);

            IFuncionesHash hash = HashFactory.getHashInstance(opcion_hash);

            ArrayList<String> Lemails = new ArrayList<String>();
            ArrayList<Estudiante> Repetidos = new ArrayList<Estudiante>();
            for(Estudiante ele : Lista){
                String email = ele.getEmail();
                if(!Lemails.contains(email)){
                    Lemails.add(email);
                    String key = hash.calcularHash(email);
                    mapa.put(key, ele);
                } else {
                    Repetidos.add(ele);
                }
            }
            System.out.println(Repetidos.size());
        }
        
        scanner.close();

    }
}