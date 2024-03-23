import java.security.MessageDigest;
import java.util.Objects;

public class SHA implements IFuncionesHash {

    @Override
    public String calcularHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = digest.digest(data.getBytes());
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar hash SHA-1", e);
        }
    }
}