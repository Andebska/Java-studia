import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java EchoClient <port> <expression>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        String expression = args[1];

        expression = expression.replace("--", "+");
        expression = expression.replace("+-", "-");
        expression = expression.replace("-+", "-");

        try (Socket socket = new Socket("localhost", port);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {

            String[] tokens;

            if (expression.startsWith("-")) {
                tokens = expression.substring(1).split("(?<=[*/+-])|(?=[*/+-])");
                output.writeDouble(-Double.parseDouble(tokens[0].trim()));
            } else {
                tokens = expression.split("(?<=[*/+-])|(?=[*/+-])");
                output.writeDouble(Double.parseDouble(tokens[0].trim()));
            }

            for (int i = 1; i < tokens.length; i += 2) {
                char operator = tokens[i].trim().charAt(0);
                double number = Double.parseDouble(tokens[i + 1].trim());
                output.writeChar(operator);
                output.writeDouble(number);
            }

            double result = input.readDouble();
            System.out.println((int) result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
