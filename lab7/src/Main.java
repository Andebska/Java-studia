import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String STATISTICS_FILE = "statistics.txt";
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Błędne użycie programu (java Main <liczba_pszczół>");
            return;
        }
        int beesNumber = 0;
        try {
            beesNumber = Integer.parseInt(args[0]);
            if (beesNumber <= 0) {
                System.out.println("Liczba pszczół musi być dodatnia!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Zły format argumentu (liczba_pszczół)");
            return;
        }

        clearStatisticsFile();
        Hive hive = new Hive();
        List<Bee> bees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("ROZPOCZĘCIE SYMULACJI");
        System.out.println("W celu zakończenia symulacji, użyj klawisza ENTER");

        for (int i = 1; i <= beesNumber; i++) {
            Bee bee = new Bee(i, hive);
            bees.add(bee);
            bee.start();
        }

        scanner.nextLine();

        for (Bee bee : bees) {
            bee.interrupt();
        }

        for (Bee bee : bees) {
            try {
                bee.join();
            } catch (InterruptedException e) {
                System.err.println("Wystąpił błąd podczas czekania na zakończenie działania pszczoły: " + e.getMessage());
            }
        }

        System.err.println("KONIEC SYMULACJI");
    }

    private static void clearStatisticsFile() {
        try (FileWriter writer = new FileWriter(STATISTICS_FILE, false)){
            writer.write("");
        } catch (IOException e) {
            System.err.println("Błąd przy czyszczeniu pliku statystyk: " + e.getMessage());
        }
    }
}