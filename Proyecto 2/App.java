
/**
 * 
 */
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // Connexión a la base en Sandbox
        String username = "neo4j";
        String password = "technology-secretary-repair";
        String boltURL = "bolt://3.84.219.222";

        try (EmbeddedNeo4j db = new EmbeddedNeo4j(boltURL, username, password)) {
            LinkedList<String> myusers = db.getUsers();

            for (int i = 0; i < myusers.size(); i++) {
                System.out.println(myusers.get(i));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Ingrese un interes:");
        Scanner in = new Scanner(System.in);
        String myInterest = in.nextLine();

        try (EmbeddedNeo4j db = new EmbeddedNeo4j(boltURL, username, password)) {
            LinkedList<String> users = db.getUsersByInterest(myInterest);

            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i));

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Ingrese una nueva Pelicula");
        System.out.println("Ingrese Nombre de la pelicula");
        String movieTitle = in.nextLine();
        System.out.println("Ingrese A�o Lanzamiento");
        int movieReleaseYear = Integer.parseInt(in.nextLine());
        System.out.println("Ingrese Descripcion");
        String movieTagLine = in.nextLine();

        try (EmbeddedNeo4j db = new EmbeddedNeo4j(boltURL, username, password)) {
            String result = db.insertUser(movieTitle, movieReleaseYear, movieTagLine);

            if (result.equalsIgnoreCase("OK")) {
                System.out.println("Pelicula insertada correctamente");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        in.close();
    }

}