import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinarySearchTree<String, Automovil> Tree = new BinarySearchTree<String, Automovil>(new ALnumComparator<String>());

        while (true){
            System.out.println("Ingrese los datos del vehículo (marca, modelo, línea, placa, precio), o escriba 'salir' para terminar:");
            String entrada = scan.nextLine();
            if (entrada.equalsIgnoreCase("salir")) {
                break;
            }
            String[] datos = entrada.split(",");
            if (datos.length == 5) {
                String marca = datos[0].trim();
                int modelo = Integer.parseInt(datos[1].trim());
                String linea = datos[2].trim();
                String placa = datos[3].trim();
                double precio = Double.parseDouble(datos[4].trim());

                Automovil vehiculo = new Automovil(marca, modelo, linea, placa, precio);
                Tree.insert(placa, vehiculo);
            }
        }
        SaveInArrayListWalk<Automovil> walk = new SaveInArrayListWalk<Automovil>();

        Tree.InOrderWalk(walk);

        
        System.out.println(walk.getListado().toString());

        scan.close();
    }
}