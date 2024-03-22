import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Objects;

public class MD5 implements IFuncionesHash {

    @Override
    public int calcularHash(Object data) {
        if (data instanceof String) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest((String.valueOf(data)).getBytes());
                return Objects.hash(bytes); // Convertir bytes a un valor entero único
            } catch (Exception e) {
                throw new RuntimeException("Error al generar hash MD5", e);
            }
        } else {
            throw new IllegalArgumentException("Tipo de objeto no válido para el hash MD5");
        }
    }

}
