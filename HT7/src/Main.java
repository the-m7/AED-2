import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

            TreeEnglish.InOrderWalk(new ConsolaWalk<String[]>());

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
            System.out.println("Ingrese el indice de la opción que desea");
            
            to_sel = scan.nextInt()-1;
            
            scan.close();
            
            if(from_sel >= 0 && from_sel <= 3){
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
                    case 3:

                    int contE = 0;
                    int contS = 0;
                    int contF = 0;

                    for(String LineaO : Texto){
                        String[] LineaO2 = LineaO.split(" ");
                        for(String Palabra : LineaO2){
                            Palabra = Palabra.strip();
                            if(TreeEnglish.find(Palabra)!=null){
                                contE++;
                            }
                            if(TreeSpanish.find(Palabra)!=null){
                                contS++;
                            }
                            if(TreeFrench.find(Palabra)!=null){
                                contF++;
                            }

                        }
                    }
                    if(contE>contS){
                        selTree=TreeEnglish;
                    } else if(contS>contF){
                        selTree=TreeSpanish;
                    } else {
                        selTree=TreeFrench;
                    }
                    break;

                }

                ArrayList<String> TextoTrad = new ArrayList<String>();

                for(String Linea : Texto){
                    String[] Linea2 = Linea.split(" ");
                    String LineaTrad = "";
                    
                    for(String Palabra : Linea2){
                        Palabra = Palabra.strip().toLowerCase();
                        
                        String[] PalTradL = selTree.find(Palabra);

                        if(PalTradL!=null){
                            String PalTrad = PalTradL[to_sel];
                            LineaTrad += PalTrad + " ";
                        } else {
                            LineaTrad += "*" + Palabra + "* ";
                        }
                    }
                    System.out.println(LineaTrad);
                    TextoTrad.add(LineaTrad);
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
