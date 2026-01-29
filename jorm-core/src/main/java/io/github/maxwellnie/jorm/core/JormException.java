package io.github.maxwellnie.jorm.core;

/**
 * @author Maxwell Nie
 */
public class JormException extends RuntimeException {
    public JormException() {
    }

    public JormException(String message) {
        super(message);
    }

    public JormException(String message, Throwable cause) {
        super(message, cause);
    }

    public JormException(Throwable cause) {
        super(cause);
    }

    public JormException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
