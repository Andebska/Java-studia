import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        System.out.println(serverSocket.getLocalPort());


        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

                double result = input.readDouble();
                while (input.available() > 0) {
                    char operator = input.readChar();
                    double number = input.readDouble();

                    switch (operator) {
                        case '+':
                            result += number;
                            break;
                        case '-':
                            result -= number;
                            break;
                        case '*':
                            result *= number;
                            break;
                        case '/':
                            if (number != 0) {
                                result /= number;
                            } else {
                                throw new ArithmeticException("Division by zero");
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown operator: " + operator);
                    }
                }

                output.writeDouble(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
