public class Polibiusz implements Algorithm {
    private final String[][] square;
    private final String alphabet;
    private final int size;

    public Polibiusz(String alphabet, int size) {
        this.alphabet = alphabet.length() > size * size ? alphabet.substring(0, size * size) : alphabet;
        this.size = size;
        this.square = new String[size][size];
        fillSquare();
    }

    public Polibiusz() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", 5);
    }

    private void fillSquare() {

        int index = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(index < alphabet.length()) {
                    square[i][j] = String.valueOf(alphabet.charAt(index));
                    index++;
                }
                else {
                    square[i][j] = "";
                }
            }
        }
    }

    @Override
    public String crypt(String inputWord) {
        StringBuilder result = new StringBuilder();
        for(char c: inputWord.toCharArray()) {
            if (c == ' ') {
                result.append("  ");
            } else {
                boolean found = false;
                for (int i = 0; i < size && !found; i++) {
                    for (int j = 0; j < size; j++) {
                        if (square[i][j].equals(String.valueOf(c))) {
                            result.append((i + 1)).append(j + 1).append(" ");
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    continue;
                }
            }
        }
        return result.toString().trim();
    }

    @Override
    public String decrypt(String inputWord) {
        StringBuilder result = new StringBuilder();
        String[] tokens = inputWord.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) {
                result.append(" ");
            } else if (token.length() == 2 && Character.isDigit(token.charAt(0)) && Character.isDigit(token.charAt(1))) {
                int row = Character.getNumericValue(token.charAt(0)) - 1;
                int col = Character.getNumericValue(token.charAt(1)) - 1;

                if (row >= 0 && row < size && col >= 0 && col < size) {
                    result.append(square[row][col]);
                }
            }
        }
        String decryptedText = result.toString();
        decryptedText = decryptedText.replaceAll(" {3}", "  ");
        decryptedText = decryptedText.replaceAll(" {2}", " ");
        return decryptedText;
    }

}