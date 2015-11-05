package org.dfernandez.rest;

import org.dfernandez.exception.CommunicationException;
import org.dfernandez.exception.InternalException;
import org.dfernandez.exception.TooManyMazesException;
import org.dfernandez.model.Cell;
import org.dfernandez.model.Direction;
import org.dfernandez.model.Maze;

/**
 * Interface Blackout API
 */
public interface RestApi {


    public Maze getActualMaze();

    /**
     * Initializes a new maze which will exist for 10 minutes.
     *
     * @return Cell Information about the initial position on the maze
     * @throws CommunicationException Communications problems with the server
     * @throws TooManyMazesException  There are too many mazes currently in memory on the server
     * @throws InternalException      Problem with RestEasy API
     */
    public Cell initMaze() throws CommunicationException, TooManyMazesException, InternalException;

    /**
     * Retrieve the standard response information for your current location in the maze.
     *
     * @return Cell Information about the actual position on the maze
     * @throws InternalException
     */
    public Cell getActualInfo() throws InternalException;

    /**
     * Move in the specified direction
     *
     * @param direction NORTH, WEST, EAST,SOUTH
     * @return Cell Information about the new position on the maze
     * @throws InternalException
     */
    public Cell movePosition(Direction direction) throws InternalException;

    /**
     * Jump to a specified location in the maze
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @throws InternalException
     */
    public void jumpPosition(int x, int y) throws InternalException;

}