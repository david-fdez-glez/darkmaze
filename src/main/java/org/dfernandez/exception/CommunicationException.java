package org.dfernandez.exception;

/**
 * Exception http status
 */
public class CommunicationException extends DarkMazeException {

    public CommunicationException(Throwable cause) {
        super(cause);
    }

    public CommunicationException(String message) {
        super(message);
    }
}