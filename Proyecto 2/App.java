import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Connexión a la base en Sandbox
        String username_ = "neo4j";
        String password_ = "technology-secretary-repair";
        String boltURL = "bolt://3.84.219.222";

        EmbeddedNeo4j embeddedNeo4j = new EmbeddedNeo4j(boltURL, username_, password_);

        Scanner in = new Scanner(System.in);

        boolean isLoggedIn = false;

        while (true) {
            if (isLoggedIn) {
                mostrarMenuPrincipal(in, embeddedNeo4j);
            } else {
                isLoggedIn = mostrarMenuInicioSesion(in);
            }
        }
    }

    public static boolean mostrarMenuInicioSesion(Scanner in) {
        System.out.println("\n<3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 ");
        System.out.println("        C O M P A T I B I L I D A D   A M O R O S A         ");
        System.out.println("¿Ya tiene una cuenta?");

        String opc = in.nextLine();

        switch (opc.toLowerCase()) {
            case "si":
                // inicia sesión
                return true;
            case "no":
                // crea una cuenta
                return false;
            default:
                System.out.println("Opción no válida");
                return false;
        }
    }

    public static void mostrarMenuPrincipal(Scanner in, EmbeddedNeo4j embeddedNeo4j) {
        System.out.println("\n<3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 ");
        System.out.println("        C O M P A T I B I L I D A D   A M O R O S A         ");
        System.out.println("                     menu de opciones:");
        System.out.println("\n 1. Encontrar usuarios con mejor compatibilidad.");
        System.out.println(" 2. Ver mis 'MATCH'.");
        System.out.println(" 3. Actualizar datos sobre mi cuenta.");
        System.out.println(" 4. Cerrar sesión.");
        System.out.println(" 5. Salir.");

        int opc = in.nextInt();
        in.nextLine();

        switch (opc) {
            case 1:
                // pedir el nombre no debería ser necesario con el inicio de sesión
                System.out.println("Ingrese su nombre:");
                String myUser = in.nextLine();
                System.out.println("Tienes una alta compatibilidad con:");
                System.out.println();
                embeddedNeo4j.printCompatibleUsers(myUser);
                break;

            case 2:
                // MATCH
                break;
            case 3:
                // ACTUALIZAR DATOS
                break;
            case 4:
                // CERRAR SESIÓN
                break;
            case 5:
                // SALIR
                break;

            default:
                System.out.println("Opción no válida");
                break;
        }
    }

}
