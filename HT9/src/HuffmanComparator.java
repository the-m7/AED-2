import java.util.Comparator;

// Se compara por medio de los valores de los nodos.
public class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y){
        return x.data-y.data;
    }

}
