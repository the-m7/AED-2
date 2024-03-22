import java.security.MessageDigest;
import java.util.Objects;

public class SHA implements IFuncionesHash {

    @Override
    public int calcularHash(Object data) {
        if (data instanceof String) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                byte[] bytes = digest.digest((String.valueOf(data)).getBytes());
                return Objects.hash(bytes); // Convertir bytes a un valor entero único
            } catch (Exception e) {
                throw new RuntimeException("Error al generar hash SHA-1", e);
            }
        } else {
            throw new IllegalArgumentException("Tipo de objeto no válido para el hash SHA-1");
        }
    }
}
