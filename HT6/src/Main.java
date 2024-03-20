import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de mapa que desea crear:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int opcion = scanner.nextInt();

        Map<String, String> mapa = crearMapa(opcion);
    }
}