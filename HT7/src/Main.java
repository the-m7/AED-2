import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> Archivo = FileHelper.readFile("src/diccionario.txt");
            List<String> Texto = FileHelper.readFile("src/texto.txt");

            StrComparator<String> compa = new StrComparator<String>();

            BinarySearchTree<String,String[]> TreeEnglish = new BinarySearchTree<String,String[]>(compa);
            BinarySearchTree<String,String[]> TreeSpanish = new BinarySearchTree<String,String[]>(compa);
            BinarySearchTree<String,String[]> TreeFrench = new BinarySearchTree<String,String[]>(compa);
            
            for(String Linea : Archivo){
                String[] Palabras = Linea.split(",");
                String keyE = Palabras[0];
                String keyS = Palabras[1];
                String keyF = Palabras[2];

                TreeEnglish.insert(keyE, Palabras);
                TreeSpanish.insert(keyS, Palabras);
                TreeFrench.insert(keyF, Palabras);
            }

            Scanner scan = new Scanner(System.in);

            int from_sel = -1;
            
            System.out.println("Cual es el idioma original del texto?");
            System.out.println(" 1. Ingles");
            System.out.println(" 2. Español");
            System.out.println(" 3. Frances");
            System.out.println(" 4. Detectar Automaticamente");
            System.out.println("Ingrese el indice de la opción que desea");
            
            from_sel = scan.nextInt()-1;

            int to_sel = -1;

            System.out.println("A que idioma lo desea traducir?");
            System.out.println(" 1. Ingles");
            System.out.println(" 2. Español");
            System.out.println(" 3. Frances");
            System.out.println(" 4. Detectar Automaticamente (no implementado)");
            System.out.println("Ingrese el indice de la opción que desea");
            
            to_sel = scan.nextInt()-1;
            
            scan.close();
            if(from_sel >= 0 && from_sel <= 2){
                BinarySearchTree<String,String[]> selTree = null;
    
                switch(from_sel){
                    case 0:
                    selTree = TreeEnglish;
                    break;
                    case 1:
                    selTree = TreeSpanish;
                    break;
                    case 2:
                    selTree = TreeFrench;
                    break;
                }

            }

            

        } catch (FileNotFoundException e){
            System.out.println("Error al leer los archivos");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
