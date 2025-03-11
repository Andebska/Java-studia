public class MicroException extends Exception{
    private final int errorLine;

    public MicroException(String message, int errorLine) {
        super(message);
        this.errorLine = errorLine;
    }
    public int getErrorLine() {
        return errorLine;
    }
}
