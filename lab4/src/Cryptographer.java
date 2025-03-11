import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cryptographer {
    public static void cryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path_to_file_in)));
            String encrypted = algorithm.crypt(content);
            Files.write(Paths.get(path_to_file_out), encrypted.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static void decryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path_to_file_in)));
            String decrypted = algorithm.decrypt(content);
            Files.write(Paths.get(path_to_file_out), decrypted.getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
