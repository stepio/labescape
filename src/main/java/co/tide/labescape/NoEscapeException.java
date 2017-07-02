package co.tide.labescape;

/**
 * Project-specific exception, intended to indicate that escape path cannot be drawn from the given data.
 */
public class NoEscapeException extends Exception {

    public NoEscapeException(String message) {
        super(message);
    }
}
