package y111studios.utils;

public class UnreachableException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Presumed unreachable code executed";

    public UnreachableException() {
        super(DEFAULT_MESSAGE + ".");
    }

    public UnreachableException(String message) {
        super(String.format("%s : %s", DEFAULT_MESSAGE, message));
    }

}
