package org.dfernandez.exception;

/**
 * Exception if there are too many mazes currently in memory on the server
 */
public class TooManyMazesException extends DarkMazeException {

    public TooManyMazesException(String message) {
        super(message);
    }
}