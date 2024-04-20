import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            List<String> datos = FileHelper.readFile("src/datos.txt");

        
            IHeap<Integer, Process> heap = new HeapUsingIterativeBinaryTree<Integer, Process>(new ComparadorNumeros<Integer>());

            for(String Linea : datos){
                String[] Pals = Linea.split(",");
                //System.out.println(Linea);
                String name = Pals[0];
                String user = Pals[1];
                Integer ni = Integer.parseInt(Pals[2]);

                Process proc = new Process(name, user, ni);

                heap.Insert(proc.getPr(), proc);
            }

            int num = heap.count();

            System.out.println("Los procesos retirados son: ");
            for(int i = 0; i < num; i++) {
                System.out.println(heap.remove().toString());
            }

        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Un numero 'nice' es inválido");
            e.printStackTrace();
        }
        
    }
}
