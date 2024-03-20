import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        ArrayList<Estudiante> Lista = JSONRead.jsonRead();

        Scanner scanner = new Scanner(System.in);

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

        //IMap mapa = MapFactory.crearMapa(opcion_mapa);
    }
}