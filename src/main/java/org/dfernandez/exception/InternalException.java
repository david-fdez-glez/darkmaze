package org.dfernandez.exception;

/**
 * Exception Api RestEasy
 */
public class InternalException extends DarkMazeException {

    public InternalException(Throwable cause) {
        super(cause);
    }

    public InternalException(String message) {
        super(message);
    }
}