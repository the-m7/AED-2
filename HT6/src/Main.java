import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        List<Estudiante> estudiantes = JSONRead
                .jsonRead("C:\\Users\\Usuario\\OneDrive\\Escritorio\\HT6\\AED-2\\HT6\\src\\estudiantes.json");
        // "C:\\Users\\Usuario\\OneDrive\\Escritorio\\HT6\\AED-2\\HT6\\src\\estudiantes.json"
        // "C:\\Users\\marti\\OneDrive\\Documentos\\uni\\s3\\progra\\git\\AED-2\\HT6\\src\\estudiantes.json"

        Scanner scanner = new Scanner(System.in);

        boolean salir = false;

        do {
            if (estudiantes != null && !estudiantes.isEmpty()) {
                System.out.println("\n====== Seleccione el tipo de mapa que desea crear ========");
                System.out.println("1. HashMap");
                System.out.println("2. TreeMap");
                System.out.println("3. LinkedHashMap");

                int opcion_mapa = scanner.nextInt();
                AbstractMap<String, Estudiante> mapa = MapFactory.getMapInstance(opcion_mapa);

                System.out.println("\n===== Seleccione el tipo de algoritmo para la función hash =====");
                System.out.println("1. MD5");
                System.out.println("2. SHA-1");
                System.out.println("3. Orgánica");
                System.out.println("4. Salir");

                int opcion_hash = scanner.nextInt();
                IFuncionesHash funcionHash = HashFactory.getHashInstance(opcion_hash);

                if (opcion_hash == 4) {
                    salir = true;
                    break;
                }

                scanner.nextLine();

                for (Estudiante estudiante : estudiantes) {
                    mapa.put(funcionHash.calcularHash(estudiante.getEmail()), estudiante);
                }

                System.out.println("Ingrese la llave del estudiante a buscar:");
                String llave = scanner.nextLine();
                buscarEstudiantePorLlave(mapa, llave);

                System.out.println("Ingrese la nacionalidad de los estudiantes a buscar:");
                String nacionalidad = scanner.nextLine();
                buscarEstudiantesPorNacionalidad(mapa, nacionalidad);
            }
        } while (!salir);
    }

    // Método de búsqueda por llave
    public static void buscarEstudiantePorLlave(AbstractMap<String, Estudiante> mapa, String llave) {
        if (mapa.containsKey(llave)) {
            Estudiante estudiante = (Estudiante) mapa.get(llave);
            System.out.println("Estudiante encontrado:");
            System.out.println(estudiante);
        } else {
            System.out.println("Estudiante no encontrado con la llave: " + llave);
        }
    }

    // **Método de búsqueda por nacionalidad**
    public static void buscarEstudiantesPorNacionalidad(AbstractMap<String, Estudiante> mapa, String nacionalidad) {
        List<Estudiante> estudiantesEncontrados = new ArrayList<>();
        for (Map.Entry<String, Estudiante> entry : mapa.entrySet()) {
            Estudiante estudiante = entry.getValue();
            if (estudiante.getCounty().equals(nacionalidad)) {
                estudiantesEncontrados.add(estudiante);
            }
        }

        if (!estudiantesEncontrados.isEmpty()) {
            System.out.println("Estudiantes encontrados con la nacionalidad: " + nacionalidad);
            for (Estudiante estudiante : estudiantesEncontrados) {
                System.out.println(estudiante);
            }
        } else {
            System.out.println("No se encontraron estudiantes con la nacionalidad: " + nacionalidad);
        }
    }
}
