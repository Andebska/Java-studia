import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Bee extends Thread {
    private final int id;
    private int entriesCount = 0;
    private long totalWaitTime = 0;
    private final Hive hive;
    private final Random random = new Random();

    public Bee(int id, Hive hive) {
        this.id = id;
        this.hive = hive;
    }

    @Override
    public void run() {
        try {
            while(true) {
                long startTime = System.currentTimeMillis();
                int passage = hive.tryToEnter(id);
                long waitTime = System.currentTimeMillis() - startTime;
                totalWaitTime += waitTime;
                entriesCount++;
                Thread.sleep(1000);       // wejście trwa 1 sekundę

                int randomSecondsIn = random.nextInt(5) + 1;
                int timeIn = randomSecondsIn * 1000;
                String secondsStr = "";
                if (timeIn == 1000) {
                    secondsStr = "sekundę";
                } else if (timeIn == 5000) {
                    secondsStr = "sekund";
                } else {
                    secondsStr = "sekundy";
                }

                Thread.sleep(timeIn);       // czas w ulu
                System.out.println("Pszczoła " + id + " spędza w ulu " + (timeIn/1000) + " " + secondsStr);

                hive.leaveHive(passage, id);
                Thread.sleep(1000);        // wyjście trwa 1 sekundę

                int randomSecondsOut = random.nextInt(6) + 5;
                int timeOut = randomSecondsOut * 1000;
                Thread.sleep(timeOut);
                System.out.println("Pszczoła " + id + " spędza w ulu " + (timeOut/1000) + " sekund");
            }
        } catch (InterruptedException e) {
            System.out.println("Pszczoła " + id + " zakończyła pracę");
            saveStatistics();
        }
    }

    private void saveStatistics() {
        String filename = "statistics.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String data = "Pszczoła " + id + ": liczba wlotów = " + entriesCount + ", średni czas oczekiwania = " + (entriesCount > 0 ? totalWaitTime / (double) entriesCount / 1000 : 0) + "s";
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Wystąpił błąd przy zapisywaniu statystyk do pliku dla pszczoły " + id + ": " + e.getMessage());
        }
    }
}
