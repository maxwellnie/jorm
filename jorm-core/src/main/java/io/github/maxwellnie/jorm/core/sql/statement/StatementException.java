package io.github.maxwellnie.jorm.core.sql.statement;


import io.github.maxwellnie.jorm.core.JormException;

/**
 * @author Maxwell Nie
 */
public class StatementException extends JormException {
    public StatementException() {
    }

    public StatementException(String message) {
        super(message);
    }

    public StatementException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementException(Throwable cause) {
        super(cause);
    }

    public StatementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
