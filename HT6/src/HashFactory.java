public class HashFactory {

    public static IFuncionesHash getHashInstance(int hashType) {
        switch (hashType) {
            case 1:
                return new SHA();
            case 2:
                return new MD5();
            case 3:
                return new organico();
            case 4:
                return null;
            default:
                return new SHA();
        }
    }
}
