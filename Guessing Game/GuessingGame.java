import java.util.*;
import java.io.*;

public class GuessingGame {
    public static void main(String[] args) {
        // Poziom 1: Podstawowa gra
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int userGuess = 0; // Zmienna do przechowywania zgadywanej liczby
        int attempts = 0;
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = Integer.MAX_VALUE;

        System.out.println("Witaj w grze 'Zgadnij liczbę'!");
        System.out.println("Wybierz poziom trudności:");
        System.out.println("1. Łatwy (1-50, brak limitu prób)");
        System.out.println("2. Średni (1-100, limit 10 prób)");
        System.out.println("3. Trudny (1-200, limit 5 prób)");
        int difficulty = scanner.nextInt();
        switch (difficulty) {
            case 1:
                lowerBound = 1;
                upperBound = 50;
                break;
            case 2:
                lowerBound = 1;
                upperBound = 100;
                maxAttempts = 10;
                break;
            case 3:
                lowerBound = 1;
                upperBound = 200;
                maxAttempts = 5;
                break;
            default:
                System.out.println("Nieprawidłowy wybór, ustawiono poziom Średni.");
                lowerBound = 1;
                upperBound = 100;
                maxAttempts = 10;
        }

        int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound; // Liczba do zgadnięcia
        while (userGuess != numberToGuess && attempts < maxAttempts) {
            System.out.printf("Podaj swoją liczbę (zakres: %d-%d): ", lowerBound, upperBound);
            userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < numberToGuess) {
                System.out.println("Za mało!");
                lowerBound = Math.max(lowerBound, userGuess + 1);
            } else if (userGuess > numberToGuess) {
                System.out.println("Za dużo!");
                upperBound = Math.min(upperBound, userGuess - 1);
            } else {
                System.out.printf("Gratulacje! Zgadłeś liczbę w %d próbach.\n", attempts);
                System.out.println("Podaj swoje imię: ");
                String userName = scanner.next();
                saveBestScore(userName, attempts);
                displayBestScores();
                return;
            }
        }
        if (attempts >= maxAttempts) {
            System.out.println("Przekroczono limit prób. Przegrałeś!");
        }
        scanner.close();
    }

    public static void saveBestScore(String playerName, int attempts) {
        try (PrintWriter writer = new PrintWriter (new FileWriter("scores.txt", true))) {
            writer.println(playerName + ": " + attempts + " próby");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania wyniku: " + e.getMessage());
        }
    }

    public static void displayBestScores() {
        System.out.println("Najlepsze wyniki:");
        List<String> scores = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("scores.txt"))) {
            while (fileScanner.hasNextLine()) {
                scores.add(fileScanner.nextLine());
            }
            scores.sort(Comparator.comparingInt(line -> Integer.parseInt(line.split(":")[1].replaceAll("\\D", ""))));

            for (int i = 0; i < Math.min(5, scores.size()); i++) {
                String[] parts = scores.get(i).split(":");
                System.out.printf("%d. %s - %s próby\n", i + 1, parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak zapisanych wyników.");
        } catch (Exception e) {
            System.out.println("Błąd podczas odczytu wyników: " + e.getMessage());
        }
    }
}
