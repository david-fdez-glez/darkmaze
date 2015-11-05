package org.dfernandez.algorithm;

import org.dfernandez.exception.InternalException;
import org.dfernandez.model.Cell;
import org.dfernandez.rest.RestApi;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Breadth-first search algorithm
 */
public final class Bfs extends AbstractSearch {

    private final Queue<Node> queue;

    public Bfs(RestApi restApi, Cell cellStart) {
        super(restApi, cellStart);
        this.queue = new LinkedList<>();
    }

    /**
     * Implementation of the Breadth-first search algorithm
     *
     * @return
     * @throws InternalException
     */
    public boolean search() throws InternalException {

        Node actual;

        int x, y;
        addNode(startNode);

        while (queue.peek() != null) {

            actual = queue.remove();
            x = actual.getX();
            y = actual.getY();
            restApi.jumpPosition(x, y);

            if (isSolution(restApi.getActualMaze().getCell(x, y))) {

                printSolution(x, y, actual);
                return true;
            }

            expandNeighbours(x, y, actual);

        }

        return false;
    }

    /**
     * Add a Node to the queue (data structure to store intermediate results)
     *
     * @param node Node(int x, int y, int distance)
     */
    @Override
    protected void addNode(Node node) {
        queue.add(node);
    }
}
