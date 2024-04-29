import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BytesToString {

    public static void main(String[] args) throws IOException {

        String str = FileHelper.readFile2("HT9\\src\\text.txt");

        // string to byte[]
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        System.out.println("Text : " + str);
        System.out.println("Text [Byte Format] : " + bytes);

        // no, don't do this, it returns the address of the object in memory
        System.out.println("Text [Byte Format] toString() : " + bytes.toString());

        // convert byte[] to string
        String s = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Output : " + s);

    }
}
