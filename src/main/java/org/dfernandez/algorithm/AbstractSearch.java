package org.dfernandez.algorithm;

import org.dfernandez.exception.InternalException;
import org.dfernandez.model.Cell;
import org.dfernandez.model.Direction;
import org.dfernandez.rest.RestApi;

/**
 * Abstract class search algorithm
 */
abstract class AbstractSearch implements Search {

    protected final RestApi restApi;
    protected final Node startNode;

    protected AbstractSearch(RestApi restApi, Cell startCell) {
        this.restApi = restApi;
        this.startNode = new Node(startCell.getX(), startCell.getY(), 0);
    }

    protected abstract void addNode(Node node);

    /**
     * Check location neighbours positions (north, east, west and south)
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param actual Node actual
     * @throws InternalException
     */
    protected final void expandNeighbours(int x, int y, Node actual) throws InternalException {

        Cell cell;
        Node adjacent;
        switch (restApi.getActualMaze().getCell(x, y).getNorth()) {
            case UNEXPLORED:

                cell = restApi.movePosition(Direction.NORTH);

                if (cell != null) {
                    adjacent = new Node(cell.getX(), cell.getY(), actual.getDistance() + 1);
                    addNode(adjacent);

                    restApi.jumpPosition(x, y);

                }

                break;
        }
        switch (restApi.getActualMaze().getCell(x, y).getEast()) {
            case UNEXPLORED:

                cell = restApi.movePosition(Direction.EAST);

                if (cell != null) {

                    adjacent = new Node(cell.getX(), cell.getY(), actual.getDistance() + 1);
                    addNode(adjacent);

                    restApi.jumpPosition(x, y);

                }
                break;
        }
        switch (restApi.getActualMaze().getCell(x, y).getWest()) {
            case UNEXPLORED:

                cell = restApi.movePosition(Direction.WEST);

                if (cell != null) {
                    adjacent = new Node(cell.getX(), cell.getY(), actual.getDistance() + 1);
                    addNode(adjacent);

                    restApi.jumpPosition(x, y);

                }
                break;
        }
        switch (restApi.getActualMaze().getCell(x, y).getSouth()) {
            case UNEXPLORED:

                cell = restApi.movePosition(Direction.SOUTH);

                if (cell != null) {
                    adjacent = new Node(cell.getX(), cell.getY(), actual.getDistance() + 1);
                    addNode(adjacent);

                    restApi.jumpPosition(x, y);

                }
                break;
        }

    }

    /**
     * Check if you have reached the final cell of the maze.
     *
     * @param cell Current location in the maze
     * @return
     */
    protected final boolean isSolution(Cell cell) {
        if (cell.isAtEnd()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print Distance and Information about the final cell of the maze
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param actual Node actual
     */
    protected final void printSolution(int x, int y, Node actual) {
        System.out.println(restApi.getActualMaze().getCell(x, y).toString());
        System.out.println("Distance " + actual.getDistance());
    }

}