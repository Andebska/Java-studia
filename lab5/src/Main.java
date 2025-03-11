import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Invalid usage (java Main <input_file> <output_file> <delay (ms)> <fps>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        int delay;
        int fps;

        try {
            delay = Integer.parseInt(args[2]);
            fps = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Delay and FPS must be integers");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){

            StringBuilder inputText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line).append("\n");
            }

            MicroDVDDelay microDVDDelay = new MicroDVDDelay();
            String delayed = microDVDDelay.delay(inputText.toString(), delay, fps);

            writer.write(delayed);
        } catch (MicroException e) {
            System.out.println(e.getErrorLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.printf("Unknown error: %s%n", e.getMessage());
        }
    }
}