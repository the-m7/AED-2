public class HashFactory {
    public static final int SHA = 1;
    public static final int MD5 = 2;
    public static final int ORGANIC = 3;

    public static IFuncionesHash getHashInstance(int hashType){
        switch (hashType) {
            case SHA:
                return new SHA();   
            case MD5:
                return new MD5();     
            case ORGANIC:
                return new organico();
            default:
                return new SHA();
        }
    }
}
