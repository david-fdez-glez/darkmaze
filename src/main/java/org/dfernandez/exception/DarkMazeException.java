package org.dfernandez.exception;

/**
 * Abstract Exception
 */
public abstract class DarkMazeException extends Exception {

    public DarkMazeException() {
    }

    public DarkMazeException(String message) {
        super(message);
    }

    public DarkMazeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DarkMazeException(Throwable cause) {
        super(cause);
    }

}