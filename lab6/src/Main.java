import pl.edu.uj.javaframe.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Nieprawidłowa liczba argumentów programu");
            return;
        }

        String fileName = args[0];
        String columnName = args[1];
        int threadsNumber;
        int index;

        try {
            threadsNumber = Integer.parseInt(args[2]);
            index = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Liczba wątków i indeks wynikowy muszą być liczbami całkowitymi");
            return;
        }

        try {
            Class<? extends Value>[] types = new Class[]{Int.class};
            DataFrame df = DataFrame.readCSV(fileName, types);
            Factorize factorize = new Factorize();
            Series result = df.apply(factorize, columnName, threadsNumber);
            if (index >= 0 & index < result.getValues().size()) {
                System.out.println(result.getValues().get(index));
            } else {
                System.err.println("Nieprawidłowy indeks");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }
}

