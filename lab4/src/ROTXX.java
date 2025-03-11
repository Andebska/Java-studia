public class ROTXX implements Algorithm {
    private final String alphabet;
    private final int rotation;

    public ROTXX(String alphabet, int rotation) {
        this.alphabet = alphabet;
        this.rotation = rotation;
    }

    public ROTXX() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", 11);
    }

    @Override
    public String crypt(String inputWord) {
        return shiftString(inputWord, rotation);
    }

    @Override
    public String decrypt(String inputWord) {
        return shiftString(inputWord, -rotation);
    }

    private String shiftString(String input, int rotation) {
        StringBuilder result = new StringBuilder();
        for(char c : input.toCharArray()) {
            int index = alphabet.indexOf(c);
            if(index != -1) {
                int newIndex = (index + rotation + alphabet.length()) % alphabet.length();
                result.append(alphabet.charAt(newIndex));
            }
            else {
                result.append(c);         // jeśli c nie należy do alfabetu
            }
        }
        return result.toString();
    }
}
