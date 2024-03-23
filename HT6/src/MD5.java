import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Objects;

public class MD5 implements IFuncionesHash {

    @Override
    public String calcularHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(data.getBytes());
            return new String(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar hash MD5", e);
        }
    }
}
