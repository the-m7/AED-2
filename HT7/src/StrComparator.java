import java.util.Comparator;

public class StrComparator<K> implements Comparator<K>{

    @Override
    public int compare(K o1, K o2) {
        String Str1 = o1.toString();
        String Str2 = o2.toString();
        return Str1.compareTo(Str2);
    }
    
}