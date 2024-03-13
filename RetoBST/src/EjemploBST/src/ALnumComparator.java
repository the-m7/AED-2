import java.util.Comparator;

public class ALnumComparator<K> implements Comparator<K>{

    @Override
    public int compare(K o1, K o2) {
        String key1 = (String) o1;
        String key2 = (String) o2;

        return key1.compareTo(key2);
    }
    
}
