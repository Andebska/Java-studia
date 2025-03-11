public class Main {
    public static void main(String[] args) {
        if(args.length != 4) {
            System.out.println("Usage: java Main <inputFile> <outputFile> <crypt/decrypt> <rot/pol>");
            return;
        }

        String pathToInputFile = args[0];
        String pathToOutputFile = args[1];
        String operation = args[2].toLowerCase();
        String algorithmType = args[3].toLowerCase();

        Algorithm algorithm;
        switch (algorithmType) {
            case "rot":
                algorithm = new ROTXX();
                break;
            case "pol" :
                algorithm = new Polibiusz();
                break;
            default:
                System.out.println("Nieznany algorytm");
                return;
        }

        try {
            if(operation.equals("crypt")) {
                Cryptographer.cryptfile(pathToInputFile, pathToOutputFile, algorithm);
                System.out.println("Plik został zaszyfrowany i zapisany do: " + pathToOutputFile);
            }
            else if(operation.equals("decrypt")) {
                Cryptographer.decryptfile(pathToInputFile, pathToOutputFile, algorithm);
                System.out.println("Plik został odszyfrowany i zapisany do: " + pathToOutputFile);
            }
            else {
                System.out.println("Nieznana operacja");
            }
        } catch (Exception e) {
            System.err.println("Wystąpił błąd podczas operacji na pliku" + e.getMessage());
        }

    }
}