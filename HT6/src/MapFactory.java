import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

class MapFactory<K, V> {
    public static AbstractMap<String, Estudiante> getMapInstance(int mapType) {
        switch (mapType) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Tipo de mapa no válido");
        }
    }
}
